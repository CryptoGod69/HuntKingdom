/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.User;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kaisk
 */
public class UserService {
     public ArrayList<User> userList;
     public User userNOW;
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private UserService() {
         req = new ConnectionRequest();
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<User> parseProduitNeufs(String jsonText){
        try {
            userList=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User t = new User();
                
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                t.setUsername(obj.get("username").toString());
                
                
                
                t.setEmail(obj.get("email").toString());
                
                
                
                boolean enabled = Boolean.parseBoolean(obj.get("enabled").toString());
                t.setEnabled(enabled);
                
                
                
                t.setPassword(obj.get("password").toString());
                
              
              
                
                t.setRoles(obj.get("roles").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                userList.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return userList;
    }
    public ArrayList<User> getAllUser(){
        String url = Statics.BASE_URL+"/user/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userList = parseProduitNeufs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return userList;
    }
}
