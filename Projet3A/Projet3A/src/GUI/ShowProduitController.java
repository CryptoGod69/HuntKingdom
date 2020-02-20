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
import java.sql.SQLException;
import java.util.ResourceBundle;
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
 * @author Rym
 */
public class ShowProduitController implements Initializable {

    @FXML
    private Button ButtonShow;
    @FXML
    private Button ButtonAdd;
    @FXML
    private Button ButtonDelete;
    @FXML
    private Button ButtonEdit;
    @FXML
    private TableColumn<Produits, String> ReferenceColumn;
    @FXML
    private TableColumn<Produits, String> NomColumn;
    @FXML
    private TableColumn<Produits, String> CategorieColumn;
    @FXML
    private TableColumn<Produits, String> DesciptionColumn;
    @FXML
    private TableColumn<Produits, String> PrixColumn;
    @FXML
    private TableColumn<Produits, String> nb_ProduitColumn;
    @FXML
    private TableView<Produits> ListeProduit;
    @FXML
    private TextField search;
    @FXML
    private Button searchButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Load();
    }    
 private void Load() {
        ProduitsService ps = new ProduitsService ();
        ObservableList<Produits> list = ps.getListProduits();
        ReferenceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CategorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        DesciptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nb_ProduitColumn.setCellValueFactory(new PropertyValueFactory<>("nbProduits"));

    
        ListeProduit.setItems(list);
    }
    @FXML
    private void Show(ActionEvent event) {
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduit.fxml"));
        Parent root = loader.load();
        ListeProduit.getScene().setRoot(root);
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        ProduitsService ps = new ProduitsService();
        Produits p = ListeProduit.getSelectionModel().getSelectedItem();
        ps.delete(p.getRef());
       // ps.deleteTopic(p.getId());
        Load();
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
        ProduitsService ps = new ProduitsService();
        Produits p = ListeProduit.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduit.fxml"));
        Parent root = loader.load();
        
        ListeProduit.getScene().setRoot(root);
        EditProduitController epc = loader.getController();
        epc.ReferenceField.setText(Integer.toString(p.getRef()));
        epc.NomField.setText(p.getNom());
        epc.CategorieField.setText(p.getCategorie());
        epc.DescriptionField.setText(p.getDescription());
        epc.PrixField.setText(Float.toString(p.getPrix()));
        epc.QuantiteField.setText(Integer.toString(p.getnb_produit()));
        
        
    }

    @FXML
    private void Recherche(ActionEvent event) {
        
        ProduitsService ps = new ProduitsService ();
        ObservableList<Produits> list = ps.rechercheById(Integer.parseInt(search.getText()));
        ReferenceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CategorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        DesciptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nb_ProduitColumn.setCellValueFactory(new PropertyValueFactory<>("nbProduits"));

    
        ListeProduit.setItems(list);
    }
    
}
