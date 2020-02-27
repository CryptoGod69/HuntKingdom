
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Rendezvous;
import Services.RendezvousService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AddRDVController implements Initializable {
    ObservableList<String> Lieu = FXCollections.observableArrayList("Tunis","Ben arou","Bizerte","Béja","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","La manouba","Le kef","Mahdia","Monastir","Medenin","Nabeul","Sfax","Sidi bouzid","Siliana","Sousse","Tataouine","Tozeur","Zaghaouan");
    @FXML
     TextField AcheteurField;
    
    @FXML
     TextField VendeurField;
    @FXML
    private TextField NumerfoField;
    @FXML
    private TextField HeureField;
    private TextField LieuField;
    @FXML
    private Button cancelButton;
    @FXML
     TextField idAcheteurField;
int nbr=8;
int h1=8;
int h2=20;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private ComboBox<String> LieuBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatePicker.setValue(LocalDate.now());
         LieuBox.setItems(Lieu);
        
    }    

    public DatePicker getDatePicker() {
        return DatePicker;
    }

    public void setDatePicker(DatePicker DatePicker) {
        this.DatePicker = DatePicker;
    }

    @FXML
    private void Save(ActionEvent event) throws IOException, SQLException {
        String numero=NumerfoField.getText();
         if(!idAcheteurField.getText().isEmpty() && !AcheteurField.getText().isEmpty()  && !VendeurField.getText().isEmpty() && numero.length()<=nbr && !HeureField.getText().isEmpty() && !LieuBox.getValue().isEmpty()){
            RendezvousService rdv = new RendezvousService();
           
            Rendezvous r = new Rendezvous(Integer.parseInt(idAcheteurField.getText()),AcheteurField.getText(),VendeurField.getText(),Integer.parseInt(NumerfoField.getText()),DatePicker.getValue(),HeureField.getText(),LieuBox.getValue());
           rdv.ajouterRendezvous(r);
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setContentText("Rendez vous ajouté avec succès");
        alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
            Parent root = loader.load();
            AcheteurField.getScene().setRoot(root);
        }
         else
         {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setContentText("vous avez mal saisi vos coordonées");
        alert.showAndWait();
         }
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
       AcheteurField.getScene().setRoot(root);
    }
    
}
