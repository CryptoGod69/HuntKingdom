/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.WebBrowser;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Legion
 */

   public class MapForm extends Form {
    
     public MapForm()
     {
         this.setTitle("map");
         
         this.setLayout(BoxLayout.y());      
        WebBrowser web=new WebBrowser("http://127.0.0.1:8000/youssef");//met ici le lien de la map
        this.addAll(web);
}
}
