/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author bouyo
 */
public class CommandeProduit {

    private int idCommandeProduit;
    private Produit idproduit;
    private User idAcheteur;
    private int quantiteCommandeProduit;

   
     public CommandeProduit(User idAcheteur,Produit idproduit, int quantiteCommandeProduit) {
        this.idproduit = idproduit;
        this.idAcheteur = idAcheteur;
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }
      public CommandeProduit(Produit idproduit, int quantiteCommandeProduit) {
        this.idproduit = idproduit;
       // this.idAcheteur = idAcheteur;
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }

    public int getIdCommandeProduit() {
        return idCommandeProduit;
    }

    public void setIdCommandeProduit(int idCommandeProduit) {
        this.idCommandeProduit = idCommandeProduit;
    }

    public Produit getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Produit idproduit) {
        this.idproduit = idproduit;
    }
     public User getId_user() {
        return idAcheteur;
    }

    public void setId_user(User id_user) {
        this.idAcheteur = id_user;
    }
    public User getIdAcheteur() {
        return idAcheteur;
    }

    public void setIdAcheteur(User idAcheteur) {
        this.idAcheteur = idAcheteur;
    }

    public int getQuantiteCommandeProduit() {
        return quantiteCommandeProduit;
    }

    public void setQuantiteCommandeProduit(int quantiteCommandeProduit) {
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommandeProduit=" + idCommandeProduit + ", idproduit=" + idproduit + ", idAcheteur=" + idAcheteur + ", quantiteCommandeProduit=" + quantiteCommandeProduit + '}';
    }

    
   

   

    

}
