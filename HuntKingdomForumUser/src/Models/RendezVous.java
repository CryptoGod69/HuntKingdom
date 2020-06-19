/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Rym
 */
public class RendezVous {
    int id;
    String acheteur,vendeur,date,heure,lieu;

    public RendezVous(int id, String acheteur, String vendeur, String date, String heure, String lieu) {
        this.id = id;
        this.acheteur = acheteur;
        this.vendeur = vendeur;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
    }

    public RendezVous(String acheteur, String vendeur, String date, String heure, String lieu) {
        this.acheteur = acheteur;
        this.vendeur = vendeur;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
    }
    public RendezVous ()
    {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", acheteur=" + acheteur + ", vendeur=" + vendeur + ", date=" + date + ", heure=" + heure + ", lieu=" + lieu + '}';
    }
    
    
    
}
