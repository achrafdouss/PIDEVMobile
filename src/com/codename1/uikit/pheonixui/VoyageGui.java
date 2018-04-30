/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Produit;
import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Voyage;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.VoyageService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.pheonixui.Produits.idProduit;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Tab;
import static tdanford.json.schema.tests.SchemaTests.top;

/**
 *
 * @author amira
 */
public class VoyageGui extends BaseForm {
    public static int idVoyage;

    Resources theme;

    public VoyageGui() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public VoyageGui(Resources res) {
     //super(BoxLayout.y());
         theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Voyage", "categorie")
                )
        );
         super.installSidemenu(res);
          // getContentPane().setScrollable(false);
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:1020/Our/web/app_dev.php/VoyageA/AfficherAllVoyagesM");
        con.addResponseListener((NetworkEvent evt) -> {
            //System.out.println(getListRando(new String(con.getResponseData())));
           
            
            VoyageService vs= new VoyageService();
            ArrayList<Voyage> listProduits =vs.MesParticipations();
            //randoaffichForm.refreshTheme();

            for (Voyage o : listProduits) {
                try {
                    add(addItem(o));
                } catch (IOException ex) {
                }
            }
            //form.revalidate();
            //form.refreshTheme();
        });
        NetworkManager.getInstance().addToQueue(con);
     
    }

    public Container addItem(Voyage r) throws IOException {
        //
       
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BorderLayout());
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               ImageViewer img = new ImageViewer();
//image=Image.createImage("/tunis.jpg").fill(170, 100);
      EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
       img.setImage(URLImage.createToStorage(enc, r.getPhoto(), "http://localhost:1020/Our/web/uploads/"+r.getPhoto() ).fill(200, 150));
       MultiButton mb= new MultiButton();
       mb.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        mb.setPropertyValue("uiid2", "RedLabelRight");
       //
       // img.setImage(image);
        Label categorie = new Label(r.getCategorie());
        Label prix = new Label(""+r.getPrix());
         TextArea description = new TextArea(r.getDescription());
           description.setRows(2);
        description.setColumns(50);
        description.setGrowByContent(false);
        description.setEditable(false);
         description.setUIID("SlightlySmallerFontLabelLeft");
          Button fleche=new Button();
         fleche.setText("");
        fleche.setUIID("Label");
        fleche.addActionListener(new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent evt) {
           idVoyage= r.getId_voyage();
             ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost:1020/Our/web/app_dev.php/VoyageA/AfficheDetailMobile/" + r.getId_voyage()+"");
            req.addResponseListener(new ActionListener<NetworkEvent>() {

               @Override
               public void actionPerformed(NetworkEvent evt) {
                   byte[] data = (byte[]) evt.getMetaData();
                   String s = new String(data);

                  System.out.println(r.getId_voyage() + "d5alna hahah ");
                     Voyage.voyage=r;
                   try {
                       new DetailVoyageForm(theme).show();
                   } catch (IOException ex) {
                       System.out.println(ex.getMessage());                   }
                      
                   
                 }
              });
             NetworkManager.getInstance().addToQueue(req);
              }
              });
        
        
        
         com.codename1.ui.FontImage.setMaterialIcon(fleche,'');
        
         Label nbrplace = new Label(""+r.getNbr_place());
         //C1.add(BorderLayout.EAST,stock);
        // C1.add(BorderLayout.NORTH,stock);
          Container cc= BoxLayout.encloseY(description,categorie,BorderLayout.east(fleche));
          C1.add(BorderLayout.SOUTH,cc); 
         // C1.addComponent(BorderLayout.EAST, fleche);
       // C2.add(stock);
        C1.add(BorderLayout.CENTER,img);
       // C3.add(C1);
//        C3.add(desc);
      //  C1.add(BorderLayout.CENTER,C3);
       // C1.add(BorderLayout.SOUTH,);
       // C3.add(v);
       // C3.add(p);
      //  C1.add(BorderLayout.EAST,C3);
         //Container cntEspace = new Container();
       // cntEspace.setHeight(20);
        //cntEspace.add(new ImageViewer(Image.createImage(20, 20)));
        C0.add(C1);
       // C0.add(cntEspace);
        Border line=Border.createCompoundBorder(Border.createLineBorder(1), null, null, null);
 
        C1.getUnselectedStyle().setBorder(line);
       
       
        return C0;
        
    }

    
}
