/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author Achraf
 */
public class Commentaire {
    public int id_com;
    public int id_owner;
    public int id_rec;
    public String contenu;
    public double note;
    public static Commentaire commentaire;

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Commentaire(int id_com, int id_owner, int id_rec, String contenu, double note) {
        this.id_com = id_com;
        this.id_owner = id_owner;
        this.id_rec = id_rec;
        this.contenu = contenu;
        this.note = note;
    }

    public Commentaire() {
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public Commentaire(int id_owner, int id_rec, String contenu, double note) {
        this.id_owner = id_owner;
        this.id_rec = id_rec;
        this.contenu = contenu;
        this.note = note;
    }

   
    
}
