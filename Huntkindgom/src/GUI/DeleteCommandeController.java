/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Commande;
import Services.CommandeService;
import Services.ProduitNeufService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class DeleteCommandeController implements Initializable {

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
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(ShowCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
    
    private void Load() throws SQLException {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> clist = cs.afficherListeCommandeV();
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
    private void Delete(ActionEvent event) throws SQLException {
        if(tableCommandeId.getSelectionModel().getSelectedItem() != null){
            CommandeService cs = new CommandeService();
            Commande c = tableCommandeId.getSelectionModel().getSelectedItem();
            cs.SupprimerCommande(c.getIdCom());
            Load();
        }
    }
        
}
