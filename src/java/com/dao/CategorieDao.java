/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Categorie;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ASUS
 */
public class CategorieDao {
    
    public ArrayList<Categorie> listeDesCategories(){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categorie.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        ArrayList<Categorie> m =(ArrayList<Categorie>) session.createCriteria(Categorie.class).list();	
	tx.commit();
        return m;
    }
    
    
    public void ajouterCategorie(Categorie categorie){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categorie.class);
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.save(categorie);
        tx.commit();
    }
    
    
    
}
