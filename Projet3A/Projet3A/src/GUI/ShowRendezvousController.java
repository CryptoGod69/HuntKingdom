/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import static GUI.ShowProduitController.user;
import Models.RdvListCell;
import Models.Rendezvous;
import Models.User;

import Services.RendezvousService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class ShowRendezvousController implements Initializable {

    @FXML
    private ListView<Rendezvous> listeRDV;
    @FXML
    private Button showButton;
 protected static User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Load();
    }    
    private void Load() {
         for (int i=0;i<listeRDV.getItems().size();i++)
       {
          listeRDV.getItems().clear();
       }
        RendezvousService rdv = new RendezvousService();
        ObservableList<Rendezvous> list = rdv.getListRendezvous();
        listeRDV.setItems(list);
         listeRDV.setItems(list);
        listeRDV.setCellFactory(new Callback<ListView<Rendezvous>, ListCell<Rendezvous>>() { 
            @Override 
            public ListCell<Rendezvous> call(ListView<Rendezvous> lv) { 
                return new RdvListCell();
            }
        });
    }

    @FXML
    private void show(ActionEvent event) throws IOException {
        RendezvousService ps = new RendezvousService();
        Rendezvous r = listeRDV.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rendezvous.fxml"));
        Parent root = loader.load();
        
        listeRDV.getScene().setRoot(root);
        RendezvousListeController epc = loader.getController();
        epc.LabelAcheteur.setText(r.getAcheteur());
        epc.LabelDate.setText(r.getDate().toString());
        epc.LabelHeure.setText(r.getHeure());
        epc.LabelVendeur.setText(r.getVendeur());
        epc.LabelLieu.setText(r.getLieu());
        epc.LabelNum.setText(Integer.toString(r.getNumero()));
    }

    private void Delete(ActionEvent event) throws SQLException {
          if(listeRDV.getSelectionModel().getSelectedItem() != null){
         RendezvousService rdv = new RendezvousService();
        Rendezvous r = listeRDV.getSelectionModel().getSelectedItem();
        rdv.delete(r.getReferenceRDV());
        Load();
      
          }
    }

    private void Add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRDV.fxml"));
        Parent root = loader.load();
        listeRDV.getScene().setRoot(root);
        AddRDVController arc = loader.getController();
        arc.idAcheteurField.setText(Integer.toString(user.getIdUser()));
    }

    @FXML
    private void Return(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
       listeRDV.getScene().setRoot(root);
    }
    
}
