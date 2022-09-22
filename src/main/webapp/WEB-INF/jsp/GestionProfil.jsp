<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>Création d'un utilisateur</title>
	</head>
	<body>
		<h1>Création profil</h1>
		
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
		
		<form method="post"  action="<%=request.getContextPath()%>/profil">
			<div class="form">
					<div>
						<label for="pseudo">Pseudo</label>
						<input type="text" name="pseudo" id="pseudo" placeholder="Renseigner votre pseudo..."><br>
						
						<label for="prenom">Prénom</label>
						<input type="text" name="prenom" id="prenom" placeholder="Renseigner votre Prénom..."><br>
						
						<label for="tel">Numéro de téléphone</label>
						<input type="tel" name="tel" id="tel"><br>
						
						<label for="cp">Code postal</label>
						<input type="number" name="codepostal" id="cp" min="01000" max="99999"><br>
						
						<label for="mdp">Mot de passe</label>
						<input type="password" name="mdp" id="mdp"><br>
					</div>
					
					<div>
						<label for="nom">NOM</label>
						<input type="text" name="nom" id="nom" placeholder="Renseigner votre NOM..."><br>
						
						<label for="mail">E-mail</label>
						<input type="email" name="email" id="mail"><br>
						
						<label for="rue">Rue</label>
						<input type="text" name="rue" id="rue"><br>
						
						<label for="ville">Ville</label>
						<input type="text" name="ville" id="ville"><br>
						
						<div>
							<label for="confirmationMdp">Confirmation</label>
							<input type="password" name="password" id="confirmationMdp"><br>
						</div>
					</div>
				</div>
				<br>
				<div>
					<button type="submit" name="action" value="creation">Créer</button>
					<button type="submit" name="action" value="suppression">Supprimer</button>
					<button type="submit" name="action" value="modification">Modifier</button>
					<button type="submit" name="action" value="annulation">Annuler</button>
				</div>
			</form>
	</body>
</html>