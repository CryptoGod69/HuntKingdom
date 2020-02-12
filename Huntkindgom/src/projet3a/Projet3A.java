/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;

import Models.ProduitNeuf;
import Models.Commande;
import Utils.DataSource;
import Services.ProduitNeufService;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import Services.CommandeService;

/**
 *
 * @author Mourad
 */
public class Projet3A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DataSource ds = DataSource.getInstance();
        System.out.println(ds.hashCode());
        //ProduitNeuf e = new ProduitNeuf(1,"kais","kaiskasmikais","Boys",300,1,2);
        Commande c = new Commande(1,1,1,"2020-02-12",500,0);
        ProduitNeufService es = new ProduitNeufService();
        CommandeService ec = new CommandeService();
        //es.ajouterProduitNeuf(e);
        //ec.ajouterCommande(c);
        ec.modifierCommande(1,10,10,"2020-03-13",350,0);
        
        System.out.println(ec.afficherCommande(1));
        //ec.SupprimerCommande(1);
       //es.SupprimerProduitNeuf(1);
       //System.out.println(es.afficherProduit(1));
       //System.out.println(es.afficherListeProduitNeuf());
       String cat="kaiskasmikais";
       //System.out.println(es.FiltrerListeProduitNeuf("kaiskasmikais"));
       //System.out.println(es.TriListeProduitNeufNomDesc());
     //System.out.println(es.showEventList());
      //  es.modifierProduitNeuf(1,10,"Mourad Tliliii","Ariana","aaa",123,2);
      // System.out.println(e.getNom()+"--> ADDED TO EVENT LIST");
    }
  
}
