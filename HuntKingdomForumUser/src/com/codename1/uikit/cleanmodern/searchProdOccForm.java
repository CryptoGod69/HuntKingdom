/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.ProductOcc;
import Services.ProductOccService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Rym
 */
public class searchProdOccForm extends BaseForm {
    public static ArrayList<Container> al = new ArrayList<Container>();
    public static Container main;
    
    public searchProdOccForm(Resources res,String nom) {
        //********PARTIE FIXE********//

        super("Produits d'occasion", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produits d'occasion");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Label spacer3 = new Label();
        addTab(swipe, res.getImage("hunting1.jpg"), spacer1, "liste des produits");
        addTab(swipe, res.getImage("hunting2.jpg"), spacer2, "Be gentle");
        addTab(swipe, res.getImage("hunting3.jpg"), spacer3, "Spam = Ban");
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
        Component.setSameSize(radioContainer, spacer1, spacer2, spacer3);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
//********PARTIE TOPICS********//

        //Bouton New Topic
       Button newProduct = new Button("Add product");
        newProduct.addActionListener(e -> new addProductForm(res).show());
        newProduct.setUIID("NewProduct"); 
        Container cnt = BorderLayout.west(newProduct);
        add(cnt);
        for (ProductOcc p: ProductOccService.getInstance().rechercher(nom))
        {
            createElement(res,p);
        }
       /* ArrayList<Topic> T = TopicService.getInstance().getAllTopics();
        
        for(int i=0; i<T.size(); i++){
            addButton(res, T.get(i).getId(), T.get(i).getTitle(), T.get(i).getAuthor(), T.get(i).getLastPostDate(), T.get(i).getNbPosts(), T.get(i).getState());
        } */ 
        //addButton(res, "Comment chasser ?", "Omar", new Date(), 23, "open");
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
    private void createElement(Resources res,ProductOcc p) {
 
       String url ="http://localhost/User/User/web/images/"+p.getImage();
       System.out.println(url);
       int deviceWidth=Display.getInstance().getDisplayWidth();
       Image placeholder = Image.createImage(deviceWidth / 10,deviceWidth / 10,0xbfc9d2);
       EncodedImage encImage=EncodedImage.createFromImage(placeholder, false);
       Image img = URLImage.createToStorage(encImage, "Medium_" + url, url, URLImage.RESIZE_SCALE);
      /* if(state.equals("solved")){
           img = res.getImage("solved.png");
       }else{
           img = res.getImage("open.png");
       } */
      // Image img;
       int height = Display.getInstance().convertToPixels(8f);
       int width = Display.getInstance().convertToPixels(8f);
        Button imageA = new Button(img.fill(width, height));
       imageA.setUIID("Label");
       Container cnt = BorderLayout.west(imageA);
       cnt.setLeadComponent(imageA);
       TextArea ta = new TextArea(p.getNom());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false); 
       /*Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(Nom);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false); */

       Label proprietaireLabel = new Label(p.getProprietaire());
       proprietaireLabel.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(proprietaireLabel, FontImage.MATERIAL_ASSIGNMENT_IND);
       
       Date date = Calendar.getInstance().getTime();  
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        Label dateAchatLabel = new Label(p.getDateachat());
      // Label dateAchatLabel = new Label(dateFormat.format(p.getDateachat()));
       FontImage.setMaterialIcon(dateAchatLabel, FontImage.MATERIAL_DATE_RANGE);
       
       Label nbVueLabel = new Label(Integer.toString(p.getNbVue()));
       nbVueLabel.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(nbVueLabel, FontImage.MATERIAL_FORMAT_LIST_NUMBERED);
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(proprietaireLabel, dateAchatLabel, nbVueLabel)
               ));
       Container cnt1 = new Container(BoxLayout.y());
        Button b = new Button("Retour");
       add(cnt);
       add(cnt1);
       cnt1.add(b);
       //onCLICK ------>>> !!!!!!!
       int idProduct=p.getId();
      imageA.addActionListener(e -> new OneProductOccForm(res,idProduct).show());
      b.addActionListener((e -> new ShopOccasionForm(res).show()));
}
}