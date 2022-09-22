<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.enchere.bo.Utilisateur" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>afficher pseudo</title>
    </head>
    <body>
    
   		<c:if test="${!empty user}">
	        <div class="affichePseudo">
	            <h1>
	                Pseudo: ${user.pseudo }
	            </h1>
	
	            <p>
	                NOM: ${user.nom }
	            </p>
	
	            <p>
	                Prénom: ${user.prenom }
	            </p>
	
	            <p>
	                Email: ${user.email }
	            </p>
	
	            <p>
	                Téléphone: ${user.telephone }
	            </p>
	
	            <p>
	                Rue: ${user.rue }
	            </p>
	
	            <p>
	                Code postal: ${user.codePostal }
	            </p>
	
	            <p>
	                Ville: ${user.ville }
	            </p>
	        </div>
	    </c:if> 
	    
	    <c:if test="${empty user }">
	    	<p>User n'existe pas</p>
	    </c:if>
	    
        <% Utilisateur userConnected =  (Utilisateur) session.getAttribute("user"); %>
       
        <c:if test="${userConnected.getPseudo() } == ${requestScope.user.pseudo }">
        	 <form method="post" action="<%= request.getContextPath()%>/visualisation">
		        <div class="bouton">
		           <button type="submit" name="action" value="modifier">Modifier</button>
		       	</div>
	       	 </form>
	    </c:if>
       
    </body>
</html>