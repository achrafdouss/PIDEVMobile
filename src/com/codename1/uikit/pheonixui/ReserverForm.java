/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.bonplan.entities.CommandeProduit;
import com.bonplan.entities.Produit;
import com.bonplan.entities.Reservation;
import com.bonplan.entities.User;
import com.bonplan.entities.Voyage;
import com.bonplan.services.ReservationService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.io.IOException;



import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author amira
 */
public class ReserverForm extends BaseForm {

    public int id;
    Resources theme;
    Voyage v;
    Reservation r ;
    public ReserverForm() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    protected boolean isCurrentForum() {
        return true;
    }

    public ReserverForm(Resources res) throws IOException {
        // super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Reserver Voyage", "Title")
                )
        );

        super.installSidemenu(res);
        
        getToolbar().setBackCommand("", e -> {
            // con.setUrl("http://localhost/aryak/DestroyGuide.php");

            

         
           
   
});
        
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label lllll=new Label("Il ne reste que"+Voyage.voyage.nbr_place+" places");
        Label l2=new Label("Nombre de place");
        TextField Nbr_res=new TextField();
        Nbr_res.setHint("Nombre de place à res");
        Button Res=new Button("Reserver");
        C0.add(lllll);
        C1.add(l2);
        C1.add(Nbr_res);
        C0.add(C1);
        C0.add(Res);
        Res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReservationService rs=new ReservationService();
                System.out.println(User.getUserconnected().toString());
                Reservation r = new Reservation(Voyage.voyage,User.getUserconnected().getId(),Integer.parseInt(Nbr_res.getText()));
                    rs.Reserver(r);
                   
                new VoyageGui(theme).show();
             }
            
            
           
        });
        add(C0);
        
        
        
        
        
                
    } 
}
