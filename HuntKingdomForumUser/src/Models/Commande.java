/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author kaisk
 */
public class Commande {
    private int idCom,etat,quantite,phone;
    private String dateCommande;
    private String adresse;
    private ProduitNeuf produitn;
    private User usern;
    public Commande() {
    }
    private float total;

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    

    public ProduitNeuf getProduitn() {
        return produitn;
    }

    public void setProduitn(ProduitNeuf produitn) {
        this.produitn = produitn;
    }


    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idCom;
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
        final Commande other = (Commande) obj;
        if (this.idCom != other.idCom) {
            return false;
        }
        return true;
    }

    public User getUsern() {
        return usern;
    }

    public void setUsern(User usern) {
        this.usern = usern;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCom=" + idCom + ", etat=" + etat + ", quantite=" + quantite + ", phone=" + phone + ", dateCommande=" + dateCommande + ", adresse=" + adresse + ", produitn=" + produitn + ", usern=" + usern + ", total=" + total + '}';
    }  
}
