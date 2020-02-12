/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Commande;
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

public class CommandeService {
    
    Connection cnx = DataSource.getInstance().getCnx(); 
    
    
    public void ajouterCommande(Commande C) throws SQLException{
       
        int insIdCom=C.getIdCom();
        int insIdClient=C.getIdClient();
        int IdProdNeuf=C.getIdProdNeuf();
        String insDateCommande=C.getDateCommande();
        float insTotal=C.getTotal();
        int insEtat=C.getEtat();
      
        String requete = "insert into commande (idCom,idClient,idProdNeuf,dateCommande,total,etat) values ('"+insIdCom+"','"+insIdClient+"','"+IdProdNeuf+"','"+insDateCommande+"','"+insTotal+"','"+insEtat+"')";
      
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
       public void SupprimerCommande(int id) throws SQLException{
                   
        String requete = "DELETE FROM commande WHERE idCom="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
       
       public Commande afficherCommande(int id) throws SQLException{
       
          Commande E = new Commande();  
        String requete = "SELECT * FROM commande WHERE idCom="+id;    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      E.setIdCom(rs.getInt(1));
                      E.setIdClient(rs.getInt(2));
                      E.setIdProdNeuf(rs.getInt(3));
                      E.setDateCommande(rs.getString(4));
                      E.setTotal(rs.getFloat(5));
                      E.setEtat(rs.getInt(6));                  
          }               
           
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return E;
    }
     
       
       
       
    public List<Commande> afficherListeCommande() throws SQLException{
       
          List<Commande> ListCommande = new ArrayList<>();  
        String requete = "SELECT * FROM commande";    
        try{
           Statement st = cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
                      Commande E = new Commande();
                      E.setIdCom(rs.getInt(1));
                      E.setIdClient(rs.getInt(2));
                      E.setIdProdNeuf(rs.getInt(3));
                      E.setDateCommande(rs.getString(4));
                      E.setTotal(rs.getFloat(5));
                      E.setEtat(rs.getInt(6));                                  
                      ListCommande.add(E);                      
          }                        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return ListCommande;
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
    public void modifierCommande (int idCom,int idClient,int idProdNeuf,String dateCommande,float total,int etat)
     {
             String requete="UPDATE commande SET idClient='"+idClient+"',idProdNeuf='"+idProdNeuf+"',dateCommande='"+dateCommande+"',total='"+total+"',etat='"+etat+"' WHERE idCom="+idCom;
         try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Commande bien Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     }   
}
