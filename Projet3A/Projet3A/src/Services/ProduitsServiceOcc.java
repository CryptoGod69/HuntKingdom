/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Produits;
import Models.ProduitsOcc;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rym
 */
public class ProduitsServiceOcc extends ProduitsService{
     Connection cnx = DataSource.getInstance().getCnx();
     
     public void ajouterProduit ()
     {
         
     }
    
}
