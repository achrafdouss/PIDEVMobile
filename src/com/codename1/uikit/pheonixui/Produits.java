/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Produit;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
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
import com.codename1.uikit.pheonixui.BaseForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author amira
 */
public class Produits  extends BaseForm   {
     //String prixProduit="test";
      String pays;
      String ville;
      Resources theme;
     //String descriptif="test";
    //  String stock="test";
      Image image;
      public static int idProduit;
     public Produits() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    
    public Produits( Resources res) {
     //super(BoxLayout.y());
         theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Produit", "Title")
                )
        );
         super.installSidemenu(res);
          // getContentPane().setScrollable(false);
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/PublicationMobile");
        con.addResponseListener((NetworkEvent evt) -> {
            //System.out.println(getListRando(new String(con.getResponseData())));
            ArrayList<Produit> listProduits = getListProduits(new String(con.getResponseData()));
            //randoaffichForm.refreshTheme();

            for (Produit o : listProduits) {
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
   public Container addItem(Produit oo) throws IOException {

        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BorderLayout());
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               ImageViewer img = new ImageViewer();
//image=Image.createImage("/tunis.jpg").fill(170, 100);
      EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
       img.setImage(URLImage.createToStorage(enc, oo.getPhotoProduit(), "http://localhost/PIDEV/web/image/"+oo.getPhotoProduit() ).fill(200, 150));
       MultiButton mb= new MultiButton();
       mb.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        mb.setPropertyValue("uiid2", "RedLabelRight");
       //
       // img.setImage(image);
        Label nomProduit = new Label(oo.getNomProduit());
        Label prixProduit = new Label(""+oo.getPrixProduit());
         TextArea categorie = new TextArea(oo.getCategorieProduit());
           categorie.setRows(2);
        categorie.setColumns(50);
        categorie.setGrowByContent(false);
        categorie.setEditable(false);
         categorie.setUIID("SlightlySmallerFontLabelLeft");
          Button fleche=new Button();
         fleche.setText("");
        fleche.setUIID("Label");
        fleche.addActionListener(new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent evt) {
           idProduit= oo.getIdProduit();
             ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/PublicationMobileDetail/" + oo.getIdProduit()+"");
            req.addResponseListener(new ActionListener<NetworkEvent>() {

               @Override
               public void actionPerformed(NetworkEvent evt) {
                   byte[] data = (byte[]) evt.getMetaData();
                   String s = new String(data);

                  System.out.println(oo.getIdProduit() + "    hahah ");
                     
                       new DetailProduitForm(theme).show();
                      
                   
                 }
              });
             NetworkManager.getInstance().addToQueue(req);
              }
              });
        
        
        
         com.codename1.ui.FontImage.setMaterialIcon(fleche,'');
         Label p = new Label(pays);
        Label v = new Label(ville);
         Label stock = new Label(""+oo.getStockProduit());
         //C1.add(BorderLayout.EAST,stock);
        // C1.add(BorderLayout.NORTH,stock);
          Container cc= BoxLayout.encloseY(categorie,nomProduit,BorderLayout.east(fleche));
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
     public ArrayList<Produit> getListProduits(String json) {
        ArrayList<Produit> listProd = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> rando = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) rando.get("root");

            for (Map<String, Object> obj : list) {
                Produit o = new Produit();
                
                o.setIdProduit((int)Float.parseFloat(obj.get("id_produit").toString()));
                
                o.setCategorieProduit(obj.get("categorieProduit").toString());
                o.setNomProduit(obj.get("nom_produit").toString());
                 o.setPrixProduit(Float.parseFloat(obj.get("prixProduit").toString()));
//              o.setStockProduit((int)Integer.parseInt(obj.get("stockProduit").toString()));
                 o.setDescriptionProduit(obj.get("description").toString());
                   o.setPhotoProduit(obj.get("photo").toString());
               // o.setIdpromotion(Integer.parseInt(obj.get("idpromo").toString()));
//System.out.println(o.get);
                listProd.add(o);

            }

        } catch (IOException ex) {
        }
        return listProd;

    } 
    
    

     
     
}
