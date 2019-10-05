<%-- 
    Document   : Recherche
    Created on : 29 janv. 2018, 14:15:16
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    <br><br>
    
    <form:form method="post"  modelAttribute="jouet" action="RechercheJouet"  id="formula">
        <form:input path="nom" type="text"/>
        <button type="submit" class="button button-block"/>Rechercher</button>
    </form:form>
        <table>  
            <tr>
        <c:forEach items="${resultatrecherche}" var="jouet" >  
            
           
            <td>
                <img src="resources/img/${jouet.photo}" height="150" width="150"><br>
                <b>Nom :</b><c:out value="${jouet.nom} "/><br>
                <b>Prix :</b><c:out value="${jouet.prix} "/>dt<br>
                <b>Stock:</b><c:out value="${jouet.stock} "/> <br>
                <a href="AccueilControllerPanier?link_nomJouet=${jouet.nom}">Ajouter Au panier</a>
                
            </td>
        </c:forEach>
        </tr>
                </table>  
</div>
    </body>
</html>