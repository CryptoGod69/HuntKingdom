/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuntKingdom;

import Services.TopicService;
import Services.PostService;
import Models.Topic;
import Models.Post;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Omar
 */
public class HuntKingdom {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        //Database Connexion
        DataSource ds = DataSource.getInstance();
        System.out.println(ds.hashCode());
        
        //Service
        TopicService ts = new TopicService();
        PostService ps = new PostService();
        List<Topic> T;
        
        //creation topics
        Topic t1 = new Topic("Les munitions pour sanglier ?", "muftox", "09/02/2020");
        ts.createTopic(t1);
        Topic t2 = new Topic("Quelle periode pour chasser le lapin ?", "mourad", "09/02/2020");
        ts.createTopic(t2);
        Topic t3 = new Topic("Je viens d attraper un gros cerf", "muftox", "09/02/2020");
        ts.createTopic(t3);
        
        //creation posts
        Post p1 = new Post(1,"mourad", "09/02/2020", "Salut, prends un bon calibre 12");
        ps.createPost(p1);
        Post p2 = new Post(2,"sirine", "09/02/2020", "Le pringtemps est la bonne période");
        ps.createPost(p2);
        Post p3 = new Post(2,"sam", "09/02/2020", "Je confirmes !");
        ps.createPost(p3);
        Post p4 = new Post(3,"sam", "09/02/2020", "Super mec ! mamiii novaaa");
        ps.createPost(p4);
        Post p5 = new Post(3,"muftox", "09/02/2020", "mammiiiiiii");
        ps.createPost(p5);
        
        //BOUCLE AFFICHAGE
        T = ts.readTopicList();
        System.out.println("\n\n");
        for(int i=0; i<T.size(); i++){
            System.out.println(T.get(i));
            List<Post> P = ps.readPostTopic(T.get(i).getId());
            for(int j=0; j<P.size(); j++){
                System.out.println(P.get(j));
            }
        }
        
        //Suppression topic et post avec id
        ts.deleteTopic(3);
        ps.deletePost(2);
        
        //BOUCLE AFFICHAGE
        T = ts.readTopicList();
        System.out.println("\n\n");
        for(int i=0; i<T.size(); i++){
            System.out.println(T.get(i));
            List<Post> P = ps.readPostTopic(T.get(i).getId());
            for(int j=0; j<P.size(); j++){
                System.out.println(P.get(j));
            }
        }
        
        //Modification topic et post avec id
        ts.updateTopic(1,"Les munitionnnnns pour sanglier ?", "muftox", 3, "09/02/2020");
        ps.updatePost(1, 1, "pascal", "1/1/1990", "coucou");
        
        //Cherchez un topic et post avec id
        System.out.println("----------------");
        System.out.println(ts.readTopic(1));
        System.out.println("----------------");
        System.out.println(ps.readPost(1));
    }
    
}
