/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.service;

import java.util.List;
import com.bonplan.entities.Evenement;
import com.bonplan.entities.Participation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author souab
 */
public class EvenementService {
    public static String web="http://127.0.0.1/html/";
    public static int iduser = 1;
    public static String status(int i)
    {
        if (i==1) {
            return "Confirmed";
        } 
              return "Pending";  
    }

    public void ajoutParticipation(Participation ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = web+"PIDEV/web/app_dev.php/event/api/participer?IdEvent=" + ta.getId_event() + "&IdInscrit=" + ta.getId_inscrit() + "&nbre=" + ta.getNbre();
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Evenement> getListTask(String json) {

        ArrayList<Evenement> listEvent = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Events);

            List<Map<String, Object>> list = (List<Map<String, Object>>) Events.get("root");

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id_event").toString());
                System.out.println(id);
                e.setId((int) id);
                e.setCategorie(obj.get("categorie").toString());
                e.setNomEvenement(obj.get("nomEvenement").toString());
                e.setNbrplace((int) Float.parseFloat(obj.get("nbrplace").toString()));
                e.setDate_evenement(obj.get("dateEvenement").toString().substring(6, 16));
                e.setLieu(obj.get("lieu").toString());
                e.setDescription(obj.get("description").toString());
                e.setPhoto(obj.get("photo").toString());
                e.setLat(obj.get("lat").toString());
                e.setLng(obj.get("lng").toString());
                e.setId_owner((int) Float.parseFloat(obj.get("id_owner").toString()));
                e.setPrix((int) Float.parseFloat(obj.get("prix").toString()));

                System.out.println(e);
                listEvent.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }

    public static ArrayList<Evenement> listTasks;

    public ArrayList<Evenement> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"PIDEV/web/app_dev.php/event/api/findall");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    static ArrayList<Evenement> listTasks2;

    public ArrayList<Evenement> getListMy() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"PIDEV/web/app_dev.php/event/api/findOne/" + EvenementService.iduser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                listTasks2 = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks2;
    }

    public ArrayList<Participation> MesParticipations(int id) {
        ArrayList<Participation> MesParticipations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"PIDEV/web/app_dev.php/event/api/MesParticipations/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> participations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) participations.get("root");
                    for (Map<String, Object> obj : list) {
                        Participation e = new Participation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        e.setId((int) id);
                        e.setId_event((int) Float.parseFloat(obj.get("id_event").toString()));
                        e.setId_inscrit((int) Float.parseFloat(obj.get("id_inscrit").toString()));
                        e.setDate(obj.get("date").toString().substring(6, 16));
                        e.setStatus((int) Float.parseFloat(obj.get("status").toString()));
                        e.setNbre((int) Float.parseFloat(obj.get("nbre").toString()));
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

    public ArrayList<Participation> EventParticipations(int id) {
        ArrayList<Participation> MesParticipations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"PIDEV/web/app_dev.php/event/api/EventParticipations/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> participations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) participations.get("root");
                    for (Map<String, Object> obj : list) {
                        Participation e = new Participation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        e.setId((int) id);
                        e.setId_event((int) Float.parseFloat(obj.get("id_event").toString()));
                        e.setId_inscrit((int) Float.parseFloat(obj.get("id_inscrit").toString()));
                        e.setDate(obj.get("date").toString().substring(6, 16));
                        e.setStatus((int) Float.parseFloat(obj.get("status").toString()));
                        e.setNbre((int) Float.parseFloat(obj.get("nbre").toString()));
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

    public void annuler(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"PIDEV/web/app_dev.php/event/api/annuler/" + id);
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    public void confirmer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"PIDEV/web/app_dev.php/event/api/confirmer/" + id);
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
