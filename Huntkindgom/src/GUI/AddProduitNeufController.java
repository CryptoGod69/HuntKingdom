/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.ProduitNeuf;
import Services.ProduitNeufService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kaisk
 */
public class AddProduitNeufController implements Initializable {

    ObservableList<String> categ = FXCollections.observableArrayList("Arme","Vetement","Munitions","Accesoires");
    
    
    @FXML
    private TextField idText;
    @FXML
    private TextField refText;
    @FXML
    private TextField nomText;
    @FXML
    private TextField prixText;
    @FXML
    private TextField qteText;
    @FXML
    private ChoiceBox ctgBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextArea descTextArea;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ctgBox.setValue("Arme");
        ctgBox.setItems(categ);
    }    

    @FXML
    private void Save(ActionEvent event) throws SQLException, IOException {
        if(!idText.getText().isEmpty() && !refText.getText().isEmpty() && !nomText.getText().isEmpty()&& !prixText.getText().isEmpty()&& !qteText.getText().isEmpty()&& !descTextArea.getText().isEmpty()){
            ProduitNeufService pn = new ProduitNeufService();           
            ProduitNeuf p = new ProduitNeuf(Integer.parseInt(refText.getText()), nomText.getText(),ctgBox.getValue().toString(),descTextArea.getText(),Float.parseFloat(prixText.getText()),Integer.parseInt(qteText.getText()),Integer.parseInt(idText.getText()));
            pn.ajouterProduitNeuf(p);          
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduitNeuf.fxml"));
            Parent root = loader.load();
            idText.getScene().setRoot(root);
        }
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduitNeuf.fxml"));
        Parent root = loader.load();
        idText.getScene().setRoot(root);
    }
    
}
