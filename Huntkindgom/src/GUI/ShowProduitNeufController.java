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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kaisk
 */
public class ShowProduitNeufController implements Initializable {

    @FXML
    private TableView<ProduitNeuf> TableProduitNeuf;
    @FXML
    private Button ajouterBouton;
    @FXML
    private Button supprimerBouton;
    @FXML
    private Button modifierBouton;
    @FXML
    private TableColumn<ProduitNeuf, String> idColonne;
    @FXML
    private TableColumn<ProduitNeuf, String> refColonne;
    @FXML
    private TableColumn<ProduitNeuf, String> nomColonne;
    @FXML
    private TableColumn<ProduitNeuf, String> ctgColonne;
    @FXML
    private TableColumn<ProduitNeuf, String> descColonne;
    @FXML
    private TableColumn<ProduitNeuf, String> prixColonne;
    @FXML
    private TableColumn<ProduitNeuf, String> qteColonne;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(ShowProduitNeufController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void Load() throws SQLException {
        ProduitNeufService pns = new ProduitNeufService();
        ObservableList<ProduitNeuf> plist = pns.afficherListeProduitNeuf();
        idColonne.setCellValueFactory(new PropertyValueFactory<>("idProdNeuf"));
        refColonne.setCellValueFactory(new PropertyValueFactory<>("reference"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ctgColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        descColonne.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColonne.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteColonne.setCellValueFactory(new PropertyValueFactory<>("nbProduits"));
        TableProduitNeuf.setItems(plist);
    }
    
    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduitNeuf.fxml"));
        Parent root = loader.load();
        TableProduitNeuf.getScene().setRoot(root);
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        if(TableProduitNeuf.getSelectionModel().getSelectedItem() != null){
            ProduitNeufService ps = new ProduitNeufService();
            ProduitNeuf p = TableProduitNeuf.getSelectionModel().getSelectedItem();
            ps.SupprimerProduitNeuf(p.getIdProdNeuf());
            Load();
        }
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        if(TableProduitNeuf.getSelectionModel().getSelectedItem() != null){
            //TopicService ts = new TopicService();
            ProduitNeuf p = TableProduitNeuf.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduitNeuf.fxml"));
            Parent root = loader.load();
            TableProduitNeuf.getScene().setRoot(root);
            EditProduitNeufController etc = loader.getController();
            etc.idText.setText(Integer.toString(p.getIdProdNeuf()));
            etc.refText.setText(Integer.toString(p.getReference()));
            etc.nomText.setText(p.getNom());
            etc.ctgBox.setValue(p.getCategorie());
            etc.descTextArea.setText(p.getDescription());
            etc.prixText.setText(Float.toString(p.getPrix()));
            etc.qteText.setText(Integer.toString(p.getNbProduits()));
        }
    }
}