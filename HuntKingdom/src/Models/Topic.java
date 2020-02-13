/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Omar
 */
public class Topic {
    private int id;
    private String title;
    private String author;
    private int nbPosts;
    private String lastPostDate;
    private int hashCode;
    //private List<Post> postList = new ArrayList<>();
    
    public Topic() {
        title = "";
        author = "";
        nbPosts = 0;
        lastPostDate = "";
    }
    
    public Topic(String title, String author, String lastPostDate) {
        this.title = title;
        this.author = author;
        this.nbPosts = 0;
        this.lastPostDate = lastPostDate;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }    
    
    public String getAuthor() {
        return author;
    }
    
    public int getNbPosts() {
        return nbPosts;
    }
    
    public String getLastPostDate() {
        return lastPostDate;
    }
    
    /*public List<Post> getPostList() {
        return postList;
    }*/
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setNbPosts(int nbPosts) {
        this.nbPosts = nbPosts;
    }
    
    public void setLastPostDate(String lastPostDate) {
        this.lastPostDate = lastPostDate;
    }
    
    /*public void setPostList(List<Post> postList) {
        this.postList = postList;
    }*/

    @Override
    public String toString() {
        return "Topic n°" + id + ": " + title + " (by " + author + ") " + nbPosts + " " + lastPostDate + "\n\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.author);
        hash = 37 * hash + this.nbPosts;
        hash = 37 * hash + Objects.hashCode(this.lastPostDate);
        hash = 37 * hash + this.hashCode;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Topic other = (Topic) obj;
        if (this.nbPosts != other.nbPosts) {
            return false;
        }
        if (this.hashCode != other.hashCode) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.lastPostDate, other.lastPostDate)) {
            return false;
        }
        return true;
    }

    
}
