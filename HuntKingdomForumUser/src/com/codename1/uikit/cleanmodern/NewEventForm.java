/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Models.Evenement;
import Services.EvenementService;
import Services.UserService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.text.DateFormat;
import java.util.Date;


/**
 *
 * @author Omar
 */
public class NewEventForm extends BaseForm {
        
    public NewEventForm(Resources res) {
        
//********PARTIE FIXE********//

        super("New Event", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("New Event");
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
    
        /////////PARTIE NEW
     
        
        
        Label TitreLabel = new Label("Title: ");
        TextArea title = new TextArea();
        title.setSingleLineTextArea(true);
        add(TitreLabel);
        add(title);
        
        
        Label DescriptionLabel = new Label("Description: ");
        TextArea description = new TextArea();
        description.setSingleLineTextArea(true);
        add(DescriptionLabel);
        add(description);
        
        
         Label LieuLabel = new Label("Lieu: ");
        TextArea lieu = new TextArea();
        lieu.setSingleLineTextArea(true);
        add(LieuLabel);
        add(lieu);
           
        
         Label TypeLabel = new Label("Type: ");
        TextArea type = new TextArea();
        type.setSingleLineTextArea(true);
        add(TypeLabel);
        add(type);
        
        
            Label NbrPartLabel = new Label("Nbr Part: ");
        TextArea nbrPart = new TextArea();
        type.setSingleLineTextArea(true);
        add(NbrPartLabel);
        add(nbrPart);
        
     
     

Label DateDeb = new Label("Date Debut: ");
add(DateDeb);


Picker dateD = new Picker();
dateD.setType(Display.PICKER_TYPE_DATE);
add(dateD);
   
   
   
Label DateFin = new Label("Date Fin: ");
add(DateFin); 
Picker dateFin = new Picker();
dateFin.setType(Display.PICKER_TYPE_DATE);
String pattern = "yyyy-MM-dd";
SimpleDateFormat sdf = new SimpleDateFormat(pattern);
dateD.setFormatter(sdf);
dateFin.setFormatter(sdf);


        System.out.println(dateD.getText());
        System.out.println(dateFin.getFormatter());
        
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");



        add(dateFin);
        
        
        System.out.println(dateD.getDate());
        //Bouton OK
        Button ok = new Button("Ok");
        
        
        
        
        ok.addActionListener(e -> {     
            
                
                                Evenement ev;
                                ev = new Evenement(title.getText(), UserService.getInstance().userNOW.getUsername(),lieu.getText(),description.getText(),type.getText(),Integer.parseInt(nbrPart.getText()),null,null ,0);

            try {
               ev.setDate(formatter.parse(dateD.getText()));
               ev.setDateFin(formatter.parse(dateFin.getText()));
            } catch (ParseException ex) {
               
            }
            System.out.println("date : "+dateD.getText());
                
                EvenementService.getInstance().addEvent(res, ev,dateD.getText(),dateFin.getText());
                });
        
        ok.setUIID("Ok");
        Container cnt = BorderLayout.west(ok);
        add(cnt);
        
        //Bouton Back
        Button back = new Button("Back");
        back.addActionListener(e -> new EventForm(res).show());
        back.setUIID("Back");
        Container cntBack = BorderLayout.west(back);
        add(cntBack);
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
    
}

    
    
