/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class Produits {
    private int reference;
    private String nom,categorie,description;
    private float prix;
    private int nbProduits;

    public Produits(int reference, String nom, String categorie, String description, float prix, int nbProduits ) {
        this.reference = reference;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.nbProduits = nbProduits;
    }

    public Produits() {
        this.reference = 0;
        this.nom = "";
        this.categorie = "";
        this.description = "";
        this.prix = 0;
        this.nbProduits = 0;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference= reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

      
    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix= prix;
    }
    
   
    public int getNbProduits() {
        return nbProduits;
    }

    public void setNbProduits(int nbProduit) {
        this.nbProduits= nbProduit;
    }    
    
    @Override
    public String toString() {
        return "Ref=" + reference+ ", nom=" + nom + ", Description=" + description + ", Categorie=" + categorie + ", Prix=" + prix + ", Quantite=" + nbProduits ;
    }
    
}
