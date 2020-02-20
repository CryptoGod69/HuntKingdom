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
public class EditProduitController implements Initializable {

    @FXML
     TextField ReferenceField;
    @FXML
     TextField NomField;
    @FXML
     TextField CategorieField;
    @FXML
     TextField DescriptionField;
    @FXML
     TextField PrixField;
    @FXML
     TextField QuantiteField;
    @FXML
    private Button EditButton;
    @FXML
    private Button Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Save(ActionEvent event) throws IOException {
        ProduitsService ps = new ProduitsService();
        ps.update(Integer.parseInt(ReferenceField.getText()),NomField.getText(),CategorieField.getText(),DescriptionField.getText(), (int) Float.parseFloat(PrixField.getText()),Integer.parseInt(QuantiteField.getText()));
      //  Produits p = new Produits(Integer.parseInt(ReferenceField.getText()),NomField.getText(),CategorieField.getText(),DescriptionField.getText(),Float.parseFloat(PrixField.getText()),Integer.parseInt(QuantiteField.getText()));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
        ReferenceField.getScene().setRoot(root);
    }

    public void setReferenceField(TextField ReferenceField) {
        this.ReferenceField = ReferenceField;
    }

    public void setNomField(TextField NomField) {
        this.NomField = NomField;
    }

    public void setCategorieField(TextField CategorieField) {
        this.CategorieField = CategorieField;
    }

    public void setDescriptionField(TextField DescriptionField) {
        this.DescriptionField = DescriptionField;
    }

    public void setPrixField(TextField PrixField) {
        this.PrixField = PrixField;
    }

    public void setQuantiteField(TextField QuantiteField) {
        this.QuantiteField = QuantiteField;
    }

    public void setEditButton(Button EditButton) {
        this.EditButton = EditButton;
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
        ReferenceField.getScene().setRoot(root);
    }
    
}
