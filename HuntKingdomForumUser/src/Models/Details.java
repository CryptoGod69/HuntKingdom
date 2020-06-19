/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Legion
 */
public class Details {
    String arme,article;
    int idD,periode;

    public Details() {
    }

    public Details(String arme, String article, int idD, int periode) {
        this.arme = arme;
        this.article = article;
        this.idD = idD;
        this.periode = periode;
    }

    public Details(String arme, String article, int periode) {
        this.arme = arme;
        this.article = article;
        this.periode = periode;
    }

    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    @Override
    public String toString() {
        return "Details{" + "arme=" + arme + ", article=" + article + ", idD=" + idD + ", periode=" + periode + '}';
    }
    
    
}
