/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Omar
 */
public class Evenement {

    private int idEvent;
    private String Titre;
    private String Organisateur;
    private String Lieu;
    private String Description;
    private String Type;
    private int NbrPart;
    private Date Date;
    private Date DateFin;
    private int CmpPart;
    private String Image; 

   

    public Evenement() {
    }

    public Evenement(String Titre,String Organisateur, String Lieu, String Description, String Type, int NbrPart, Date Date, Date DateFin, int CmpPart) {
        this.Titre = Titre;
        this.Organisateur=Organisateur;
        this.Lieu = Lieu;
        this.Description = Description;
        this.Type = Type;
        this.NbrPart = NbrPart;
        this.Date = Date;
        this.DateFin = DateFin;
        this.CmpPart = CmpPart;
    }
 
    
    public String getOrganisateur() {
        return Organisateur;
    }

    public void setOrganisateur(String Organisateur) {
        this.Organisateur = Organisateur;
    }
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getNbrPart() {
        return NbrPart;
    }

    public void setNbrPart(int NbrPart) {
        this.NbrPart = NbrPart;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public int getCmpPart() {
        return CmpPart;
    }

    public void setCmpPart(int CmpPart) {
        this.CmpPart = CmpPart;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idEvent;
        hash = 29 * hash + Objects.hashCode(this.Titre);
        hash = 29 * hash + Objects.hashCode(this.Lieu);
        hash = 29 * hash + Objects.hashCode(this.Description);
        hash = 29 * hash + Objects.hashCode(this.Type);
        hash = 29 * hash + this.NbrPart;
        hash = 29 * hash + Objects.hashCode(this.Date);
        hash = 29 * hash + Objects.hashCode(this.DateFin);
        hash = 29 * hash + this.CmpPart;
        hash = 29 * hash + Objects.hashCode(this.Image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.NbrPart != other.NbrPart) {
            return false;
        }
        if (this.CmpPart != other.CmpPart) {
            return false;
        }
        if (!Objects.equals(this.Titre, other.Titre)) {
            return false;
        }
        if (!Objects.equals(this.Lieu, other.Lieu)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        if (!Objects.equals(this.DateFin, other.DateFin)) {
            return false;
        }
        if (!Objects.equals(this.Image, other.Image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", Titre=" + Titre + ", Organisateur=" + Organisateur + ", Lieu=" + Lieu + ", Description=" + Description + ", Type=" + Type + ", NbrPart=" + NbrPart + ", Date=" + Date + ", DateFin=" + DateFin + ", CmpPart=" + CmpPart + ", Image=" + Image + '}';
    }

    

  

   
}
