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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class StatProductController implements Initializable {

    @FXML
    private BarChart<?, ?> statChart;
    @FXML
    private NumberAxis percentAxis;
    @FXML
    private CategoryAxis productAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Percentage");
        series1.getData().add(new XYChart.Data("produit",70));
        series1.getData().add(new XYChart.Data("pr", 10));
        series1.getData().add(new XYChart.Data("azerty",5));
        series1.getData().add(new XYChart.Data("nv prod",5));
        series1.getData().add(new XYChart.Data("prod", 2));  
        statChart.getData().addAll(series1);
       /* Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show(); */
    }    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProduit.fxml"));
        Parent root = loader.load();
       statChart.getScene().setRoot(root);
    }
    
}
