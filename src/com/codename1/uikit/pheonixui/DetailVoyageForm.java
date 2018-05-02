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
import com.bonplan.entities.User;
import com.bonplan.services.ReservationService;
import com.bonplan.services.VoyageService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
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

import com.codename1.share.FacebookShare;
import com.codename1.facebook.FaceBookAccess;


import java.io.IOException;
import jdk.nashorn.internal.parser.Scanner;





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
        Container C88 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C188 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String pic;
       
        Label categorie = new Label(oo.getCategorie());
        
        Label DateDepart = new Label(oo.getDate_dep());
        Label prix = new Label("" + oo.getPrix());
        Label nbrplace = new Label("" + oo.getNbr_place());
        Label DateArrivee=new Label(oo.getDate_arr());
        Label Destin=new Label(oo.getDestination());

        TextArea descriptif = new TextArea(oo.getDescription());

        ImageViewer img = new ImageViewer();
       
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
        img.setImage(URLImage.createToStorage(enc, oo.getPhoto(), "http://localhost/PIDEV/web/uploads/" + oo.getPhoto()).fill(200, 150));

        descriptif.setRows(2);
        descriptif.setColumns(50);
        descriptif.setGrowByContent(false);
        descriptif.setEditable(false);
        descriptif.setUIID("SlightlySmallerFontLabelLeft");

        Label l1 = new Label("Catégorie : ");
        Label l2 = new Label("Date Depart: ");
        Label l5 = new Label("Description: ");
        Label l3 = new Label("Prix unitaire: ");
        Label l4 = new Label("Nombre Place: ");
        Label l88=new Label("Date Arrivée: ");
        Label l99=new Label("Destination: ");
        
        
         FontImage.setMaterialIcon(prix, FontImage.MATERIAL_ATTACH_MONEY);
        FontImage.setMaterialIcon(DateDepart, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(DateArrivee, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(nbrplace, FontImage.MATERIAL_GROUP);
        FontImage.setMaterialIcon(categorie, FontImage.MATERIAL_INFO_OUTLINE);
        FontImage.setMaterialIcon(Destin, FontImage.MATERIAL_PLACE);
        C1.add(l1);
        C1.add(categorie);
        C0.add(img);
        C0.add(C1);
        C2.add(l2);
        C2.add(DateDepart);
        C0.add(C2);
        C88.add(l88);
        C88.add(DateArrivee);
        C0.add(C88);
        C3.add(l3);
        C3.add(prix);
        C0.add(C3);
        C4.add(l4);
        C4.add(nbrplace);
        C0.add(C4);
        C188.add(l99);
        C188.add(Destin);
        C0.add(C188);
        C5.add(l5);
        C5.add(descriptif);
        C0.add(C5);
        
      Button reserver= new Button("Reserver");
      Button commander = new Button("Supprimer");
  if(User.getUserconnected().getId()!=Voyage.voyage.getId_owner())            
  {C6.add(reserver);}
  else
  {C6.add(commander);}
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
         
          
              

Button partager=new Button("Facebook");
partager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
                FacebookShare fs=new FacebookShare();
                FaceBookAccess.setClientId("100002394186435");
                FaceBookAccess.setClientSecret("EAACEdEose0cBAGyioxMlI56n0ZAcZAvPdKl20CGZBZBVboWHsu93bUZBUekVOaAmOWIZBRcXYhfkiVWpD52cUcLsBXuCietSGFfGmVUtiujWcaW5quf8JqwuZAOJRZACW1TNBSzIVeH6YU6ZAFPA6dgY42Dc09NZBUg1sraAKTyDdjMNTQNCyumwxjLGWNpeNhdLFhKOf7oyavKAZDZD");
                FaceBookAccess.setToken("EAACEdEose0cBAPkcBm1IN1nvtlvKuaOWFSLCddGfBLFXo4yQPy2cBLP4Rom7kXFJ4UZBgSKSpIeWI9LqgcpwahstF9nTZAgygBK0fqaZB4e9dzmqM9EtLLvFPZBrMqKyGjGPHqwb6TOhUawO63a4SPqKjbaiK3nJBrAAV6LxLYpH5S7wwgZATg7dZCfiPLGexlXSbPQgO7wwZDZD");
                fs.share("Voyager Vers "+Voyage.voyage.getDestination()+" à partir de "+
                             Voyage.voyage.getPrix()+"Dt."+Voyage.voyage.getDescription()+".Connectez-vous "
                                     + "sur l'une de nos applications mobile,desktop ou web afin de reserver"
                                     + "vos place.Allez!!!.Le lien est ci-dessous "
                                     + "http://localhost/PIDEV/web/app_dev.php/VoyageA/AfficheDetailMobile/"+Voyage.voyage.getId_voyage());
        
                
                
            }
        });




C6.add(partager);
C0.add(C6);
         commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VoyageService rs=new VoyageService();
             rs.SupprimerVoyage(Voyage.voyage);
             new VoyageGui(theme).show();
              
            }
        });
        return C0;
    }

    public Voyage getVoyageDet(String json) throws JSONException {
        int i = json.indexOf("{");
        json = json.substring(i);
        JSONObject obj = new JSONObject(json.trim());

//JSONObject json = new JSONObject(result.trim()); 
        
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
