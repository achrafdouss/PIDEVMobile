/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.io.Serializable;
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
//import javax.persistence.Table;

/**
 *
 * @author Assil
 */

public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int idMenu;
    
    private double prixMenu;
    
    private String nomMenu;
    
    private String descriptionMenu;
    
    private String photoM;
    
    private int idRestaurant;

    public Menu() {
    }

   
    public Menu(int idMenu, double prixMenu, String nomMenu, String descriptionMenu, String photoM) {
        this.idMenu = idMenu;
        this.prixMenu = prixMenu;
        this.nomMenu = nomMenu;
        this.descriptionMenu = descriptionMenu;
        this.photoM = photoM;
    }

    public Menu(String nomMenu, String descriptionMenu,double prixMenu,String photoM,int idRestaurant) {
        this.prixMenu = prixMenu;
        this.nomMenu = nomMenu;
        this.descriptionMenu = descriptionMenu;
        this.idRestaurant = idRestaurant;
        this.photoM = photoM;
    }
    
    
    
    
    

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public double getPrixMenu() {
        return prixMenu;
    }

    public void setPrixMenu(double prixMenu) {
        this.prixMenu = prixMenu;
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = nomMenu;
    }

    public String getDescriptionMenu() {
        return descriptionMenu;
    }

    public void setDescriptionMenu(String descriptionMenu) {
        this.descriptionMenu = descriptionMenu;
    }

    public String getPhotoM() {
        return photoM;
    }

    public void setPhotoM(String photoM) {
        this.photoM = photoM;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", prixMenu=" + prixMenu + ", nomMenu=" + nomMenu + ", descriptionMenu=" + descriptionMenu + ", photoM=" + photoM + ", idRestaurant=" + idRestaurant + '}';
    }

    

    
    

    
    
}
