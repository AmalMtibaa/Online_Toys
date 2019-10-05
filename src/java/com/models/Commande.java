package com.models;

import com.dao.CommandeDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author asus
 */
@Entity
@Table(name="COMMANDE")
public class Commande implements Serializable {
    
    @Id
    private int id_commande;
    
    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    private User user;
    
    private int delivre;
    private String datee;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable( 
            name="JOUETCOMMANDE",
            joinColumns = { @JoinColumn(name = "ID_COMMANDE" )},
            inverseJoinColumns ={ @JoinColumn(name="ID_JOUET")})
    private Set<Jouet> jouets=new HashSet<Jouet>(0);
    @Transient
    private CommandeDao commandeDao=new CommandeDao();

        //constructeur
    public Commande(){
        delivre=0;
    }

    public int getDelivre() {
        return delivre;
    }

    public void setDelivre(int delivre) {
        this.delivre = delivre;
    }
    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Jouet> getJouets() {
        return jouets;
    }

    public void setJouets(Set<Jouet> jouets) {
        this.jouets = jouets;
    }

    
    
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }
    

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }
    
    public void passerCommande(Commande commande){
        this.commandeDao.passerCommande(commande);
    }
    
    public ArrayList<Commande> listeDesCommande(){
        return commandeDao.listeDesCommandes();
    }
   public ArrayList<Commande> listeDesCommandesParUser(String mail){
       return commandeDao.listeDesCommandesParUser(mail);
   }
   
   public Commande chercherCommande(int id){
       return commandeDao.chercherCommande(id);
   }
   
   public void updateCommande(Commande commande){
       commandeDao.updateCommande(commande);
   }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", datee=" + datee + '}';
    }
    
    
    
    
}
