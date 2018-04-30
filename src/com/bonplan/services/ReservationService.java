/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Reservation;
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
public class ReservationService {
     ArrayList<Reservation> L=new ArrayList<>();
     public String web="http://localhost:1020/Our/";

    public ReservationService() {
      
    }
    public ArrayList<Reservation> MesReservations(int id) {
        ArrayList<Reservation> MesReservations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"web/app_dev.php/VoyageA/MesReservationsMobile/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> participations = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) participations.get("root");
                    for (Map<String, Object> obj : list) {
                        Reservation e = new Reservation();
                        e.setId_resv((int) Float.parseFloat(obj.get("id_reservation").toString()));
                        e.setId_user((int) Float.parseFloat(obj.get("IdInscrit").toString()));
                        VoyageService vs=new VoyageService();
                        Voyage v=vs.OneById(Integer.parseInt(obj.get("id_voyage").toString()));
                        e.setVoyage(v);
                        e.setNbr_place_resv(0);
                        MesReservations.add(e);

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return MesReservations;
    }
 public void Reserver(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = web+"web/app_dev.php/VoyageA/ReserverVoyageMobile?id_voyage=" + r.getVoyage().getId_voyage() + "&IdInscrit=" + r.getId_user() + "&nbr_place_res=" + r.getNbr_place_resv();
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 
 
 public void AnnulerReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(web+"web/app_dev.php/VoyageA/AnnulerReservationMobile/" + r.getId_resv());
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
