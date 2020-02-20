/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Produits;
import Services.ProduitsService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AddProduitController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField ReferenceField;
    @FXML
    private TextField NomField;
    @FXML
    private TextField CategorieField;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField QuantiteField;
    @FXML
    private TextField PrixField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
        ReferenceField.getScene().setRoot(root);
    }

    @FXML
    private void save(ActionEvent event) throws SQLException, IOException {
        if(!ReferenceField.getText().isEmpty() && !NomField.getText().isEmpty() && !CategorieField.getText().isEmpty() && !DescriptionField.getText().isEmpty() && !PrixField.getText().isEmpty() && !QuantiteField.getText().isEmpty()){
            ProduitsService ps = new ProduitsService();
           
            Produits p = new Produits(Integer.parseInt(ReferenceField.getText()),NomField.getText(),CategorieField.getText(),DescriptionField.getText(), (int) Float.parseFloat(PrixField.getText()),Integer.parseInt(QuantiteField.getText()));
            ps.ajouterProduit(p);
            
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
            Parent root = loader.load();
            ReferenceField.getScene().setRoot(root);
        }
    }
    
}
