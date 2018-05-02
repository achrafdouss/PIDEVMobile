/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.CommandeProduit;
import com.bonplan.entities.Produit;
import com.bonplan.entities.User;
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
import com.codename1.ui.Form;
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
public class DetailProduitForm extends BaseForm {

    public int id;
    Resources theme;
    Produits p;
    CommandeProduit cp;

    public DetailProduitForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    protected boolean isCurrentForum() {
        return true;
    }

    public DetailProduitForm(Resources res) {
        // super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Detail Produit", "Title")
                )
        );

        super.installSidemenu(res);
        ConnectionRequest con = new ConnectionRequest();
        id = new Produits().idProduit;
        getToolbar().setBackCommand("", e -> {
            // con.setUrl("http://localhost/aryak/DestroyGuide.php");

            con.addResponseListener((NetworkEvent evt) -> {

            });
            new Produits(res).showBack();
        });
        System.out.println("chiiiyyyyyyy");

        con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/PublicationMobileDetail/" + id + "");

        con.addResponseListener((NetworkEvent evt) -> {
            try {
                Produit og = getProduit(new String(con.getResponseData()));
                System.out.println("im here");
                add(addItem(og));
                //  refreshTheme();
            } catch (IOException ex) {
            } catch (JSONException ex) {
                System.out.println(ex.getMessage());
            }

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
        Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C9 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C10 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String pic;
        //  Label pays = new Label();
        //  Label ville = new Label();  
        Label categorie = new Label(oo.getCategorieProduit());
        //  Label stock = new Label();
        Label nomProduit = new Label(oo.getNomProduit());
        Label prixProduit = new Label("" + oo.getPrixProduit());
        Label stock = new Label("" + oo.getStockProduit());




        TextArea descriptif = new TextArea(oo.getDescriptionProduit());

        ImageViewer img = new ImageViewer();
        //image=Image.createImage("/tunis.jpg").fill(170, 100);
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
        Label l12 = new Label("Propriétaire: ");
        Label l13 = new Label("Téléphone: ");

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
        
        Button favorie = new Button("Favorie");

        C6.add(favorie);
        favorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                // c.setColor(getContentPane().getUnselectedStyle().getBgColor()+"");
                ConnectionRequest con = new ConnectionRequest();

                con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/ajoutFavorie/" + id + "");
                con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        new Favorie(theme).show();

                    }
                });

                NetworkManager.getInstance().addToQueue(con);
            }

            //new ActivityMain(com.codename1.ui.util.Resources.getGlobalResources()).show();
        });
        C0.add(C6);

        Button commander = new Button("Commander");

        C7.add(commander);
        C0.add(C7);

        commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form com = new Form("Commander le produit " + oo.getNomProduit(), new BoxLayout(BoxLayout.Y_AXIS));
                Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                // c.setColor(getContentPane().getUnselectedStyle().getBgColor()+"");
                ConnectionRequest con = new ConnectionRequest();
                Label lb = new Label("Veuillez Saisir votre quantité");
                TextArea quantite = new TextArea();
                Button next = new Button("Suivant");

                cc.add(lb);
                cc.add(quantite);
                cc.add(next);
                // C0.add(cc);
                com.add(cc);
                com.show();

                // String q = quantite.getText();
                // int x = (int) (Integer.parseInt(quantite.getText()));
                // Integer x = Integer.valueOf(q);
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        if ((Integer.parseInt(quantite.getText())) > oo.getStockProduit()) {
                            Dialog.show("Stock Insuffisant", ".........", "OK", "Cancel");
                        }
                        if ((Integer.parseInt(quantite.getText())) == 0) {
                            Dialog.show("Veuiller saisir une quantité non null", ".........", "OK", "Cancel");
                            new Produits(theme).show();

                        }
                        if ((Integer.parseInt(quantite.getText())) < 0) {
                            Dialog.show("Veuiller saisir une quantité non négatif", ".........", "OK", "Cancel");
                            new Produits(theme).show();

                        }
                        if ((Integer.parseInt(quantite.getText())) <= oo.getStockProduit()) {

                            Form com1 = new Form("Prix Commande" + oo.getNomProduit(), new BoxLayout(BoxLayout.Y_AXIS));
                            Container cc1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                            Label lb2 = new Label("Le prix de votre commande est : ");
                            Label lb4 = new Label("                         ");

                            int x = Integer.parseInt(quantite.getText());
                            float y = oo.getPrixProduit();
                            float z = x * y;
                            Label lb3 = new Label("" + z);
                            Button valider = new Button("Valider");
                            Button annuler = new Button("annuler");
                            cc1.add(lb2);
                            cc1.add(lb4);
                            cc1.add(lb3);
                            cc1.add(valider);
                            cc1.add(annuler);
                            com1.add(cc1);
                            com1.show();
                            annuler.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    com.showBack();
                                }
                            });
                            valider.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {

                                    ConnectionRequest con = new ConnectionRequest();
                                    System.out.println("hjhjhj validr  " + id);
                                    System.out.println("hjhjhj   " + Integer.parseInt(quantite.getText()));

                                    con.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/commander/" + id + "?quantite=" + quantite.getText());
                                    con.addResponseListener(new ActionListener<NetworkEvent>() {

                                        @Override
                                        public void actionPerformed(NetworkEvent evt) {
                                            Dialog.show("Confirmation", "Commande effectuer avec succée", "Ok", null);
                                            new Produits(theme).show();
                                        }
                                    });
                                    NetworkManager.getInstance().addToQueue(con);
                                }
                            });

                        }

                    }
                });

            }
        });

        return C0;
    }

    public Produit getProduit(String json) throws JSONException {
        int i = json.indexOf("{");
        json = json.substring(i);
        JSONObject obj = new JSONObject(json.trim());

//JSONObject json = new JSONObject(result.trim()); 
        System.out.println(obj.toString(4) + " kakkaka");
        //System.out.println(json);
        Produit o = new Produit();
        // o.setIdProduit(2);
        // System.out.println("iiid   "+obj.getIdProduit());
        o.setIdProduit((int) Float.parseFloat(obj.get("id_produit").toString()));

        o.setCategorieProduit(obj.get("categorieProduit").toString());
        o.setNomProduit(obj.get("nom_produit").toString());
        o.setPrixProduit(Float.parseFloat(obj.get("prixProduit").toString()));
        o.setStockProduit(Integer.parseInt(obj.get("stockProduit").toString()));
        o.setDescriptionProduit(obj.get("description").toString());
        o.setPhotoProduit(obj.get("photo").toString());
      



        System.out.println(o.getCategorieProduit() + "jjjjj");
        System.out.println("iiid 1  " + o.getIdProduit());

        return o;

    }

}
