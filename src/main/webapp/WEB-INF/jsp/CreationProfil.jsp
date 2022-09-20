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
		
		<form method="post" enctype="multipart/form-data">
			<div class="form">
				<div class="gauche">
					<p>
						<label for="pseudo">Pseudo</label>
						<input type="text" name="pseudo" id="pseudo" required="required" placeholder="Renseigner votre pseudo...">
					</p>
					<p>	
						<label for="prenom">Prénom</label>
						<input type="text" name="prenom" id="prenom" required="required" placeholder="Renseigner votre Prénom...">
					</p>
					<p>	
						<label for="tel">Numéro de téléphone</label>
						<input type="tel" name="tel" id="tel" required="required">
					</p>
					<p>	
						<label for="cp">Code postal</label>
						<input type="number" name="number" id="cp" min="01000" max="99999" required="required">
					</p>
					<p>	
						<label for="mdp">Mot de passe</label>
						<input type="password" name="password" id="mdp" required="required">
					</p>
				</div>
				<div class="droite">
					<p>
						<label for="nom">NOM</label>
						<input type="text" name="nom" id="nom" required="required" placeholder="Renseigner votre NOM...">
					</p>
					<p>	
						<label for="mail">E-mail</label>
						<input type="email" name="email" id="mail" required="required">
					</p>
					<p>	
						<label for="rue">Rue</label>
						<input type="text" name="rue" id="rue" required="required">
					</p>
					<p>
						<label for="ville">Ville</label>
						<input type="text" name="ville" id="ville" required="required">
					</p>	
					<p>
							<label for="confMdp">Confirmation</label>
							<input type="password" name="password" id="confMdp" required="required">
					</p>
				</div>
			</div>
				
				<div class="bouton">
					<a href="#">Créer</a>
					<a href="#">Annuler</a>
				</div>
		</form>
	</body>
</html>