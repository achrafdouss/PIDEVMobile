/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.CommandeProduit;
import com.bonplan.entities.Produit;
import com.bonplan.entities.Reservation;
import com.bonplan.entities.Voyage;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.io.IOException;





import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author amira
 */
public class DetailVoyageForm extends BaseForm {

    public int id;
    Resources theme;
    Voyage v;
    Reservation r ;
    public DetailVoyageForm() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    protected boolean isCurrentForum() {
        return true;
    }

    public DetailVoyageForm(Resources res) throws IOException {
        // super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Detail Voyage", "Title")
                )
        );

        super.installSidemenu(res);
        
        getToolbar().setBackCommand("", e -> {
            // con.setUrl("http://localhost/aryak/DestroyGuide.php");

            

         
            new VoyageGui(res).showBack();
        });
        System.out.println("chiiiyyyyyyy");

        
            
                add(addItem(Voyage.voyage));
           
           
       
        
    }
  
    public Container addItem(Voyage oo) throws IOException {
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String pic;
        //  Label pays = new Label();
        //  Label ville = new Label();  
        Label categorie = new Label(oo.getCategorie());
        //  Label stock = new Label();
        Label DateDepart = new Label(oo.getDate_dep());
        Label prix = new Label("" + oo.getPrix());
        Label nbrplace = new Label("" + oo.getNbr_place());

        TextArea descriptif = new TextArea(oo.getDescription());

        ImageViewer img = new ImageViewer();
        //image=Image.createImage("/tunis.jpg").fill(170, 100);
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
        img.setImage(URLImage.createToStorage(enc, oo.getPhoto(), "http://localhost:1020/Our/web/uploads/" + oo.getPhoto()).fill(200, 150));

        descriptif.setRows(2);
        descriptif.setColumns(50);
        descriptif.setGrowByContent(false);
        descriptif.setEditable(false);
        descriptif.setUIID("SlightlySmallerFontLabelLeft");

        Label l1 = new Label("Catégorie : ");
        Label l2 = new Label("Date Depart: ");
        Label l5 = new Label("Description: ");
        Label l3 = new Label("Prix: ");
        Label l4 = new Label("Nombre Place: ");
        C1.add(l1);
        C1.add(categorie);
        C0.add(img);
        C0.add(C1);
        C2.add(l2);
        C2.add(DateDepart);
        C0.add(C2);
        C3.add(l3);
        C3.add(prix);
        C0.add(C3);
        C4.add(l4);
        C4.add(nbrplace);
        C0.add(C4);
        C5.add(l5);
        C5.add(descriptif);
        C0.add(C5);
      Button reserver= new Button("Reserver");
              
C6.add(reserver);
         reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        
               
                try {
                    new ReserverForm(theme).show();
                } catch (IOException ex) {
                    ex.getMessage();
                }
                
                   
                
                }
           
        //new ActivityMain(com.codename1.ui.util.Resources.getGlobalResources()).show();
        
            });
         
          Button commander = new Button("Commander");
              
C6.add(commander);

C0.add(C6);
         commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
         
         // c.setColor(getContentPane().getUnselectedStyle().getBgColor()+"");
           ConnectionRequest con = new ConnectionRequest();
           Label lb = new Label ("Veuillez Saisir votre quantité");
         TextArea quantite = new TextArea ();
         
                   Button next = new Button("Suivant");

         
         Label lb2 = new Label("Le prix de votre commande est : ");
         //Double prixcommande = (quantite.getText()* Float.parseFloat(oo.getPrixProduit()));
               // con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/ajoutFavorie/"+id+"");
                con.addResponseListener(new ActionListener<NetworkEvent>()
				{

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Favorie Ajouté", "Ok", null);
                        }
                    }
                });
                
//                NetworkManager.getInstance().addToQueue(con);
                }
           
        //new ActivityMain(com.codename1.ui.util.Resources.getGlobalResources()).show();
        
            });
        return C0;
    }

    public Voyage getVoyageDet(String json) throws JSONException {
        int i = json.indexOf("{");
        json = json.substring(i);
        JSONObject obj = new JSONObject(json.trim());

//JSONObject json = new JSONObject(result.trim()); 
        System.out.println(obj.toString(4) + " kakkaka");
        //System.out.println(json);
        Voyage e = new Voyage();
        // o.setIdProduit(2);
        // System.out.println("iiid   "+obj.getIdProduit());
        float id = Float.parseFloat(obj.get("id_voyage").toString());
                        e.setId_voyage((int) id);
                        e.setCategorie(obj.get("categorie").toString());
                        e.setType(obj.get("type").toString());
                        e.setNbr_place((int) Float.parseFloat(obj.get("nbrPlace").toString()));
                        e.setId_owner((int) Float.parseFloat(obj.get("id_owner").toString()));
                        e.setDate_dep(obj.get("dateDepart").toString().substring(6, 16));
                        e.setPrix( Float.parseFloat(obj.get("prix").toString()));
                        e.setDate_arr(obj.get("dateArrivee").toString().substring(6, 16));
                        e.setDescription(obj.get("description").toString());
                        e.setDestination(obj.get("destination").toString());
                        e.setPhoto(obj.get("photo").toString());

        return e;

    }

}
