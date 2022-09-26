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
        <title>état vente</title>
    </head>
    <body>
        <%@ include file="../html/header.html" %>
    
    
    <div>
    <c:if test="${article.etatVente == Article.EtatsVente.ENCOURS}">
    	<h1>Détail vente</h1>
    	<img alt="Impossible de charger l'image..." src="">
    	<h2>"${article.nom}"</h2>
    	<h2 class="description">Description : ${description}</h2>
    	<h2 class="cat">Catégorie : ${choixCat}</h2>
    	<div class="enchere">Meilleure offre : </div>                  <!-- insérer enchère encours -->
		<div class="price">Mise à prix : ${article.miseAPrix}</div>
    	<div class="date-fin">Fin de l'enchère : ${article.dateFinEncheres}</div>
    	<div class="retrait">Retrait : ${article.getVendeur().retrait}</div>
    	<div class="vendeur">Vendeur : ${article.getVendeur().getPseudo()}</div>    
		<input type="number">
	</c:if>
	</div>
					    
	<c:if test="${article.etatVente == Article.EtatsVente.TERMINEE}"> 
		<h1>ACHETEUR a remporté l'enchère</h1>	
			<!-- insérer l'acheteur final  -->
	</c:if>
        
        <div class="content">
			<h3>"${article.nom}"</h3>
				<c:if test="${article.etatVente == Article.EtatsVente.ENCOURS}">
					<div class="cat">Catégorie : ${choixCat}</div>
				
				</c:if>
				<div class="enchere">Meilleure offre : </div>                  <!-- insérer enchère finale -->
				<div class="price">Mise à prix : ${article.miseAPrix}</div>
				
				<c:if test="${article.etatVente == Article.EtatsVente.ENCOURS}">
					<div class="date-fin">Fin de l'enchère : ${article.dateFinEncheres}</div>
				</c:if>
				
				
				<div class="retrait">Retrait : ${article.getVendeur().retrait}</div>
				<div class="vendeur">Vendeur : ${article.getVendeur().getPseudo()}</div>
				<c:if test="${article.etatVente == Article.EtatsVente.TERMINEE}">
					<div class="telephone">Tel : ${article.getTelephone()}</div>
				</c:if>	















				<c:if test="${article.etatVente == Article.EtatsVente.ENCOURS}">
				</c:if>
					    
				<c:if test="${article.etatVente == Article.EtatsVente.TERMINEE}">
				</c:if>
		</div>
	
		<%@ include file="../html/footer.html" %>
		
    </body>
</html>