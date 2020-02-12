/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.ProduitNeuf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;
import java.sql.JDBCType;


public class ProduitNeufService {
    
    Connection cnx = DataSource.getInstance().getCnx(); 
    public void ajouterProduitNeuf(ProduitNeuf P) throws SQLException{
       
        int insIdProdNeuf=P.getIdProdNeuf();
        int insReference=P.getReference();
        String insNom=P.getNom();
        String insDescription=P.getDescription();
        String insCategorie=P.getCategorie();
        float insPrix=P.getPrix();
        int insNbProduits=P.getNbProduits();
      
        String requete = "insert into produitneuf (idProdNeuf,reference,nom,description,categorie,prix,nbProduits) values ('"+insIdProdNeuf+"','"+insReference+"','"+insNom+"','"+insDescription +"','"+insCategorie +"','"+insPrix +"','"+ insNbProduits+ "')";
      
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void SupprimerProduitNeuf(int id) throws SQLException{
                   
        String requete = "DELETE FROM produitneuf WHERE idProdNeuf="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public ProduitNeuf afficherProduit(int id) throws SQLException{
       
          ProduitNeuf E = new ProduitNeuf();  
        String requete = "SELECT * FROM produitneuf WHERE idProdNeuf="+id;    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                  
          }               
           
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return E;
    }
     
    public List<ProduitNeuf> afficherListeProduitNeuf() throws SQLException{
       
          List<ProduitNeuf> ListProduit = new ArrayList<>();  
        String requete = "SELECT * FROM produitneuf";    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      ProduitNeuf E = new ProduitNeuf();
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                                   
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
   /*
    public void ajouterPersonne2(Evenement E) throws SQLException {
        String requete = "INSERT into Evenement (idEvent,Titre,Organisateur,Lieu,Description,Type,nbrPart,Date) values " + " ('"+E.getIdEvent()+"' , '"+E.getTitre()+"' , '"+E.getOrganisateur()+"', '"+E.getLieu()+"' , '"+E.getDescription()+"' , '"+E.getType()+"' , '"+E.getNbrPart()+"' , '"+E.getDate()+"' , ')";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,E.getIdEvent());
            pst.setString(2, E.getTitre());
            pst.setString(3, E.getOrganisateur());
            pst.setString(4, E.getLieu());
            pst.setString(5, E.getDescription());
            pst.setString(6, E.getType());
            pst.setInt(7, E.getNbrPart());
            pst.setString(8, E.getDate());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   */
    
    /*
    public List<Evenement> getListPersonne(){
        List<Evenement> list = new ArrayList<>();
        String requete = "SELECT * from Evenement";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Evenement  = new Evenement();
                p.getIdEvent(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString("prenom"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        * **/
    public void modifierProduitNeuf (int idProdNeuf,int reference,String nom,String description,String categorie,float prix,int nbProduits)
     {
             String requete="UPDATE produitneuf SET reference='"+reference+"',nom='"+nom+"',description='"+description+"',categorie='"+categorie+"',prix='"+prix+"',nbProduits='"+nbProduits+"' WHERE idProdNeuf="+idProdNeuf;
         try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit bien Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    


    public List<ProduitNeuf> FiltrerListeProduitNeuf(String categorie) throws SQLException{
       
        List<ProduitNeuf> ListProduit = new ArrayList<>();  
        String requete = "SELECT * FROM produitneuf WHERE categorie=" +(char)34+categorie+(char)34;    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      ProduitNeuf E = new ProduitNeuf();
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                                   
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
    
        
    public List<ProduitNeuf> TriListeProduitNeufNomAsc() throws SQLException{
       
        List<ProduitNeuf> ListProduit = new ArrayList<>();  
        String requete = "SELECT * FROM produitneuf ORDER BY nom ASC";    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      ProduitNeuf E = new ProduitNeuf();
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                                   
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
    
    public List<ProduitNeuf> TriListeProduitNeufNomDesc() throws SQLException{
       
        List<ProduitNeuf> ListProduit = new ArrayList<>();  
        String requete = "SELECT * FROM produitneuf ORDER BY nom DESC";    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      ProduitNeuf E = new ProduitNeuf();
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                                   
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
    
    public List<ProduitNeuf> TriListeProduitNeufPrixAsc() throws SQLException{
       
        List<ProduitNeuf> ListProduit = new ArrayList<>();  
        String requete = "SELECT * FROM produitneuf ORDER BY prix ASC";    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      ProduitNeuf E = new ProduitNeuf();
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                                   
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
    
    public List<ProduitNeuf> TriListeProduitNeufPrixDesc() throws SQLException{
       
        List<ProduitNeuf> ListProduit = new ArrayList<>();  
        String requete = "SELECT * FROM produitneuf ORDER BY prix DESC";    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      ProduitNeuf E = new ProduitNeuf();
                      E.setIdProdNeuf(rs.getInt(1));
                      E.setReference(rs.getInt(2));
                      E.setNom(rs.getString(3));
                      E.setDescription(rs.getString(4));
                      E.setCategorie(rs.getString(5));
                      E.setPrix(rs.getFloat(6));
                      E.setNbProduits(rs.getInt(7));                                   
                      ListProduit.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListProduit;
    }
    
}