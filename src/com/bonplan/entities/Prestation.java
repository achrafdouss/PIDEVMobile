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
public class Prestation {
    private int idPrestation;
    private int idDiplome;
    private int idInscrit;
    private String titre;
    private String description;
    private float salaire;
    private String lieu;
    private String dateAjout;
    private String categorie;

    public Prestation(int idPrestation, int idDiplome, int idInscrit, String titre, String description, float salaire, String lieu, String dateAjout, String categorie, boolean valide, boolean statut) {
        this.idPrestation = idPrestation;
        this.idDiplome = idDiplome;
        this.idInscrit = idInscrit;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.lieu = lieu;
        this.dateAjout = dateAjout;
        this.categorie = categorie;
    }

    public Prestation() {
    }
     /* *******************************
     TOSTRING WAKEL JAW LE5ER
     ******************************** */

    @Override
    public String toString() {
        return "Prestation{" + "idPrestation=" + idPrestation + ", idDiplome=" + idDiplome + ", idInscrit=" + idInscrit + ", titre=" + titre + ", description=" + description + ", salaire=" + salaire + ", lieu=" + lieu + ", dateAjout=" + dateAjout + ", categorie=" + categorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idPrestation;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestation other = (Prestation) obj;
        if (this.idPrestation != other.idPrestation) {
            return false;
        }
        return true;
    }
    
    
/* *******************************
     GET
     +
     SET
     ******************************** */
    public int getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        this.idDiplome = idDiplome;
    }

    public int getIdInscrit() {
        return idInscrit;
    }

    public void setIdInscrit(int idInscrit) {
        this.idInscrit = idInscrit;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
