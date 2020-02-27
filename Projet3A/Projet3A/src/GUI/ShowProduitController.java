/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Produits;
import Models.ProduitsListCell;
import Models.User;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    private ListView<Produits> ListeProduit;
    @FXML
    private TextField search;
    @FXML
    private Button searchButton;
    
    protected static User user;
    @FXML
    private Button AddRDVButton;
    @FXML
    private Button statButton;
    @FXML
    private Button myRDVButton;
    ObservableList<Produits> pplist;
    private Produits produitTest;

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
        ObservableList<Produits> list = ps.getListProduits(); //user.getProp apres avoir ajouter where nomprp = pro ajouter parametre dans la fficharge
        ListeProduit.setItems(list);
        ListeProduit.setCellFactory(new Callback<ListView<Produits>, ListCell<Produits>>() { 
            @Override 
            public ListCell<Produits> call(ListView<Produits> lv) { 
                return new ProduitsListCell();
            }
        });
        pplist=list;
        if (list.size()>0){
            produitTest=list.get(list.size()-1);
        }


    }
    @FXML
    private void Show(ActionEvent event) throws IOException {
        ProduitsService ps = new ProduitsService();
        Produits p = ListeProduit.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsProduit.fxml"));
        Parent root = loader.load();
        
        ListeProduit.getScene().setRoot(root);
        DetailsProduitController dpc = loader.getController();
        dpc.PropLabel.setText(p.getProprietaire());
        dpc.ProduitLabel.setText(p.getNom());
        dpc.DescLabel.setText(p.getDescription());
        dpc.PrixLabel.setText(Float.toString(p.getPrix()));
        dpc.QteLabel.setText(Integer.toString(p.getnb_produit()));
       
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduit.fxml"));
        Parent root = loader.load();
        ListeProduit.getScene().setRoot(root);
        AddProduitController apc = loader.getController();
        apc.PropField.setText(user.getNomUser());
        if(pplist.size()>0){
            apc.lastIdProduitO=produitTest.getRef();
        }
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        if(ListeProduit.getSelectionModel().getSelectedItem() != null){
        ProduitsService ps = new ProduitsService();
        Produits p = ListeProduit.getSelectionModel().getSelectedItem();
        ps.delete(p.getRef());
      
        Load();
        }
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
        if(ListeProduit.getSelectionModel().getSelectedItem() != null){
        ProduitsService ps = new ProduitsService();
        Produits p = ListeProduit.getSelectionModel().getSelectedItem();
        EditProduitController.id = p.getRef();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduit.fxml"));
        Parent root = loader.load();
        
        ListeProduit.getScene().setRoot(root);
        EditProduitController epc = loader.getController();
       // epc.ReferenceField.setText(Integer.toString(p.getRef()));
       epc.PropField.setText(p.getProprietaire());
        epc.NomField.setText(p.getNom());
        epc.categorieBox.setValue(p.getCategorie().toString());
        epc.DescriptionField.setText(p.getDescription());
        epc.PrixField.setText(Float.toString(p.getPrix()));
        epc.QuantiteField.setText(Integer.toString(p.getnb_produit()));
        }
        
    }
 @FXML
    private void AddRDV(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRDV.fxml"));
        Parent root = loader.load();
        ListeProduit.getScene().setRoot(root);
        AddRDVController arc = loader.getController();
        arc.AcheteurField.setText(user.getNomUser());
        if(ListeProduit.getSelectionModel().getSelectedItem() != null){
            ProduitsService ps = new ProduitsService();
        Produits p = ListeProduit.getSelectionModel().getSelectedItem();
        arc.VendeurField.setText(p.getProprietaire());
        arc.idAcheteurField.setText(Integer.toString(user.getIdUser()));
        }
    }
   @FXML
    private void Recherche(ActionEvent event) {
        for(int i=0; i<ListeProduit.getItems().size(); i++){
            ListeProduit.getItems().clear();
        }
         ProduitsService ps = new ProduitsService ();
      //ReferenceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));  
            ObservableList<Produits> alist =ps.rechercheByNom(search.getText());
        ListeProduit.setItems(alist);
        ListeProduit.setCellFactory(new Callback<ListView<Produits>, ListCell<Produits>>() {
            @Override
            public ListCell<Produits> call(ListView<Produits> lv) {
                return new ProduitsListCell();
            }
        });  

    
  //      ListeProduit.setItems(list);
    } 

    @FXML
    private void Stat(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("StatProduct.fxml"));
        Parent root = loader.load();
       ListeProduit.getScene().setRoot(root);
    }

    @FXML
    private void ShowMyRDV(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowRendezvous.fxml"));
        Parent root = loader.load();
       ListeProduit.getScene().setRoot(root);
    }

   
    
}
