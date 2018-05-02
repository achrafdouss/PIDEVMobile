/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.Participation;
import static com.bonplan.gui.EventsAll.accueil;
import com.bonplan.service.EvenementService;
import com.bonplan.services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author souab
 */
public class EventsDetails {

    Form details;
    Form hi;
    Evenement t;

    public void EventsDetails() {
        EvenementService es = new EvenementService();

        details = new Form(t.getNomEvenement(), BoxLayout.y());
        details.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            accueil.showBack();
        });
        details.getToolbar().addMaterialCommandToSideMenu("All Events", FontImage.MATERIAL_HOME, e -> {
            EventsAll all = new EventsAll();
        });

        details.getToolbar().addMaterialCommandToSideMenu("My Participations", FontImage.MATERIAL_EVENT_SEAT, e -> {
            EventslistParticipants mesParticipations = new EventslistParticipants(details);
        });
        details.getToolbar().addMaterialCommandToSideMenu("My Events", FontImage.MATERIAL_EVENT, e -> {
            EventsMy myevents = new EventsMy(details);
        });
        URLImage photo = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(hi.getWidth() / 2, hi.getWidth() / 2, 0xffff0000), true), t.getPhoto(),
                EvenementService.web + "PIDEV/web/uploads/" + t.getPhoto()
        );
        photo.fetch();
        ImageViewer imv = new ImageViewer(photo);

        details.add(imv);
        URLImage map = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(hi.getWidth() / 2, hi.getWidth() / 2, 0xffff0000), true), "map" + t.getNomEvenement(),
                "https://maps.googleapis.com/maps/api/staticmap?center=" + t.getLat() + "," + t.getLng() + "&zoom=16&size=400x400&markers=color:green%7Clabel:E%7C" + t.getLat() + "," + t.getLng() + "&key=AIzaSyDzwkxwIz7hgPCcMwhK2nogMGtTnAUlYzo"
        );
        map.fetch();
        ImageViewer imv2 = new ImageViewer(map);

        SpanLabel description = new SpanLabel(t.getDescription());
        Label date = new Label(t.getDate_evenement());
        Label nbre = new Label(Integer.toString(t.getNbrplace()) + " Ticket(s) available");
        Label prix = new Label(Integer.toString(t.getPrix()) + " DT");
        SpanLabel lieu = new SpanLabel("Adress: " + t.getLieu());

        details.add(description);
        details.add(date);
        details.add(nbre);
        details.add(prix);
        FontImage.setMaterialIcon(prix, FontImage.MATERIAL_ATTACH_MONEY);
        FontImage.setMaterialIcon(date, FontImage.MATERIAL_DATE_RANGE);
        FontImage.setMaterialIcon(nbre, FontImage.MATERIAL_GROUP);
        FontImage.setMaterialIcon(description, FontImage.MATERIAL_INFO_OUTLINE);
        FontImage.setMaterialIcon(lieu, FontImage.MATERIAL_PLACE);
        details.add(lieu);
        Button directions = new Button("Directions");
        directions.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    //Display.getInstance().openNativeNavigationApp(Double.parseDouble(t.getLat()), Double.parseDouble(t.getLng()));
                    EventsDirections d = new EventsDirections(new Coord(LocationManager.getLocationManager().getCurrentLocation().getLatitude(), LocationManager.getLocationManager().getCurrentLocation().getLongitude()), new Coord(Float.parseFloat(t.getLat()), Float.parseFloat(t.getLng())), details);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        details.add(imv2);
        details.add(directions);
        if (t.getId_owner() == EvenementService.iduser) {
            Button participants = new Button("Participants");
            details.add(participants);

            if (es.EventParticipations(t.id).isEmpty()) {
                Button delete = new Button("Delete");
                details.add(delete);
                delete.addActionListener((evt2) -> {

                });
            }

            participants.addActionListener((evt) -> {
                TableLayout table = new TableLayout(1, 3);
                table.setGrowHorizontally(true);
                Form f = new Form(t.getNomEvenement(), table);
                f.add(new Label("User"));
                f.add(new Label("Status"));
                f.add(new Label("Action"));
                ArrayList<Participation> list = es.EventParticipations(t.id);
                for (Participation participation : list) {
                    Label l = new Label(new UserService().getuserId(participation.getId_inscrit()).getUsername());
                    f.add(l);
                    Label l1 = new Label(es.status(participation.getStatus()));
                    f.add(l1);
                    Label l2 = new Label("Confirm");
                    f.add(l2);
                    l2.addPointerPressedListener((evt2) -> {
                        es.confirmer(participation.getId());
                        participation.setStatus(1);
                        l1.setText("confirmed");
                    });

                }

                f.getToolbar().addCommandToRightBar("Back", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, new Style()), (e) -> {
                    details.showBack();
                });
                f.show();
            });
        } else {
            ArrayList<Participation> listp = es.MesParticipations(EvenementService.iduser);
            boolean x = false;
            for (Participation participation : listp) {
                if ((participation.id_event == t.id) && (participation.id_inscrit == EvenementService.iduser)) {
                    x = true;
                }
            }
            if (x == false) {
                Button participer = new Button("Participate");
                details.add(participer);
                participer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Participation p = new Participation(t.id, EvenementService.iduser, 1);
                        es.ajoutParticipation(p);
                        nbre.setText(Integer.toString(t.getNbrplace() - 1));

                        ToastBar.showMessage("Your Participation is pending ,you'll be notified by SMS when confirmed", FontImage.MATERIAL_INFO_OUTLINE);;
                    }
                });
            }

        }
        details.show();
        details.getToolbar().addCommandToRightBar("Back", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, new Style()), (e) -> {
            hi.showBack();
        });
    }

    public Form getDetails() {
        return details;
    }

    public void setDetails(Form details) {
        this.details = details;
    }

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }

    public Evenement getT() {
        return t;
    }

    public void setT(Evenement t) {
        this.t = t;
    }

}
