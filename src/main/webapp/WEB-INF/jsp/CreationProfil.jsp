<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Création profil</h1>
		
		<div class="form">
				<div>
					<label for="pseudo">Pseudo</label>
					<input type="text" name="pseudo" id="pseudo" required="required" placeholder="Renseigner votre pseudo..."><br>
					
					<label for="prenom">Prénom</label>
					<input type="text" name="prenom" id="prenom" required="required" placeholder="Renseigner votre Prénom..."><br>
					
					<label for="tel">Numéro de téléphone</label>
					<input type="tel" name="tel" id="tel" required="required"><br>
					
					<label for="cp">Code postal</label>
					<input type="number" name="number" id="cp" min="01000" max="99999" required="required"><br>
					
					<label for="mdp">Mot de passe</label>
					<input type="password" name="password" id="mdp" required="required"><br>
				</div>
				
				<div>
					<label for="nom">NOM</label>
					<input type="text" name="nom" id="nom" required="required" placeholder="Renseigner votre NOM..."><br>
					
					<label for="mail">E-mail</label>
					<input type="email" name="email" id="mail" required="required"><br>
					
					<label for="rue">Rue</label>
					<input type="text" name="rue" id="rue" required="required"><br>
					
					<label for="ville">Ville</label>
					<input type="text" name="ville" id="ville" required="required"><br>
					
					<div>
						<label for="confMdp">Confirmation</label>
						<input type="password" name="password" id="confMdp" required="required"><br>
					</div>
				</div>
			</div>
			
			<div>
				<a href="#">Créer</a>
				<a href="CreaProfil.jsp">Annuler</a>
			</div>
	</body>
</html>