/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;


/**
 *
 * @author dell
 */
public class CommentairePrestation {
    private int id_commentaire;
    private int id_prestation;
    private int id_user;
    private String contenu;
    private int signalisation;
    private String date_ajout;
    private String username;

    public CommentairePrestation(int id_commentaire, int id_prestation, int id_user, String contenu, int signalisation, String date_ajout) {
        this.id_commentaire = id_commentaire;
        this.id_prestation = id_prestation;
        this.id_user = id_user;
        this.contenu = contenu;
        this.signalisation = signalisation;
        this.date_ajout = date_ajout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CommentairePrestation{" + "id_commentaire=" + id_commentaire + ", id_prestation=" + id_prestation + ", id_user=" + id_user + ", contenu=" + contenu + ", signalisation=" + signalisation + ", date_ajout=" + date_ajout + '}';
    }
    

    public CommentairePrestation() {
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getId_prestation() {
        return id_prestation;
    }

    public void setId_prestation(int id_prestation) {
        this.id_prestation = id_prestation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getSignalisation() {
        return signalisation;
    }

    public void setSignalisation(int signalisation) {
        this.signalisation = signalisation;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }
    
    
}
