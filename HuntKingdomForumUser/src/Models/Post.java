/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Omar
 */
public class Post {
    private int id; 
    private int topicId;
    private String author;
    private Date date;
    private String message;
    
    public Post() {
    }
    
    public Post(int topicId, String author, String message) {
        this.topicId = topicId;
        this.author = author;
        this.message = message;
    }
    
    public int getId() {
        return id;
    }    
    
    public int getTopicId() {
        return topicId;
    }
    
    public String getAuthor() {
        return author;
    }    
    
    public Date getDate() {
        return date;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "    Post n°" + id + "-" + topicId + " (by " + author + ") " + date + "\n        " + message + "\n\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.topicId;
        hash = 37 * hash + Objects.hashCode(this.author);
        hash = 37 * hash + Objects.hashCode(this.date);
        hash = 37 * hash + Objects.hashCode(this.message);
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
        final Post other = (Post) obj;
        if (this.topicId != other.topicId) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }
    
    
}
