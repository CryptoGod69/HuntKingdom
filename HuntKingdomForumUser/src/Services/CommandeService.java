/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Commande;
import Models.ProduitNeuf;
import Models.User;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kaisk
 */
public class CommandeService {
    public ArrayList<Commande> commandeList;
    
    public static CommandeService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private CommandeService() {
         req = new ConnectionRequest();
    }
    
    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
    }
    
    //id,title,author,nbPosts,lastPostDate,state
    
    public boolean addCommande(Resources res, Commande c) {
        String url = Statics.BASE_URL + "/shopneuf/mobile/commande/new?dateCommande=" + c.getDateCommande() + "&total=" + c.getTotal() + "&etat=" + c.getEtat() + "&quantite=" + c.getQuantite() + "&adresse=" + c.getAdresse() + "&idClient=" + c.getUsern().getId()+ "&idProduit=" + c.getProduitn().getId()+ "&phone=" + c.getPhone(); //création de l'URL
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
        //new CommandeForm(res, p.getTopicId()).show();
        return resultOK;
    }
    
    
    public boolean updateProduit(Resources res, Commande c) {
        String url = Statics.BASE_URL + "/shopneuf/mobile/commande/update/new?qte=" + c.getQuantite() + "&idProduit=" + c.getProduitn().getId(); //création de l'URL
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
        //new CommandeForm(res, p.getTopicId()).show();
        return resultOK;
    }
    
    public ArrayList<Commande> parseUser(String jsonText){
        try {
            commandeList=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> commandeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)commandeListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();
                
                float idCom = Float.parseFloat(obj.get("id").toString());
                c.setIdCom((int)idCom);
                
                c.setDateCommande(obj.get("dateCommande").toString());
                
                
                float total = Float.parseFloat(obj.get("total").toString());
                c.setTotal(total);
                
                float etat = Float.parseFloat(obj.get("etat").toString());
                c.setEtat((int)etat);
                
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                c.setQuantite((int)quantite);
                        
                
                c.setAdresse(obj.get("adresse").toString());
                  
                
                String produit = obj.get("produit").toString();
                String produitId = produit.substring(4,5);
                System.out.println(produitId); 
                c.setIdCom(Integer.parseInt(produitId));
                
                float phone = Float.parseFloat(obj.get("phone").toString());
                c.setPhone((int)phone);
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                commandeList.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return commandeList;
    }
    
    public ArrayList<Commande> getAllCommandesU(int idUser){
        String url = Statics.BASE_URL+"/shopneuf/mobile/commande/"+idUser;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandeList = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandeList;
    }
    
}
