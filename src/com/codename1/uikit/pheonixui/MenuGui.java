/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.Menu;
import com.bonplan.entities.Restaurant;
import com.bonplan.services.MenuService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

/**
 *
 * @author Assil
 */
public class MenuGui extends BaseForm {

    static int idrm;
    static int id;
    Form f;
    TextField tnom;
    //TextField tetat;
    TextField desc;
    TextField prix;
    TextField timage;
    Label l1 = new Label("Nom : ");
    Label l2 = new Label("Description : ");
    Label l3 = new Label("Prix :");

    Button btnajout;
    

    public MenuGui() {
        f = new Form("Ajouter Menu");
        tnom = new TextField();
        tnom.setUIID("TextFieldBlack");
        desc = new TextField();
        desc.setUIID("TextFieldBlack");
        prix = new TextField();
        prix.setUIID("TextFieldBlack");

        timage = new TextField(" ");

        timage.setUIID("TextFieldBlack");

        btnajout = new Button("Ajouter");
        //btnaff=new Button("Afficher mes reclamations");
        f.add(l1);
        f.add(tnom);
        f.add(l2);
        f.add(desc);
        f.add(l3);
        f.add(prix);
        

        //f.add(timage);
        f.add(btnajout);

        
        f.add(timage);
        getF().show();

        btnajout.addActionListener((e) -> {
            MenuService ms = new MenuService();
            //Reclamation R = new Reclamation("email","user","desc","titre","sujet");
            
            if (tnom.getText().equals("") || desc.getText().equals("")  || prix.getText().equals("") ) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", "Cancel");
            } else {
            Menu m = new Menu(tnom.getText(), desc.getText(), Double.parseDouble(prix.getText()), timage.getText(), this.getIdrm());

            //Restaurant R = new Restaurant(0,"assil",11,"rades","email@exemple","spec");
            //Restaurant a = new Restaurant(1, "", 11, " ", " ", " ");
            //Restaurant m = new Restaurant("nomRestaurant", 555555555, "adresseRestaurant", "emailRestaurant", "specialiteRestaurant", "photo");
//           Restaurant m = new Restaurant();
//           m.setIdRestaurant(0);
//           m.setNomRestaurant(" ");
//           m.setAdresseRestaurant(" ");
//           m.setTelephoneRestaurant(555);
//           m.setEmailRestaurant(" ");
//           m.setSpecialiteRestaurant(" ");
            ms.ajoutMenu(m, this.getIdrm());
            Dialog.show("Succes", "Ajout Effectué", "OK", "Cancel");
            System.out.println(m.toString());
            }
//Reclamation R = new Reclamation(temail.getText(),tnom.getText(),desc.getText());
            //Reclamation R = new Reclamation("emaaail","nadaaa","titreee","1111","reclamationnnnn");
        });
        
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                UpdateRestau ur = new UpdateRestau(this.getIdrm());
                ur.getF().show();
            });

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
