/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.io.Serializable;
import java.util.Collection;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;

/**
 *
 * @author Assil
 */

public class Restaurant /*implements Serializable*/ {

    //private static final long serialVersionUID = 1L;
   
    private int idRestaurant;
    
    private String nomRestaurant;
    
    private int telephoneRestaurant;
    
    private String adresseRestaurant;
    
    private String emailRestaurant;
    
    private String specialiteRestaurant;
    
    private String photo;
    
    private User idOwner;
    
    private Collection<Menu> menuCollection;

    public Restaurant() {
    }

    

    public Restaurant(String nomRestaurant, int telephoneRestaurant, String adresseRestaurant, String emailRestaurant, String specialiteRestaurant, String photo) {
        
        this.nomRestaurant = nomRestaurant;
        this.telephoneRestaurant = telephoneRestaurant;
        this.adresseRestaurant = adresseRestaurant;
        this.emailRestaurant = emailRestaurant;
        this.specialiteRestaurant = specialiteRestaurant;
        this.photo = photo;
    }

    

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public int getTelephoneRestaurant() {
        return telephoneRestaurant;
    }

    public void setTelephoneRestaurant(int telephoneRestaurant) {
        this.telephoneRestaurant = telephoneRestaurant;
    }

    public String getAdresseRestaurant() {
        return adresseRestaurant;
    }

    public void setAdresseRestaurant(String adresseRestaurant) {
        this.adresseRestaurant = adresseRestaurant;
    }

    public String getEmailRestaurant() {
        return emailRestaurant;
    }

    public void setEmailRestaurant(String emailRestaurant) {
        this.emailRestaurant = emailRestaurant;
    }

    public String getSpecialiteRestaurant() {
        return specialiteRestaurant;
    }

    public void setSpecialiteRestaurant(String specialiteRestaurant) {
        this.specialiteRestaurant = specialiteRestaurant;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(User idOwner) {
        this.idOwner = idOwner;
    }

   

    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    

   

    @Override
    public String toString() {
        return "Restaurant{" + "idRestaurant=" + idRestaurant + ", nomRestaurant=" + nomRestaurant + ", telephoneRestaurant=" + telephoneRestaurant + ", adresseRestaurant=" + adresseRestaurant + ", emailRestaurant=" + emailRestaurant + ", specialiteRestaurant=" + specialiteRestaurant + ", photo=" + photo + ", idOwner=" + idOwner + ", menuCollection=" + menuCollection + '}';
    }

    

    
    
    
    
    
    
    
    
}
