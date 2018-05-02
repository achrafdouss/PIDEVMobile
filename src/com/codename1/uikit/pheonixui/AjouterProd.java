
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.User;
import com.codename1.capture.Capture;
import com.codename1.components.SignatureComponent;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author amira
 */
public class AjouterProd extends BaseForm {
String pic;
public String imagepath;
    public String imagename;
    Resources theme;
      public AjouterProd() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    
    public AjouterProd( Resources res) {
     super(new BorderLayout());
         theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajouter Produit", "Title")
                )
        );
         super.installSidemenu(res);
       
         ConnectionRequest req = new ConnectionRequest();
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
       // getTitleArea().getStyle().setOpacity(0);
       // setUIID("LoginBack");
       //  setScrollableY(true);
       // getToolbar().setUIID("Container");
       // getToolbar().getTitleComponent().setUIID("SigninTitle");
     Image back = res.getImage("back.jpg");
       FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, "SigninTitle", 3.5f);

       getToolbar().addCommandToLeftBar("", back, (ActionListener) (ActionEvent evt) -> {
     new SignInForm(res).show(); });

        Button avatar = new Button("");
        avatar.setUIID("InputAvatar");
        Image defaultAvatar = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, "InputAvatarImage", 8);
        Image circleMaskImage = res.getImage("logo.png").fill(90, 90);
        defaultAvatar = defaultAvatar.scaled(circleMaskImage.getWidth(), circleMaskImage.getHeight());
        defaultAvatar = ((FontImage)defaultAvatar).toEncodedImage();
        Object circleMask = circleMaskImage.createMask();
        defaultAvatar = defaultAvatar.applyMask(circleMask);
        avatar.setIcon(defaultAvatar);

        avatar.addActionListener(e -> {
            if(Dialog.show("Gallery photo", "veuillez choisir une photo pour votre profile", "Gallery", null)) {
               /*  pic = Capture.capturePhoto();
                if(pic != null) {
                    try {
                        Image img = Image.createImage(pic).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                        avatar.setIcon(img.applyMask(circleMask));
                    } catch(IOException err) {
                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                        Log.e(err);
                    }*/
              //  }
             try {
                String filePath = Capture.capturePhoto();
                System.out.println("origine temp file :" + filePath);
                int ind = filePath.indexOf("temp") + 4;
                int ind1 = filePath.indexOf("..");
                String text = filePath.substring(ind, ind1);
                System.out.println("Reference:" + text);
                int intpoint = filePath.indexOf("..") + 2;
                String format = filePath.substring(intpoint);
                System.out.println("Format:" + format);
                String fullnameimage = text + "." + format;
                System.out.println("Full name :" + fullnameimage);
                imagepath = filePath;
                imagename = fullnameimage;
                System.out.println("imagepath:" + imagepath);
               SignatureComponent sig = new SignatureComponent();
                sig.addActionListener((evt)-> {
                System.out.println("The signature was changed");
                Image img = sig.getSignatureImage();
    // Now we can do whatever we want with the image of this signature.
});
                Image img = Image.createImage(filePath).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
              avatar.setIcon(img.applyMask(circleMask));
            } catch (Exception exx) {
            }
            
            } 
        });


        Container cameraLayer = LayeredLayout.encloseIn(
       avatar);

        Container titleContainer = Container.encloseIn(
        new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER), 
        cameraLayer, BorderLayout.CENTER);
        titleContainer.setUIID("TitleContainer");

        ComboBox<String> categorie = new ComboBox<>();
  categorie.addItem("Immobilier");
        categorie.addItem("Véhicule");
        categorie.addItem("Pour Jardin");
        TextField Reference = new TextField("", "Description");
        TextField Nom = new TextField("", "Nom");
        TextField Prix = new TextField("", "Prix");
       // TextField Categorie = new TextField("", "Categorie");
      



        TextField stock = new TextField("", "stock");
      // Reference.setUIID("TxtField");
       //Nom.setUIID("TxtField");
       //Prix.setUIID("TxtField");
      // Categorie.setUIID("TxtField");
       //stock.setUIID("TxtField");
       // phonePrefix.setUIID("TxtField");

       TableLayout fullNameLayout = new TableLayout(1, 3);
        Container fullName = new Container(fullNameLayout);
        fullName.add(fullNameLayout.createConstraint().widthPercentage(49), Reference).
            add(fullNameLayout.createConstraint().widthPercentage(1), createSeparatorSignUp()).
            add(fullNameLayout.createConstraint().widthPercentage(50), Nom);


        // The button in the south portion needs the arrow icon to be on the right side so we place the text on the left
        Button southButton = new Button("Ajouter", rightArrow);     
        southButton.addActionListener(new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent evt) {

             ConnectionRequest req = new ConnectionRequest();
          req.setUrl("http://localhost/PIDEV/web/app_dev.php/produit/AjoutProduitJson?categorieProduit=" +  categorie.getSelectedItem() + "&nom_produit=" + Nom.getText() + "&prixProduit=" + Prix.getText() + "&description=" +Reference.getText() + "&stockProduit=" + stock.getText()+"&id_owner="+User.getUserconnected().getId()+"&photo="+imagename+"");

           req.addResponseListener(new ActionListener<NetworkEvent>() {

               @Override
               public void actionPerformed(NetworkEvent evt) {
                //   byte[] data = (byte[]) evt.getMetaData();
                  
                 ConnectionRequest req1 = new ConnectionRequest();
           req1.setUrl("http://localhost/test.php?imagepath=" + imagepath+"&photo="+imagename+"");

           req1.addResponseListener(new ActionListener<NetworkEvent>() {

               @Override
               public void actionPerformed(NetworkEvent evt) {
                       Dialog.show("Confirmation", "ajout effectuer avec succée", "Ok", null);
                       new Produits(res).show();
                 }
           });

           NetworkManager.getInstance().addToQueue(req1);


       
               }
           });

           NetworkManager.getInstance().addToQueue(req);


       }
   });

        southButton.setTextPosition(Component.LEFT);
        southButton.setUIID("ButtonSouth");

        Container by = BoxLayout.encloseY(
                titleContainer,
                        fullName, 
                        createSeparatorSignUp(), 
                        Prix, 
                        createSeparatorSignUp(), 
                        categorie, 
                        createSeparatorSignUp(), 
                        stock,
                        createSeparatorSignUp()
                );
        by.setScrollableY(true);
                this.add(BorderLayout.SOUTH, southButton).
                add(BorderLayout.CENTER, by);

    }
      public Label createSeparatorSignUp() {
        Label sep = new Label();
        sep.setUIID("SeparatorSignUp");
        // the separator line  is implemented in the theme using padding and background color, by default labels 
        // are hidden when they have no content, this method disables that behavior
        sep.setShowEvenIfBlank(true);
        return sep;
    }

}
