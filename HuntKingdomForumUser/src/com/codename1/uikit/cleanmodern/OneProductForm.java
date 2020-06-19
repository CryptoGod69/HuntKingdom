/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.Commande;
import Models.ProduitNeuf;
import Services.CommandeService;
import Services.ProduitNeufService;
import Services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.NumericConstraint;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kaisk
 */
public class OneProductForm extends BaseForm {
    public OneProductForm(Resources res, int idProduct)  {
        
//********PARTIE FIXE********//

        super("Shop neuf", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Shop neuf");
        getContentPane().setScrollVisible(false);
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
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

       
        ArrayList<ProduitNeuf> T = ProduitNeufService.getInstance().getOneProduct(idProduct);
        
        
            addButton(res, T.get(0).getId(), T.get(0).getNom(), T.get(0).getCategorie(), T.get(0).getDescription(), T.get(0).getPrix(), T.get(0).getNbProduits(), T.get(0).getImage(), T.get(0));
        
        
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
    private void addButton(Resources res, int id, String nom, String categorie, String description, float prix, int nbProduits, String image, ProduitNeuf p)   {

       
       int height = Display.getInstance().getDisplayHeight()/3;
       int width = Display.getInstance().getDisplayWidth()/2;
        System.out.println(height+" / "+width);
       
       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(width,height), true);
       URLImage background = URLImage.createToStorage(placeholder, image+"1"+".jpg","http://localhost/HuntKingdom/web/Uploads/produit/photo/"+image);
    
        ImageViewer img= new ImageViewer(background);
        img.setHeight(height);
        img.setWidth(width);
       Button imageB = new Button(background.fill(width, height));
       imageB.setUIID("Label");
       Container cnt = new Container(BoxLayout.y());
       Container cnt1 = new Container(BoxLayout.x());
       Container cnt2 = new Container(BoxLayout.x());
       Container cnt3 = new Container(BoxLayout.x());
       Container cnt4 = new Container(BoxLayout.x());
       cnt.add(img);
       
       TextArea taNom = new TextArea("Name : "+nom);
       taNom.setUIID("NewsTopLine");
       taNom.setEditable(false);
       
       TextArea taDesc = new TextArea("Description : "+description);
       taDesc.setUIID("NewsTopLine");
       taDesc.setEditable(false);
       
       TextArea taPrice = new TextArea("Price : "+prix);
       taPrice.setUIID("NewsTopLine");
       taPrice.setEditable(false);
       
       TextArea taQte = new TextArea("Quantity availble : "+nbProduits);
       taQte.setUIID("NewsTopLine");
       taQte.setEditable(false);
       
       TextArea taQteB = new TextArea("Qte : ");
       taQteB.setUIID("NewsTopLine");
       taQteB.setEditable(false);
       
       Label categorieLabel = new Label(categorie);
       FontImage.setMaterialIcon(categorieLabel, FontImage.MATERIAL_LIST);
       Button buyButton = new Button("Buy");
        NumericSpinner spn = new NumericSpinner();
        
       spn.setMax(nbProduits+1);
       spn.setMin(1);
       spn.setPreferredH(200);
        System.out.println(spn.getPreferredSize());
        
       

       cnt1.add(taNom);
       cnt1.add(categorieLabel);
       
       cnt.add(cnt1);
       cnt.add(taDesc);
       
       cnt2.add(taPrice);
       cnt2.add(taQte);
       
       cnt.add(cnt2);
       
       cnt3.add(taQteB);
       cnt3.add(spn);
       
       cnt.add(cnt3);
       NumericConstraint nc1 = new NumericConstraint(false,10000000,99999999,"Veuillez entrer un numéro de telephone correct");
       
           Container cntp1 = new Container(BoxLayout.x());
           //Container cntp2 = new Container(BoxLayout.x());
           
           Label lbPad = new Label("Address :");
           TextArea popAdresse = new TextArea("", 1, 6, TextArea.ANY);
           popAdresse.setSingleLineTextArea(true);
           Label lbPhone = new Label("Phone :");
           TextArea popPhone = new TextArea("", 1, 6, TextArea.NUMERIC);
           popPhone.setSingleLineTextArea(true);
           popAdresse.setSingleLineTextArea(true);
           cntp1.add(lbPad);
           cntp1.add(popAdresse);
          
           cntp1.add(lbPhone);
           cntp1.add(popPhone);
           
         cnt.add(cntp1);
         
         
         
         cnt.add(buyButton);
       
       add(cnt);
///////////////////////////////////////////////////////////////////// PARTIE DIALOG
       String format = "yyyy-MM-dd";

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        System.out.println(formater.format(date));

       
           
           
           
       //onCLICK ------>>> !!!!!!!
       buyButton.addActionListener(e -> {
          Commande cmd = new Commande();
          String test = popPhone.getText();
          int phoneNumber=0;
           try
            {
             phoneNumber = Integer.parseInt(test);
            }
            catch (NumberFormatException ex)
            {
            
            }
           String newDate = formater.format(date);
           
           if (nc1.isValid(phoneNumber) && !(popAdresse.getText().isEmpty())){
                System.out.println(phoneNumber);
                System.out.println(popAdresse.getText());
                cmd.setDateCommande(newDate);
                cmd.setTotal((float) (prix*spn.getValue()));
                cmd.setEtat(0);
                cmd.setQuantite((int) spn.getValue());
                cmd.setAdresse(popAdresse.getText());                
                cmd.setUsern(UserService.getInstance().userNOW); // A CHANGER !!
                cmd.setProduitn(p);
                cmd.setPhone(phoneNumber);
                CommandeService.getInstance().addCommande(res, cmd);
                CommandeService.getInstance().updateProduit(res, cmd);
                new ShopNeufForm(res).showBack();
           }else{
               Dialog.show("Erreur", "Saisir une adresse et un numero valide", "ok", "cancel");
           }
       });
   }
    private List<Integer> createListEntry(int nombre) {
    List<Integer> entry = new ArrayList<>();
        entry.add(nombre);
    return entry;
}
}
