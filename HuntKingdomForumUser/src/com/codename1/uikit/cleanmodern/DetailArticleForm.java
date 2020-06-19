/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.Article;
import Models.Post;
import Models.UserM;
import Services.ArticleService;
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
public class DetailArticleForm extends BaseForm {
    public  DetailArticleForm (Resources res,Article a){
         //********PARTIE FIXE********//

        super(a.getCategorie(), BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(a.getCategorie());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        String url = "http://localhost/Huntkingdom/web/Uploads/article/photo/"+a.getImage();
        //System.out.println(url);
       int deviceWidth=Display.getInstance().getDisplayWidth();
       Image placeholder = Image.createImage(deviceWidth / 10,deviceWidth / 10,0xbfc9d2);
       EncodedImage encImage=EncodedImage.createFromImage(placeholder, false);
       Image img = URLImage.createToStorage(encImage, "Medium_" + url, url, URLImage.RESIZE_SCALE);
       addTab(swipe, img, spacer1, a.getTitre()+"\n"+a.getSousTitre());
        
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
        back.addActionListener(e -> new ArticleForm(res).show());
        back.setUIID("Back");
        Container cntBack = BorderLayout.west(back);
        add(cntBack);
      /*  
        ArrayList<Post> P = PostService.getInstance().getAllPostsByTopic(topicId);
        
        for(int i=0; i<P.size(); i++){
            addButton(res, P.get(i).getMessage(), P.get(i).getAuthor(), P.get(i).getDate());
        }
        */
        createElement(res,a);
          
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
    private void createElement(Resources res, Article a) {

      //creation container en tant que center
       Container cnt = new Container(new BorderLayout (4));
     //creation des labels
       Label adminLabel = new Label("  Posted by Admin | ");
       Label DateLabel = new Label(a.getDate());
       FontImage.setMaterialIcon(DateLabel, FontImage.MATERIAL_DATE_RANGE);
       TextArea chapeauLabel = new TextArea("      "+a.getChapeau());
      // chapeauLabel.setTextPosition(RIGHT);
       TextArea texteLabel = new TextArea(a.getTexte());
       chapeauLabel.setEditable(false);
       chapeauLabel.setGrowByContent(true);
       chapeauLabel.setUIID("NewsTopLine2");
       texteLabel.setEditable(false);
       texteLabel.setUIID("NewsTopLine2");
       texteLabel.setGrowByContent(true); 
       Button likeButton = new Button("");
       
       if(ArticleService.getInstance().getLikeConnection(a.getIdA(), UserM.id))
       {
       likeButton.setText("Unlike");
       likeButton.setUIID("Button2");
       }
       else {  
       likeButton.setText("Like");
       likeButton.setUIID("Button");
       }
       likeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               if (likeButton.getText().equals("Like")){
               ArticleService.getInstance().likerArticle(a.getIdA());
               }else {   
               ArticleService.getInstance().unlikerArticle(a.getIdA());
               }
               new DetailArticleForm(res,a).show();
                }
       });
    //   likeButton.addActionListener(e -> ToastBar.showMessage("Article Liked", FontImage.MATERIAL_INFO));
       
        Button signalButton = new Button(" Report ");
       signalButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                ArticleService.getInstance().signalerArticle(a.getIdA());
           }
       });
       

      //ajout des labels au container
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                      BoxLayout.encloseX(adminLabel,DateLabel), 
                      BoxLayout.encloseX(chapeauLabel),
                      BoxLayout.encloseX(texteLabel),
                      BoxLayout.encloseX(likeButton,signalButton)
               ));
       add(cnt);
   }

}