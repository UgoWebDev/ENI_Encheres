<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>Etat vente</title>
    </head>
    <body>
        <%@ include file="../html/header.html" %>
    
    
    <div>
    <c:if test="${article.etatVente == Article.EtatsVente.ENCOURS}">
    	
    	<h1>Détail vente</h1>
    	<img alt="Impossible de charger l'image..." src="">
    	<h2>"${article.nomArticle}"</h2>
    	<h2 class="description">Description : ${article.description}</h2>
    	<h2 class="cat">Catégorie : ${choixCat}</h2>
    	<div class="enchere">Meilleure offre : </div>                  <!-- insérer enchère encours -->
		<div class="price">Mise à prix : ${article.miseAPrix}</div>
    	<div class="date-fin">Fin de l'enchère : ${article.dateFinEncheres}</div>
    	<div class="retrait">Retrait : ${article.getVendeur().getRetrait}</div>
    	<div class="vendeur">Vendeur : ${article.getVendeur().getPseudo()}</div>
    	<form method="post" action="detail">
	    	<label for="proposition">Ma proposition : </label>   
			<input type="number" name="proposition" min="5">
			<button class="encherir" type="submit" name="action" value="encherir">Enchérir</button>
		</form>
	</c:if>
	
	<c:if test="${article.etatVente.name} == TERMINEE && ${user.pseudo} == ${enchere.getUtilisateurEnchere().getPseudo() }">     <!-- enchere gagnée -->
    	<h1>Vous avez remporté la vente</h1>
    	<img alt="Impossible de charger l'image..." src="">
    	<h2>"${article.nomArticle}"</h2>
    	<h2 class="description">Description : ${article.description}</h2>
    	<div class="enchere">Meilleure offre : </div>                  <!-- insérer enchère encours -->
		<div class="price">Mise à prix : ${article.miseAPrix}</div>
    	<div class="date-fin">Fin de l'enchère : ${article.dateFinEncheres}</div>
    	<div class="retrait">Retrait : ${article.getVendeur().getRetrait}</div>
    	<div class="vendeur">Vendeur : ${article.getVendeur().getPseudo()}</div>
		<button class="retrait" type="button">Retrait effectué</button>
		
	</c:if>
	
	<c:if test="${article.etatVente.name} == TERMINEE && ${user.pseudo} != ${enchere.getUtilisateurEnchere().getPseudo() } ">		<!-- enchere perdue -->
    	<h1>${enchere.getUtilisateurEnchere().getPseudo()} a remporté la vente</h1>
    	<img alt="Impossible de charger l'image..." src="">
    	<h2>"${article.nomArticle}"</h2>
    	<h2 class="description">Description : ${article.description}</h2>
    	<div class="enchere">Meilleure offre : ${enchere.getMontantEnchere() } par ${enchere.getUtilisateurEnchere().getPseudo()}</div>                  <!-- insérer enchère encours -->
		<div class="price">Mise à prix : ${article.miseAPrix}</div>
    	<div class="retrait">Retrait : ${article.getVendeur().getRetrait}</div>
    	<div class="vendeur">Vendeur : ${article.getVendeur().getPseudo()}</div>
    	<div class="telephone">Tel : ${article.getVendeur().getTelephone()}</div>
		<button class="back" type="button">Back</button>
	</c:if>
	</div>
	
		<%@ include file="../html/footer.html" %>
		
    </body>
</html>