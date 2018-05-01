/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.CommentairePrestation;
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
 * @author dell
 */
public class CommentairePrestationService {
    
     public ArrayList<CommentairePrestation> getCommentaires(String json) {
        ArrayList<CommentairePrestation> listCommentaires = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                CommentairePrestation cp = new CommentairePrestation();       
                cp.setId_commentaire((int) Float.parseFloat(obj.get("id_prestation").toString().trim()));
                cp.setId_user((int) Float.parseFloat(obj.get("id_user").toString().trim()));
                cp.setContenu(obj.get("contenu").toString());
                cp.setDate_ajout(obj.get("date_ajout").toString());
                cp.setUsername(obj.get("username").toString());
                //System.out.println(cp.toString());
                listCommentaires.add(cp);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listCommentaires;
    }
    ArrayList<CommentairePrestation> listCommentaires = new ArrayList<>();
    public ArrayList<CommentairePrestation> findbyidprestation(int idPrest) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/prestation/CommentairesPrestation/find/"+idPrest);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentairePrestationService cps = new CommentairePrestationService();
                listCommentaires = cps.getCommentaires(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommentaires;
    }
String reponse;
    public String addCommentaire(int idPrestation, String text) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/prestation/CommentairesPrestation/add?idPrest="+idPrestation+
                "&idUser=2"+"&contenu="+text);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               reponse = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reponse;
        
    }
    
}
