/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.ProduitNeuf;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kais
 */
public class ProduitNeufService {
    
    public ArrayList<ProduitNeuf> ProduitNeufList;
    public static ProduitNeufService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ProduitNeufService() {
         req = new ConnectionRequest();
    }
    
    public static ProduitNeufService getInstance() {
        if (instance == null) {
            instance = new ProduitNeufService();
        }
        return instance;
    }
    
    //id,title,author,nbPosts,lastPostDate,state
    
    /*public boolean addProduitNeuf(ProduitNeuf t) {
        String url = Statics.BASE_URL + "/topics/" + t.getId() + "/" + t.getNom() + "/" + t.getCategorie() + "/" + t.getDescription()+ "/" + t.getPrix()+ "/" + t.getNbProduits()+ "/" + t.getImage(); //création de l'URL
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
                la réponse reçue correspond à une autre URL(get par exemple)
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    */
    public ArrayList<ProduitNeuf> parseProduitNeufs(String jsonText){
        try {
            ProduitNeufList=new ArrayList<>();
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
            Map<String,Object> produitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)produitListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                ProduitNeuf t = new ProduitNeuf();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                t.setNom(obj.get("nom").toString());
                
                t.setCategorie(obj.get("categorie").toString());
                
                t.setDescription(obj.get("description").toString());
                
                t.setPrix((float)Float.parseFloat(obj.get("prix").toString()));
                
                float nbProduits = Float.parseFloat(obj.get("nbProduits").toString());
                t.setNbProduits((int)nbProduits);
                
                t.setImage(obj.get("image").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                ProduitNeufList.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return ProduitNeufList;
    }
    
    
    public ArrayList<ProduitNeuf> parseOneProduitNeufs(String jsonText){
        try {
            ProduitNeufList=new ArrayList<>();
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
            
            String jsonTextC;
            
            jsonTextC="["+jsonText+"]";

            Map<String,Object> produitListJson = j.parseJSON(new CharArrayReader(jsonTextC.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)produitListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                ProduitNeuf t = new ProduitNeuf();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                t.setNom(obj.get("nom").toString());
                
                t.setCategorie(obj.get("categorie").toString());
                
                t.setDescription(obj.get("description").toString());
                
                t.setPrix((float)Float.parseFloat(obj.get("prix").toString()));
                
                float nbProduits = Float.parseFloat(obj.get("nbProduits").toString());
                t.setNbProduits((int)nbProduits);
                
                t.setImage(obj.get("image").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                ProduitNeufList.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return ProduitNeufList;
    }
    
    public ArrayList<ProduitNeuf> getAllProduitNeufs(){
        String url = Statics.BASE_URL+"/shopneuf/mobile/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitNeufList = parseProduitNeufs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ProduitNeufList;
    }
    
   public ArrayList<ProduitNeuf> getOneProduct(int idProduct){
        String url = Statics.BASE_URL+"/shopneuf/mobile/"+idProduct;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitNeufList = parseOneProduitNeufs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ProduitNeufList;
    }
}