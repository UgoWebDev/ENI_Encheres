<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>se connecter</title>
	</head>
	<body>
		
		<header>	
			<input type="checkbox" name="" id="toggler"> 
			<label for="toggler" class="fas fa-bars"></label> <a href="accueil" class="logo">ENI-ENCHERE<span>.</span></a>
		</header>
		
		<form method="post" action="<%=request.getContextPath()%>/connexion" class="home">
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
				<div>
					<label for="login">Login</label> 
					<input type="text" id="login" name="login"> 
					
					<label for="mdp">Mot de passe</label> 
					<input type="password" id="mdp" name="mdp">
				</div>
				
				<div>
					<button type="submit" name="action" value="connexion" class="btn">Se connecter</button>
					<button type="submit" name="action" value="inscription" class="btn">S'inscrire</button>
					<button type="submit" name="action" value="annulation" class="btn">Annuler</button>
				</div>
			</div>
		</form>
	
	</body>
</html>