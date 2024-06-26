<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<%@ page import="fr.eni.enchere.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>Création d'un utilisateur</title>
	</head>
	<body>
		  <%@ include file="../html/header.html" %>
		  
		  <div class="homeModif">
			
		    <h1><strong><span> ${user.pseudo } </span></strong></h1>
			
			<c:if test="${empty user}">
				<h2><span>Création profil</span></h2>
			</c:if>
			<c:if test="${!empty user}">
				<h2><span>Mofication profil</span></h2>
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
			<c:if test="${OK==1 }">
				<h2> Modification du profil effectuée</h2>
			</c:if>
			
			
			<form method="post"  action="<%=request.getContextPath()%>/profil">
				<div class="form">
						<div class="infos">
							<label for="pseudo">Pseudo</label>
							<input type="text" name="pseudo" id="pseudo" placeholder="Renseigner votre pseudo..." value=
							<c:if test="${!empty user}"> "${user.pseudo }"</c:if>><br>
							
							<label for="mdp">Mot de passe</label>
							<input type="password" name="mdp" id="mdp" value=
							<c:if test="${!empty user}"> "${user.motDePasse }"</c:if>><br>
							
							<label for="confirmationMdp">Confirmation mot de passe</label>
								<input type="password" name="password" id="confirmationMdp" value=
							<c:if test="${!empty user}"> "${user.motDePasse }"</c:if>><br>
						</div>
						<div class="infos">
							<label for="prenom">Prénom</label>
							<input type="text" name="prenom" id="prenom" placeholder="Renseigner votre Prénom..." value=
							<c:if test="${!empty user}"> "${user.prenom }"</c:if>><br>
							
							<label for="nom">NOM</label>
							<input type="text" name="nom" id="nom" placeholder="Renseigner votre NOM..."value=
							<c:if test="${!empty user}"> "${user.nom }"</c:if>><br>
							
							<label for="tel">Numéro de téléphone</label>
							<input type="tel" name="tel" id="tel" value=
							<c:if test="${!empty user}"> "${user.telephone }"</c:if>><br>
							
							<label for="mail">E-mail</label>
							<input type="email" name="email" id="mail"value=
							<c:if test="${!empty user}"> "${user.email }"</c:if>><br>
	
						</div>
						
						<div class="infos">
							<label for="rue">Rue</label>
							<input type="text" name="rue" id="rue"value=
							<c:if test="${!empty user}"> "${user.adresse.rue }"</c:if>><br>
							
							<label for="ville">Ville</label>
							<input type="text" name="ville" id="ville"value=
							<c:if test="${!empty user}"> "${user.adresse.ville }"</c:if>><br>
							
							<label for="cp">Code postal</label>
							<input type="number" name="codepostal" id="cp" min="01000" max="99999" value=
							<c:if test="${!empty user}"> "${user.adresse.codePostal }"</c:if>><br>

						</div>
					</div>
					<br>
					<div class="buttonModif">
					     <c:if test="${empty user.noUtilisateur }">
							<button class="btn" type="submit" name="action" value="creation">Créer</button>
					      </c:if>
					      
					     <c:if test="${!empty user.noUtilisateur }">
					    	<button class="btn" type="submit" name="action" value="modification">Enregistrer</button>
							<button class="btn" type="submit" name="action" value="suppression">Supprimer</button>
					      </c:if>
	
						<button class="btn" type="submit" name="action" value="annulation">Annuler</button>
					</div>
				</form>
			
			</div>
			
			<%@ include file="../html/footer.html" %>
			
	</body>
</html>