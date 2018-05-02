/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import static com.bonplan.gui.EventsAll.accueil;
import com.bonplan.service.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.List;

/**
 *
 * @author souab
 */
public class EventsMy {

    Form myEvents;
    Form hi;

    public Form getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(Form myEvents) {
        this.myEvents = myEvents;
    }

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }

    public EventsMy(Form hi) {
        this.hi = hi;
        EvenementService es = new EvenementService();
        List<Evenement> EventList = es.getListMy();
        myEvents = new Form("My Events", BoxLayout.y());
        myEvents.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            accueil.showBack();
        });
        myEvents.getToolbar().addMaterialCommandToSideMenu("All Events", FontImage.MATERIAL_HOME, e -> {
            EventsAll all = new EventsAll();
        });
        myEvents.getToolbar().addMaterialCommandToSideMenu("My Participations", FontImage.MATERIAL_EVENT_SEAT, e -> {
            EventslistParticipants mesParticipations = new EventslistParticipants(myEvents);
        });
        myEvents.getToolbar().addCommandToSideMenu("My Events",UIManager.initFirstTheme("/theme").getImage("selection-in-sidemenu.png"), e -> {
            EventsMy myevents = new EventsMy(myEvents);
        });
        myEvents.getToolbar().addCommandToRightBar("Back", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, new Style()), (e) -> {
            hi.showBack();

        });
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Evenement t : EventList) {
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label label = new Label(t.getNomEvenement());
            URLImage background = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(hi.getWidth() / 2, hi.getWidth() / 2, 0xffff0000), true), t.getPhoto(),
                    EvenementService.web + "PIDEV/web/uploads/" + t.getPhoto());
            background.fetch();
            ImageViewer imv = new ImageViewer(background);

            c.add(label);
            c.add(imv);

            c1.add(c);
            label.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    EventsDetails details = new EventsDetails();
                    details.setHi(myEvents);
                    details.setT(t);
                    details.EventsDetails();
                    details.getDetails().show();

                }
            });

        }
        myEvents.add(c1);
        myEvents.show();

    }

}
