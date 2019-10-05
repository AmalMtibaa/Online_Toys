<%-- 
    Document   : AjoutPanier
    Created on : 17 janv. 2018, 20:30:04
    Author     : asus
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" type="text/css" href="resources/css/default.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/component.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/tableau.css" />
        
    </head>
    
    <body class="body">
        <div class="container">	
	<div class="main clearfix">
		<nav id="menu" class="nav">					
			<ul>
				<li>
					<a href="welcome">
						<span class="icon">
							<i aria-hidden="true" class="icon-home"></i>
						</span>
						<span>Accueil</span>
					</a>
				</li>
				<li>
                                    <% String userConnection = (String)request.getSession().getAttribute("connecte");
                                    if(userConnection!=null)
                                    {
                                       %>
                                       <a href="Deconnexion">
						<span class="icon"> 
							<i aria-hidden="true" class="icon-services"></i>
						</span>
						<span>Deconnexion</span>
					</a>
                                       <%
                                    }
                                    else  {
                                    %>
					<a href="Formulaire">
						<span class="icon"> 
							<i aria-hidden="true" class="icon-services"></i>
						</span>
						<span>Connexion</span>
					</a>
                                    <%
                                        }
                                        %>
                                </li>
				<li>
					<a href="AffichagePanier">
						<span class="icon">
							<i aria-hidden="true" class="icon-portfolio"></i>
						</span>
						<span>Panier</span>
					</a>
				</li>
                                <li>
					<a href="Recherche">
						<span class="icon">
							<i aria-hidden="true" class="icon-team"></i>
						</span>
						<span>Recherche Jouet</span>
					</a>
				</li>
				<li>
					<a href="AboutUs">
						<span class="icon">
							<i aria-hidden="true" class="icon-blog"></i>
						</span>
						<span>A Propos de nous</span>
					</a>
				</li>
				
				<li>
					<a href="PartieLogAdmin">
						<span class="icon">
							<i aria-hidden="true" class="icon-contact"></i>
						</span>
						<span>Partie Admin</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div><!-- /container -->

<div class="main1">  
    <br><br>
        <table>
        <tr>
                    <td><b><font color="red">Nom </font></b></td>
                    <td><b><font color="red">Description</font></b></td>
                    <td><b><font color="red">Prix(dt)</font></b></td>
                    <td><b><font color="red">Nombre</font></b></td>
        </tr>
        <c:forEach items="${listeJouetsSelectionee}" var="jouet" >    
            <tr>
            
            <td><c:out value="${jouet.nom} "/></ul></td>  
            <td><c:out value="${jouet.description} "/></ul></td> 
            <td><c:out value="${jouet.prix} "/>dt</ul></td>
            <td><c:out value="${NombreJouet[jouet.nom]}"/></td>
            <td><button><a style="color: white;" href="SupprimerduPanier?link_codeJouet=${jouet.nom}" >Supprimer du Panier</a></button></td>
            
            
            </tr>

       
        </c:forEach>
        </table>
        <br>
        <% int i=0;%>
             <c:forEach items="${listeJouetsSelectionee}" var="jouet">
                    <% i=i+1; %>
              </c:forEach>
          <% if(i==0){ %>
        <button disable>Passer Commande</button> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <% } else{ %>
        <button><a style="color: white;" href="PasserCommande" >Passer Commande</a></button> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
        <% } %>
        <button><a style="color: white;" href="ViderPannier"  >Vider Panier</a></button> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
        <button><a style="color: white;" href="HistoriqueClient"  >Historique des Commandes</a></button>
 </div>    
    <script src="resources/js/modernizr.custom.js"></script>
    </body>
</html>
