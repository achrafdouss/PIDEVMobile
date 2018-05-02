/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Restaurant;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Assil
 */
public class RestaurantService {
    
    
    public void ajoutRest(Restaurant rs) {
        ConnectionRequest con = new ConnectionRequest();
        //String Url = "http://localhost/pidev/PIDEV/web/app_dev.php/ajouter?nom=" + rs.getNomRestaurant()+"&telephoneRestaurant=" +rs.getTelephoneRestaurant()+"&adresseRestaurant=" +rs.getAdresseRestaurant()+"&emailRestaurant="+rs.getEmailRestaurant()+"&specialiteRestaurant="+rs.getSpecialiteRestaurant()+"&photo="+rs.getPhoto();
       String Url =  "http://localhost/PIDEV/web/app_dev.php/restau/ajouter?nom="+rs.getNomRestaurant() +"&tel="+rs.getTelephoneRestaurant()+"&adr="+rs.getAdresseRestaurant()+"&em="+rs.getEmailRestaurant()+"&spec="+rs.getSpecialiteRestaurant()+"&ph="+rs.getPhoto();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    
    public ArrayList<Restaurant> rechercheSkill() {
      ArrayList<Restaurant> listRestaurants = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/PIDEV/web/app_dev.php/restau/all");
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(restaurant);
                    System.out.println(restaurant.keySet());
                    System.out.println(restaurant.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
                    for (Map<String, Object> obj : list) {
                        Restaurant rest = new Restaurant();
//                        float id = Float.parseFloat(obj.get("id").toString());
                        //skill.setId((int) id);
                        
                        rest.setIdRestaurant(Integer.parseInt(obj.get("id").toString()));
                        rest.setNomRestaurant(obj.get("nom").toString());
                        rest.setEmailRestaurant(obj.get("email").toString());
                        rest.setAdresseRestaurant(obj.get("adr").toString());
                        rest.setTelephoneRestaurant(Integer.parseInt(obj.get("tel").toString()));
                        rest.setSpecialiteRestaurant(obj.get("spec").toString());
                        
                       /* 
                        int mm = Display.getInstance().convertToPixels(3);
                    rest.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(rest.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    rest.setImage(im);
                        */
                      
                        
                        listRestaurants.add(rest);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRestaurants;
    }
 
    
    
    public ArrayList<Restaurant> rechercheRestau(int id) {
      ArrayList<Restaurant> listRestaurants = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/PIDEV/web/app_dev.php/restau/recherche/"+id);
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(restaurant);
                    System.out.println(restaurant.keySet());
                    System.out.println(restaurant.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
                    for (Map<String, Object> obj : list) {
                        Restaurant rest = new Restaurant();
                    //   int id = Integer.parseInt(obj.get("tel").toString());
                      //  rest.setTelephoneRestaurant(id);
                        //skill.setId((int) id);
                        
                        //rest.setIdRestaurant(Integer.parseInt(obj.get("id").toString()));
                        
                        rest.setNomRestaurant(obj.get("nom").toString());
                        rest.setEmailRestaurant(obj.get("email").toString());
                        rest.setAdresseRestaurant(obj.get("adr").toString());
                        int  n = Integer.parseInt((String) obj.get("tel")); 
                        rest.setTelephoneRestaurant((int)n);
                        
                       
                        rest.setSpecialiteRestaurant(obj.get("spec").toString());
                        
                       /* 
                        int mm = Display.getInstance().convertToPixels(3);
                    rest.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(rest.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    rest.setImage(im);
                        */
                      
                        
                        listRestaurants.add(rest);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRestaurants;
    }
 
    public ArrayList<Restaurant> UpdateRestau(int id,String nom/*, int tel*//*, String temail*//**/) {
      ArrayList<Restaurant> listRestaurants = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/PIDEV/web/app_dev.php/restau/updateRest/"+id+"?nom="+nom);
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(restaurant);
                    System.out.println(restaurant.keySet());
                    System.out.println(restaurant.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
                    for (Map<String, Object> obj : list) {
                        Restaurant rest = new Restaurant();
//                        float id = Float.parseFloat(obj.get("id").toString());
                        //skill.setId((int) id);
                        
                        rest.setNomRestaurant(obj.get("nom").toString());
                        //rest.setEmailRestaurant(obj.get("email").toString());
                        rest.setAdresseRestaurant(obj.get("adr").toString());
                        /*int  n = Integer.parseInt((String) obj.get("tel")); 
                        rest.setTelephoneRestaurant((int)n);
                        */
                       
                        rest.setSpecialiteRestaurant(obj.get("spec").toString());
                        
                       /* 
                        int mm = Display.getInstance().convertToPixels(3);
                    rest.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(rest.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    rest.setImage(im);
                        */
                      
                        
                        listRestaurants.add(rest);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRestaurants;
    }
    
}
