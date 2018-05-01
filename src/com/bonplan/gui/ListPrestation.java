/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;
import com.bonplan.entities.Prestation;
import com.bonplan.services.PrestationService;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class ListPrestation {
    //Form f = new Form("Liste des prestations", new BoxLayout(BoxLayout.Y_AXIS));
    PrestationService ps = new PrestationService();
    //ArrayList<Prestation> listPrestations = new ArrayList<>();
    public void showlist(ArrayList<Prestation> listPrestations)
    {
        Form f = new Form("Liste des prestations", new BoxLayout(BoxLayout.Y_AXIS));
        Container father = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container crecherche = makerecherche();
        father.add(crecherche);
        try {
            Image head = Image.createImage("/prestation.jpg");
            father.add(head);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for(Prestation p : listPrestations)
        {
            Container cprestation = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label titre = new Label(p.getTitre());
            Label categorie = new Label("Categorie: "+p.getCategorie());
            Label lieu = new Label("Lieu: "+p.getLieu());
            try {
            Image img = Image.createImage("/separator.png");
            father.add(img);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            titre.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    ConsulterPrestation cp = new ConsulterPrestation();
                    cp.showprestation(p);
                }
            });
            cprestation.add(titre);
            cprestation.add(categorie);
            cprestation.add(lieu);
            father.add(cprestation);
        }
        f.add(father);
        f.show();
        
    }
    public Container makerecherche()
    {
        Container crecherche = new Container(new BoxLayout(BoxLayout.X_AXIS));
        TextField recherche = new TextField();
        Button brecherche = new Button("Search");
        brecherche.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showlist(ps.find(recherche.getText()));
            }
        });
        crecherche.add(brecherche);
        crecherche.add(recherche);
        return crecherche;
    }
    
}
