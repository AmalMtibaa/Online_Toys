/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;


import com.models.Categorie;
import com.models.Commande;
import com.models.User;
import com.models.Jouet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class AcceuilController {

 
    /*-----------Accueil:liste de Categories de Jouet----------*/
    @RequestMapping("/welcome")
    public String getAllCategories(Model model)
    {
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        return "Acceuil";
    }
    
    
    /*--------Liste produit associé au produit--------*/
    @RequestMapping( value="AcceuilController",method = RequestMethod.GET)
    public String getAllSpecificJouet(Model model,HttpServletRequest request)
    {
        
        Jouet jouet=new Jouet();
        model.addAttribute("listeJouets", jouet.listeJouetParCategorie(request.getParameter("link_codeCategorie")));
        model.addAttribute("nomCategorie",request.getParameter("link_codeCategorie"));
        return "listeJouets";
    }
	

/*-----------Ajout dans Panier-------------------*/
    @RequestMapping( value="AccueilControllerPanier",method = RequestMethod.GET)
    public String AjoutPanier(Model model,HttpServletRequest request,HttpSession session)
    {
        ArrayList<Jouet> listeJouetsSelectionee;
        Hashtable<String,Integer> NombreJouet;
        Jouet jouet=new Jouet();
       
        HttpSession session_vente_En_Ligne = request.getSession();
        if(session_vente_En_Ligne.getAttribute("listeJouetsSelectionee")== null)
        {
            listeJouetsSelectionee=new ArrayList<>(); 
        }
        else {
            listeJouetsSelectionee=(ArrayList<Jouet>)session_vente_En_Ligne.getAttribute("listeJouetsSelectionee");
        }
        if(session_vente_En_Ligne.getAttribute("NombreJouet")== null)
        {
            NombreJouet=new Hashtable<String,Integer>(); 
        }
        else {
            NombreJouet=(Hashtable<String,Integer>)session_vente_En_Ligne.getAttribute("NombreJouet");
        }
        
        Jouet jeu=jouet.JouetSpecifique((String)request.getParameter("link_nomJouet"));
        boolean a=false;
        for (int i=0;i<listeJouetsSelectionee.size();i++)
        {
            if (listeJouetsSelectionee.get(i).getId()==jeu.getId())
            {
                a=true;
            }}
        if (a==true){
            NombreJouet.put(jeu.getNom(), NombreJouet.get(jeu.getNom())+1);
        }
        else{
                listeJouetsSelectionee.add(jeu);
                NombreJouet.put(jeu.getNom(), 1);
        }
            
        
        session_vente_En_Ligne.setAttribute("listeJouetsSelectionee", listeJouetsSelectionee);
        session_vente_En_Ligne.setAttribute("NombreJouet",NombreJouet );
        model.addAttribute("listeJouetsSelectionee", listeJouetsSelectionee);
        
        return "AjoutPanier";
    
    }
    
    /*-------------Affichage Panier-------------*/
    @RequestMapping( value="AffichagePanier",method = RequestMethod.GET)
    public String AffichagePanier(Model model,HttpServletRequest request)
    {
        ArrayList<Jouet> listeJouetsSelectionee;
       
       
        HttpSession session_vente_En_Ligne = request.getSession();
        if(session_vente_En_Ligne.getAttribute("listeJouetsSelectionee")== null)
        {
            listeJouetsSelectionee=new ArrayList<>(); 
        }
        else {
            listeJouetsSelectionee=(ArrayList<Jouet>)session_vente_En_Ligne.getAttribute("listeJouetsSelectionee");
        }
        
        session_vente_En_Ligne.setAttribute("listeJouetsSelectionee", listeJouetsSelectionee);
        model.addAttribute("listeJouetsSelectionee", listeJouetsSelectionee);
        
        return "AjoutPanier";
    }
    
    
    /*---------Affichage Formulaire ou logo Inscription----------*/
     @RequestMapping( value="Formulaire",method = RequestMethod.GET)
    public String affichgeFormulaire (Model model)
    {
        
       User user=new User();
        model.addAttribute("user", user);
        return "FormulaireClient";
    
    }
    
    /*----------Inscrire User dans le site-----------------*/
    @RequestMapping( value="AjoutClient",method = RequestMethod.POST)
    public String AjoutClient (@ModelAttribute("user") User form, Model model,HttpSession session)
    {
        
        User user=new User();
        user.setAge(form.getAge());
        user.setMail(form.getMail());
        user.setNom(form.getNom());
        user.setRolee("client");
        user.setPassword(form.getPassword());
        System.out.println(user.toString());
        User modele=new User();
        modele.ajouterUser(user);
        session.setAttribute("user",user.chercherClient(user));
        session.setAttribute("connecte", "oui" );
        model.addAttribute(session);
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        return "Acceuil";
        
    }
    
    /*---------Redirection About us----------*/
     @RequestMapping( value="AboutUs",method = RequestMethod.GET)
    public String affichgeAboutUs (Model model)
    {
        return "AboutUs";
    }
    
    /*---------Redirection Contact----------*/
     @RequestMapping( value="Contact",method = RequestMethod.GET)
    public String affichgeContact (Model model)
    {
        return "Contact";
    }
    
       /*---------Passer Commande---------------*/
    @RequestMapping( value="PasserCommande",method = RequestMethod.GET)
    public String PasserCommande (Model model,HttpSession session)
    {
        if (session.getAttribute("connecte")!=null && session.getAttribute("connecte").equals("oui")){
            Commande commande=new Commande();
            User user=(User) session.getAttribute("user");
            commande.setUser(user);
            commande.setDatee(LocalDateTime.now().toString());
            commande.setId_commande(ThreadLocalRandom.current().nextInt(0,100000));
            Hashtable<String,Integer> NombreJouet=(Hashtable<String,Integer>) session.getAttribute("NombreJouet");
            Jouet jouet=null;
            ArrayList<Jouet> listeJouet=(ArrayList<Jouet>) session.getAttribute("listeJouetsSelectionee");
            Set<Jouet> jouets=new HashSet<Jouet>();
            for (int j=0;j<listeJouet.size();j++){
                
                jouets.add(listeJouet.get(j));
                jouet=listeJouet.get(j);
                jouet.setStock(jouet.getStock()-NombreJouet.get(jouet.getNom()));
                jouet.updateJouet(jouet);
            }
            commande.setJouets(jouets);
            commande.passerCommande(commande);
            listeJouet.clear();
            session.setAttribute("listeJouetsSelectionee", listeJouet);
            Categorie categories=new Categorie();
            model.addAttribute("listeCategories", categories.listeDesCategories());
            return "PasserCommande";
       }
        else{
            User user=new User();
            model.addAttribute("user",user);
            return "FormulaireClient";
        }

    }

    
    /*----------Affichage Log as An Admin----------------*/
    @RequestMapping( value="PartieLogAdmin",method = RequestMethod.GET)
    public String PartieLogAdmin (Model model,HttpSession session)
    {
        
        if (session.getAttribute("admin") !=null && session.getAttribute("admin").equals("oui"))
            return "PartieAdmin";
        else{
        User user=new User();
        model.addAttribute("AdminForm",user);
        return "LogPartieAdmin";
        }
    }
    
    /*---------Verfication Admin---------------*/
    @RequestMapping( value="VerificationAdmin",method = RequestMethod.POST)
    public String VerficationAdmin (@ModelAttribute("AdminForm") User user, Model model, HttpSession session)
    {
        
        if (user.chercherClient(user) != null && user.chercherClient(user).getPassword().equals(user.getPassword()) && 
                user.chercherClient(user).getRolee().equals("admin"))
        {
            session.setAttribute("user",user.chercherClient(user));
            session.setAttribute("connecte", "oui" );
            session.setAttribute("admin", "oui");
            model.addAttribute(session);
            Categorie categories=new Categorie();
            model.addAttribute("listeCategories", categories.listeDesCategories());
            return "PartieAdmin";
        }
        else{
            
            model.addAttribute("erreur1","mail ou mot de passe ");
            model.addAttribute("user",new User());
            return "FormulaireClient";
        }
        
    }
    

    /*------------AFfichage de formulaire d'Ajout JOUET dans Partie Admin-------------*/
    @RequestMapping( value="AffichageAjoutJouetPartieAdmin",method = RequestMethod.GET)
    public String AffichageAjoutJouetPartieAdmin (Model model)
    {
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        Jouet jouet=new Jouet();
        model.addAttribute("jouet",jouet);
        return "AjoutJouet";
    }
    
    /*------------Ajout JOUET dans Base de donnée-------------*/
    @RequestMapping( value="AjoutJouetPartieAdmin",method = RequestMethod.POST)
    public String AjoutJouetPartieAdmin (@ModelAttribute("jouet") Jouet form, Model model){  
    
        Jouet jouet=new Jouet();
    
        jouet.setId(form.getId());
        jouet.setNom(form.getNom());
        jouet.setPrix(form.getPrix());
        jouet.setDescription(form.getDescription());
        jouet.setCategorie(form.getCategorie());
        jouet.setStock(form.getStock());
        jouet.setPhoto(form.getPhoto());
        
        Jouet modele=new Jouet();
        modele.ajouterJouet(jouet);
        return "PartieAdmin";
    }
    
    
    /*----------Affichage de Supprimer categorie ou supprimer une jouet qui appartiet à une categorie------*/
    @RequestMapping( value="SupprimerJouetPartieAdmin",method = RequestMethod.GET)
    public String SupprimerJouetlisteCAtegorie(Model model){
        
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        return "supprimerCategorie";
    }
    
    /*-----------Affichage liste de Jouet à Supprimer --------------------------------*/
    @RequestMapping( value="listeJouetASupprimer",method = RequestMethod.GET)
     public String AffichageJouetASupprimer(Model model,HttpServletRequest request)
    {
        Jouet jouet=new Jouet();
        model.addAttribute("listeJouets", jouet.listeJouetParCategorie(request.getParameter("link_codeCategorie")));
        model.addAttribute("nomCategorie",request.getParameter("link_codeCategorie"));
        return "listeJouetsASupprimer";
       
    }
     
     /*------------Supprimer Un jouet specifique-----------------*/
     @RequestMapping( value="SupprimerJouet",method = RequestMethod.GET)
     public String JouetASupprimer(Model model,HttpServletRequest request)
    {
        Jouet modele=new Jouet();
        Jouet jouet=modele.JouetSpecifique(request.getParameter("link_codeJouet"));
        System.out.println("##########+"+request.getParameter("link_codeJouet"));
        jouet.setStock(0);
        System.out.println("##########+"+jouet.toString());
        modele.updateJouet(jouet);
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        return "supprimerCategorie";
       
    }
     
     
       /*------------AFfichage de formulaire d'Ajout JOUET dans Partie Admin-------------*/
    @RequestMapping( value="AffichageAjoutCategorie",method = RequestMethod.GET)
    public String AffichageAjoutJoueCategorie (Model model)
    {
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        model.addAttribute("categories",categories);
        return "AjoutCategorie";
    }
    
    /*------------Ajout JOUET dans Base de donnée-------------*/
    
    @RequestMapping( value="AjoutCategorie",method = RequestMethod.POST)
    public String AjoutCategoriePartieAdmin (@ModelAttribute("categories") Categorie form, Model model)
    {
        Categorie categorie=new Categorie();
      
        categorie.setId(form.getId());
        categorie.setNom(form.getNom());
        Categorie modele=new Categorie();
        modele.ajouterCategorie(categorie);
        return "PartieAdmin";
    }
    
   /*-----------Affichage Update Jouet--------------*/
    @RequestMapping( value="AffichageUpdateJouet",method = RequestMethod.GET)
    public String AffichageUpdateJoeuet(Model model,HttpServletRequest request)
    {
       
        Jouet modele=new Jouet();
        Jouet jouet=modele.JouetSpecifique(request.getParameter("link_codeJouet"));
        model.addAttribute("jouet",jouet);
        Categorie categories=new Categorie();
        model.addAttribute("listeCategories", categories.listeDesCategories());
        return "UpdateJouet";
        
    }
    
   /*--------Update Jouet------------------------*/
    @RequestMapping( value="UpdateJouet",method = RequestMethod.POST)
    public String UpdateJouet(@ModelAttribute("jouet") Jouet form, Model model)
    {
        Jouet jouet=new Jouet();
        jouet.updateJouet(form);
        return "PartieAdmin";
        
    }
    
    
    @RequestMapping(value="connexion",method= RequestMethod.POST)
    public String ConnexionClient(@ModelAttribute("user") User user, HttpSession session ,Model model){
        User util=user.chercherClient(user);
        if (util!= null && util.getPassword().equals(user.getPassword()))
        {
            session.setAttribute("user",util);
            session.setAttribute("connecte", "oui" );
            if (util.getRolee().equals("admin"))
                session.setAttribute("admin","oui");
            model.addAttribute(session);
            Categorie categories=new Categorie();
            model.addAttribute("listeCategories", categories.listeDesCategories());
            return "Acceuil";
        }
        else{
            model.addAttribute("erreur1","mail ou mot de passe ");
            model.addAttribute("user", new User());
            return "FormulaireClient";
            
        }
    }
    
        /*------------- Affichage Deconnexion------------------*/
        @RequestMapping( value="Deconnexion",method = RequestMethod.GET)
        public String AffichageDeconnexion( Model model,HttpSession session,HttpServletRequest request)
        {
            User user=(User)session.getAttribute("user");
                    
            model.addAttribute("user",user);
            return "Deconnexion";
        }

        /*-------------Deconnexion User---------------------*/
        @RequestMapping( value="DeconnexionUser",method = RequestMethod.GET)
        public String DeconnexionUser( Model model,HttpSession session)
        {
            session.removeAttribute("listeJouetsSelectionee");
            session.removeAttribute("NombreJouet");
            session.removeAttribute("connecte");
            session.removeAttribute("user");
            if (session.getAttribute("admin")!=null)
                session.removeAttribute("admin");
            User user=new User();
            model.addAttribute("user", user);
            return "FormulaireClient";
        }
        
              /*-------------Affichage Recherche Jouets---------------------*/
        
        @RequestMapping( value="Recherche",method = RequestMethod.GET)
        public String rechecheJouet( Model model)
        {
            model.addAttribute("jouet",new Jouet());
            model.addAttribute("resultatrecherche",new ArrayList<>());
            return "Recherche";
        }
        
        /*------------- Recherche Jouets---------------------*/
        @RequestMapping( value="RechercheJouet",method = RequestMethod.POST)
        public String RechercherJouet( @ModelAttribute("jouet") Jouet form, Model model)
        {
            String nom=form.getNom();
            Jouet jouet= new Jouet();
            model.addAttribute("resultatrecherche",jouet.RechercheJouet(nom));
            return "Recherche";
        }
        
        /*----------Liste Commande non delivrer Partie Admin------------*/
        @RequestMapping(value ="listeCommandeNonLivrer", method= RequestMethod.GET)
        public String listeCommandeNonLivrer(Model model){
            
            Commande commande=new Commande();
            ArrayList<Commande> liste=commande.listeDesCommande();
            for (int i=0;i<liste.size();i++)
                if (liste.get(i).getDelivre()==1){
                    liste.remove(i);
                    i--;
            }
            model.addAttribute("listecommande",liste);
            return "listeCommande";
        }
        
        /*----------Liste Commande livrer Partie Admin------------*/
        @RequestMapping(value ="listeCommandeLivrer", method= RequestMethod.GET)
        public String listeCommandeLivrer(Model model){
            
            Commande commande=new Commande();
            ArrayList<Commande> liste=commande.listeDesCommande();
            for (int i=0;i<liste.size();i++)
                if (liste.get(i).getDelivre()==0){
                    liste.remove(i);
                    i--;
            }
            model.addAttribute("listecommande",liste);
            return "CommandeDelivree";
        }
        
    
        /*----------Vider Panier-----------------------------*/
        @RequestMapping(value ="ViderPannier", method= RequestMethod.GET)
        public String ViderPanier(Model model,HttpSession session){
            
            session.removeAttribute("listeJouetsSelectionee");
            return "AjoutPanier";
        }
        
        /*--------Supprimer Jouets du Panier-------------*/
         @RequestMapping(value ="SupprimerduPanier", method= RequestMethod.GET)
        public String SupprimerduPanier(Model model,HttpServletRequest request,HttpSession session){
            
            Jouet jouet=new Jouet();
            Jouet jouetAsuprimer=jouet.JouetSpecifique(request.getParameter("link_codeJouet"));
            ArrayList<Jouet> liste=(ArrayList<Jouet>)session.getAttribute("listeJouetsSelectionee");
            
            for(int i=0;i<liste.size();i++){
                if(liste.get(i).equals(jouetAsuprimer))
                {
                    liste.remove(i);
                }
            }
            session.setAttribute("listeJouetsSelectionee", liste);
            return "AjoutPanier";
        }
        
        /*----------Historique des Commandes--------------*/
        @RequestMapping(value ="HistoriqueClient", method= RequestMethod.GET)
        public String HistoriqueClient(Model model,HttpSession session){
         
            if(session.getAttribute("user")!=null )
            {
            User user=(User)session.getAttribute("user");
            Commande commande=new Commande();
            model.addAttribute("listecommande",commande.listeDesCommandesParUser(user.getMail()));
            return "HistoriqueCommande";
            }
            else{
               model.addAttribute("user",new User());
               model.addAttribute("erreurConnection","Vous devez s'inscrire");
               return "FormulaireClient";
                }
        }   
        
        /*-----------Delivrer Commande----------------*/
        @RequestMapping(value ="DelivrerCommande", method= RequestMethod.GET)
        public String DelivrerCommande(Model model,HttpSession session,HttpServletRequest request){
           Commande commande=new Commande();
           commande=commande.chercherCommande(Integer.parseInt(request.getParameter("link_codeCommande")));
           commande.setDelivre(1);
           commande.updateCommande(commande);
           ArrayList<Commande> liste=commande.listeDesCommande();
            for (int i=0;i<liste.size();i++)
                if (liste.get(i).getDelivre()==1){
                    liste.remove(i);
                    i--;
                    }
            model.addAttribute("listecommande",liste);
            return "listeCommande";
        }
} 
        
        
        
        
        
        
  /*      
        // Affichage ajout admin
       
        @RequestMapping(value ="AffichageAjoutAdmin", method= RequestMethod.GET)
        public String affichageAjoutAdmin(Model model ){
            model.addAttribute("user",new User());
            return "AjoutAdmin";
        }
        
        // ajout admin
        
      @RequestMapping(value ="AjoutAdmin", method= RequestMethod.POST)
        public String ajoutAdmin(@ModelAttribute("user") User user, Model model){
            User util=user.chercherClient(user);
            util.setRolee("admin");
            user.updateUser(util);
            
            
            return "PartieAdmin";
        } */
        
       


