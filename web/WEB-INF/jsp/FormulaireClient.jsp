<%-- 
    Document   : FormulaireClient
    Created on : 19 janv. 2018, 10:42:13
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
        <script src="resources/js/modernizr.custom.js"></script>
  
</head>

<body>
    <c:if test="${!empty erreur1}">
    <script>
        alert("${erreur1}"); 
    </script>
 </c:if>
    
     <c:if test="${!empty erreurConnection}">
    <script>
        alert("${erreurConnection}"); 
    </script>
 </c:if>
    

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

   <ul class="tab-group">
        <li class="tab active"><a href="#login">Login</a></li>
        <li class="tab "><a href="#signup">S'inscrire</a></li>
        
      </ul>
      
      <div class="tab-content">
          <div id="login">   
              <h1 style="color:white"><b>Login</b></h1>
          
          <form method="post" modelAttribute="user" action="connexion" id="formula">
          
            <div class="field-wrap">
            <label>
              Email <span class="req">*</span>
            </label>
            <input name="mail" type="email" autocomplete="off" required />
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input name="password" type="password" autocomplete="off" required/>
          </div>

          <button type="submit" class="button button-block"/>login</button>
          </form>
          </div>
          
        <div id="signup">   
            <h1 style="color:white"><b>S'inscrire</b></h1>
          
          <form:form method="post" action="AjoutClient" modelAttribute="user" id="formula">
          
          <div class="field-wrap">
            <label>
              Nom<span class="req">*</span>
            </label>
            <form:input path="nom" type="text" required="required" />
          </div>
       

          <div class="field-wrap">
            <label>
              Age<span class="req">*</span>
            </label>
            <form:input path="age" type="number" value="     ." />
          </div>

          <div class="field-wrap">
            <label>
              Email<span class="req">*</span>
            </label>
            <form:input path="mail" type="email" required="required" />
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <form:input path="password" type="password" required="required"/>
          </div>
          
          <button type="submit" class="button button-block"/>OK</button>
          
          </form:form>

        </div>
      

        </div>
      </div><!-- form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="resources/js/index.js"></script>

</body>
</html>