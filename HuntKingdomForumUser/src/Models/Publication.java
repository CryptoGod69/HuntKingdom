package Models;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Publication {
        private int id;
    private String idUser;
    private Date datePub;
    private String contentPub;
    private int likesPub;
    private String image;
    
    
    public Publication() {
    }
    public Publication(String contentPub,String idUser) {
        this.contentPub = contentPub;
       this.idUser=idUser;
    
    }

    public int getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public Date getDatePub() {
        return datePub;
    }

    public String getContentPub() {
        return contentPub;
    }

    public int getLikesPub() {
        return likesPub;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public void setContentPub(String contentPub) {
        this.contentPub = contentPub;
    }

    public void setLikesPub(int likesPub) {
        this.likesPub = likesPub;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
