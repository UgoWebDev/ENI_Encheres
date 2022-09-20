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
					<div>
						<label for="pseudo">Pseudo</label>
						<input type="text" name="pseudo" id="pseudo" placeholder="Renseigner votre pseudo..."><br>
						
						<label for="prenom">Prénom</label>
						<input type="text" name="prenom" id="prenom" placeholder="Renseigner votre Prénom..."><br>
						
						<label for="tel">Numéro de téléphone</label>
						<input type="tel" name="tel" id="tel"><br>
						
						<label for="cp">Code postal</label>
						<input type="number" name="number" id="cp" min="01000" max="99999"><br>
						
						<label for="mdp">Mot de passe</label>
						<input type="password" name="password" id="mdp"><br>
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
							<label for="confMdp">Confirmation</label>
							<input type="password" name="password" id="confMdp"><br>
						</div>
					</div>
				</div>
				
				<div>
					<button type="submit" name="action" value="creation">Créer</button>
					<button type="submit" name="action" value="suppression">Supprimer</button>
					<button type="submit" name="action" value="modification">Modifier</button>
					<button type="submit" name="action" value="annulation">Annuler</button>
				</div>
			</form>
	</body>
</html>