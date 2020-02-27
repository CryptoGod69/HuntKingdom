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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class EditProduitController implements Initializable {
ObservableList<String> categorie = FXCollections.observableArrayList("Arme","Vetement","Nutritions","Accessoires");
    
    @FXML
     TextField NomField;
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
    @FXML
     TextField PropField;
    
    protected static int id;
    @FXML
     ComboBox<String> categorieBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categorieBox.setItems(categorie);
    }    

    @FXML
    private void Save(ActionEvent event) throws IOException {
        ProduitsService ps = new ProduitsService();
        ps.update(id,PropField.getText(),NomField.getText(),categorieBox.getValue(),DescriptionField.getText(), (int) Float.parseFloat(PrixField.getText()),Integer.parseInt(QuantiteField.getText()));
      //  Produits p = new Produits(Integer.parseInt(ReferenceField.getText()),NomField.getText(),CategorieField.getText(),DescriptionField.getText(),Float.parseFloat(PrixField.getText()),Integer.parseInt(QuantiteField.getText()));
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setContentText("le produit was edited successfully");
        alert.showAndWait();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
     NomField.getScene().setRoot(root);
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
       PropField.getScene().setRoot(root);
    }
    
}
