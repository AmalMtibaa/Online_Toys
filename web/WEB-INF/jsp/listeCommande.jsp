<%-- 
    Document   : listeCommande
    Created on : 29 janv. 2018, 16:21:37
    Author     : asus
--%>

<%@page import="com.models.Jouet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>liste Commandes</title>
        <link rel="stylesheet" type="text/css" href="resources/css/default.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/component.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/tableau.css" />
        <script src="resources/js/modernizr.custom.js"></script>
    </head>
    <body>
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
    <br><
    <h1>liste de Commandes non livres</h1>
        <table>  
           
        <c:forEach items="${listecommande}" var="commande" >  
            <% int i=0;%>
             <c:forEach items="${commande.jouets}" var="jouet">
                    <% i=i+1; %>
              </c:forEach>
            
            <tr>
          
                <td rowspan=<%=i %>>
                
                <b>ID :</b><c:out value="${commande.id_commande} "/><br>
                <b>Date :</b><c:out value="${commande.datee} "/><br>
                <b>Utilisateur:</b><c:out value="${commande.user.mail} "/> <br>
                </td>  
            <% int test=1;%>
                <c:forEach items="${commande.jouets}" var="jouet">
                    <td>
                        <b>ID_Jouet :</b><c:out value="${jouet.id} "/> <br>
                        <b>Nom_Jouet :</b><c:out value="${jouet.nom} "/><br>
                    </td>
                    <% if(test==1){ %>
                    <td rowspan=<%=i %>><button><a style="color: white;" href="DelivrerCommande?link_codeCommande=${commande.id_commande}">Delivrer Commande</a></button></td>
                    <% test=2;
                    } %>
                    </tr>
                    
                </c:forEach>
            
        </c:forEach>
            
        
                </table>  
</div>
    </body>
</html>
