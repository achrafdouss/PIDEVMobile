/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
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
 * @author Achraf
 */
public class RecommendationService {
    public ArrayList<Recommendation> getallrec(){
        ArrayList<Recommendation> listrec= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/recommendation/allrec");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> recommendation = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(recommendation);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) recommendation.get("root");

            for (Map<String, Object> obj : list) {
                Recommendation r = new Recommendation();
                r.setId(Integer.parseInt(obj.get("id").toString()));
                r.setId_owner(Integer.parseInt(obj.get("id").toString()));
                r.setTitre(obj.get("titre").toString());
                r.setCategorie(obj.get("categorie").toString());
                r.setNom(obj.get("nom").toString());
                r.setAdresse(obj.get("adresse").toString());
                r.setNum_tel(obj.get("num_tel").toString());
                r.setEmail(obj.get("email").toString());
                r.setNote(Float.parseFloat(obj.get("note").toString()));             
                r.setPhoto(obj.get("photo").toString());
                r.setDescription(obj.get("description").toString());
                
                listrec.add(r);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listrec;
    }
    public ArrayList<Recommendation> getcatrec(String categorie){
        ArrayList<Recommendation> listrec= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/recommendation/toprec/"+categorie);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> recommendation = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(recommendation);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) recommendation.get("root");

            for (Map<String, Object> obj : list) {
                Recommendation r = new Recommendation();
                r.setId(Integer.parseInt(obj.get("id").toString()));
                r.setId_owner(Integer.parseInt(obj.get("id").toString()));
                r.setTitre(obj.get("titre").toString());
                r.setCategorie(obj.get("categorie").toString());
                r.setNom(obj.get("nom").toString());
                r.setAdresse(obj.get("adresse").toString());
                r.setNum_tel(obj.get("num_tel").toString());
                r.setEmail(obj.get("email").toString());
                r.setNote(Float.parseFloat(obj.get("note").toString()));             
                r.setPhoto(obj.get("photo").toString());
                r.setDescription(obj.get("description").toString());
                
                listrec.add(r);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listrec;
    }
    
    
}
