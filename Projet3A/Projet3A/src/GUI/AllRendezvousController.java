/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.RdvListCell;
import Models.Rendezvous;
import Services.RendezvousService;
import java.io.IOException;
import java.net.URL;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AllRendezvousController implements Initializable {

    @FXML
    private Button ShowButton;
    @FXML
    private ListView<Rendezvous> listeRDV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Load();
    }    
private void Load() {
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
    private void Show(ActionEvent event) throws IOException {
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
    
}
