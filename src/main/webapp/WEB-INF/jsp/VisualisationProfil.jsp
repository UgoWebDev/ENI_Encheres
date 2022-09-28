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
	        <div class="margeTop">
	            <h1>
	                <span class="entetePat">Pseudo: </span>${profil.pseudo }
	            </h1>
	
	            <p>
	                <span class="entetePat">NOM: </span>${profil.nom }
	            </p>
	
	            <p>
	                <span class="entetePat">Prénom: </span>${profil.prenom }
	            </p>
	
	            <p>
	                <span class="entetePat">Email: </span>${profil.email }
	            </p>
	
	            <p>
	                <span class="entetePat">Téléphone: </span>${profil.telephone }
	            </p>
	
	            <p>
	                <span class="entetePat">Rue: </span>${profil.getAdresse().getRue()}
	            </p>
	
	            <p>
	                <span class="entetePat">Code postal: </span>${profil.getAdresse().getCodePostal()}
	            </p>
	
	            <p>
	                <span class="entetePat">Ville: </span>${profil.getAdresse().getVille()}
	            </p>
	        </div>
	    </c:if> 
	    
	    <c:if test="${empty profil }">
	    	<div class="visualisation">
	    		<p>Ce profil n'existe pas</p>
	    	</div>
	    </c:if>
	    
       
        <c:if test="${user.pseudo  == profil.pseudo }">
        	 <form method="post" action="<%= request.getContextPath()%>/visualisation">
		        <div>
		           <button type="submit" name="action" value="modifier" class="btn">Modifier</button>
		       	</div>
	       	 </form>
	    </c:if>
       
       <%@ include file="../html/footer.html" %>
    
    </body>
</html>