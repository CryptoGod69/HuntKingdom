/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Utils.Statics;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.EventForm;
import com.codename1.uikit.cleanmodern.PostForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Omar
 */
public class EvenementService {
    
    public ArrayList<Evenement> events;
    
    public static EvenementService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private EvenementService() {
         req = new ConnectionRequest();
    }
    
    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }
    
    
    
    
    public boolean addEvent(Resources res, Evenement e,String dateDebut,String dateFin) {
        String url = Statics.BASE_URL + "/events/new?Titre=" + e.getTitre() + "&Organisateur=" + e.getOrganisateur() 
                +"&Lieu=" + e.getLieu() + "&Description=" + e.getDescription() 
                + "&Type=" + e.getType() + "&NbrPart=" + e.getNbrPart() + "&Date=" + dateDebut +
                "&DateFin=" + dateFin + "&CmpPart=" + e.getCmpPart() + "&Image="+"XYZ"
                ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        new EventForm(res).show();
        return resultOK;
    }
    
    public boolean editEvent(Resources res, Evenement e,String dateDebut,String dateFin) {
        String url = Statics.BASE_URL + "/events/mobile/edit/"+e.getIdEvent()+"?Titre=" + e.getTitre() + "&Organisateur=" + e.getOrganisateur() 
                +"&Lieu=" + e.getLieu() + "&Description=" + e.getDescription() 
                + "&Type=" + e.getType() + "&NbrPart=" + e.getNbrPart() + "&Date=" + dateDebut +
                "&DateFin=" + dateFin + "&CmpPart=" + e.getCmpPart() + "&Image="+"XYZ"
                ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        new EventForm(res).show();
        return resultOK;
    }
    
    public boolean DeleteEvent(Resources res,Evenement e) {
        String url = Statics.BASE_URL + "/events/mobile/delete?id=" + e.getIdEvent();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        new EventForm(res).show();
        return resultOK;
    }
    
    public ArrayList<Evenement> parseEvents(String jsonText){
        
            ArrayList<Evenement> evenements = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
        try {
            
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Evenement e = new Evenement();

                
                float idEvent = Float.parseFloat(obj.get("idEvent").toString());
                e.setIdEvent((int)idEvent);
                System.out.println("test test test :"+idEvent);
             
                e.setTitre(obj.get("titreEvent").toString());
                
                e.setOrganisateur(obj.get("orgaEvent").toString());
                
                e.setDescription(obj.get("descEvent").toString());
                
                e.setLieu(obj.get("lieuEvent").toString());
                
                e.setType(obj.get("typeEvent").toString());
               
                float nbrPartEventParse = Float.parseFloat(obj.get("nbrPartEvent").toString());
                e.setNbrPart((int)nbrPartEventParse);
               
                float cmpPartParse = Float.parseFloat(obj.get("cmpPartEvent").toString());
                e.setCmpPart((int)cmpPartParse);
                
              //  e.setImage(obj.get("imageEvent").toString());



                java.util.LinkedHashMap date=(java.util.LinkedHashMap)obj.get("dateEvent");
                Double dateD=(Double)date.get("timestamp");
                Date date_d=new Date((dateD.longValue())*1000);
                e.setDate(date_d);
                
                  java.util.LinkedHashMap dateFin=(java.util.LinkedHashMap)obj.get("dateFinEvent");
                Double dateF=(Double)dateFin.get("timestamp");
                Date date_f=new Date((dateF.longValue())*1000);
                e.setDateFin(date_f);
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                evenements.add(e);
                System.out.println("eve: "+e.toString());
                
            }
            System.out.println("bonjouuuuuuur= "+evenements);
            
        } catch (IOException ex) {
            
        }
         
        return evenements;
    }
    
    public ArrayList<Evenement> getAllEventsByIdEvent(int IdEvent){
        String url = Statics.BASE_URL+"/events/findByIdEvent/"+IdEvent;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
         
            public void actionPerformed(NetworkEvent evt) {
                ArrayList<Evenement> events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
    public ArrayList<Evenement> getAllEvents(){
        String url = Statics.BASE_URL+"/events/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

         
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("test test 2 :"+events);
        return events;
    }
    
    public ArrayList<Evenement> getOneEvent(int idEvent){
        String url = Statics.BASE_URL+"/events/mobile/one/"+idEvent;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseOneEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
public ArrayList<Evenement> parseOneEvents(String jsonText){
        try {
            JSONParser j = new JSONParser();

           
            String jsonTextC;
           
            jsonTextC="["+jsonText+"]";

            Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonTextC.toCharArray()));
           
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventListJson.get("root");
           
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                Evenement e = new Evenement();

                
                float idEvent = Float.parseFloat(obj.get("idEvent").toString());
                e.setIdEvent((int)idEvent);
                System.out.println("test test test :"+idEvent);
             
                e.setTitre(obj.get("titreEvent").toString());
                
                e.setOrganisateur(obj.get("orgaEvent").toString());
                
                e.setDescription(obj.get("descEvent").toString());
                
                e.setLieu(obj.get("lieuEvent").toString());
                
                e.setType(obj.get("typeEvent").toString());
               
                float nbrPartEventParse = Float.parseFloat(obj.get("nbrPartEvent").toString());
                e.setNbrPart((int)nbrPartEventParse);
               
                float cmpPartParse = Float.parseFloat(obj.get("cmpPartEvent").toString());
                e.setCmpPart((int)cmpPartParse);

                java.util.LinkedHashMap date=(java.util.LinkedHashMap)obj.get("dateEvent");
                Double dateD=(Double)date.get("timestamp");
                Date date_d=new Date((dateD.longValue())*1000);
                e.setDate(date_d);
                
                  java.util.LinkedHashMap dateFin=(java.util.LinkedHashMap)obj.get("dateFinEvent");
                Double dateF=(Double)dateFin.get("timestamp");
                Date date_f=new Date((dateF.longValue())*1000);
                e.setDateFin(date_f);
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                events.add(e);
            }
           
           
        } catch (IOException ex) {
           
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
       
        */
        return events;
    }
    
}