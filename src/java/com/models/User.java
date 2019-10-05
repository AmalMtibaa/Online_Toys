/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dao.UserDao; 
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author asus
 */
@Entity
@Table(name="USERS")
public class User implements Serializable  {

   
    private String nom;
    private int age;
    @Id
    private String mail;
    private String password;
    private  String rolee;
    @Transient
    private UserDao userDao=new UserDao();
    
    @OneToMany(mappedBy="user")
    private Set<Commande> commandes;

    public User(){
        super();
    
    }
    public User(String nom, int age, String mail, String password, String rolee) {
        this.nom = nom;
        this.age = age;
        this.mail = mail;
        this.password = password;
        this.rolee = rolee;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }
    
    

    
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolee() {
        return rolee;
    }

    public void setRolee(String rolee) {
        this.rolee = rolee;
    }
    
    public void supprimerClient(User user){
        userDao.supprimerClient(user);
    }
    
    public void ajouterUser(User user){
        System.out.println(user.toString());
        userDao.ajouterUser(user);
    }
    
    public User chercherClient(User user){
        return userDao.chercherClient(user);
    }
    
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", age=" + age + ", mail=" + mail + ", password=" + password + ", rolee=" + rolee + '}';
    }


    
}
