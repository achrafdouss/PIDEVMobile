/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Voyage;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author souab
 */
public class VoyageService {
     ArrayList<Voyage> l=new ArrayList<>();
     public String web="http://localhost:1020/Our/";

    public VoyageService() {
      
    }

    public  ArrayList<Voyage> getL() {
        return l;
    }
    
 
    public ArrayList<Voyage> MesParticipations() {
        ArrayList<Voyage> MesParticipations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"web/app_dev.php/VoyageA/AfficherAllVoyagesM");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> participations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) participations.get("root");
                    for (Map<String, Object> obj : list) {
                        Voyage e = new Voyage();
                        float id = Float.parseFloat(obj.get("id_voyage").toString());
                        e.setId_voyage((int) id);
                        e.setCategorie(obj.get("categorie").toString());
                        e.setType(obj.get("type").toString());
                        e.setNbr_place((int) Float.parseFloat(obj.get("nbrPlace").toString()));
                        e.setId_owner((int) Float.parseFloat(obj.get("id_owner").toString()));
                        e.setDate_dep(obj.get("dateDepart").toString().substring(6, 16));
                        e.setPrix( Float.parseFloat(obj.get("prix").toString()));
                        e.setDate_arr(obj.get("dateArrivee").toString().substring(6, 16));
                        e.setDescription(obj.get("description").toString());
                        e.setDestination(obj.get("destination").toString());
                        e.setPhoto(obj.get("photo").toString());
                        MesParticipations.add(e);

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return MesParticipations;
    }
    
    
    public Voyage OneById(int id_voy) {
        ArrayList<Voyage> MesParticipations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"web/app_dev.php/VoyageA/findOne/"+id_voy);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> participations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) participations.get("root");
                    for (Map<String, Object> obj : list) {
                        Voyage e = new Voyage();
                        float id = Float.parseFloat(obj.get("id_voyage").toString());
                        e.setId_voyage((int) id);
                        e.setCategorie(obj.get("categorie").toString());
                        e.setType(obj.get("type").toString());
                        e.setNbr_place((int) Float.parseFloat(obj.get("nbrPlace").toString()));
                        e.setId_owner((int) Float.parseFloat(obj.get("id_owner").toString()));
                        e.setDate_dep(obj.get("dateDepart").toString().substring(6, 16));
                        e.setPrix( Float.parseFloat(obj.get("prix").toString()));
                        e.setDate_arr(obj.get("dateArrivee").toString().substring(6, 16));
                        e.setDescription(obj.get("description").toString());
                        e.setDestination(obj.get("destination").toString());
                        e.setPhoto(obj.get("photo").toString());
                        MesParticipations.add(e);

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return MesParticipations.get(0);
    }
    
    
}

