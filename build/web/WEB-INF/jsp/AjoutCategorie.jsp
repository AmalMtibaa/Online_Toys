<%-- 
    Document   : AjoutCategorie
    Created on : 21 janv. 2018, 20:24:37
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>formulaire</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" type="text/css" href="resources/css/default.css" />
 <link rel="stylesheet" type="text/css" href="resources/css/component.css" />
       
  
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
  <div class="form">
         
      
        <div id="signup">   
            <h1 style="color: white;"><b>Ajout Categorie</b></h1>
          
          <form:form method="post" action="AjoutCategorie" modelAttribute="categories" id="formula">
          
          <div class="field-wrap">
            <label>
              ID<span class="req">*</span>
            </label>
            <form:input path="id" type="number" required="required" value="     ."  />
          </div>
              
           <div class="field-wrap">
            <label>
              Nom<span class="req">*</span>
            </label>
            <form:input path="nom" type="text" required="required" />
          </div>
       
          <button type="submit" class="button button-block"/>OK</button>
          
     </form:form>     

        
       <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="resources/js/index.js"></script>
        
      </div><!-- form -->


</body>
</html>