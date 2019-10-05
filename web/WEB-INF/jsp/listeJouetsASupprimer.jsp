<%-- 
    Document   : listeJouetsASupprimer
    Created on : 24 janv. 2018, 14:33:20
    Author     : asus
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${nomCategorie}</title>
        <link rel="stylesheet" type="text/css" href="resources/css/default.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/component.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/tableau.css" />
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
        <h1>${nomCategorie}</h1>
         <table>
             <tr>
                    <td><b><font color="red">Photo</font></b></td>
                    <td><b><font color="red">Nom </font></b></td>
                    <td><b><font color="red">Description</font></b></td>
                    <td><b><font color="red">Prix(dt)</font></b></td>
                    <td><b><font color="red">Stock</font></b></td>
             </tr>
             
            
        <c:forEach items="${listeJouets}" var="jouet" >    
             <tr>
            <td> <img src="resources/img/${jouet.photo}" height="150" width="150"></td>
            <td></b><c:out value="${jouet.nom}"/></td>
            <td></b><c:out value="${jouet.description}"/>dt</td>
            <td></b><c:out value="${jouet.prix}"/>dt   </td>
            <td><c:out value="${jouet.stock}"/> </td>
            <td>   <a href="SupprimerJouet?link_codeJouet=${jouet.nom}">Supprimer</a></td>
            <td>   <a href="AffichageUpdateJouet?link_codeJouet=${jouet.nom}">modifier</a></td>
            
          </tr>
       
    
        </c:forEach>
            
         </table>
</div>
    </body>
</html>
