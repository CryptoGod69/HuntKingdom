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
public class ProduitsOcc extends Produits{
    int IDProdOcc;
    int IDClient;
    public ProduitsOcc (int ref,String nom,String categorie,String description,float prix,int nbProduit,int IDProdOcc,int IDclient)
    {
        super(ref,nom,categorie,description,prix,nbProduit);
        this.IDClient=IDclient;
        this.IDProdOcc=IDProdOcc;
    }

    public int getIDProdOcc() {
        return IDProdOcc;
    }

    public int getIDClient() {
        return IDClient;
    }

    public void setIDProdOcc(int IDProdOcc) {
        this.IDProdOcc = IDProdOcc;
    }

    public void setIDClient(int IDClient) {
        this.IDClient = IDClient;
    }

    @Override
    public String toString() {
        return "ProduitsOcc{" + "IDProdOcc=" + IDProdOcc + ", IDClient=" + IDClient + '}';
    }
    
}
