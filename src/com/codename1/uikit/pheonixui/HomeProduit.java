/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bouyo
 */
public class HomeProduit extends  BaseForm{
    Resources theme;
      public HomeProduit() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
       public HomeProduit(Resources res) {
       // super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
                super.installSidemenu(res);

        Button produit = new Button("Produits");
        produit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Produits(theme).show();

            }
        });
        Button mproduit = new Button("Mes produits");
        mproduit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MesProduits(theme).show();

            }
        });
        
         Button mfav = new Button("Mes favoris");
        mfav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Favorie(theme).show();

            }
        });
           Button ajout = new Button("Ajouter produit");
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AjouterProd(theme).show();

            }
        });
          Button sat = new Button("Les statistiques");
        sat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AjouterProd(theme).show();
                statistique st = new statistique(res);
                st.createPieChartForm().show();
            }
        });
         this.add(produit).add(mproduit).add(mfav).add(ajout).add(sat);
        
                }


      
}
