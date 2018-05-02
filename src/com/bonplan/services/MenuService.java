/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Menu;
import com.bonplan.entities.Restaurant;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author Assil
 */
public class MenuService {
    
    
    
    public void ajoutMenu(Menu m, int r) {
        ConnectionRequest con = new ConnectionRequest();
        
       String Url =  "http://localhost/PIDEV/web/app_dev.php/restau/ajoutem?nom="+m.getNomMenu() +"&desc="+m.getDescriptionMenu()+"&prix="+m.getPrixMenu()+"&ph="+m.getPhotoM()+"&idmr="+r;
        con.setUrl(Url);

        //System.out.println("tt");

        
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
