/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

//import Models.ProductOcc;
import Models.RendezVous;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rym
 */
public class RendezVousService {
    public ArrayList<RendezVous> rendezvous;
    
    public static RendezVousService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private RendezVousService() {
         req = new ConnectionRequest();
    }

    public static RendezVousService getInstance() {
        if (instance == null) {
            instance = new RendezVousService();
        }
        return instance;
    }

    public boolean addRendezVous(RendezVous r) {
        String url =Statics.BASE_URL+"/createRdv?acheteur=" + r.getAcheteur() + "&vendeur=" + r.getVendeur()+ "&date=" + r.getDate()+ "&heure=" + r.getHeure()+ "&lieu=" + r.getLieu(); //création de l'URL
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
        return resultOK;
    }

    public ArrayList<RendezVous> parseProduits(String jsonText){
        try {
            rendezvous=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> produitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)produitsListJson.get("root"); 
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                RendezVous p =new RendezVous ();
                float id = Float.parseFloat(obj.get("id").toString());
                
                p.setId((int)id);
                p.setAcheteur(obj.get("acheteur").toString());
                p.setVendeur(obj.get("vendeur").toString());
                p.setDate(obj.get("date").toString());
                p.setHeure(obj.get("heure").toString());
                p.setLieu(obj.get("lieu").toString());
                
            
                
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                rendezvous.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return rendezvous;
    }
    
    public ArrayList<RendezVous> getAllRDV(){
        String url = Statics.BASE_URL+"/allRdv";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rendezvous =parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
     /*   req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                productOccs = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        }); */
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rendezvous;
    }
    
}
