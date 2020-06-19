/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.ProductOcc;
import Services.ProductOccService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rym
 */
public class addProductForm extends BaseForm{
    public addProductForm(Resources res)  {
        
//********PARTIE FIXE********//

        super("Shop occasion", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Shop Occasion");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();

        addTab(swipe, res.getImage("hunting1.jpg"), spacer1, "Welcome to the shop");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
//********PARTIE PRODUIT********//

       
        
        
        addButton(res);
        
    }
    
    //POUR AJOUTER UNE PHOTO
    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    //POUR AJOUTER UN TOPIC
    private void addButton(Resources res)   {

       
        ProductOcc p = new ProductOcc();
       Container cnt = new Container(BoxLayout.y());
       Container cnt1 = new Container(BoxLayout.x());
       Container cnt2 = new Container(BoxLayout.x());
       Container cnt3 = new Container(BoxLayout.x());
       Container cnt4 = new Container(BoxLayout.x());
       Container cnt5 = new Container(BoxLayout.x());
       Container cnt6 = new Container(BoxLayout.x());
       
        Label lbNom = new Label("Name :");
        TextField tfNom = new TextField("", "First Name");
        tfNom.setUIID("name");
        tfNom.getAllStyles().setAlignment(LEFT);
        tfNom.getAllStyles().setBgColor(000000);
        //get
        
        Label lbCategorie = new Label("Categorie :");
        ComboBox<String> cbCategorie = new ComboBox<>("Choose");
        cbCategorie.addItem("Arme");
        cbCategorie.addItem("Vetement");
        cbCategorie.addItem("Munition");
        
        Label lbDes = new Label("Description :");
        TextArea taDescription = new TextArea();
        
        
        
        Label lbPrix = new Label("Price :");
        TextField tfPrix = new TextField("", "Price",20,TextArea.NUMERIC);
        tfPrix.setUIID("Price");
        tfPrix.getAllStyles().setAlignment(LEFT);
        tfPrix.getAllStyles().setBgColor(000000);
        
        Label lbDate = new Label("Date :");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        
        String format = "YYYY/MM/DD";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        System.out.println( formater.format( date ) );
        datePicker.setDate(date);

        
        
        
        
        Label lbImage = new Label("Image :");
        TextArea tfImage = new TextArea();
        
         Button addButton = new Button("ajouter");
         Button retourButton = new Button("retour");
         
        
         
        cnt1.add(lbNom); 
        cnt1.add(tfNom);
        
        cnt2.add(lbCategorie);
        cnt2.add(cbCategorie);
        
        cnt3.add(lbDes);
        cnt3.add(taDescription);
        
        cnt4.add(lbPrix);
        cnt4.add(tfPrix);
        
        cnt5.add(lbDate);
        cnt5.add(datePicker);
        
       /* cnt6.add(lbImage);
        cnt6.add(tfImage); */
        //cnt3.add();
        
        cnt.add(cnt1);
        cnt.add(cnt2);
        cnt.add(cnt3);
        cnt.add(cnt4);
        cnt.add(cnt5);
        cnt.add(cnt6);
        //if clicked 
        cnt.add(addButton);
        cnt.add(retourButton);
       
  
       
       add(cnt);
       
       //onCLICK ------>>> !!!!!!!
       addButton.addActionListener(e -> {
           p.setNbVue(0);
           p.setProprietaire("user");
           p.setNom(tfNom.getText());
           p.setCategorie(cbCategorie.getSelectedItem());
           p.setDescription(taDescription.getText());
           p.setImage("image1");
           p.setPrix(tfPrix.getText());
           p.setEtat("disponible");
           p.setProdLikes("0");
           p.setDateachat(datePicker.getValue().toString());

           
           if( ProductOccService.getInstance().addProduct(p))
                        {
                            
                                   
                             Dialog.show("SUCCESS", "Produit Ajoute", new Command("OK"));
                             
                        }
                            
                        else
                        {
                  
                             Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
       });
     retourButton.addActionListener(e-> new ShopOccasionForm(res).show());
   }
}
