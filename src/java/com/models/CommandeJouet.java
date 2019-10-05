/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dao.CommandeJouetDao;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name="JOUETCOMMANDE")
public class CommandeJouet implements Serializable {
    @Id
    private int id_commande;
    @Id
    private int id_jouet;
    @Transient
    private CommandeJouetDao commandeJouetDao=new CommandeJouetDao();

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public CommandeJouetDao getCommandeJouetDao() {
        return commandeJouetDao;
    }

    public void setCommandeJouetDao(CommandeJouetDao commandeJouetDao) {
        this.commandeJouetDao = commandeJouetDao;
    }
    
    

    public int getId_jouet() {
        return id_jouet;
    }

    public void setId_jouet(int id_jouet) {
        this.id_jouet = id_jouet;
    }
    
    public void enregistrerCommande(CommandeJouet commandeJouet){
        this.commandeJouetDao.enregistrerJouer(commandeJouet);
    }
    
    
    
    
}
