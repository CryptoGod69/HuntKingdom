/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.Post;
import java.util.ArrayList;
import java.util.Date;
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
    private Date lastPostDate;
    private String state;
    private int hashCode;
    
    public Topic() {
    }
    
    public Topic(String title, String author) {
        this.title = title;
        this.author = author;
        this.state = "open";
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
    
    public Date getLastPostDate() {
        return lastPostDate;
    }
    
    public String getState() {
        return state;
    }
    
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
    
    public void setLastPostDate(Date lastPostDate) {
        this.lastPostDate = lastPostDate;
    }

    public void setState(String state) {
        this.state = state;
    }

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
