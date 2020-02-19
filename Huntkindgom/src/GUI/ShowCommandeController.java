/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Commande;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kaisk
 */
public class ShowCommandeController implements Initializable {

    @FXML
    private TableView<Commande> tableCommandeId;
    @FXML
    private TableColumn<Commande, String> idColumn;
    @FXML
    private TableColumn<Commande, String> clientColumn;
    @FXML
    private TableColumn<Commande, String> produitColumn;
    @FXML
    private TableColumn<Commande, String> dateColumn;
    @FXML
    private TableColumn<Commande, String> totalColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button validerButton;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(ShowCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void Load() throws SQLException {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> clist = cs.afficherListeCommandeNonV();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCom"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        produitColumn.setCellValueFactory(new PropertyValueFactory<>("idProdNeuf"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableCommandeId.setItems(clist);
    }

    @FXML
    private void Chercher(ActionEvent event) throws SQLException {
        if(!searchField.getText().isEmpty()){
        CommandeService cs = new CommandeService();
        ObservableList<Commande> clist = cs.afficherCommande(Integer.parseInt(searchField.getText()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCom"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        produitColumn.setCellValueFactory(new PropertyValueFactory<>("idProdNeuf"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableCommandeId.setItems(clist);
        }
    }

    @FXML
    private void Valider(ActionEvent event) throws IOException {
        if(tableCommandeId.getSelectionModel().getSelectedItem() != null){
            //TopicService ts = new TopicService();
           Commande c = tableCommandeId.getSelectionModel().getSelectedItem();            
           CommandeService ps = new CommandeService();
           ps.validerCommande (c.getIdCom());                                                                  
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCommande.fxml"));
           Parent root = loader.load();
           tableCommandeId.getScene().setRoot(root);
    }
    
}

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteCommande.fxml"));
        Parent root = loader.load();
        tableCommandeId.getScene().setRoot(root);
    }
}
