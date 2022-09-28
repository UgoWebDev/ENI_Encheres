<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>Etat vente</title>
    </head>
    <body>
        <%@ include file="../html/header.html" %>
    
    
	    <div class="connexion">
		    <c:if test="${article.etatVente == 'ENCOURS'}">
		    	<h1>Détail vente</h1>
		    </c:if>
		    
		    <c:if test="${article.etatVente == 'TERMINEE' && user.pseudo == enchere.utilisateurEnchere.pseudo }">     <!-- enchere gagnée -->
		    	<h1>Vous avez remporté la vente</h1>
		    </c:if>
		    
		    <c:if test="${article.etatVente == 'TERMINEE' && user.pseudo != enchere.utilisateurEnchere.pseudo } ">		<!-- enchere perdue -->
		    	<h1>${enchere.utilisateurEnchere.pseudo} a remporté la vente</h1>
		    </c:if>
		    
		    	<img alt="Impossible de charger l'image..." src="">
		    	<h2>"${article.nomArticle}"</h2>
		    	<h2 class="description">Description : ${article.description}</h2>
		    	
	    	 <c:if test="${article.etatVente == 'ENCOURS'}">
		    	<h2 class="cat">Catégorie : ${article.categorie.libelleCategorie}</h2>
	   		 </c:if>
	   		 
		    	<div class="enchere">Meilleure offre : ${enchere.montantEnchere } par ${enchere.utilisateurEnchere.pseudo}</div>                  <!-- insérer enchère encours -->
				<div class="price">Mise à prix : ${article.miseAPrix}</div>
		    	<div class="date-fin">Fin de l'enchère : ${article.dateFinEncheres}</div>
		    	<div class="retrait">Retrait : ${article.retrait}</div>
		    	<div class="vendeur">Vendeur : ${article.vendeur.pseudo}</div>
		    	
		    <c:if test="${article.etatVente == 'ENCOURS'}">
				<form method="post" action="detail">
			    	<label for="proposition">Ma proposition : </label>   
					<input type="number" name="proposition" min="5">
					<button class="encherir" type="submit" name="action" value="encherir">Enchérir</button>
				</form>		    
			</c:if>
			
		    <c:if test="${article.etatVente == 'TERMINEE' && user.pseudo == enchere.utilisateurEnchere.pseudo }">     <!-- enchere gagnée -->
		    	<div class="telephone">Tel : ${article.vendeur.telephone}</div>
		    </c:if>
		    
			<c:if test="${article.etatVente == 'TERMINEE' && user.pseudo == enchere.utilisateurEnchere.pseudo }">     <!-- enchere gagnée -->
				<button class="back" type="button">Back</button>   
			 </c:if>
			 
			 <c:if test="${article.etatVente == 'TERMINEE' && user.pseudo != enchere.utilisateurEnchere.pseudo } ">		<!-- enchere perdue -->
				<button class="retrait" type="button">Retrait effectué</button>
		    </c:if>
		</div>
	
		<%@ include file="../html/footer.html" %>
		
    </body>
</html>