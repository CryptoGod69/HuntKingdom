/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author kaisk
 */
public class Commande {
private int idCom,idClient,idProdNeuf;
    private String dateCommande;
    private float total;
    private int etat;

    public Commande(int idCom,int idClient,int idProdNeuf, String dateCommande, float total, int etat ) {
        this.idCom = idCom;
        this.idClient = idClient;
        this.idProdNeuf = idProdNeuf;
        
        this.dateCommande = dateCommande;
        this.total = total;
        this.etat = etat;
    }

    public Commande() {
        this.idCom = 0;
        this.idClient = 0;
        this.idProdNeuf = 0;
        this.dateCommande = "";
        this.total = 0;
        this.etat =0;
    }

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom= idCom;
    }
    
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient= idClient;
    }
    
    public int getIdProdNeuf() {
        return idProdNeuf;
    }

    public void setIdProdNeuf(int idProdNeuf) {
        this.idProdNeuf= idProdNeuf;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }
   
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total= total;
    }
    
   
    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat= etat;
    }    
    
    @Override
    public String toString() {
        return "Id commande=" + idCom+ ",Id Client=" + idClient + ", Id Produit Neuf=" + idProdNeuf + ", Date de commande=" + dateCommande + ", Total=" + total + ", Etat de la commande=" + etat ;
    }   
    
}

