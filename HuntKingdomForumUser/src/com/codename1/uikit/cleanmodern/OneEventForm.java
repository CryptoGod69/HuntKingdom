/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;


import Models.Evenement;
import Services.EvenementService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
import java.util.ArrayList;
import com.codename1.share.FacebookShare;
/**
 *
 * @author kaisk
 */
public class OneEventForm extends BaseForm{
    public OneEventForm(Resources res,int idEvent)  {
       
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

       
        ArrayList<Evenement> T = EvenementService.getInstance().getOneEvent(idEvent);
       
       addButton(res, T.get(0));
            
       
       
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
    private void addButton(Resources res, Evenement e)   {
   
       Container cnt = new Container(BoxLayout.y());
       Container cnt2 = new Container(BoxLayout.x());

       TextArea taTitle = new TextArea("Title : "+e.getTitre());
       taTitle.setUIID("NewsTopLine");
       taTitle.setEditable(false);
       
       TextArea taType = new TextArea("Type : "+e.getType());
       taType.setUIID("NewsTopLine");
       taType.setEditable(false);
       
       TextArea taDesc = new TextArea("Description : "+e.getDescription());
       taDesc.setUIID("NewsTopLine");
       taDesc.setEditable(false);
       
       TextArea taPlace = new TextArea("Place  : "+e.getLieu());
       taPlace.setUIID("NewsTopLine");
       taPlace.setEditable(false);
       
       TextArea taOrg = new TextArea("Organizer : "+e.getOrganisateur());
       taOrg.setUIID("NewsTopLine");
       taOrg.setEditable(false);
       
       TextArea taNbr = new TextArea("Number of paticipant : "+e.getNbrPart());
       taNbr.setUIID("NewsTopLine");
       taNbr.setEditable(false);
       
       TextArea taDateS = new TextArea("Date of start : "+e.getDate());
       taDateS.setUIID("NewsTopLine");
       taDateS.setEditable(false);
       
       TextArea taDateF = new TextArea("Date of end : "+e.getDateFin());
       taDateF.setUIID("NewsTopLine");
       taDateF.setEditable(false);
       
       Button deleteButton = new Button("Delete");
       Button editButton = new Button("Edit");
       ShareButton sb = new ShareButton();
       sb.setText("Share Content");
       Button supportButton = new Button("Support");

       cnt.add(taTitle);
       cnt.add(taType);
       cnt.add(taDesc);
       cnt.add(taPlace);
       cnt.add(taOrg);
       cnt.add(taNbr);
       cnt.add(taDateS);
       cnt.add(taDateF);
       cnt2.add(editButton);
       cnt2.add(deleteButton);
       cnt2.add(supportButton);
       cnt2.add(sb);
       cnt.add(cnt2);
         
         
         
         Message m = new Message("Body of message");
       
       add(cnt);
///////////////////////////////////////////////////////////////////// PARTIE DIALOG

       //onCLICK ------>>> !!!!!!!
       deleteButton.addActionListener(l -> {
            EvenementService.getInstance().DeleteEvent(res, e);
           }
       );
        supportButton.addActionListener(l -> {
Display.getInstance().sendMessage(new String[] {"mouradtlili66@gmail.com"}, "Subject of message", m);
           }
       );
       editButton.addActionListener(f -> {
           new EditEventForm(res,e).show();
       });
      
   }
    
    
}
