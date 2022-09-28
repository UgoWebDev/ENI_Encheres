<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>Se connecter</title>
	</head>
	<body>
		
		<header>	
			<input type="checkbox" name="" id="toggler"> 
			<label for="toggler" class="fas fa-bars"></label> <a href="accueil" class="logo">ENI-ENCHERE<span>.</span></a>
		</header>

	
	<div class="home" id="connexion">
		<form method="post" action="<%=request.getContextPath()%>/connexion" class="content">
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
			<div class="connexion">
				<div class="login">
					<label for="login"><span>Login</span></label> 
					<input type="text" id="login" name="login"> 
				</div>
				<div class="mdp">
					<label for="mdp"><span>Mot de passe</span></label> 
					<input type="password" id="mdp" name="mdp">
				</div>
				
				<div class="bouton">
					<button type="submit" name="action" value="connexion" class="btn">Se connecter</button>
					<button type="submit" name="action" value="inscription" class="btn">S'inscrire</button>
					<button type="submit" name="action" value="annulation" class="btn">Annuler</button>
				</div>
			</div>
		</form>
	</div>
		
		<%@ include file="../html/footer.html" %>
	</body>
</html>