/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Legion
 */

import java.util.Date;

/**
 *
 * @author Legion
 */
public class Periodechasse {
    private int IdP,superficie;
    private String adresse,image,categorie;
    private String dateDebut,dateFin;
    public Periodechasse() {
    }

    public Periodechasse(int IdP, int superficie, String dateDebut, String dateFin, String adresse, String image, String categorie) {
     this.IdP = IdP;
     this.superficie = superficie;
     this.dateDebut = dateDebut;
     this.dateFin = dateFin;
     this.adresse = adresse;
     this.image = image;
     this.categorie = categorie;
    }
       
       
    public Periodechasse(int superficie, String dateDebut, String dateFin, String adresse, String image, String categorie) {
        this.superficie = superficie;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adresse = adresse;
        this.image = image;
        this.categorie = categorie;
    }

    public int getIdP() {
        return IdP;
    }

    public void setIdP(int IdP) {
        this.IdP = IdP;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Periodechasse{" + "IdP=" + IdP + ", superficie=" + superficie + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", adresse=" + adresse + ", image=" + image + ", categorie=" + categorie + '}';
    }

   
    
}
