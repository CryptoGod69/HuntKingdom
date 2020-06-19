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
public class ProductOcc {
    private int id,nbVue;
    private String Proprietaire,Nom,Categorie,Description,image,etat,dateachat,prix,prodLikes;
    //float prix;

    public ProductOcc(int id, int nbVue,String prodLikes, String Proprietaire, String Nom, String Categorie, String Description, String image, String etat, String dateachat, String prix) {
        this.id = id;
        this.nbVue = nbVue;
        this.prodLikes=prodLikes;
        this.Proprietaire = Proprietaire;
        this.Nom = Nom;
        this.Categorie = Categorie;
        this.Description = Description;
        this.image = image;
        this.etat = etat;
        this.dateachat = dateachat;
        this.prix = prix;
    }
    public ProductOcc(int nbVue,String prodLikes, String Proprietaire, String Nom, String Categorie, String Description, String image, String etat, String dateachat, String prix) {
        this.nbVue = nbVue;
        this.prodLikes=prodLikes;
        this.Proprietaire = Proprietaire;
        this.Nom = Nom;
        this.Categorie = Categorie;
        this.Description = Description;
        this.image = image;
        this.etat = etat;
        this.dateachat = dateachat;
        this.prix = prix;
    }

    public String getProdLikes() {
        return prodLikes;
    }

    public void setProdLikes(String prodLikes) {
        this.prodLikes = prodLikes;
    }
public ProductOcc ()
{
    
}
    public int getId() {
        return id;
    }

    public int getNbVue() {
        return nbVue;
    }

    public String getProprietaire() {
        return Proprietaire;
    }

    public String getNom() {
        return Nom;
    }

    public String getCategorie() {
        return Categorie;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return image;
    }

    public String getEtat() {
        return etat;
    }

    public String getDateachat() {
        return dateachat;
    }

    public String getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbVue(int nbVue) {
        this.nbVue = nbVue;
    }

    public void setProprietaire(String Proprietaire) {
        this.Proprietaire = Proprietaire;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDateachat(String dateachat) {
        this.dateachat = dateachat;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ProductOcc{" + "id=" + id + ", nbVue=" + nbVue + ", Proprietaire=" + Proprietaire + ", Nom=" + Nom + ", Categorie=" + Categorie + ", Description=" + Description + ", image=" + image + ", etat=" + etat + ", dateachat=" + dateachat + ", prix=" + prix + '}';
    }
    
    
}
