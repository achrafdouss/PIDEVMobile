/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
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
public class CommentaireService {
     public ArrayList<Commentaire> getallrec(int id){
        ArrayList<Commentaire> listcom= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/recommendation/allcom/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> commentaire = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(commentaire);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) commentaire.get("root");

            for (Map<String, Object> obj : list) {
                Commentaire r = new Commentaire();
                r.setId_com(Integer.parseInt(obj.get("id_com").toString()));
                r.setId_owner(Integer.parseInt(obj.get("id_owner").toString()));
                r.setId_rec(Integer.parseInt(obj.get("id_rec").toString()));
                r.setContenu(obj.get("contenu").toString());
                r.setNote(Double.parseDouble(obj.get("note").toString()));
                
                
                listcom.add(r);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcom;
    }
     public void ajoutCommentaire(Commentaire c){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/recommendation/ajoutcom/"+c.id_owner+"/"+c.id_rec+"/"+c.contenu+"/"+c.note);  
        NetworkManager.getInstance().addToQueueAndWait(con);
     }
     public void suppCommentaire(int id_com,int id_rec){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/recommendation/suppcom/"+id_com+"/"+id_rec);  
        NetworkManager.getInstance().addToQueueAndWait(con);
     }
     public void UpdateCommentaire(Commentaire c){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/recommendation/updatecomjson/"+c.id_rec+"/"+c.id_com+"/"+c.contenu+"/"+c.note);  
        NetworkManager.getInstance().addToQueueAndWait(con);
     }
     
    
}
