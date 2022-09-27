<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.enchere.bo.Utilisateur" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="ccs/style.css">
        <title>afficher pseudo</title>
    </head>
    <body>
    
    	<%@ include file="../html/header.html" %>
    
   		<c:if test="${!empty profil}">
	        <div class="affichePseudo">
	            <h1>
	                Pseudo: ${profil.pseudo }
	            </h1>
	
	            <p>
	                NOM: ${profil.nom }
	            </p>
	
	            <p>
	                Prénom: ${profil.prenom }
	            </p>
	
	            <p>
	                Email: ${profil.email }
	            </p>
	
	            <p>
	                Téléphone: ${profil.telephone }
	            </p>
	
	            <p>
	                Rue: ${profil.getAdresse().getRue()}
	            </p>
	
	            <p>
	                Code postal: ${profil.getAdresse().getCodePostal()}
	            </p>
	
	            <p>
	                Ville: ${profil.getAdresse().getVille()}
	            </p>
	        </div>
	    </c:if> 
	    
	    <c:if test="${empty profil }">
	    	<p>Ce profil n'existe pas</p>
	    	<a href="/accueil">Retour</a>
	    </c:if>
	    
       
        <c:if test="${user.pseudo } == ${profil.pseudo }">
        	 <form method="post" action="<%= request.getContextPath()%>/visualisation">
		        <div class="bouton">
		           <button type="submit" name="action" value="modifier">Modifier</button>
		       	</div>
	       	 </form>
	    </c:if>
       
       <%@ include file="../html/footer.html" %>
    
    </body>
</html>