<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.enchere.bo.Utilisateur" %>
<%@ page import="fr.eni.enchere.bo.Article" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <title>nouvelle vente</title>
    </head>
    <body>
    
    	<%@ include file="../html/header.html" %>
    	<div class="home">
	        <h1>Nouvelle Vente</h1>
	        <form method="post" action="">
		        <div class="content">
		            <div class="carre">
		                <h3>Photo upload</h3>
		            </div>
		                
		            <div>

		                    <label for="nomArticle">Article</label>
		                    <input type="text" name="nomArticle" id="article" maxlength="20" />

		                    <textarea rows="7" cols="40" name="description" placeholder="Décrivez votre article..."></textarea>

		            
		                <select name="cat" class="cat">
						<option value="all">--Catégories--</option>
							<c:forEach var="choixCat" items="${listeCategories}">
								<option value="${choixCat}">${choixCat}</option>
							</c:forEach>
						</select>

		                    <input type="file" id="image" name="image" accept="image/*" class="custom-file-input btn">

		                    <label for="miseAPrix">Mise à  prix</label>
		                    <input type="number" name="miseAPrix" id="prix">

		                    <label for="dateDebutEncheres">Début de l'enchère</label>
		                    <input type="date" name="dateDebutEncheres"/>

		                    <label for="dateFinEncheres">Fin de l'enchère</label>
		                    <input type="date" name="dateFinEncheres"/>

			
						<% Utilisateur userConnected =  (Utilisateur) session.getAttribute("user"); %>
						
		                <fieldset>
		
		                    <legend>Retrait</legend>
		
		                        <label for="rue">Rue</label>
		                        <input type="text" name="rue" id="rue" value="${userConnected.getAdresse().getRue() }">

		                        <label for="codePostal">Code postal</label>
		                        <input type="number" name="codePostal" id="codePostal" min="01000" max="99999" value="${userConnected.getAdresse().getCodePostal() }">

		                        <label for="ville">Ville</label>
		                        <input type="text" name="ville" id="ville" value="${userConnected.getAdresse().getVille() }">

		
		
		                </fieldset>
		
		                <div class="bouton">
			                    <button type="submit" name="action" value="enregistrer">Enregistrer</button>
			                    <button type="submit" name="action" value="annuler">Annuler</button>
			                    
			       			 <%-- 
						        <c:if test="${article.etatVente == Article.EtatsVente.CREATION || article.etatVente == Article.EtatsVente.ENCOURS}">
							        <div class="bouton">
			                    		<button type="submit" name="action" value="annulerVente${article.noArticle}">Annuler la vente</button>
							       	</div>
							    </c:if> --%>
						    
		                </div>
		
		            </div>
		        </div>
			</form>
		</div>
		
		<%@ include file="../html/footer.html" %>
    </body>
</html>