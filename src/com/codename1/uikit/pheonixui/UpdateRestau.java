/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Restaurant;
import com.bonplan.services.MenuService;
import com.bonplan.services.RestaurantService;
import com.codename1.capture.Capture;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.util.ImageIO;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Assil
 */
public class UpdateRestau {

    
    static int idrm;
    static int id;
    Form f;
    TextField tnom;
    //TextField tetat;
    TextField tel;
    TextField tadr;
    TextField temail;
    TextField tspec;
    TextField timage;
    Label l1 = new Label("Nom : ");
    Label l2 = new Label("Tel : ");
    Label l3 = new Label("Adresse :");
    Label l4 = new Label("Email :");
    Label l5 = new Label("Specialite :");
    //File file ;

    Button btnmodif;
    Button btnajout;

    public UpdateRestau(int id) {

        RestaurantService s = new RestaurantService();
        s.rechercheRestau(id);
        ArrayList <Restaurant> r = new ArrayList<>();
        r = s.rechercheRestau(id);
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrr"+r);
        
        for (int i = 0; i < s.rechercheRestau(id).size(); i++) {

            f = new Form("Modifier restaurant");
            System.out.println("ssssssssssssssssssssss"+s);
            tnom = new TextField(s.rechercheRestau(id).get(i).getNomRestaurant());
            tnom.setUIID("TextFieldBlack");
            tel = new TextField(/*s.rechercheRestau(id).get(i).getTelephoneRestaurant()*/);
            tel.setUIID("TextFieldBlack");
            tadr = new TextField();
            tadr.setUIID("TextFieldBlack");
            temail = new TextField(/*s.rechercheRestau(id).get(i).getEmailRestaurant()*/);
            temail.setUIID("TextFieldBlack");
            tspec = new TextField();
            tspec.setUIID("TextFieldBlack");
            timage = new TextField();
            timage.setUIID("TextFieldBlack");
            btnmodif = new Button("Modifier");
            btnajout = new Button("Ajouter Menu");
            //btnaff=new Button("");
            f.add(l1);
            f.add(tnom);
            f.add(l2);
            f.add(tel);
            f.add(l3);
            f.add(tadr);
            f.add(l4);
            f.add(temail);
            f.add(l5);
            f.add(tspec);
            //Button upload = new Button("ajouter image");
            //f.add(upload);
            //f.add(timage);
            //f.add(timage);
            f.add(btnmodif);
            f.add(btnajout);
            getF().show();

            btnmodif.addActionListener((e) -> {

                RestaurantService ser = new RestaurantService();
                ser.UpdateRestau(id, tnom.getText());
                //System.out.println(r.toString());

            });
            
            btnajout.addActionListener((e) -> {

                MenuGui mg = new MenuGui();
                mg.setIdrm(this.getIdrm());
                mg.getF().show();
                
                //System.out.println(r.toString());

            });
            
            f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                AffichageRestau ar = new AffichageRestau();
                ar.getF().show();
            });

            
            

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public int getIdrm() {
        return idrm;
    }

    public void setIdrm(int idrm) {
        this.idrm = idrm;
    }
    
    

}
