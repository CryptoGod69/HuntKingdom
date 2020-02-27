/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;


import Models.Produits;
import Models.Rendezvous;
import Models.User;


import Utils.DataSource;

import Services.ProduitsService;
import Services.RendezvousService;
import java.sql.Date;


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
        
       Produits prod = new Produits ("mourad", "pr", "pr","",220,20);
       Produits prod2 = new Produits ("muftox", "produit", "prod","",220,20);
      
        ProduitsService prods = new ProduitsService();
       
       //prods.ajouterProduit(prod);
      // prods.ajouterProduit(prod2);
      // prods.delete(11);
      
       // prods.update(prod,25);
       //prods.update (2,"produit","produit","",50,50);
       
        System.out.println(prods.getListProduits());

        prods.rechercheById(1);
      prods.rechercheByNom("muftox");
     // System.out.println(prods.getTrier());
        //System.out.println(prods.getTrier());
        
       /* Rendezvous r1 = new Rendezvous ("24/02/2020",14,30,"carthage",20,25);
        Rendezvous r2 = new Rendezvous ("02/02/2020",17,00,"carthage",20,25);
        RendezvousService rdv = new RendezvousService();
        rdv.ajouterRendezvous(r1);
        rdv.ajouterRendezvous(r2);
        System.out.println(rdv.getListRendezvous());
        rdv.update(20,"24/02/2020",14,00,"carthage",25); */
        User user = new User(759,"muftox", "mufti", "1 Rue de la paix", "ahmed.mouhamed@gmail.com", "71123123", "Admin" , new Date(30,12,1998));
      //   Rendezvous r1 = new Rendezvous (759,"nom","nomV",22502355,"2020-05-20","20h30","carthage");
      //  Rendezvous r2 = new Rendezvous ("02/02/2020",17,00,"carthage",20,25);
        RendezvousService rdv = new RendezvousService();
     //   rdv.ajouterRendezvous(r1);
        rdv.delete(2);
      //  rdv.ajouterRendezvous(r2);
      //System.out.println(rdv.getRendezvous(user.getNomUser()));
        System.out.println(rdv.getListRendezvous());
        System.out.println(rdv.getRendezvous(user.getIdUser()));
        //rdv.update(20,"24/02/2020",14,00,"carthage",25);  
        
    }
    
}
