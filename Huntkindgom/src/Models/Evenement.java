/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Mourad
 */
public class Evenement {
    
    private int idEvent; //ID de l'evenement
    private String Titre;
    private String Organisateur;
    private String Lieu;
    private String Description;
    private String Type; //
    private int nbrPart;
    private String Date;


    public Evenement() {
     
        this.Titre = "";
        this.Organisateur = "";
        this.Lieu = "";
        this.Description = "";
        this.Type="";
        this.nbrPart =0;
        this.Date ="";
        
    }
    
    public Evenement(
            
            String Titre,
            String Organisateur,
            String Lieu,
            String Description,
            String Type,
            int nbrPart,
            String Date) {     
        
        this.Titre = Titre;
        this.Organisateur=Organisateur;
        this.Lieu=Lieu;
        this.Description=Description;
        this.Type=Type;
        this.nbrPart=nbrPart;
        this.Date=Date;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public String getTitre() {
        return Titre;
    }

    public String getOrganisateur() {
        return Organisateur;
    }

    public String getLieu() {
        return Lieu;
    }

    public String getDescription() {
        return Description;
    }

    public String getType() {
        return Type;
    }

    public int getNbrPart() {
        return nbrPart;
    }

    public String getDate() {
        return Date;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setOrganisateur(String Organisateur) {
        this.Organisateur = Organisateur;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setNbrPart(int nbrPart) {
        this.nbrPart = nbrPart;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    
    
    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", Titre=" + 
                Titre + ", Organisateur=" + Organisateur + ", Lieu=" + Lieu + ", Description=" + Description + ", Type=" + Type + ", nbrPart=" + nbrPart + ", Date=" + Date;
    }   

    

  
}