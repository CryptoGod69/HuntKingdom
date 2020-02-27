/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Produits;
import Models.User;
import java.io.IOException;
import java.sql.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Rym
 */
public class MainProgram extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        User user = new User(759,"muftox", "mufti", "1 Rue de la paix", "ahmed.mouhamed@gmail.com", "71123123", "Admin" , new Date(30,12,1998));
         Produits p = new Produits ("client Vendeur", "pr", "pr","ce porduit est .. ",220,20);
        ShowProduitController.user = user;
        Parent root = FXMLLoader.load(getClass().getResource("ShowProduit.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Produits");
        primaryStage.setScene(scene);
        primaryStage.show();
       /* Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show(); */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
