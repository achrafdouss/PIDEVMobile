/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;



import java.util.Date;

/**
 *
 * @author Radhouen
 */
public class Voyage  {
    
    
    public int id_voyage;
    public String categorie;
   public String Type;
   public int nbr_place;
   public String date_dep;
   public String date_arr;
  public  float prix;
   public String description;
  public  String destination;
    public static int id_vModifier;
  public  String photo;
  public  int id_owner;
  public static Voyage voyage;

    
    public Voyage()
    {}

    public Voyage(int id_voyage, String categorie, String Type, int nbr_place, String date_dep, String date_arr, float prix, String description, String destination, String photo, int id_owner) {
        this.id_voyage = id_voyage;
        this.categorie = categorie;
        this.Type = Type;
        this.nbr_place = nbr_place;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        this.description = description;
        this.destination = destination;
        this.photo = photo;
        this.id_owner = id_owner;
    }

    
  
    

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
   
    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(String date_dep) {
        this.date_dep = date_dep;
    }

    public String getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(String date_arr) {
        this.date_arr = date_arr;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id_voyage=" + id_voyage + ", categorie=" + categorie + ", Type=" + Type + ", nbr_place=" + nbr_place + ", date_dep=" + date_dep + ", date_arr=" + date_arr + ", prix=" + prix + ", description=" + description + ", photo=" + photo + ", id_owner=" + id_owner + '}';
    }
    
  
    
    
}
