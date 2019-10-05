/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Jouet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class JouetDao {
    
    public void updateJouet(Jouet jouet){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.update(jouet);
        tx.commit();
    }
    
         public List<Jouet> listeJouet()
    {
    Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        List<Jouet> m =(List<Jouet>) session.createCriteria(Jouet.class).list();	
	tx.commit();
        
        return m;
    }
    
     public List<Jouet> listeJouetParCategorie(String categorie)
    {
    Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        List<Jouet> m =(List<Jouet>) session.createCriteria(Jouet.class).list();
        List<Jouet> j=new ArrayList<>();
        for (int i=0;i<m.size();i++)
        {
            if (m.get(i).getCategorie().equals(categorie))
                j.add(m.get(i));
    
        }
		
	tx.commit();
        return j;
    }
     
     
    
     
     public Jouet JouetSpecifique(String nom)
    {
    Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        List<Jouet> m =(List<Jouet>) session.createCriteria(Jouet.class).list();
        Jouet jouet=new Jouet();
        for (int i=0;i<m.size();i++)
        {
            if (m.get(i).getNom().equals(nom))
                jouet=m.get(i);
        }
		
		tx.commit();
                return jouet;
    }
     
     public ArrayList<Jouet> RechercheJouet(String nom)
    {
    Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        List<Jouet> m =(List<Jouet>) session.createCriteria(Jouet.class).list();
        ArrayList<Jouet> jouet=new ArrayList<>();
        for (int i=0;i<m.size();i++)
        {
            if (m.get(i).getNom().toLowerCase().contains(nom.toLowerCase()))
                jouet.add(m.get(i));
        }
		
		tx.commit();
                return jouet;
    }
     
    
     
     public void ajouterJouet(Jouet jouet){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.save(jouet);
        tx.commit();
         
     }
     
     public void supprimerJouet(Jouet jouet){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Jouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.delete(jouet);
        tx.commit();
         
     }
    
    
}
