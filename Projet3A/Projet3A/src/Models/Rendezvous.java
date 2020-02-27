/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author Rym
 */
public class Rendezvous {
    private int idAcheteur;
   private String acheteur,vendeur;
   private int numero;
   private LocalDate date;
   private String heure,lieu;
   private int ReferenceRDV;
    public Rendezvous()
    {
        this.idAcheteur=0;
        this.acheteur="";
        this.vendeur="";
        this.numero=0;
        //this.date="";
        this.heure="";
        this.lieu="";
    }
   public Rendezvous (int idAcheteur,String acheteur, String vend,int num,LocalDate date,String heure,String lieu)
   {
       this.idAcheteur=idAcheteur;
       this.acheteur=acheteur;
       this.vendeur=vend;
       this. numero=num;
       this.date=date;
       this.heure=heure;
       this.lieu=lieu;
      
   }

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public String getVendeur() {
        return vendeur;
    }

    public int getNumero() {
        return numero;
    }

    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "L'acheteur est " + acheteur + ", Le Vendeur est " + vendeur + '}';
    }

    public int getReferenceRDV() {
        return ReferenceRDV;
    }

    public void setReferenceRDV(int ReferenceRDV) {
        this.ReferenceRDV = ReferenceRDV;
    }

    
    
    
}
