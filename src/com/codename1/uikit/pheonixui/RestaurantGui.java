/**
 *
 * @author Assil
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Restaurant;
import com.bonplan.entities.User;
import com.bonplan.services.RestaurantService;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout.Constraint;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Validator;
//import com.codename1.uikit.cleanmodern.BaseForm;
//import com.codename1.uikit.cleanmodern.SignInForm;
//import com.esprit.entities.Animal;
//import com.esprit.entities.Reclamation;
//import com.esprit.entities.User;
//import com.esprit.services.AnimalService;
//import com.esprit.services.ReclamationService;
//import com.esprit.services.UserService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Nada
 */
public class RestaurantGui extends BaseForm {

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

    Button btnajout;
    Button btnvideo;

    public RestaurantGui() {
        f = new Form("Ajouter Restaurant");
        tnom = new TextField();
        tnom.setUIID("TextFieldBlack");
        tel = new TextField();
        tel.setUIID("TextFieldBlack");
        tadr = new TextField();
        tadr.setUIID("TextFieldBlack");
        temail = new TextField();
        temail.setUIID("TextFieldBlack");
        tspec = new TextField();
        tspec.setUIID("TextFieldBlack");
        timage = new TextField();
        timage.setUIID("TextFieldBlack");
        btnajout = new Button("Ajouter");
        btnvideo = new Button("Video");
        //btnaff=new Button("Afficher mes reclamations");
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
        Button upload = new Button("ajouter image");
//        f.add(upload);
        f.add(timage);

        //f.add(timage);
        f.add(btnajout);
        //f.add(btnvideo);

        Button affiche = new Button("Afficher");
        f.add(affiche);

        getF().show();

        btnajout.addActionListener((e) -> {
            RestaurantService ser = new RestaurantService();

            //User.getUserconnected().getId();
            if (tnom.getText().equals("") || tel.getText().equals("") || tadr.getText().equals("") || temail.getText().equals("") || /*!temail.getText().contains('@') ||*/ tspec.getText().equals("") ) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", "Cancel");
            } else {
                
                
                
                
                
                

                Restaurant r = new Restaurant(tnom.getText(), Integer.parseInt(tel.getText()), tadr.getText(), temail.getText(), tspec.getText(), timage.getText());

                //Restaurant R = new Restaurant(0,"assil",11,"rades","email@exemple","spec");
                //Restaurant a = new Restaurant(1, "", 11, " ", " ", " ");
                //Restaurant r = new Restaurant("nomRestaurant", 555555555, "adresseRestaurant", "emailRestaurant", "specialiteRestaurant", "photo");
//           Restaurant r = new Restaurant();
//           r.setIdRestaurant(0);
//           r.setNomRestaurant(" ");
//           r.setAdresseRestaurant(" ");
//           r.setTelephoneRestaurant(555);
//           r.setEmailRestaurant(" ");
//           r.setSpecialiteRestaurant(" ");
                ser.ajoutRest(r);
                Dialog.show("Succes", "Ajout Effectué", "OK", "Cancel");
                System.out.println(r.toString());
            }

//Reclamation R = new Reclamation(temail.getText(),tnom.getText(),tel.getText());
            //Reclamation R = new Reclamation("emaaail","nadaaa","titreee","1111","reclamationnnnn");
        });

//        upload.addActionListener((e) -> {
//
//            String filePath = Capture.capturePhoto();
//            if (filePath != null) {
//
//                try {
//                    String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + ".jpg";
//                    //String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg";
//                    //String pathToBeStored = "C:/wampNada/www/Images/";
//                    //String path =  e.getSource().toString();
//                    Image img = Image.createImage(filePath);
//                    OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
//                    ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 0.9f);
//                    os.close();
//
//                    timage.setText(pathToBeStored);
//
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//
//            }
//
//        });
        affiche.addActionListener((e) -> {
            AffichageRestau a = new AffichageRestau();
            a.getF().show();

        });

        Validator val = new Validator();

//        btnvideo.addActionListener((e) -> {
//
//                VideoGui vg = new VideoGui();
//                vg.getHi().show();
//                
//                //System.out.println(r.toString());
//
//            });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
