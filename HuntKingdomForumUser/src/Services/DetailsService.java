/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Article;
import Models.Details;
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
public class DetailsService {
     public ArrayList<Details> details;
   
    public static DetailsService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private DetailsService() {
         req = new ConnectionRequest();
    }

    public static DetailsService getInstance() {
        if (instance == null) {
            instance = new DetailsService();
        }
        return instance;
    }

    public boolean addDetails(Details d) {
        String url = Statics.BASE_URL+"/details/new" + d.getArme() + "/" + d.getArticle() + "/" + d.getPeriode();
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

    public ArrayList<Details> parseDetails(String jsonText){
        try {
            details=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> detailsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)detailsListJson.get("root");
            for(Map<String,Object> obj : list){
                Details d = new Details();
                float id = Float.parseFloat(obj.get("idd").toString());
                d.setIdD((int)id);
                //d.setPeriode(((int)Float.parseFloat(obj.get("periode").toString())));
                d.setArme(obj.get("arme").toString());
                d.setArticle(obj.get("article").toString());
     
                details.add(d);
            }            
        } catch (IOException ex) {
        }
        return details;
    }
    
    public ArrayList<Details> getAllDetails(){
        String url = Statics.BASE_URL+"/details/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                details = parseDetails(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return details;
    }
    public ArrayList<Details> verifD(int id){
        String url = Statics.BASE_URL+"/verifD/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                details = parseDetails(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return details;
    }
}
