/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;


import Models.Produits;

import Utils.DataSource;

import Services.ProduitsService;

import java.sql.SQLException;

/**
 *
 * @author Omar
 */
public class Projet3A {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        DataSource ds = DataSource.getInstance();
        System.out.println(ds.hashCode());
        
       Produits prod = new Produits (13, "pr", "pr","",220,20);
       Produits prod2 = new Produits (14, "produit", "prod","",220,20);
      
        ProduitsService prods = new ProduitsService();
       
      // prods.ajouterProduit(prod);
      // prods.ajouterProduit(prod2);
       prods.delete(11);
        prods.rechercheById(12);
        prods.rechercheByNom("produit");
       // prods.update(prod,25);
      prods.update (2,"produit","produit","",50,50);
       
        System.out.println(prods.getListProduits());
      System.out.println(prods.getTrier());
        //System.out.println(prods.getTrier());
        
       /* RendezVous r1 = new RendezVous (2,"24/02/2020",14,30,"true");
        RendezVousService rdv = new RendezVousService();
        rdv.ajouterProduit(r1);
        System.out.println(rdv.getListRendezVous()); */
        
        
    }
    
}
