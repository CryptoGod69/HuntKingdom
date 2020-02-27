/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Omar
 */
public class ProduitsListCell extends ListCell<Produits>{
    private final GridPane gridPane = new GridPane(); 
     private final ImageView imageIcon = new ImageView();
    private final Label nomLabel = new Label();
    private final Label categorieLabel = new Label();
   //  private final Label descriptionLabel = new Label();
      private final Label prixLabel = new Label();
    //   private final Label qteLabel = new Label(); 
    private final AnchorPane content = new AnchorPane(); 
    
    public ProduitsListCell() { 
        imageIcon.setFitWidth(20); 
        imageIcon.setPreserveRatio(true); 
        GridPane.setConstraints(imageIcon, 0, 0, 1, 3); 
        GridPane.setValignment(imageIcon, VPos.TOP); 
        // 
        nomLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(nomLabel, 1, 0); 
        // 
        prixLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(prixLabel,2,1 ); 
        //
         
        categorieLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(categorieLabel,1, 1); 
        //  
        
       
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(imageIcon,nomLabel,categorieLabel, prixLabel); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane);
    }
    
    @Override 
    protected void updateItem(Produits item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            nomLabel.setText(item.getNom()); 
            categorieLabel.setText(item.getCategorie()); 
            prixLabel.setText(Float.toString(item.getPrix()));
            String path = "";
            path = "/Resources/produits/"+item.getReference()+".jpg";
            Image image = new Image(getClass().getResource(path).toExternalForm());
            if(image != null){
                imageIcon.setImage(image);
            }
            setText(null);
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
