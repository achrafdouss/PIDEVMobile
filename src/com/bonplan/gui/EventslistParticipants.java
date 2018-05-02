/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.Participation;
import static com.bonplan.gui.EventsAll.accueil;
import com.bonplan.services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;

/**
 *
 * @author souab
 */
public class EventslistParticipants {

    Form back;
    Form listP;

    public EventslistParticipants(Form back) {
        this.back = back;
        TableLayout table = new TableLayout(1, 3);
        table.setGrowHorizontally(true);
        listP = new Form("My Participations", table);
        listP.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            accueil.showBack();
        });
        listP.getToolbar().addMaterialCommandToSideMenu("All Events", FontImage.MATERIAL_HOME, e -> {
            EventsAll all = new EventsAll();
        });

        listP.getToolbar().addCommandToSideMenu("My Participations",UIManager.initFirstTheme("/theme").getImage("selection-in-sidemenu.png"), e -> {
            EventslistParticipants mesParticipations = new EventslistParticipants(listP);
        });
        listP.getToolbar().addMaterialCommandToSideMenu("My Events", FontImage.MATERIAL_EVENT, e -> {
            EventsMy myevents = new EventsMy(listP);
        });
        listP.add(new Label("Event"));
        listP.add(new Label("Status"));
        listP.add(new Label("Action"));
        EvenementService es = new EvenementService();
        ArrayList<Participation> listp = es.MesParticipations(EvenementService.iduser);

        for (Participation participation : listp) {

            for (Evenement e : EvenementService.listTasks) {

                if (e.id == participation.id_event) {
                    Label l = new Label(e.getNomEvenement());
                    listP.add(l);
                    Label l1 = new Label(es.status(participation.getStatus()));
                    listP.add(l1);
                    Label l2 = new Label("Cancel");
                    l2.addPointerPressedListener((evt) -> {
                        es.annuler(participation.id);
                        
                        EventslistParticipants mesParticipations = new EventslistParticipants(back);
                    });
                    listP.add(l2);
                }
            }

        }
        listP.getToolbar().addCommandToRightBar("Back", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, new Style()), (e) -> {
            back.showBack();

        });
        listP.show();
    }

    public Form getBack() {
        return back;
    }

    public void setBack(Form back) {
        this.back = back;
    }

    public Form getListP() {
        return listP;
    }

    public void setListP(Form listP) {
        this.listP = listP;
    }

}
