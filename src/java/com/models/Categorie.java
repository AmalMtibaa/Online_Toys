/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dao.CategorieDao;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 *
 * @author asus
 */
@Entity
@Table(name="CATEGORIE")
public class Categorie {
    @Id
    private int id;
    private String nom;
    @Transient
    private CategorieDao categorieDao=new CategorieDao();

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public ArrayList<Categorie> listeDesCategories(){
        return categorieDao.listeDesCategories();
    }
     public void ajouterCategorie(Categorie categorie){
         categorieDao.ajouterCategorie(categorie);
     }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + "}";
    }
  
     
}
