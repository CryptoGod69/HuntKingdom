/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.ProductOcc;
import Models.RendezVous;
import Services.ProductOccService;
import Services.RendezVousService;
import Services.UserService;
import Utils.Statics;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Rym
 */
public class addRendezVousForm extends BaseForm{
    public addRendezVousForm(Resources res, int id,String Proprietaire)  {
        
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

       
        
        
        addButton(res,Proprietaire);
        
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
    
   
    private void addButton(Resources res,String Proprietaire)   {

       
       RendezVous r = new RendezVous();
       Container cnt = new Container(BoxLayout.y());
       Container cnt1 = new Container(BoxLayout.x());
       Container cnt2 = new Container(BoxLayout.x());
       Container cnt3 = new Container(BoxLayout.x());
       Container cnt4 = new Container(BoxLayout.x());
       Container cnt5 = new Container(BoxLayout.x());
       Container cnt6 = new Container(BoxLayout.x());
       
        Label lbNom = new Label("Proprietaire:");
        TextField tfNom = new TextField("", "");
        
        tfNom.setText(Proprietaire);
        tfNom.setEditable(false);
        tfNom.setUIID("name");
        tfNom.getAllStyles().setAlignment(LEFT);
        tfNom.getAllStyles().setBgColor(000000);
        //get
        
        /*Label lbCategorie = new Label("Categorie :");
        ComboBox<String> cbCategorie = new ComboBox<>("Choose");
        cbCategorie.addItem("Arme");
        cbCategorie.addItem("Vetement");
        cbCategorie.addItem("Munition"); */
        
        Label lbAcheteur = new Label("Shopper");
        TextArea taAcheteur = new TextArea();
        taAcheteur.setText(UserService.getInstance().userNOW.getUsername());
        taAcheteur.setEditable(false);
        taAcheteur.setSingleLineTextArea(true);
        
        Label lbHeure = new Label("Phone:");
        TextField tfHeure = new TextField("", "",20,TextArea.NUMERIC);
        tfHeure.setUIID("Hour");
        tfHeure.getAllStyles().setAlignment(LEFT);
        tfHeure.getAllStyles().setBgColor(000000);
        
        Label lbLieu = new Label("Place :");
        TextArea tfLieu = new TextArea();
        tfLieu.setSingleLineTextArea(true);
        
        
        Label lbDate = new Label("Date :");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        datePicker.setFormatter(sdf);


        
        
        String format = "YYYY/MM/DD";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
       // System.out.println( formater.format( date ) );
        //datePicker.setDate(date);
        TextComponent messageText = new TextComponent().labelAndHint("Mail");
         Message m =new Message (messageText.getText());
        
        
        
        
        
         Button addButton = new Button("Add");
         Button retourButton = new Button("Back");
         
        
         
        cnt1.add(lbNom); 
        cnt1.add(tfNom);
        
        cnt2.add(lbHeure);
        cnt2.add(tfHeure);
        
        cnt3.add(lbLieu);
        cnt3.add(tfLieu);
        
        cnt4.add(lbAcheteur);
        cnt4.add(taAcheteur);
        
        cnt5.add(lbDate);
        cnt5.add(datePicker);
        
       /* cnt6.add(lbImage);
        cnt6.add(tfImage); */
        //cnt3.add();
        
        cnt.add(cnt1);
        cnt.add(cnt4);
        cnt.add(cnt2);
        cnt.add(cnt3);
        cnt.add(cnt5);
        cnt.add(cnt6);
        //if clicked 
        cnt.add(addButton);
        cnt.add(retourButton);
       
  
       
       add(cnt);
       
       //onCLICK ------>>> !!!!!!!
       addButton.addActionListener(e -> {
           r.setAcheteur(taAcheteur.getText());
           r.setVendeur(tfNom.getText());
           r.setDate(datePicker.getText());
           r.setHeure(tfHeure.getText());
           r.setLieu(tfLieu.getText());


           
           if( RendezVousService.getInstance().addRendezVous(r))
                        {
                                                   
                             Dialog.show("SUCCESS", "rendez vous Ajoute", new Command("OK"));
                             Display.getInstance().sendMessage(new String [] {"feryel.dhaouadi@esprit.tn"}, "Rendez Vous Shop", m);
                             
                        }
                            
                        else
                        {
                  
                             Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
          
           
       });
     retourButton.addActionListener(e-> new ShopOccasionForm(res).show());
   }
    
}
