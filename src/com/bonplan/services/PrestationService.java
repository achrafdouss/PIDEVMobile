/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Diplome;
import com.bonplan.entities.Prestation;
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
public class PrestationService {

    public ArrayList<Prestation> getListPrestation(String json) {
        ArrayList<Prestation> listPrestations = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Prestation p = new Prestation();
                // Diplome 
                try {
                    String diplome = obj.get("idDiplome").toString();
                    Diplome d = getDiplomefromstring(diplome);
                    p.setIdDiplome(d.getIdDiplome());
                } catch (Exception ex) {
                    p.setIdDiplome(0);
                }
                // Prestation                
                p.setIdPrestation((int) Float.parseFloat(obj.get("idPrestation").toString().trim()));
                p.setTitre(obj.get("titre").toString());
                p.setDescription(obj.get("description").toString());
                try {
                    p.setSalaire(Float.parseFloat(obj.get("salaire").toString()));
                } catch (Exception ex) {
                    p.setSalaire(0);
                }
                p.setLieu(obj.get("lieu").toString());
                p.setCategorie(obj.get("categorie").toString());
                p.setDateAjout(obj.get("dateAjout").toString());
                //System.out.println(p.toString());
                listPrestations.add(p);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println(listPrestations);
        return listPrestations;
    }
    ArrayList<Prestation> listPrestations = new ArrayList<>();

    public ArrayList<Prestation> findall() {
        ConnectionRequest con = new ConnectionRequest();
        NetworkManager.getInstance().updateThreadCount(2);
        con.setUrl("http://localhost/untitled/web/app_dev.php/prestation/getallmobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PrestationService ser = new PrestationService();
                listPrestations = ser.getListPrestation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPrestations;
    }

    private Diplome getDiplomefromstring(String diplome) throws Exception {

        Diplome d = new Diplome();
        int idDiplome = (int) Float.parseFloat(diplome.substring(diplome.indexOf("idDiplome=") + 10, diplome.indexOf(", categorie")));
        String categorie = diplome.substring(diplome.indexOf("categoriediplome=") + 17, diplome.indexOf(", type"));
        String type = diplome.substring(diplome.indexOf("type=") + 5, diplome.indexOf(", etablissement"));
        String etablissement = diplome.substring(diplome.indexOf("etablissement=") + 14, diplome.indexOf(", dateObtention"));
        int date = (int) Float.parseFloat(diplome.substring(diplome.indexOf("dateObtention") + 14, diplome.indexOf(", __isInitialized__")));
        d.setIdDiplome(idDiplome);
        d.setAnnee(date);
        d.setCategorie(categorie);
        d.setType(type);
        d.setEtablissement(etablissement);
        return d;
    }

    public ArrayList<Prestation> find(String criteria) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/untitled/web/app_dev.php/prestation/findbyx/" + criteria);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PrestationService ser = new PrestationService();
                listPrestations = ser.getListPrestation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPrestations;
    }
}
