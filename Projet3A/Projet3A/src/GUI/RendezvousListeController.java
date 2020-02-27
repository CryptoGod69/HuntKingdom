/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class RendezvousListeController implements Initializable {

    @FXML
     Label LabelAcheteur;
    @FXML
     Label LabelDate;
    @FXML
     Label LabelHeure;
    @FXML
     Label LabelVendeur;
    @FXML
     Label LabelLieu;
    @FXML
     Label LabelNum;
    @FXML
    private Button RetourButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Cancel(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowRendezvous.fxml"));
        Parent root = loader.load();
       LabelAcheteur.getScene().setRoot(root);
    }
    
}
