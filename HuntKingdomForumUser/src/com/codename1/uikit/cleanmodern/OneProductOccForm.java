/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.ProductOcc;
import Services.ProductOccService;
import Services.UserService;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
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
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rym
 */
public class OneProductOccForm extends BaseForm{
    
     public OneProductOccForm(Resources res, int idProduct)  {
        
//********PARTIE FIXE********//

        super("Shop occasion", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Shop neuf");
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

       
        ArrayList<ProductOcc> T = ProductOccService.getInstance().getOneProductOcc(idProduct);
        
        
            addButton(idProduct,res,T.get(0).getId(), T.get(0).getNbVue(),T.get(0).getProdLikes(),T.get(0).getProprietaire(), T.get(0).getNom(), T.get(0).getCategorie(), T.get(0).getDescription(), T.get(0).getImage(), T.get(0).getEtat(), T.get(0).getDateachat(), T.get(0).getPrix());
        
        
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
    private void addButton(int idProduct,Resources res,int id, int nbVue,String prodLikes, String Proprietaire, String Nom, String Categorie, String Description, String image, String etat, String dateachat, String prix)   {

       
       int height = Display.getInstance().getDisplayHeight()/3;
       int width = Display.getInstance().getDisplayWidth()/2;
        System.out.println(height+" / "+width);
       
       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(width,height), true);
       URLImage background = URLImage.createToStorage(placeholder, image+"1"+".jpg",Statics.BASE_URL+"/Uploads/images/"+image);
    
        ImageViewer img= new ImageViewer(background);
        img.setHeight(height);
        img.setWidth(width);
       Button imageB = new Button(background.fill(width, height));
       imageB.setUIID("Label");
       Container cnt = new Container(BoxLayout.y());
       Container cnt1 = new Container(BoxLayout.x());
       Container cnt2 = new Container(BoxLayout.x());
       Container cnt3 = new Container(BoxLayout.x());
       Container cntL = new Container(BoxLayout.x());
       cnt.add(img);
       
       TextArea taNom = new TextArea("Nom : "+Nom);
       taNom.setUIID("NewsTopLine");
       taNom.setEditable(false);
       
       TextArea taProprietaire = new TextArea("Proprietaire : "+Proprietaire);
       taProprietaire.setUIID("NewsTopLine");
       taProprietaire.setEditable(false);
       
       TextArea taCategorie= new TextArea("Categorie : "+Categorie);
       taCategorie.setUIID("NewsTopLine");
       taCategorie.setEditable(false);
       
       TextArea taDesc = new TextArea("Description : "+Description);
       taDesc.setUIID("NewsTopLine");
       taDesc.setEditable(false);
       
       TextArea taEtat = new TextArea("Etat : "+etat);
       taEtat.setUIID("NewsTopLine");
       taEtat.setEditable(false);
       
       TextArea taPrice = new TextArea("Prix : "+prix);
       taPrice.setUIID("NewsTopLine");
       taPrice.setEditable(false);
       
       TextArea taNbvue = new TextArea("nombre de vue : "+nbVue);
       taNbvue.setUIID("NewsTopLine");
       taNbvue.setEditable(false);
       
       TextArea taLikes = new TextArea("Nombre de likes : "+prodLikes);
       taLikes.setUIID("NewsTopLine");
       taLikes.setEditable(false);
       
       Label categorieLabel = new Label(Categorie);
       FontImage.setMaterialIcon(categorieLabel, FontImage.MATERIAL_LIST);
       Button returnButton = new Button("Retour");
       Button buyButton = new Button("Acheter");
      /*  NumericSpinner spn = new NumericSpinner();
        
        spn.setMax(nbProduits+1);
       spn.setMin(1);
       spn.setPreferredH(200);
        System.out.println(spn.getPreferredSize()); */
        
      
        
        Button LikeProduct = new Button("Like");
       cnt1.add(taNom);
       cnt1.add(taCategorie);
       cnt.add(cnt1);
       cnt.add(taDesc);
       cnt2.add(taPrice);
       cnt2.add(taNbvue);
       cnt2.add(taLikes);
       cntL.add(LikeProduct);
       cnt.add(cnt2);
        
        
        LikeProduct.addActionListener(e -> {ProductOccService.getInstance().LikeProducts(UserService.getInstance().userNOW.getId(), idProduct);
        new ShopOccasionForm(res).show();
        });
        LikeProduct.setUIID("NewProduct");
       
        
       //cnt3.add(spn);
       cnt.add(cnt3);
       
       
       cnt.add(buyButton);
       cnt.add(returnButton);
        add(cntL);  
       add(cnt);
      
       
       //onCLICK ------>>> !!!!!!!
      
      returnButton.addActionListener(e -> new ShopOccasionForm(res).show());
      buyButton.addActionListener(e -> new addRendezVousForm(res,id,Proprietaire).show());
      
   }
   
}
