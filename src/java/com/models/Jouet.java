/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dao.JouetDao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author asus
 */

@Entity
@Table(name="JOUET")
public class Jouet {


    @Id
    private int id;
    private String nom;
    private String description;
    private int prix;
    private String photo;
    private String categorie;
    private int stock;

    @Transient
    JouetDao jouetDao=new JouetDao();


        public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }
    public int getPrix() {
        return prix;
    }
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
         
    public Jouet JouetSpecifique(String nom){
        return jouetDao.JouetSpecifique(nom);
    }
    
    public void ajouterJouet(Jouet jouet){
        jouetDao.ajouterJouet(jouet);
    }
    
    public void supprimerJouet(Jouet jouet){
        jouetDao.supprimerJouet(jouet);
    }
    
     public List<Jouet> listeJouetParCategorie(String categorie){
         return jouetDao.listeJouetParCategorie(categorie);
     }
     
     public List<Jouet> listeJouet(){
         return jouetDao.listeJouet();
     }

     public void updateJouet(Jouet jouet){
         jouetDao.updateJouet(jouet);
     }
     
     public ArrayList<Jouet> RechercheJouet(String nom){
         return this.jouetDao.RechercheJouet(nom);
     }
     
    @Override
    public String toString() {
        return "Jouet{" + "id=" + id + ", nom=" + nom + '}';
    }
     
     public boolean equals(Jouet obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Jouet)) return false;
    Jouet o = (Jouet) obj;
    return (o.getId() == this.getId());
}
     
     
     
    }

       


    

