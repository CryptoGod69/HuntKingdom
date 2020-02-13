/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Topic;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class TopicService {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public int getLastTopicId() {
        int id = 0;
        String requete = "select * from topics order by id desc limit 1";
            try{
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(requete);
                while(rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        return id;
    }
    
    //Ajouter topic1 DONE
    public int createTopic(Topic t) throws SQLException{
        List<Topic> T = readTopicList();
        if(!T.contains(t)) {
            String requete = "insert into topics (id,title,author,nbPosts,lastPostDate) values ('"+t.getId()+"','"+t.getTitle()+"','"+t.getAuthor()+"','"+t.getNbPosts()+"','"+t.getLastPostDate()+"')";
            try{
                Statement st = cnx.createStatement();
                st.executeUpdate(requete);
                System.out.println("Topic publié avec succès");
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return getLastTopicId();
    }
    
    //Ajouter topic2 DONE
    public int createTopic2(Topic t) throws SQLException {
        List<Topic> T = readTopicList();
        if(!T.contains(t)) {
            String requete = "insert into topics (id,title,author,nbPosts,lastPostDate) values ('"+t.getId()+"','"+t.getTitle()+"','"+t.getAuthor()+"','"+t.getNbPosts()+"','"+t.getLastPostDate()+"')";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1,t.getId());
                pst.setString(2,t.getTitle());
                pst.setString(3, t.getAuthor());
                pst.setInt(4, t.getNbPosts());
                pst.setString(5, t.getLastPostDate());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return getLastTopicId();
    }
    
    //Afficher tout les topics DONE
    public List<Topic> readTopicList(){
        List<Topic> topicList = new ArrayList<>();
        String requete = "select * from topics";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Topic t = new Topic();
                t.setId(rs.getInt(1));
                t.setTitle(rs.getString(2));
                t.setAuthor(rs.getString(3));
                t.setNbPosts(rs.getInt(4));
                t.setLastPostDate(rs.getString(5));
                topicList.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topicList;
    }
    
    //Afficher un topic avec id DONE
    public Topic readTopic(int id){
        Topic t = new Topic();
        String requete = "select * from topics where id="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                t.setId(rs.getInt(1));
                t.setTitle(rs.getString(2));
                t.setAuthor(rs.getString(3));
                t.setNbPosts(rs.getInt(4));
                t.setLastPostDate(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    //Supprimer tout les posts d'un topic DONE
    public void deletePostTopic(int topicId) {
        String requete = "delete from posts where topicId="+topicId;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Supprimer un topic, y compris les posts qu'il contient DONE
    public void deleteTopic(int id) {
        deletePostTopic(id);
        String requete = "delete from topics where id="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Topic and posts deleted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Modifier topic avec id DONE
    public void updateTopic(int id, String title, String author, int nbPosts, String lastPostDate) {
        String requete = "update topics set title='"+title+"', author='"+author+"', nbPosts='"+nbPosts+"', lastPostDate='"+lastPostDate+"' where id="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Post updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}