/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Produits;
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


public class ProduitsService {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouterPersonne(Produits p) throws SQLException{
        String requete = "insert into produits (reference,nom,categorie,description,prix,nbProduits) values ('"+p.getReference()+"','"+p.getNom()+"','"+p.getCategorie() +"','"+p.getDescription() +"','"+p.getPrix() +"','"+ p.getNbProduits()+ "')";                                                                                       
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Produits> getListPersonne(){
        List<Produits> list = new ArrayList<>();
        String requete = "select * from produits";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Produits p = new Produits();
                p.setReference(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setCategorie(rs.getString("categorie"));
                p.setDescription(rs.getString("description"));
                p.setPrix(rs.getFloat("prix"));
                p.setNbProduits(rs.getInt("nbProduits"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
