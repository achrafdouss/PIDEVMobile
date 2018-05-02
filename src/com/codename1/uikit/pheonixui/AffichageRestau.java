/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Restaurant;
import com.bonplan.services.RestaurantService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import java.util.ArrayList;

/**
 *
 * @author Assil
 */
public class AffichageRestau {

    Restaurant r = new Restaurant();
    TextArea tn;
    TextArea ts;
    TextArea tt;
    Form f = new Form("Liste des Restaurants");
    //SpanLabel lb = new SpanLabel("Liste :  ");

    public AffichageRestau() {
        //f.add(lb);
        Button up = new Button();

        RestaurantService ser = new RestaurantService();
        //  lb.setText(ser.rechercheSkill().toString());
        ArrayList<Restaurant> l = ser.rechercheSkill();
        for (int i = 0; i < l.size(); i++) {
            Label restaur = new Label("Restaurant" + l.get(i).toString());
            System.out.println("llllllllllllllll" + l);

            r = l.get(i);

            Container c = new Container(BoxLayout.y());

            c.getStyle().setBorder(Border.createLineBorder(1));

            tn = new TextArea("Nom");
            Label l1 = new Label(r.getNomRestaurant());
            tn.setUIID("TextAreaBlack");

            ts = new TextArea("Specialite");
            Label l2 = new Label(r.getSpecialiteRestaurant());
            ts.setUIID("TextAreaBlack");

            tt = new TextArea("Telephone");
            Label l3 = new Label(" " + r.getTelephoneRestaurant());
            tt.setUIID("TextAreaBlack");

            c.add(tn);
            c.add(l1);

            c.add(ts);
            c.add(l2);

            c.add(tt);
            c.add(l3);

            int id = r.getIdRestaurant();
            f.add(c);

            l1.addPointerPressedListener((e) -> {
                UpdateRestau a = new UpdateRestau(id);
                a.setIdrm(id);
                a.getF().show();

//              f.add(up);
                // up.addActionListener((e) -> {
                // UpdateRestau a = new UpdateRestau(r.getIdRestaurant());
                // a.getF().show();
                //  });
            });

            f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                RestaurantGui rg = new RestaurantGui();
                rg.getF().show();
            });

            f.show();

        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
