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
public class ShowCommandeForm extends BaseForm{
    public ShowCommandeForm(Resources res)  {
        
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

       
        ArrayList<Commande> T = CommandeService.getInstance().getAllCommandesU(UserService.getInstance().userNOW.getId());
        
        for(int i=0; i<T.size(); i++){
            addButton(res, T.get(0));
        }
        
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
    private void addButton(Resources res,Commande c)   {

       
       
       Container cnt = new Container(BoxLayout.x());
       
       
       TextArea taPrice = new TextArea(c.getTotal()+"dt");
       taPrice.setUIID("NewsTopLine");
       taPrice.setEditable(false);

       
       
       TextArea taQte = new TextArea("Qte:"+c.getQuantite());
       taQte.setUIID("NewsTopLine");
       taQte.setEditable(false);
       
       
       ArrayList<ProduitNeuf> T = ProduitNeufService.getInstance().getOneProduct(c.getIdCom());
       
       
       TextArea taProduit = new TextArea(T.get(0).getNom());
       taProduit.setUIID("NewsTopLine");
       taProduit.setEditable(false);
       
       TextArea taDate = new TextArea(c.getDateCommande());
       taDate.setUIID("NewsTopLine");
       taDate.setEditable(false);
       
       
       cnt.add(taProduit);
       cnt.add(taPrice);
       cnt.add(taQte);
       cnt.add(taDate);
       add(cnt);
       
       //onCLICK ------>>> !!!!!!!
       //imageB.addActionListener(e -> new OneProductForm(res, id).show());
    }
    
}
