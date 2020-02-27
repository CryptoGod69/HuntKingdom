/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.Rendezvous;
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
public class RdvListCell extends ListCell<Rendezvous>{
    private final GridPane gridPane = new GridPane(); 
   //  private final ImageView imageIcon = new ImageView();
    private final Label AcheteurLabel = new Label();
    private final Label VendeurLabel = new Label();
   //  private final Label descriptionLabel = new Label();
      private final Label numeroLabel = new Label();
      private final Label dateLabel = new Label(); 
    private final AnchorPane content = new AnchorPane(); 
    
    public RdvListCell() { 
      /*  imageIcon.setFitWidth(20); 
        imageIcon.setPreserveRatio(true); 
        GridPane.setConstraints(imageIcon, 0, 0, 1, 3); 
        GridPane.setValignment(imageIcon, VPos.TOP); */
        // 
        AcheteurLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(AcheteurLabel, 1, 0); 
        // 
        VendeurLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(VendeurLabel,2,1 ); 
        //
         
        numeroLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(numeroLabel,1, 1); 
        //  
        dateLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(dateLabel,2, 2); 
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
        gridPane.getChildren().setAll(AcheteurLabel,VendeurLabel,numeroLabel,dateLabel); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane);
    }
    
    @Override 
    protected void updateItem(Rendezvous item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            AcheteurLabel.setText(item.getAcheteur()); 
            VendeurLabel.setText(item.getVendeur()); 
            numeroLabel.setText(Integer.toString(item.getNumero()));
            dateLabel.setText(item.getDate().toString()); 
            String path = "";
          /*  path = "/Resources/produits/"+item.getReferenceRDV()+".jpg";
            Image image = new Image(getClass().getResource(path).toExternalForm());
            if(image != null){
                imageIcon.setImage(image);
            } */
            setText(null);
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
