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
import com.bonplan.services.ReservationService;
import com.bonplan.services.VoyageService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.facebook.User;
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
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import java.io.IOException;
import jdk.nashorn.internal.parser.Scanner;





import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author amira
 */
public class DetailReservation extends BaseForm {

    public int id;
    Resources theme;
    Voyage v;
    Reservation r ;
    public DetailReservation() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    protected boolean isCurrentForum() {
        return true;
    }

    public DetailReservation(Resources res) throws IOException {
        // super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Detail Reservation", "Title")
                )
        );

        super.installSidemenu(res);
        
        getToolbar().setBackCommand("", e -> {
            // con.setUrl("http://localhost/aryak/DestroyGuide.php");

            

         
            new VoyageGui(res).showBack();
        });
        System.out.println("chiiiyyyyyyy");

        
            
                add(addItem(Reservation.reservation));
           
           
       
        
    }
  
    public Container addItem(Reservation oo) throws IOException {
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C8888 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C456 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String pic;
        //  Label pays = new Label();
        //  Label ville = new Label();  
        Label categorie = new Label(oo.getVoyage().getCategorie());
        //  Label stock = new Label();
        Label DateDepart = new Label(oo.getVoyage().getDate_dep());
        Label prix = new Label("" + oo.getVoyage().getPrix()*oo.getNbr_place_resv()+"Dt");
        Label nbrplace = new Label("" + oo.getNbr_place_resv());
        Label DateArrivee=new Label(""+oo.getVoyage().getDate_arr());
        Label Destination=new Label(""+oo.getVoyage().getDestination());

        TextArea descriptif = new TextArea(oo.getVoyage().getDescription());

        ImageViewer img = new ImageViewer();
        //image=Image.createImage("/tunis.jpg").fill(170, 100);
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
        img.setImage(URLImage.createToStorage(enc, oo.getVoyage().getPhoto(), "http://localhost:1020/Our/web/uploads/" + oo.getVoyage().getPhoto()).fill(200, 150));

        descriptif.setRows(2);
        descriptif.setColumns(50);
        descriptif.setGrowByContent(false);
        descriptif.setEditable(false);
        descriptif.setUIID("SlightlySmallerFontLabelLeft");

        Label l1 = new Label("Catégorie : ");
        Label l2 = new Label("Date Depart: ");
        Label L22=new Label("Date Arrivée: ");
        Label L555=new Label("Destination :");
        Label l5 = new Label("Description: ");
        Label l3 = new Label("Prix Total: ");
        Label l4 = new Label("Nombre Place Reservé: ");
        FontImage.setMaterialIcon(prix, FontImage.MATERIAL_ATTACH_MONEY);
        FontImage.setMaterialIcon(DateDepart, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(DateArrivee, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(nbrplace, FontImage.MATERIAL_GROUP);
        FontImage.setMaterialIcon(categorie, FontImage.MATERIAL_INFO_OUTLINE);
        FontImage.setMaterialIcon(Destination, FontImage.MATERIAL_PLACE);
        C1.add(l1);
        C1.add(categorie);
        C0.add(img);
        C0.add(C1);
        C2.add(l2);
        C2.add(DateDepart);
        C0.add(C2);
        C8888.add(L22);
        C8888.add(DateArrivee);
        C0.add(C8888);
        C3.add(l3);
        C3.add(prix);
        C0.add(C3);
        C4.add(l4);
        C4.add(nbrplace);
        C0.add(C4);
        C456.add(Destination);
        C456.add(L555);
        C0.add(C456);
        C5.add(l5);
        C5.add(descriptif);
        C0.add(C5);
      Button reserver= new Button("Annuler Reservation");
              
C6.add(reserver);
C0.add(C6);
         reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        
               
            
             ReservationService rs=new ReservationService();
             rs.AnnulerReservation(Reservation.reservation);
             new VoyageGui(theme).show();
                
                }
           
        //new ActivityMain(com.codename1.ui.util.Resources.getGlobalResources()).show();
        
            });
         
          



         
        return C0;
    }

    public Reservation getResDet(String json) throws JSONException {
        int i = json.indexOf("{");
        json = json.substring(i);
        JSONObject obj = new JSONObject(json.trim());

//JSONObject json = new JSONObject(result.trim()); 
        
        //System.out.println(json);
       Reservation e=new Reservation();
        // o.setIdProduit(2);
        // System.out.println("iiid   "+obj.getIdProduit());
         float id = Float.parseFloat(obj.get("id_inscrit").toString());
                        e.setId_resv((int) Float.parseFloat(obj.get("id_reservation").toString()));
                        e.setId_user((int)id);
                        VoyageService vs=new VoyageService();
                        Voyage v=vs.OneById((int)Float.parseFloat(obj.get("id_voyage").toString()));
                        e.setVoyage(v);
                        e.setNbr_place_resv((int)Float.parseFloat(obj.get("nbPlaceReserve").toString()));

        return e;

    }

}
