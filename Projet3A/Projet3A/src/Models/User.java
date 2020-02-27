/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;



/**
 *
 * @author Mourad
 */
public class User {
    
    private int idUser;
    private String NomUser;
    private String PrenomUser;  
    private String AdresseUser;
    private String EmailUser;
    private String TelUser;
    private String RoleUser;
    private Date Datenaissance;
   

    public User() {
        this.idUser=0;
     this.NomUser = "";
     this.PrenomUser = "";
     this.AdresseUser = "";
     this.EmailUser = "";
     this.TelUser = "";
     this.RoleUser = "";
     
     

    }

    /**
     *
     * @param idUser
     * @param NomUser
     * @param PrenomUser
     * @param AdresseUser
     * @param EmailUser
     * @param TelUser
     * @param RoleUser
     * @param Datenaissance
     */
    public User(int idUser, String NomUser, String PrenomUser, String AdresseUser, String EmailUser, String TelUser, String RoleUser ,Date Datenaissance) {
        this.idUser = idUser;
        this.NomUser = NomUser;
        this.PrenomUser = PrenomUser;
        this.AdresseUser = AdresseUser;
        this.EmailUser = EmailUser;
        this.TelUser = TelUser;
        this.RoleUser = RoleUser;
        this.Datenaissance = Datenaissance;
        
    }

    

    public int getIdUser() {
        return idUser;
    }

    public String getNomUser() {
        return NomUser;
    }

    public String getPrenomUser() {
        return PrenomUser;
    }

    public String getAdresseUser() {
        return AdresseUser;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public String getTelUser() {
        return TelUser;
    }

    public String getRoleUser() {
        return RoleUser;
    }

    public Date getDatenaissance() {
        return Datenaissance;
    }

   

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNomUser(String NomUser) {
        this.NomUser = NomUser;
    }

    public void setPrenomUser(String PrenomUser) {
        this.PrenomUser = PrenomUser;
    }

    public void setAdresseUser(String AdresseUser) {
        this.AdresseUser = AdresseUser;
    }

    public void setEmailUser(String EmailUser) {
        this.EmailUser = EmailUser;
    }

    public void setTelUser(String TelUser) {
        this.TelUser = TelUser;
    }

    public void setRoleUser(String RoleUser) {
        this.RoleUser = RoleUser;
    }

    public void setDatenaissance(Date Datenaissance) {
        this.Datenaissance = Datenaissance;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", NomUser=" + NomUser + ", PrenomUser=" + PrenomUser + ", AdresseUser=" + AdresseUser + ", EmailUser=" + EmailUser + ", TelUser=" + TelUser + ", RoleUser=" + RoleUser + ", Datenaissance=" + Datenaissance + '}';
    }

   
  

   

    


    
    
 
     
}