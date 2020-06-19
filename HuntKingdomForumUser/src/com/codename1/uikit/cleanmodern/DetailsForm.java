/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.Article;
import Models.Details;
import Models.Periodechasse;
import Models.Post;
import Services.ArticleService;
import Services.DetailsService;
import Services.PeriodechasseService;
import Services.PostService;
import Utils.Statics;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
 * @author Legion
 */
public class DetailsForm extends BaseForm {
    public  DetailsForm (Resources res,Periodechasse p){
         //********PARTIE FIXE********//

        super("Hunting Period's Details", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Hunting Period's Details");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        String url = "http://localhost/Huntkingdom/web/Uploads/article/photo/"+p.getImage();
        //System.out.println(url);
       int deviceWidth=Display.getInstance().getDisplayWidth();
       Image placeholder = Image.createImage(deviceWidth / 10,deviceWidth / 10,0xbfc9d2);
       EncodedImage encImage=EncodedImage.createFromImage(placeholder, false);
       Image img = URLImage.createToStorage(encImage, "Medium_" + url, url, URLImage.RESIZE_SCALE);
       addTab(swipe, img, spacer1, p.getCategorie());
        
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
        
//********PARTIE POSTS********//
       /* 
        //Bouton New Post
        Button newPost = new Button("New Post");
        newPost.addActionListener(e -> new NewPostForm(res, topicId).show());
        newPost.setUIID("NewPost");
        Container cntNew = BorderLayout.west(newPost);
        add(cntNew);
        */
        //Bouton Back
        Button back = new Button("Back");
        back.addActionListener(e -> new PeriodechasseForm(res).show());
        back.setUIID("Back");
        Container cntBack = BorderLayout.west(back);
        add(cntBack);
        
       /* for(Details d: DetailsService.getInstance().getAllDetails()){
            if(d.getPeriode()==p.getIdP())
                //if (d.getIdD()!= NULL)
                     createElement(res,d,p);   
        }*/
       
            createElement(res,p);   
      
         
    }
     private boolean exist( Periodechasse p ) {
        ArrayList<Details> D = DetailsService.getInstance().verifD(p.getIdP());
          if(D.isEmpty()){
              return false;
          }else 
              return true;
      }
   
    
     private String armeD(Periodechasse p){
        if (exist(p)){
            ArrayList<Details> D = DetailsService.getInstance().verifD(p.getIdP());
            for(Details d:D){
                return d.getArme();
            }
        }
        return "--";
    }
    
    private String articleD(Periodechasse p){
        if (exist(p)){
            ArrayList<Details> D = DetailsService.getInstance().verifD(p.getIdP());
            for(Details d:D){
                return d.getArticle();
            }
        }
        return "--";
    }
    
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
    
    //POUR AJOUTER UN POST
    private void createElement(Resources res,Periodechasse p) {
      
       Container cnt = new Container(new BorderLayout (4));
        Label infoLabel= new Label("INFORMATIONS:");
        Label startedLabel = new Label("STARTED: "+p.getDateDebut());
        Label endedLabel = new Label("     ENDED: "+p.getDateFin());
        FontImage.setMaterialIcon(startedLabel, FontImage.MATERIAL_DATE_RANGE);
        
        Label adresseLabel = new Label(p.getAdresse());
        FontImage.setMaterialIcon(adresseLabel,FontImage.MATERIAL_LOCATION_ON);

        Label superficieLabel = new Label("     THE FIELD'S SURFACE: "+Integer.toString(p.getSuperficie())+" M²");
        Label detailLabel= new Label("DETAILS:");
        Label armeLabel = new Label("    WEAPON:"+armeD(p));
        Label articleLabel = new Label("    ARTICLE: "+articleD(p));
     
        Button notifyButton = new Button("Notify Me!");
        notifyButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent evt) {
               PeriodechasseService.getInstance().notifyMe(p.getIdP());
               ToastBar.showMessage("MAIL SENT !", FontImage.MATERIAL_INFO);
           }
        });
        Button mapButton =new Button("+GOOGLE MAP");
        mapButton.setUIID("Button2");
         mapButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent evt) {
              new MapForm().show();
           }
        });
        cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                    infoLabel,
                    startedLabel,
                    endedLabel,
                    adresseLabel,
                    superficieLabel,
                    detailLabel,
                    BoxLayout.encloseX(armeLabel),
                    BoxLayout.encloseX(articleLabel),
                    notifyButton,
                    mapButton
               ));
       add(cnt);
   }

}