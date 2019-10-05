/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.CommandeJouet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ASUS
 */
public class CommandeJouetDao {
    public void enregistrerJouer(CommandeJouet commandeJouet){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CommandeJouet.class);
		
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.save(commandeJouet);
        tx.commit();
    }
    
}
