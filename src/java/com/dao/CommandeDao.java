/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Commande;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ASUS
 */
public class CommandeDao {
    
    public void passerCommande(Commande commande){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Commande.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        
        session.save(commande);
        tx.commit();
        
    }
    
        public ArrayList<Commande> listeDesCommandes(){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Commande.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        ArrayList<Commande> m =(ArrayList<Commande>) session.createCriteria(Commande.class).list();	
	tx.commit();
        return m;
    }
   
         public ArrayList<Commande> listeDesCommandesParUser(String mail){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Commande.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        ArrayList<Commande> m =(ArrayList<Commande>) session.createCriteria(Commande.class).list();	
        ArrayList<Commande> resultat=new ArrayList<>();
        for(int i=0;i<m.size();i++){
            if(m.get(i).getUser().getMail().equals(mail))
            {
                resultat.add(m.get(i));
            }
        }
	tx.commit();
        return resultat;
    }
         
         public Commande chercherCommande(int id){
              Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Commande.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        ArrayList<Commande> m =(ArrayList<Commande>) session.createCriteria(Commande.class).list();	
        Commande resultat=new Commande();
        for(int i=0;i<m.size();i++){
            if(m.get(i).getId_commande()==id)
            {
                resultat=m.get(i);
            }
        }
	tx.commit();
        session.close();
        return resultat;
         }
         
         public void updateCommande(Commande commande){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Commande.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.update(commande);
        tx.commit();
        session.close();
    }
    
}
