/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Feryel
 */
public class Produits {
    private int reference;
    private String nom,categorie,description;
    private float prix;
    private int nbProduits;
    
    public Produits()
    {
        this.reference=0;
        this.nom="";
        this.categorie="";
        this.description="";
        this.prix=0;
        this.nbProduits=0;
    }
    public Produits(int ref,String nom,String categorie,String description,float prix,int nbProduit)
    {
        this.reference=ref;
        this.nom=nom;
        this.categorie=categorie;
        this.description=description;
        this.prix=prix;
        this.nbProduits=nbProduit;
    }

    
    public int getRef (){
        return reference;
    }
    public String getNom (){
        return nom;
    }
    
    public String getCategorie()
    {
        return categorie;
    }

    public String getDescription() {
        return description;
    }
    
    public float getPrix (){
        return prix;
    }
    public int getnb_produit (){
        return nbProduits;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    

    public void setNb_produit(int nb_produit) {
        this.nbProduits = nb_produit;
    }

    @Override
    public String toString() {
        return "Produits{" + "reference=" + reference + ", nom=" + nom + ", categorie=" + categorie + ", prix=" + prix + ", nbProduits=" + nbProduits + '}';
    }

    
    
}
