<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Accueil ENI-ENCHERE</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<script defer
	src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
</head>

<body>

	<!-- header section starts  -->

	<header>

		<input type="checkbox" name="" id="toggler"> <label
			for="toggler" class="fas fa-bars"></label> <a href="#" class="logo">ENI-ENCHERE<span>.</span></a>

		<nav class="navbar">
			<ul>
				<li><a href="#encheres">Enchères</a></li>
				<li><a href="#vendre">Ventes</a></li>
				<li><a href="#profil">Mon profil</a></li>
			</ul>
		</nav>

		<div class="icons">
			<a href="#" class="fa-solid fa-heart"></a>
			<a href="#"	class="fa-solid fa-cart-shopping"></a>
			<a href="#" class="fa-solid fa-user"></a> 
			<a href="#" class="fa-solid fa-power-off"></a>
		</div>

	</header>

	<!-- header section ends -->

	<!-- home section starts  -->
	<section class="home" id="home">

		<div class="content">
			<h3>les encheres</h3>
			<span> bla bla bla </span>
			<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
				Beatae laborum ut minus corrupti dolorum dolore assumenda iste
				voluptate dolorem pariatur.</p>
			<a href="#" class="btn">infos</a>
		</div>

	</section>

	<!-- home section ends -->



	<!-- liste encheres section starts  -->

	<section class="products" id="products">

		<h1 class="heading">
			liste <span>des enchères</span>
		</h1>

		<div class="search">
			<div class="search-box">
				<button class="btn-search">
					<i class="fas fa-search"></i>
				</button>
				<input type="text" class="input-search"
					placeholder="Chercher un article...">
			</div>

			<select name="cat" class="cat">
				<option value="">--Catégories--</option>
				<option value="Ameublement">Ameublement</option>
				<option value="Informatique">Informatique</option>
				<option value="Sport">Sport et Loisirs</option>
				<option value="Vêtements">Vêtements</option>
			</select>
		</div>




		<div class="box-container">

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>PC Gamer pour travailler</h3>
					<div class="price">Prix : 100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">Vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>Rocket stove pour riz et pâtes</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>Tasse renard</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>Enceinte Bluetooth</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

		</div>

	</section>

	<!-- liste enchere section ends -->



	<!-- derniers articles section starts  -->

	<section class="products" id="products">

		<h1 class="heading">
			derniers <span>articles</span>
		</h1>

		<div class="box-container">

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="image">
					<img src="css/images/article1.jpg" alt="">
					<div class="icons">
						<a href="#" class="fa-solid fa-heart-circle-plus"></a> <a href="#"
							class="fa-solid fa-cart-plus"></a> <a href="#"
							class="fa-solid fa-share-from-square"></a>
					</div>
				</div>
				<div class="content">
					<h3>article</h3>
					<div class="price">100.eni</div>
					<div class="date-fin">Fin de l'enchère : date</div>
					<div class="vendeur">
						Vendeur : <a href="#">vendeur</a>
					</div>
				</div>
			</div>

		</div>

	</section>

	<!-- derniers articles section ends -->




	<!-- contact section starts  -->

	<section class="contact" id="contact">

		<h1 class="heading">
			<span> Contactez </span> nous
		</h1>

		<div class="row">

			<form action="">
				<input type="text" placeholder="Nom" class="box"> <input
					type="email" placeholder="Email" class="box"> <input
					type="number" placeholder="Telephone" class="box">
				<textarea name="" class="box" placeholder="Votre message..." id=""
					cols="30" rows="10"></textarea>
				<input type="submit" value="envoyer message" class="btn">
			</form>

			<div class="image">
				<img src="css/images/contact-img.svg" alt="">
			</div>

		</div>

	</section>

	<!-- contact section ends -->

	<!-- about section starts  -->

	<section class="about" id="about">

		<h1 class="heading">
			<span> à </span> propos
		</h1>

		<div class="row">

			<div class="content">
				<h3>pour en savoir plus sur nous</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem
					cumque sit nemo pariatur corporis perspiciatis aspernatur a ullam
					repudiandae autem asperiores quibusdam omnis commodi alias repellat
					illum, unde optio temporibus.</p>
				<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
					Accusantium ea est commodi incidunt magni quia molestias
					perspiciatis, unde repudiandae quidem.</p>
				<a href="#" class="btn">en savoir plus</a>
			</div>

		</div>

	</section>

	<!-- about section ends -->

	<!-- footer section starts  -->

	<section class="footer">

		<div class="box-container">

			<div class="box">
				<h3>liens rapides</h3>
				<a href="#">enchères</a> <a href="#">ventes</a> <a href="#">articles</a>
				<a href="#">à propos</a> <a href="#">contact</a>
			</div>

			<div class="box">
				<h3>Espace perso</h3>
				<a href="#">mon profil</a> <a href="#">mes ventes</a> <a href="#">mes
					favoris</a>
			</div>

			<div class="box">
				<h3>Pays</h3>
				<a href="#">france</a> <a href="#">europe</a> <a href="#">USA</a> <a
					href="#">japon</a>

			</div>

			<div class="box" id="infos">
				<h3>infos contacts</h3>
				<a href="#">+123-456-7890</a> <a href="#">example@gmail.com</a> <a
					href="#">nantes, france - 44100</a> <img src="css/images/payment.png" alt="">
			</div>

		</div>

		<div class="credit">
			crée par <span> Ugo, Pierre et Patrick </span> | all rights reserved
		</div>

	</section>
</html>