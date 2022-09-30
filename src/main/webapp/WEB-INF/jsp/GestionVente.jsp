<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>nouvelle vente</title>
    </head>
    
    <body>
    
        <%@ include file="../html/header.html" %>
        <section class="vente">
            <div class="photo">
                        <h3>Photo upload</h3>
                    </div>
            <form method="post" action="">
            
                <div class="content">
					<c:if test="${empty article}">
						<h2><span>Nouvelle Vente</span></h2>
					</c:if>
					<c:if test="${!empty article}">
						<h2><span>Mofication vente</span></h2>
					</c:if>
					
				<c:if test="${!empty listeCodesErreur}">
					<div class="alert alert-danger" role="alert">
						<strong>Erreur!</strong>
						<ul>
							<c:forEach var="code" items="${listeCodesErreur}">
								<li>${LecteurMessage.getMessageErreur(code)}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>

				<div class="description">
                        <label for="nomArticle">Article : </label>
                        <input type="text" name="nomArticle" id="article" maxlength="20" value=
							<c:if test="${!empty article}"> "${article.nomArticle }"</c:if>>
							
                        <textarea rows="7" cols="40" name="description" placeholder="Décrivez votre article...">
							<c:if test="${!empty article}"> "${article.description }"</c:if></textarea>
                    </div>

                    <div class="cat">
                        <select name="categorie" class="cat">
                            <c:forEach var="choixCat" items="${listeCategories}">
                                <option value="${choixCat.noCategorie}" <c:if test="${choixCat.noCategorie == article.categorie.noCategorie }">selected</c:if>>${choixCat.libelleCategorie}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="uploadimg">
                            <input type="file" name="image" accept="image/*" class="custom-file-input">
                    </div>
                    
                    <div class="prix">
                            <label for="miseAPrix">Mise à  prix</label>
                            <input type="number" min="0" name="miseAPrix" id="prix" value=
							<c:if test="${!empty article}"> "${article.miseAPrix }"</c:if>>
                    </div>
                    <div class="dateenchere">
                    	<p>
                            <label for="dateDebutEncheres">Début de l'enchère</label>
                            <input type="date" name="dateDebutEncheres" value=
							<c:if test="${!empty article}"> "${article.dateDebutEncheres }"</c:if>/>
                    	</p>
                    	<p class="dateFin">
                            <label for="dateFinEncheres">Fin de l'enchère</label> 
                            <input type="date" name="dateFinEncheres" value=
							<c:if test="${!empty article}">"${article.dateFinEncheres }"</c:if>/>
                        </p>
                    </div>
          
                        
                        <fieldset>
        
                            <legend>Retrait : </legend>
        
                                <label for="rue">Rue</label>
                                <input type="text" name="rue" id="rue" value= "${user.getAdresse().getRue() }"/>
																			

                                <label for="codePostal">Code postal</label>
                                <input type="number" name="codePostal" id="codePostal" min="01000" max="99999" value="${user.getAdresse().getCodePostal() }">

                                <label for="ville">Ville</label>
                                <input type="text" name="ville" id="ville" value="${user.getAdresse().getVille() }">

        
        
                        </fieldset>
        
                        <div class="enregistrer">
                                <c:if test="${empty article}">
                                     <button class="btn" type="submit" name="action" value="enregistrer">Enregistrer</button>
                                </c:if>
                                
                               
                                <c:if test="${article.etatVente == 'CREATION' || article.etatVente == 'ENCOURS'}">
                                    <button class="btn" type="submit" name="action" value="modification">Enregistrer</button>
                                	
                                    <div class="bouton">
                                        <button type="submit" name="action" value="annulerVente" class="btn">Annuler la vente</button>
                                       </div>
                                     
                                </c:if>
                                
                                <button class="btn" type="submit" name="action" value="annuler">Annuler</button>
                            
                        </div>
        
                    </div>
                </form>
            </section>
        
        <%@ include file="../html/footer.html" %>
    </body>
</html>