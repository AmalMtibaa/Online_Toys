/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author asus
 */
public class UserDao {
         public void supprimerClient(User user){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
        SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.delete(user);
        tx.commit(); }
     
     public User chercherClient(User user){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
        SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        
        List<User> m =(List<User>) session.createCriteria(User.class).list();
        User util=null;
        for (int i=0;i<m.size();i++)
        {
            if (m.get(i).getMail().equals(user.getMail()))
                util=m.get(i);
        }
        tx.commit();
        return util;
     }
     
     
     public void ajouterUser(User user){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.save(user);
        tx.commit();
    }
     
        public void updateUser(User user){
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
	SessionFactory sf= con.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tx= session.beginTransaction();
        session.update(user);
        tx.commit();
    }
    
}
