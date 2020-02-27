/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Produits;
import Services.ProduitsService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AddProduitController implements Initializable {
    ObservableList<String> categorie = FXCollections.observableArrayList("Arme","Vetement","Munitions","Accessoires");
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    private TextField ReferenceField;
    @FXML
    private TextField NomField;

    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField QuantiteField;
    @FXML
    private TextField PrixField;
    @FXML
     TextField PropField;
    @FXML
    private ComboBox<String> CategorieBox;
    @FXML
    private Button importButton;
    protected static int lastIdProduitO=0;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // CategorieBox.setValue(value);
        CategorieBox.setItems(categorie);
    }    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
        PropField.getScene().setRoot(root);
    }

    @FXML
    private void save(ActionEvent event) throws SQLException, IOException {
        if( !PropField.getText().isEmpty() && !NomField.getText().isEmpty() && !CategorieBox.getValue().isEmpty() && !DescriptionField.getText().isEmpty() && !PrixField.getText().isEmpty() && !QuantiteField.getText().isEmpty()){
            ProduitsService ps = new ProduitsService();
           
            Produits p = new Produits(PropField.getText(),NomField.getText(),CategorieBox.getValue(),DescriptionField.getText(), (int) Float.parseFloat(PrixField.getText()),Integer.parseInt(QuantiteField.getText()));
            ps.ajouterProduit(p);
            
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
            Parent root = loader.load();
            PropField.getScene().setRoot(root);
        }
    }

    @FXML
    private void importImg(ActionEvent event) {
        FileChooser fc = new FileChooser ();
        fc.setInitialDirectory(new File ("C:\\Users\\Rym\\Desktop\\Projet3A\\Projet3A\\src\\Resources\\produits"));
        fc.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("JPG","*.jpg"));
        File selectedFile = fc.showSaveDialog(null);
        if (selectedFile != null)
        {
            String path ="";
            path=selectedFile.getAbsolutePath();
            System.out.print(path);
            //Image image = new Image (getClass().getResource(path).toExternalForm());
            lastIdProduitO++;
            File nouveau = new File("C:\\Users\\Rym\\Desktop\\Projet3A\\Projet3A\\src\\Resources\\produits\\"+lastIdProduitO+".jpg");
            selectedFile.renameTo(nouveau);
        }
    }
    
}
