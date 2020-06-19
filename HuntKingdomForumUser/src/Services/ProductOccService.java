/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Models.ProductOcc;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rym
 */
public class ProductOccService {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




    public ArrayList<ProductOcc> productOccs;
    public ArrayList<ProductOcc> ProduitOccList;
     public ArrayList<ProductOcc> mapRecherche;
     String chaine;
    public static ProductOccService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ProductOccService() {
         req = new ConnectionRequest();
    }

    public static ProductOccService getInstance() {
        if (instance == null) {
            instance = new ProductOccService();
        }
        return instance;
    }

    public boolean addProduct(ProductOcc p) {
        String url = Statics.BASE_URL+"/create?nbVue="+ p.getNbVue()+ "&proprietaire=" + p.getProprietaire()+"&nom=" + p.getNom() + "&categorie=" + p.getCategorie()+ "&description=" + p.getDescription()+"&image="+p.getImage()+  "&prix=" + p.getPrix()+ "&etat=" + p.getEtat()+ "&prodLikes=" + p.getProdLikes()+ "&dateachat=" + p.getDateachat(); //création de l'URL
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

    public ArrayList<ProductOcc> parseProduits(String jsonText){
        try {
            productOccs=new ArrayList<>();
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
                ProductOcc p =new ProductOcc ();
                float id = Float.parseFloat(obj.get("id").toString());
                float nbVue = Float.parseFloat(obj.get("nbVue").toString());
                //float ProdLikes = Float.parseFloat(obj.get("ProdLikes").toString());              
                p.setNbVue((int)nbVue);
                p.setProdLikes(obj.get("ProdLikes").toString());
               // p.setProdLikes((int)ProdLikes);
                 p.setId((int)id);
                p.setProprietaire(obj.get("proprietaire").toString());
                p.setNom(obj.get("nom").toString());
                p.setCategorie(obj.get("categorie").toString());
                p.setDescription(obj.get("description").toString());
                p.setImage(obj.get("image").toString());
                p.setPrix(obj.get("prix").toString());
               // p.setPrix(((int)Float.parseFloat(obj.get("prix").toString())));
                p.setEtat(obj.get("etat").toString());
                p.setDateachat(obj.get("dateAchat").toString());
                
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                productOccs.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return productOccs;
    }
    
    
    public ArrayList<ProductOcc> parseOneProduitOcc(String jsonText){
        try {
            ProduitOccList=new ArrayList<>();
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
            for(Map<String, Object> obj : list){
                //Création des tâches et récupération de leurs données
                ProductOcc t = new ProductOcc();
              /*  float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id); */
                
                t.setNom(obj.get("nom").toString());
                
                t.setProprietaire(obj.get("proprietaire").toString());
                
                t.setCategorie(obj.get("categorie").toString());
                
                t.setDescription(obj.get("description").toString());
                
                 t.setPrix(obj.get("prix").toString());
                
               // t.setPrix((float)Float.parseFloat(obj.get("prix").toString()));
                
                float id = Float.parseFloat(obj.get("id").toString());
                float nbVue = Float.parseFloat(obj.get("nbVue").toString());
                //float ProdLikes = Float.parseFloat(obj.get("ProdLikes").toString());              
                t.setNbVue((int)nbVue);
                t.setProdLikes(obj.get("ProdLikes").toString());
                
                t.setImage(obj.get("image").toString());
                
                 t.setEtat(obj.get("etat").toString());
                t.setDateachat(obj.get("dateAchat").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                ProduitOccList.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return ProduitOccList;
    }
    
    public ArrayList<ProductOcc> getAllProducts(){
        String url = Statics.BASE_URL+"/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                productOccs =parseProduits(new String(req.getResponseData()));
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
        return productOccs;
    }
     public ArrayList<ProductOcc> getOneProductOcc(int id){
        String url = Statics.BASE_URL+"/find/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                productOccs =parseOneProduitOcc(new String(req.getResponseData()));
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
        return productOccs;
    }
     public ArrayList<ProductOcc> rechercher(String nom)
     {
        String url = Statics.BASE_URL+"/findP/"+ nom ;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                mapRecherche = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         
         return mapRecherche;
     }
     public String LikeProducts(int idUser, int idProd){
         
        String url = Statics.BASE_URL+"/likeM"+"/"+idUser+"/"+idProd;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                chaine =(new String(req.getResponseData()));
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
        return chaine;
    }
}

    

