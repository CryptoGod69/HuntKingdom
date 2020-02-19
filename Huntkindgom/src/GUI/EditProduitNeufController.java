/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ProduitNeufService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kaisk
 */
public class EditProduitNeufController implements Initializable {

    ObservableList<String> categ = FXCollections.observableArrayList("Arme","Vetement","Munitions","Accesoires");
    
    @FXML
    protected TextField idText;
    @FXML
    protected TextField refText;
    @FXML
    protected TextField nomText;
    @FXML
    protected TextField prixText;
    @FXML
    protected TextField qteText;
    @FXML
    protected ChoiceBox ctgBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    protected TextArea descTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ctgBox.setItems(categ);
    }    

    @FXML
    private void Save(ActionEvent event) throws IOException {
        ProduitNeufService ps = new ProduitNeufService();
        ps.modifierProduitNeuf(Integer.parseInt(idText.getText()),Integer.parseInt(refText.getText()), nomText.getText(),descTextArea.getText(),ctgBox.getValue().toString(),Float.parseFloat(prixText.getText()),Integer.parseInt(qteText.getText()));                                                                  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduitNeuf.fxml"));
        Parent root = loader.load();
        idText.getScene().setRoot(root);
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduitNeuf.fxml"));
        Parent root = loader.load();
        idText.getScene().setRoot(root);
    }
    
    
    
    public void setIdField(TextField idText) {
        this.idText = idText;
    }

    public void setRefField(TextField refText) {
        this.refText = refText;
    }

    public void setNomField(TextField nomText) {
        this.nomText = nomText;
    }

    public void setPrixField(TextField prixText) {
        this.prixText = prixText;
    }
    
    public void setQteField(TextField qteText) {
        this.qteText = qteText;
    }
    
    public void setDescField(TextArea descTextArea) {
        this.descTextArea = descTextArea;
    }
    
    public void setctgBoxField(ChoiceBox ctgBox) {
        this.ctgBox = ctgBox;
    }
    
}
