/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.CommentairePrestation;
import com.bonplan.entities.Prestation;
import com.bonplan.services.CommentairePrestationService;
import com.bonplan.services.PrestationService;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.providers.GoogleMapsProvider;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author dell
 */
public class ConsulterPrestation {

    private Resources theme;
    PrestationService ps = new PrestationService();
    CommentairePrestationService cps = new CommentairePrestationService();

    public void showprestation(Prestation p) {
        theme = UIManager.initFirstTheme("/theme");
        Form f = new Form("Prestation", new BoxLayout(BoxLayout.Y_AXIS));
        Tabs tabs = new Tabs();
        f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> {
            ListPrestation lp = new ListPrestation();
            PrestationService ps = new PrestationService();
            lp.showlist(ps.findall());
        });
        Container main = MainContainer(p);
        Container map = mapContainer(p);
        Container commentaire = ComContainer(p);
        try {
            tabs.addTab("Info", Image.createImage("/desc.png"), main);
            tabs.addTab("Lieu", Image.createImage("/map.png"), map);
            tabs.addTab("Avis", Image.createImage("/com.png"), commentaire);
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "mochkel image tabs");
        }
        f.add(tabs);
        f.show();

    }

    public Container ComContainer(Prestation p) {
        ArrayList<CommentairePrestation> listCommentaires = cps.findbyidprestation(p.getIdPrestation());
        Container comContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        if (listCommentaires.size() == 0) {
            TextArea ta = new TextArea("Aucun avis ou commentaires laissé.\nSoyez le premier à donner votre avis :)");
            comContainer.add(ta);
        } else {
            for (CommentairePrestation cp : listCommentaires) {
                Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label date = new Label("Le " + cp.getDate_ajout() + ",");
                c2.add(date);
                if (cp.getUsername().length()<=1) {
                    Label user = new Label("Anonyme a commenté:");
                    c2.add(user);
                } else {
                    Label user = new Label(cp.getUsername() + " a commenté:");
                    c2.add(user);
                }
                Label contenu = new Label(cp.getContenu());
                c.add(c2);
                c.add(contenu);
                comContainer.add(c);
            }
        }
        //zone ajout
        TextField comment = new TextField("", "Laissez votre avis ici..");
        Button ajouter = new Button("Ajouter");
        ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (comment.getText().length() == 0) {
                    Dialog dlg = new Dialog("Erreur");
                    Dialog.show("Erreur", "Le champs ne peut pas être vide", "Ok", "Annuler");
                } else if (comment.getText().length() < 4) {
                    Dialog dlg = new Dialog("Erreur");
                    Dialog.show("Erreur", "Minimum 4 caractères", "Ok", "Annuler");
                } else {
                    String reponse = cps.addCommentaire(p.getIdPrestation(), comment.getText());
                    Dialog dlg = new Dialog("Ajout");
                    if(reponse.equals("\"effectue\""))
                    {
                        dlg.show("ajouté", "Merci d'avoir laissé votre commentaire", "Ok", "Fermer");
                    }
                    else
                    {
                        dlg.show("Erreur", "Une erreur s'est produite", "Ok", "Fermer");
                    }
                }
            }
        });
        Container ajoutcon = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ajoutcon.add(ajouter);
        ajoutcon.add(comment);
        comContainer.add(ajoutcon);
        return comContainer;

    }

    private Container MainContainer(Prestation p) {
        Container main = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label date = new Label("Le " + p.getDateAjout());
        main.add(date);
        Label categorie = new Label("Categorie: " + p.getCategorie());
        main.add(categorie);
        Label title = new Label(p.getTitre());
        main.add(title);
        TextArea desc = new TextArea(p.getDescription());
        //Label desc = new Label(p.getDescription());
        main.add(desc);
        if (p.getSalaire() == 0) {
            Label salary = new Label("Rémuneration à négocier");
            main.add(salary);
        } else {
            Label salary = new Label("Rémuneration: " + Float.toString(p.getSalaire()) + " TND");
            main.add(salary);
        }

        return main;
    }

    public Container mapContainer(Prestation p) {
        Container mapcontainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label place = new Label(p.getLieu());
        mapcontainer.add(place);
        GoogleMapsProvider gmp = new GoogleMapsProvider("AIzaSyBd-JmicXhCBPQvn_0KoFpgM3DBQVp98YY");
        //gmp.tileSize(new Dimension(300, 400));
        MapContainer cnt = new MapContainer("AIzaSyA3QkuAIN9G5n8M3D6PbMDD6Wja7dwsEGE");
        Coord coords = getCoords(p.getLieu());
        System.out.println(coords.toString());
        cnt.setMapType(MapContainer.MAP_TYPE_NONE);
        cnt.setSize(new Dimension(300, 400));
        cnt.setCameraPosition(coords);
        cnt.zoom(coords, 14);
        mapcontainer.add(cnt);
        return mapcontainer;
    }

    public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", "AIzaSyDQMsLFHaUNWYhINwkPngzzEH1l-kEpLBA");
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
