/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
    

import java.util.Date;

/**
 *
 * @author Legion
 */
public class Article {
    private int IdA,nbLikes=0,nbSignal=0;
    private String categorie,titre,sousTitre,chapeau,texte,image;
    private String date;

    public Article() {
    }

    public Article(int IdA, String categorie, String titre, String sousTitre, String chapeau, String texte, String image, String date) {
        this.IdA = IdA;
        this.categorie = categorie;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.chapeau = chapeau;
        this.texte = texte;
        this.image = image;
        this.date = date;
    }

    public Article(String categorie, String titre, String sousTitre, String chapeau, String texte, String image, String date) {
        this.categorie = categorie;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.chapeau = chapeau;
        this.texte = texte;
        this.image = image;
        this.date = date;
    }
       
   
    public int getIdA() {
        return IdA;
    }

    public void setIdA(int IdA) {
        this.IdA = IdA;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getChapeau() {
        return chapeau;
    }

    public void setChapeau(String chapeau) {
        this.chapeau = chapeau;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" + "IdA=" + IdA + ", nbLikes=" + nbLikes + ", nbSignal=" + nbSignal + ", categorie=" + categorie + ", titre=" + titre + ", sousTitre=" + sousTitre + ", chapeau=" + chapeau + ", texte=" + texte + ", image=" + image + ", date=" + date + '}';
    }

      

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
    }
    
    
}

