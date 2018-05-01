/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


import javafx.concurrent.Task;
import static tdanford.json.schema.tests.SchemaTests.json;

/**
 *
 * @author Achraf
 */
public class UserService {
    public boolean login(String username,String password){
        ArrayList<User> listuser= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/user/login1/"+username+"/"+password);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(user);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");

            for (Map<String, Object> obj : list) {
                User u = new User();
                u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setEnabled(Integer.parseInt(obj.get("id").toString()));
                
                u.setPassword(obj.get("password").toString());
                u.setConfirmation_token(obj.get("confirmation_token").toString());
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setAddresse(obj.get("addresse").toString());
                u.setTelephone(Integer.parseInt(obj.get("id").toString()));
                listuser.add(u);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return !listuser.isEmpty();
    }
    public User getuser(String username,String password){
        ArrayList<User> listuser= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/user/login1/"+username+"/"+password);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(user);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");

            for (Map<String, Object> obj : list) {
                User u = new User();
                u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setEnabled(Integer.parseInt(obj.get("id").toString()));
                
                u.setPassword(obj.get("password").toString());
                u.setConfirmation_token(obj.get("confirmation_token").toString());
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setAddresse(obj.get("addresse").toString());
                u.setTelephone(Integer.parseInt(obj.get("id").toString()));
                listuser.add(u);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser.get(0);
    }
    public User getuserId(int id){
        ArrayList<User> listuser= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/user/userid/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(user);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");

            for (Map<String, Object> obj : list) {
                User u = new User();
                u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setEnabled(Integer.parseInt(obj.get("id").toString()));
                
                u.setPassword(obj.get("password").toString());
                u.setConfirmation_token(obj.get("confirmation_token").toString());
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setAddresse(obj.get("addresse").toString());
                u.setTelephone(Integer.parseInt(obj.get("id").toString()));
                listuser.add(u);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser.get(0);
    }
    
}
