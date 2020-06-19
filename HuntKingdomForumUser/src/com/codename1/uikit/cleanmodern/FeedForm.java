/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Services.PublicationService;
import Models.Publication;
import Models.Topic;
import Services.TopicService;
import Services.UserService;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.TextField;
import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class FeedForm extends BaseForm {
    
    public FeedForm(Resources res) {
        
//********PARTIE FIXE********//

        super("Feed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Feed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Label spacer3 = new Label();
        addTab(swipe, res.getImage("hunting1.jpg"), spacer1, "Welcome to the Forum");
        addTab(swipe, res.getImage("hunting2.jpg"), spacer2, "Be gentle");
        addTab(swipe, res.getImage("hunting3.jpg"), spacer3, "Spam = Ban");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        LocalNotification n = new LocalNotification();
n.setId("demo-notification");
n.setAlertBody("It's time to take a break and look at me");
n.setAlertTitle("Break Time!");
n.setAlertSound("/notification_sound_beep-01a.mp3");
Display.getInstance().scheduleLocalNotification(
        n,
        System.currentTimeMillis() + 1 * 1, // fire date/time
        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
);
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
        
//********PARTIE Feed********//
        Label LT = new Label("Publier: ");
        TextArea content = new TextArea();
        content.setSingleLineTextArea(true);
        add(LT);
        add(content);
      

        //Bouton OK
        Button ok = new Button("Ok");
        ok.addActionListener(e -> PublicationService.getInstance().addPublication(res, new Publication(content.getText(), UserService.getInstance().userNOW.getUsername())));
  
        ok.setUIID("Ok");
        Container cnt = BorderLayout.west(ok);
        add(cnt);
        
        ArrayList<Publication> P = PublicationService.getInstance().getAllPublications();
        
        for(int i=0; i<P.size(); i++){
            addButton(res, P.get(i).getId(), P.get(i).getIdUser(), P.get(i).getDatePub(), P.get(i).getContentPub(), P.get(i).getLikesPub(), P.get(i));
        }
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
    private void addButton(Resources res, int id, String idUser, Date datePub, String contentPub, int likesPub, Publication p) {

       Image img = res.getImage("solved.png");

       int height = Display.getInstance().convertToPixels(8f);
       int width = Display.getInstance().convertToPixels(8f);
       Button imge = new Button(img.fill(width, height));
       imge.setUIID("Label");
       Container cntt = BorderLayout.west(imge);
       cntt.setLeadComponent(imge);
       
       TextArea ta = new TextArea(contentPub);
       ta.setUIID("contentPub");
       ta.setEditable(false);

       Label authorLabel = new Label(idUser);
       authorLabel.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(authorLabel, FontImage.MATERIAL_ASSIGNMENT_IND);
       
       Date date = Calendar.getInstance().getTime();  
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
       Label lastPostDateLabel = new Label(dateFormat.format(datePub));
       FontImage.setMaterialIcon(lastPostDateLabel, FontImage.MATERIAL_DATE_RANGE);
       
       Label postsNumberLabel = new Label(Integer.toString(likesPub));
       postsNumberLabel.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(postsNumberLabel, FontImage.MATERIAL_FORMAT_LIST_NUMBERED);
       
       cntt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(authorLabel, lastPostDateLabel, postsNumberLabel)
               ));
       add(cntt);
      Button like = new Button("like");
        like.addActionListener(e -> {
            try {
                PublicationService.getInstance().LikePublication(res,id);
            } catch (Exception f) {
                
            }
            
        });
        add(like);
   
       //onCLICK ------>>> !!!!!!!
       
           imge.addActionListener(e -> {
                   new ModifierPublicationForm(res,p).show();
              
                   });
               
       }
     
       
   }
