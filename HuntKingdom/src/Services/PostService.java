/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Post;
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
public class PostService {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    //incrementer le nbPosts du topic
    public void incrementNbPosts(int id){
        String requete = "update topics set nbPosts=nbPosts+1 where id="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Avoir le topicId d'un post avec id
    public int getTopicId(int id){
        Post p = new Post();
        String requete = "select * from posts where id="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                p.setId(rs.getInt(1));
                p.setTopicId(rs.getInt(2));
                p.setAuthor(rs.getString(3));
                p.setDate(rs.getString(4));
                p.setMessage(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p.getTopicId();
    }
    
    //decrementer le nbPosts du topic
    public void decrementNbPosts(int id){
        String requete = "update topics set nbPosts=nbPosts-1 where id="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Ajouter post 1 DONE
    public void createPost(Post p) throws SQLException{
        List<Post> P = readPostList();
        if(!P.contains(p)) {
            String requete = "insert into posts (id,topicId,author,date,message) values ('"+p.getId()+"','"+p.getTopicId()+"','"+p.getAuthor()+"','"+p.getDate()+"','"+p.getMessage()+"')";
            try{
                Statement st = cnx.createStatement();
                st.executeUpdate(requete);
                System.out.println("Post added successfully");
                incrementNbPosts(p.getTopicId()); //incrementer le nbPosts
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Ajouter post 2 DONE
    public void createPost2(Post p) throws SQLException {
        List<Post> P = readPostList();
        if(!P.contains(p)) {
            String requete = "insert into posts (id,topicId,author,date,message) values ('"+p.getId()+"','"+p.getTopicId()+"','"+p.getAuthor()+"','"+p.getDate()+"','"+p.getMessage()+"')";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1,p.getId());
                pst.setInt(2, p.getTopicId());
                pst.setString(3, p.getAuthor());
                pst.setString(4, p.getDate());
                pst.setString(5, p.getMessage());
                pst.executeUpdate();
                incrementNbPosts(p.getTopicId()); //incrementer le nbPosts
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Afficher tout les posts DONE
    public List<Post> readPostList(){
        List<Post> list = new ArrayList<>();
        String requete = "select * from posts";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setTopicId(rs.getInt(2));
                p.setAuthor(rs.getString(3));
                p.setDate(rs.getString(4));
                p.setMessage(rs.getString(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    //Afficher un post avec id DONE
    public Post readPost(int id){
        Post p = new Post();
        String requete = "select * from posts where id="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                p.setId(rs.getInt(1));
                p.setTopicId(rs.getInt(2));
                p.setAuthor(rs.getString(3));
                p.setDate(rs.getString(4));
                p.setMessage(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    //Afficher tout les posts d'un topic avec l'idTopic DONE
    public List<Post> readPostTopic(int topicId){
        List<Post> list = new ArrayList<>();
        String requete = "select * from posts where topicId="+topicId;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setTopicId(rs.getInt(2));
                p.setAuthor(rs.getString(3));
                p.setDate(rs.getString(4));
                p.setMessage(rs.getString(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    //Supprimer post avec id DONE
    public void deletePost(int id) {
        String requete = "delete from posts where id="+id;
        try{
            decrementNbPosts(getTopicId(id)); //decrementer le nbPosts
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Post deleted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Modifier post avec id DONE
    public void updatePost(int id, int topicId, String author, String date, String message) {
        String requete = "update posts set topicId='"+topicId+"', author='"+author+"', date='"+date+"', message='"+message+"' where id="+id;
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Post updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}