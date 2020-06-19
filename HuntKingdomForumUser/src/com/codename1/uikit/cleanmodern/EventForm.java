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
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Services.EvenementService;
import Models.Evenement;
import Services.PostService;
import java.util.ArrayList;
import java.util.List;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class EventForm extends BaseForm {
    
    public EventForm(Resources res) {
        
//********PARTIE FIXE********//

        super("Forum", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Forum");
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

        //Bouton New Event
        Button newEvent = new Button("New Event");
       newEvent.addActionListener(e -> new NewEventForm(res).show());
        newEvent.setUIID("newEvent");
        Container cnt = BorderLayout.west(newEvent);
        add(cnt);
       
        ArrayList<Evenement> E = EvenementService.getInstance().getAllEvents();

        for(int i=0; i<E.size(); i++){
            addButton(res, E.get(i).getIdEvent(),E.get(i).getTitre(), E.get(i).getOrganisateur(), E.get(i).getLieu(), E.get(i).getDescription(),E.get(i).getType(),E.get(i).getNbrPart(),E.get(i).getDate(),E.get(i).getDateFin(),E.get(i).getCmpPart(),E.get(i).getImage());
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
    private void addButton(Resources res, int idEvent, String titre, String organisateur,String Lieu, String Description,String Type,int NbrPart,Date Date,Date DateFin,int CmpPart,String Image) {

       Image img = res.getImage("solved.png");
       int height = Display.getInstance().convertToPixels(8f);
       int width = Display.getInstance().convertToPixels(8f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       
       
       TextArea ta = new TextArea(titre);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label authorLabel = new Label(organisateur);
       authorLabel.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(authorLabel, FontImage.MATERIAL_ASSIGNMENT_IND);
       
       Date date = Calendar.getInstance().getTime();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
       Label DateDebLabel = new Label(dateFormat.format(Date));
       Label LieuLabel = new Label(Lieu);// Lieu

        Date dateFin = Calendar.getInstance().getTime();  //date fin
       DateFormat dateFormatFin = new SimpleDateFormat("yyyy-mm-dd");  
       Label DateFinLabel = new Label(dateFormat.format(dateFin)); //date fin label
       
       FontImage.setMaterialIcon(DateFinLabel, FontImage.MATERIAL_DATE_RANGE);
       FontImage.setMaterialIcon(DateDebLabel, FontImage.MATERIAL_DATE_RANGE);
       
       Label NbrPartLabel = new Label(Integer.toString(NbrPart)); //NbrPart label
       NbrPartLabel.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(NbrPartLabel, FontImage.MATERIAL_FORMAT_LIST_NUMBERED);
       
       Label DescLabel = new Label(Description);// description
       NbrPartLabel.setTextPosition(LEFT);

       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(LieuLabel, DateDebLabel, DateFinLabel)
               ));
       add(cnt);
       
       //onCLICK ------>>> !!!!!!!
       image.addActionListener(e -> new OneEventForm(res, idEvent).show());
   }
}