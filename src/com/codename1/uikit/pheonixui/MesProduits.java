/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.CommandeProduit;
import com.bonplan.entities.FavorisProduit;
import com.bonplan.entities.Produit;
import com.bonplan.entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author amira
 */
public class MesProduits extends BaseForm {

    public int id;
    Resources theme;
    FavorisProduit p;
    public static int idProduit;

    public MesProduits() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    protected boolean isCurrentForum() {
        return true;
    }

    public MesProduits(Resources res) {
        // super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Mes Produit", "Title")
                )
        );

        super.installSidemenu(res);
        ConnectionRequest con = new ConnectionRequest();
        // id = new Produits().idProduit;

        System.out.println("chiiiyyyyyyy");

        con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/afichermyproduct/"+User.getUserconnected().getId());

        con.addResponseListener((NetworkEvent evt) -> {

            ArrayList<Produit> listProduits = getProduit((new String(con.getResponseData())));

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
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String pic;
        //  Label pays = new Label();
        //  Label ville = new Label();  
        Label categorie = new Label(oo.getCategorieProduit());
        //  Label stock = new Label();
        Label nomProduit = new Label(oo.getNomProduit());
        Label prixProduit = new Label("" + oo.getPrixProduit());

        Label stock = new Label("" + oo.getStockProduit());
        System.out.println("hhh iiddd taw taw" + oo.getCategorieProduit() + oo.getIdProduit());
        TextArea descriptif = new TextArea(oo.getDescriptionProduit());
        System.out.println(idProduit + "taawaa test");
        ImageViewer img = new ImageViewer();
        // image=Image.createImage("/tunis.jpg").fill(170, 100);
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("logo.png"), false);
        img.setImage(URLImage.createToStorage(enc, oo.getPhotoProduit(), "http://localhost/PIDEV/web/image/" + oo.getPhotoProduit()).fill(200, 150));

        descriptif.setRows(2);
        descriptif.setColumns(50);
        descriptif.setGrowByContent(false);
        descriptif.setEditable(false);
        descriptif.setUIID("SlightlySmallerFontLabelLeft");

        Label l1 = new Label("Catégorie Produit: ");
        Label l2 = new Label("Nom Produit: ");
        Label l5 = new Label("Description: ");
        Label l3 = new Label("Prix: ");
        Label l4 = new Label("Stock: ");
        C1.add(l1);
        C1.add(categorie);
        C0.add(img);
        C0.add(C1);
        C2.add(l2);
        C2.add(nomProduit);
        C0.add(C2);
        C3.add(l3);
        C3.add(prixProduit);
        C0.add(C3);
        C4.add(l4);
        C4.add(stock);
        C0.add(C4);
        C5.add(l5);
        C5.add(descriptif);
        C0.add(C5);
        Button favorie = new Button("Supprimer");

        C6.add(favorie);
        C0.add(C6);
        favorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                idProduit = oo.getIdProduit();
//float id = Float.parseFloat(valeur.get("id_produit").toString()); 
                // c.setColor(getContentPane().getUnselectedStyle().getBgColor()+"");
                ConnectionRequest con = new ConnectionRequest();
//oo.setIdProduit(Integer.parseInt(obj.get("id").toString().trim()));
                System.err.println("iiiid tawa" + oo.getIdProduit());
                con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/supprimerJsonProduit/" + oo.getIdProduit());
                con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                       //Dialog.show("Confirmation", "ajout effectuer avec succée", "Ok", null);
                       new MesProduits(theme).show();
                    }
                });

                NetworkManager.getInstance().addToQueue(con);
            }

            //new ActivityMain(com.codename1.ui.util.Resources.getGlobalResources()).show();
        });

        return C0;
    }

    public ArrayList<Produit> getProduit(String json) {

        ArrayList<Produit> listProd = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> rando = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) rando.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(list + "jjjjj");
                Produit o = new Produit();
                o.setIdProduit((int)Float.parseFloat(obj.get("id_produit").toString().trim()));
               // float id = Float.parseFloat(valeur.get("id_produit").toString());
                //System.out.println("com.codename1.uikit.pheonixui.Favorie.getProduit()");
//                System.out.println(o.getProduit().getCategorieProduit()+"waalaa");
                //o.getProduit().setIdProduit((int)Float.parseFloat(obj.get("id_produit").toString()));
                o.setCategorieProduit(obj.get("categorieProduit").toString());
                o.setNomProduit(obj.get("nom_produit").toString());
                o.setPrixProduit(Float.parseFloat(obj.get("prixProduit").toString()));
                o.setStockProduit((int) Float.parseFloat(obj.get("stockProduit").toString()));
                o.setDescriptionProduit(obj.get("description").toString());
                  o.setPhotoProduit(obj.get("photo").toString());

                listProd.add(o);

            }

        } catch (IOException ex) {
        }
        return listProd;

    }

}
