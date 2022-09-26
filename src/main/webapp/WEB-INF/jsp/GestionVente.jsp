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
        <h1>Nouvelle Vente</h1>
        
        <div class="page">
            <div class="carre">
                <p>Photo upload</p>
            </div>
                
            <div>
                <p>
                    <label for="nomArticle">Article</label>
                    <input type="text" name="nomArticle" id="article" maxlength="20" />
                </p>
                
                <p>
                    <textarea rows="7" cols="40" name="description">Décrivez votre article...</textarea>
                </p>
            
                <select name="cat" class="cat">
				<option value="all">--Catégories--</option>
					<c:forEach var="choixCat" items="${listeCategories}">
						<option value="${choixCat}">${choixCat}</option>
					</c:forEach>
				</select>
            
                <p>
                    <input type="file" id="image" name="image" accept="image/*" class="custom-file-input">
                </p>
                
                <p>
                    <label for="miseAPrix">Mise à  prix</label>
                    <input type="number" name="miseAPrix" id="prix">
                </p>
            
                <p>
                    <label for="dateDebutEncheres">Début de l'enchère</label>
                    <input type="date" name="dateDebutEncheres"/>
                </p>
            
                <p>
                    <label for="dateFinEncheres">Fin de l'enchère</label>
                    <input type="date" name="dateFinEncheres"/>
                </p>
	
				<% Utilisateur userConnected =  (Utilisateur) session.getAttribute("user"); %>
				
                <fieldset>

                    <legend>Retrait</legend>

                    <p>
                        <label for="rue">Rue</label>
                        <input type="text" name="rue" id="rue" value="${userConnected.getRue() }">
                    </p>

                    <p>
                        <label for="codePostal">Code postal</label>
                        <input type="number" name="codePostal" id="codePostal" min="01000" max="99999" value="${userConnected.getCodePostal() }">
                    </p>

                    <p>
                        <label for="ville">Ville</label>
                        <input type="text" name="ville" id="ville" value="${userConnected.getVille() }">
                    </p>


                </fieldset>

                <div class="bouton">
                	<form method="post" action="">
	                    <button type="submit" name="action" value="enregistrer">Enregistrer</button>
	                    <button type="submit" name="action" value="annuler">Annuler</button>
	                    
	        
				        <c:if test="${article.etatVente == Article.EtatsVente.CREATION || article.etatVente == Article.EtatsVente.ENCOURS}">
					        <div class="bouton">
	                    		<button type="submit" name="action" value="annulerVente">Annuler la vente</button>
					       	</div>
					    </c:if>
				    </form>
                </div>

            </div>
        </div>

    </body>
</html>