/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Article;
import Models.UserM;
import Services.ArticleService;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Legion
 */
public class ArticleService {
     public ArrayList<Article> articles;
    String s;
    public static ArticleService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    boolean ret;
    private ArticleService() {
         req = new ConnectionRequest();
    }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    public boolean addArticle(Article a) {
        String url = Statics.BASE_URL+"/articles/new" + a.getCategorie() + "/" + a.getTitre() + "/" + a.getSousTitre() + "/" + a.getChapeau() + "/" + a.getTexte()+ "/" +a.getDate() + "/" + a.getImage() +"/" +a.getNbLikes();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Article> parseArticles(String jsonText){
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> articlesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)articlesListJson.get("root");
            for(Map<String,Object> obj : list){
                Article a = new Article();
                float id = Float.parseFloat(obj.get("ida").toString());
                a.setIdA((int)id);
                a.setNbLikes(((int)Float.parseFloat(obj.get("nblikes").toString())));
                a.setNbSignal(((int)Float.parseFloat(obj.get("nbSignal").toString())));
                a.setCategorie(obj.get("categorie").toString());
                a.setChapeau(obj.get("chapeau").toString());
                a.setTitre(obj.get("titre").toString());
                a.setSousTitre(obj.get("soustitre").toString());
                a.setTexte(obj.get("texte").toString());
                
                //*****formater la date 
                java.util.LinkedHashMap date=(java.util.LinkedHashMap)obj.get("date");
                Double dateA=(Double)date.get("timestamp");    
                Date dateAr=new Date((dateA.longValue())*1000);
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String dateAform=formater.format(dateAr);
                a.setDate(dateAform);
                a.setImage(obj.get("image").toString());
                articles.add(a);
            }            
        } catch (IOException ex) {
        }
        return articles;
    }
    
    public ArrayList<Article> getAllArticles(){
        String url = Statics.BASE_URL+"/articles/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    public ArrayList<Article> findArticle(int ida){
        String url=Statics.BASE_URL+"/articles/find/"+ida;
        req.setUrl(url);
        req.setPost(false);        
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    public String likerArticle(int ida){
        String url=Statics.BASE_URL+"/likerJ/"+ida+"/"+UserM.id+"/"+UserM.username;
        req.setUrl(url);
        req.setPost(false);  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                s = new String( req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return s;
    }
   
    public String unlikerArticle(int ida){
        String url=Statics.BASE_URL+"/removeLike/"+ida+"/"+UserM.id;
        req.setUrl(url);
        req.setPost(false);  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                s = new String( req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return s;
    }
    
      public String signalerArticle(int ida){
        String url=Statics.BASE_URL+"/signalerJ/"+ida+"/"+UserM.id+"/"+UserM.username;
        req.setUrl(url);
        req.setPost(false);  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                s = new String( req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return s;
    }
       public boolean parseLikes(String jsonText){
        try {
           
            UserM.isConnected = false; 
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
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                
               return true;
           
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return   false;
    }
      public boolean getLikeConnection(int idA , int idU){
        String url = Statics.BASE_URL+"/existLike/" + idA + "/" + idU;
               req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                ret=parseLikes(new String(req.getResponseData()));
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return   ret;
    }
       
    
}
