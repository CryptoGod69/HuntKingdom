/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Rendezvous;
import Models.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rym
 */
public class RendezvousService {
     Connection cnx = DataSource.getInstance().getCnx();
     private ResultSet res ;
    // private PreparedStatement pst ;
     private Connection con;
     private Statement ste;
     
     public void ajouterRendezvous(Rendezvous rdv) throws SQLException{
        String requete = "insert into rendezvous (idAcheteur,Acheteur,Vendeur,numero,date,heure,lieu) values ('"+rdv.getIdAcheteur()+"','"+rdv.getAcheteur()+"','"+rdv.getVendeur()+"','"+rdv.getNumero()+"','"+rdv.getDate()+"','"+rdv.getHeure()+"','"+rdv.getLieu()+"')";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
     /*public void Participer(int idE, int idU){
        if(!partExist(idE,idU)){
        String requete = "INSERT into Participation(idEvent,idUser)values ('"+idE+"','"+idU+"')";
        try{
        Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Participation ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        incrementCmpPart(idE);
        }else{
            System.out.println("Vous participez deja à l'evenement");
        }
    } */
     public ObservableList<Rendezvous> getListRendezvous(){
        ObservableList<Rendezvous> liste = FXCollections.observableArrayList();
        String requete = "select * from rendezvous ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                
                Rendezvous rdv = new Rendezvous();
                
                rdv.setAcheteur(rs.getString("Acheteur"));
                rdv.setVendeur(rs.getString("Vendeur"));
                rdv.setNumero(rs.getInt("numero"));
                 rdv.setDate(rs.getDate("date").toLocalDate());
                 rdv.setHeure(rs.getString("heure"));
                  rdv.setLieu(rs.getString("lieu"));
                liste.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
      public ObservableList<Rendezvous> getRendezvous(int id){
          
         
          
        ObservableList<Rendezvous> liste = FXCollections.observableArrayList();
        String requete = "SELECT * FROM rendezvous where idAcheteur="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                
                Rendezvous rdv = new Rendezvous();
                rdv.setIdAcheteur(rs.getInt(1));
                rdv.setAcheteur(rs.getString("Acheteur"));
                rdv.setVendeur(rs.getString("Vendeur"));
                rdv.setNumero(rs.getInt("numero"));
                 rdv.setDate(rs.getDate("date").toLocalDate());
                 rdv.setHeure(rs.getString("heure"));
                  rdv.setLieu(rs.getString("lieu"));
                liste.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
     public void delete(int ref) throws SQLException {
       String requete = "DELETE FROM rendezvous WHERE ReferenceRDV="+ref;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("rendez vous supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
   /*  public void update (String acheteur,String vendeur,int numero,String date,String heure,String lieu)
     {
             String requete="UPDATE rendezvous SET numero='"+numero+"',heure='"+heure+"',minute='"+minute+"',lieu='"+lieu+"',idVendeur='"+idVendeur+"' WHERE idVendeur="+idVendeur;
         try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("rendez vous bien modofié");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
     } */

    private boolean partExist(int idE, int idU) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
