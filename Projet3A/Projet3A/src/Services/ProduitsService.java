/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

//import Models.Personne;

import Models.Produits;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Feryel
 */
public class ProduitsService {
    Connection cnx = DataSource.getInstance().getCnx();
     private ResultSet res ;
    // private PreparedStatement pst ;
     private Connection con;
     private Statement ste;
     
     public void ajouterProduit(Produits prod) throws SQLException{
        String requete = "insert into produits (reference,nom,categorie,description,prix,nbProduits) values ('"+prod.getRef()+"','"+prod.getNom()+"','"+prod.getCategorie()+"','"+prod.getDescription()+"','"+prod.getPrix()+"','"+prod.getnb_produit()+"')";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
     public ObservableList<Produits> getListProduits(){
        ObservableList<Produits> liste = FXCollections.observableArrayList();
        String requete = "select * from produits";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Produits p = new Produits();
                p.setReference(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setCategorie(rs.getString(3));
                p.setDescription(rs.getString(4));
                 p.setPrix(rs.getFloat(5));
                 p.setNb_produit(rs.getInt(6));
                liste.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
     public void delete(int ref) throws SQLException {
       String requete = "DELETE FROM produits WHERE reference="+ref;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public ObservableList<Produits> rechercheById(int ref){
        ObservableList<Produits> liste = FXCollections.observableArrayList();
        String requete = "select * from produits where reference="+ref;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Produits p = new Produits();
                p.setReference(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setCategorie(rs.getString("categorie"));
                p.setDescription(rs.getString("description"));
                 p.setPrix(rs.getFloat(1));
                 p.setNb_produit(rs.getInt(1));
                liste.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    /* public void rechercheById (int ref)
     {
        String requete = "SELECT * FROM produits WHERE reference="+ref;
        try{
            Statement st = cnx.createStatement();
            res = st.executeQuery(requete);
            res.last();
            int nbrRow =res.getRow();
            if (nbrRow!=0)
            {
                System.out.println("Produit trouvé");
            }
            else
            {
                System.out.println("Produit non trouvé");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } 
     } */
    
     public void update (int ref,String nom,String categorie,String desc,int prix,int nb_prod)
     {
             String requete="UPDATE produits SET nom='"+nom+"',categorie='"+categorie+"',description='"+desc+"',prix='"+prix+"',nbProduits='"+nb_prod+"' WHERE reference="+ref;
         try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit bien modofié");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void rechercheByNom (String nom)
     {
        String requete = "SELECT * FROM produits WHERE nom='"+nom+"'";
        try{
            Statement st = cnx.createStatement();
            res = st.executeQuery(requete);
            res.last();
            int nbrRow =res.getRow();
            if (nbrRow!=0)
            {
                System.out.println("Produit trouvé");
            }
            else
            {
                System.out.println("Produit non trouvé");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
    
    /* public void update (int ref,String nom,String categorie,String desc,int prix,int nb_prod)
     {
             String requete="UPDATE produits SET nom='"+nom+"',categorie='"+categorie+"',description='"+desc+"',prix='"+prix+"',nbProduits='"+nb_prod+"' WHERE reference="+ref;
         try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit bien modofié");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     } */

 /*public List<Produits> getTrier() throws SQLException {
    List<Produits> arr=new ArrayList<>();
    ResultSet rs=ste.executeQuery("select * from produits ORDER BY reference DESC");
    ste=con.createStatement();
    
     while (rs.next()) {                
               int reference=rs.getInt(1);
               String nom=rs.getString("nom");
               String categorie=rs.getString(3);
               String description=rs.getString("description");
               int prix=rs.getInt(4);
               int nb_p=rs.getInt(2);
               Produits p=new Produits(reference, nom, categorie,description, prix,nb_p);
     arr.add(p);
     }
    return arr;
    }*/
     public List<Produits> getTrier() throws SQLException {
    List<Produits> arr=new ArrayList<>();
    String requete="select * from produits ORDER BY reference DESC";
    try{
            Statement st = cnx.createStatement();
            res = st.executeQuery(requete);
            Produits p;
            while (res.next())
            {
                p = new Produits (
                        res.getInt("reference"),
                        res.getString("nom"),
                        res.getString("categorie"),
                        res.getString("description"),
                        res.getFloat("prix"),
                        res.getInt("nbProduits")
                );
              arr.add(p);
            }
            
    }
    catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } 
     return arr;
}
}