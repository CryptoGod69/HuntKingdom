/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Models.Periodechasse;
import Models.UserM;
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
public class PeriodechasseService {
    public ArrayList<Periodechasse> periodes;
    
    public static PeriodechasseService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private PeriodechasseService() {
         req = new ConnectionRequest();
    }

    public static PeriodechasseService getInstance() {
        if (instance == null) {
            instance = new PeriodechasseService();
        }
        return instance;
    }


    public ArrayList<Periodechasse> parsePeriodes(String jsonText){
        try {
            periodes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> periodesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)periodesListJson.get("root");
            for(Map<String,Object> obj : list){
                Periodechasse p = new Periodechasse();
                float id = Float.parseFloat(obj.get("idp").toString());
                p.setIdP((int)id);
               // p.setSuperficie(((int)Float.parseFloat(obj.get("superficie").toString())));
                p.setCategorie(obj.get("categorie").toString());
                p.setAdresse(obj.get("adresse").toString());
                                
                java.util.LinkedHashMap dateD=(java.util.LinkedHashMap)obj.get("datedebut");
                Double dateDe=(Double)dateD.get("timestamp");    
                Date date_Debut=new Date((dateDe.longValue())*1000);
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String dateAform=formater.format(date_Debut);
                p.setDateDebut(dateAform);
                
                java.util.LinkedHashMap dateF=(java.util.LinkedHashMap)obj.get("datefin");
                Double dateFi=(Double)dateF.get("timestamp");    
                Date date_fin=new Date((dateFi.longValue())*1000);
                SimpleDateFormat formaterF = new SimpleDateFormat("yyyy-MM-dd");
                String dateFform=formater.format(date_fin);
                p.setDateFin(dateFform);
                  
                Double superficie=Double.valueOf(obj.get("superficie").toString());
                int superf =superficie.intValue();
                p.setSuperficie(superf);
                
                p.setImage(obj.get("image").toString());
                periodes.add(p);
            }            
        } catch (IOException ex) {
        }
        return periodes;
    }
    
    public ArrayList<Periodechasse> getAllPeriodes(){
        String url = Statics.BASE_URL+"/periodes/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                periodes = parsePeriodes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return periodes;
    }
    public String notifyMe(int idP){
        String url=Statics.BASE_URL+"/notifyMe/"+idP+"/"+UserM.email;
        req.setUrl(url);
        req.setPost(false);  
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 new String( req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return "";
    }
    
    
    
}

