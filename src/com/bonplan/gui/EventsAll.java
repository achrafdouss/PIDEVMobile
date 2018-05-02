/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
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
public class EventsAll {
  public static Form accueil;
    Form hi = new Form("All Events", BoxLayout.y());

    public EventsAll() {
        EvenementService es = new EvenementService();
        List<Evenement> EventList = es.getList2();
        hi.getToolbar().addCommandToRightBar("Back", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, new Style()), (e) -> {
            accueil.showBack();
        });
        hi.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            accueil.showBack();
        });
        hi.getToolbar().addCommandToSideMenu("All Events",UIManager.initFirstTheme("/theme").getImage("selection-in-sidemenu.png"), e -> {
            EventsAll all = new EventsAll();
        });
        hi.getToolbar().addMaterialCommandToSideMenu("My Participations", FontImage.MATERIAL_EVENT_SEAT, e -> {
            EventslistParticipants mesParticipations = new EventslistParticipants(hi);
        });
        hi.getToolbar().addMaterialCommandToSideMenu("My Events", FontImage.MATERIAL_EVENT, e -> {
            EventsMy myevents = new EventsMy(hi);
        });

        Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Evenement t : EventList) {
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label label = new Label(t.getNomEvenement());
            URLImage background = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(hi.getWidth() / 2, hi.getWidth() / 2, 0xffff0000), true), t.getPhoto(),
                    EvenementService.web + "PIDEV/web/uploads/" + t.getPhoto());
            background.fetch();
            ImageViewer imv = new ImageViewer(background);

            c.add(label);
            c.add(imv);

            c0.add(c);
            label.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    EventsDetails details = new EventsDetails();
                    details.setHi(hi);
                    details.setT(t);
                    details.EventsDetails();
                    details.getDetails().show();

                }
            });
        }

        hi.add(c0);
        hi.show();
    }

    public Form getHi() {
        return hi;
    }

}
