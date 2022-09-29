<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<title>Accueil ENI-ENCHERE</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
	</head>
	
	<body>

		<!-- header section starts  -->
		
		
			<c:if test="${!empty user}">							
				<%@ include file="../html/header.html" %>
			</c:if>
			
			<c:if test="${empty user}">
				<header>	
					<input type="checkbox" name="" id="toggler"> 
					<label for="toggler" class="fas fa-bars"></label> <a href="accueil" class="logo">ENI-ENCHERE<span>.</span></a>
					
					
					<div class="icons">
						<a href="${pageContext.request.contextPath}/connexion" class="fa-solid fa-power-off"></a>
					</div>
				</header>						
			</c:if>	
	
		<!-- header section ends -->
	
		<!-- home section starts  -->
		<section class="home">
	
			<div class="content">
				<h3>Les Encheres</h3>
				<span> Les objets sont nos amis </span>
				<p><strong>Nos ambitions</strong>
					Donner une seconde vie aux objets en facilitant les échanges.
					Encourager la réutilisation.
					Promouvoir des échanges non commerciaux.</p>
				<a href="#info" class="btn">Infos</a>
			</div>
	
		</section>
	
		<!-- home section ends -->
	
	
	
		<!-- liste encheres section starts  -->
	
		<section class="products" id="products">
	
			<h1 class="heading">
				Liste <span>des enchères</span>
			</h1>
		<form method="post" action="<%=request.getContextPath()%>/accueil">
			
			<div class="search">

				<c:if test="${!empty user}">
				
					<div class="optionAchatVente">
						<div class="optionAchat">
							<input type="radio" name="radioAchatVente" id="achat" value="achat"/>
							<label for="achat">Achats</label>
							
							<div>
								<input type="checkbox" name="checkbox" id="enchereOuverte"/>
								<label for="enchereOuverte">Enchères ouverte</label><br/>
								<input type="checkbox" name="checkbox" id="enchereEnCours"/>
								<label for="enchereEnCours">Mes enchères en cours</label><br/>
								<input type="checkbox" name="checkbox" id="enchereRemporte"/>
								<label for="enchereRemporte">Mes enchères remportées</label>
							</div>
						</div>
						
						<div class="optionVente">
							<input type="radio" name="radioAchatVente" id="vente" value="vente"/>
							<label for="vente">Mes ventes</label>
							
							<div>
								<input type="checkbox" name="checkbox" id="venteEnCours"/>
								<label for="venteEnCours">Mes ventes en cours</label><br/>
								<input type="checkbox" name="checkbox" id="venteNonDebute"/>
								<label for="venteNonDebute">Mes ventes non débutées</label><br/>
								<input type="checkbox" name="checkbox" id="venteTermine"/>
								<label for="venteTermine">Mes ventes terminées</label>
							</div>
						</div>
					</div>
					
					<select name="choixCategorie" class="cat">
						<option value="0">--Catégories--</option>
						<c:forEach var="choixCat" items="${listeCategories}">
							<option value="${choixCat.noCategorie}">${choixCat.libelleCategorie}</option>
						</c:forEach>
					</select>
				</c:if>
				
				<div class="search-box">
					<button class="btn-search">
						<i class="fas fa-search"></i>
					</button>
					<input name="chaineRecherche" type="text" class="input-search" placeholder="Chercher un article...">
				</div>
			</div>
			
			<div class="bouton">
				<button type="submit" name="action" value="rechercher" class="btn">Rechercher</button>
			</div>
			
		</form>

		<div class="box-container">
				<c:choose>
					<c:when test="${listeArticle.size()>0}">
						
							<c:forEach var="article" items="${listeArticle}">
							<div class="box">
								<div class="image">
									<img src="css/images/article1.jpg" alt="">
									
									<c:if test="${!empty user}">
										<div class="icons">
											<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
											<a href="${pageContext.request.contextPath}/detail?noArticle=${article.noArticle}" class="fa-solid fa-cart-plus"></a> 
											<a href="#" class="fa-solid fa-share-from-square"></a>
										</div>
									</c:if>
									
								</div>
								<div class="content">
									<h3>"${article.nomArticle}"</h3>
									<div class="price">Prix : ${article.miseAPrix}</div>
									<div class="date-fin">Fin de l'enchère : ${article.dateFinEncheres}</div>
									<div class="vendeur">Vendeur : 
										<c:if test="${!empty user}">
											<a href="${pageContext.request.contextPath}/visualisation?profilPseudo=${article.getVendeur().getPseudo()}">${article.getVendeur().getPseudo()}</a>
										</c:if>
										<c:if test="${empty user}">${article.getVendeur().getPseudo()}
										</c:if>
									</div>
								</div>
								</div>
							</c:forEach>
						
					</c:when>
					<c:otherwise>
						<p>Pas d'articles disponibles actuellement...
						<p>
					</c:otherwise>
				</c:choose>
			</div>
	
		</section>
	
		<!-- liste enchere section ends -->
	
	
	
		<!-- derniers articles section starts  -->
	
		<section class="products" id="products">
	
			<h1 class="heading">
				Derniers <span>articles</span>
			</h1>
	
			<div class="box-container">
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a>
						</div>
					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>

					</div>
				</div>
	
				<div class="box">
					<div class="image">
						<img src="css/images/article1.jpg" alt="">
						<div class="icons">
							<a href="#" class="fa-solid fa-heart-circle-plus"></a> 
							<a href="#" class="fa-solid fa-cart-plus"></a> 
							<a href="#" class="fa-solid fa-share-from-square"></a>
						</div>
					</div>
					<div class="content">
						<h3>Article</h3>
						<div class="price">100.eni</div>
						<div class="date-fin">Fin de l'enchère : date</div>
						<div class="vendeur">Vendeur : <a href="#">vendeur</a></div>
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
					<input type="text" placeholder="Nom" class="box"> 
					<input type="email" placeholder="Email" class="box"> 
					<input type="number" placeholder="Telephone" class="box">
					<textarea name="" class="box" placeholder="Votre message..." id="" cols="30" rows="10"></textarea>
					<input type="submit" value="Envoyer message" class="btn">
				</form>
	
				<div class="image">
					<img src="css/images/contact-img.svg" alt="">
				</div>
	
			</div>
	
		</section>
	
		<!-- contact section ends -->
	
		<!-- about section starts  -->
	
		<section class="about">
	
			<h1 class="heading">
				<span> A </span> propos
			</h1>
	
			<div class="row">
	
				<div class="content" id="info">
					<h3>Pour en savoir plus sur nous</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem
						cumque sit nemo pariatur corporis perspiciatis aspernatur a ullam
						repudiandae autem asperiores quibusdam omnis commodi alias repellat
						illum, unde optio temporibus.</p>
					<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
						Accusantium ea est commodi incidunt magni quia molestias
						perspiciatis, unde repudiandae quidem.</p>
					<a href="#" class="btn">En savoir plus</a>
				</div>
	
			</div>
	
		</section>
	
		<!-- about section ends -->
	
		<!-- footer section starts  -->
	
		<section class="footer">
	
			<div class="box-container">
	
				<div class="box">
					<h3>Liens rapides</h3>
					<c:if test="${!empty user}">
						<a href="#">Enchères</a> 
						<a href="#">Ventes</a> 
					</c:if>
					<a href="#">Articles</a>
					<a href="#">A propos</a> 
					<a href="#">Contact</a>
				</div>
	
				<c:if test="${!empty user}">
					<div class="box">
						<h3>Espace perso</h3>
						<a href="#">Mon profil</a> 
						<a href="#">Mes ventes</a> 
						<a href="#">Mes favoris</a>
				</div>
				</c:if>
	
				<div class="box">
					<h3>Pays</h3>
					<a href="#">France</a> 
					<a href="#">Europe</a> 
					<a href="#">USA</a> 
					<a href="#">Japon</a>
	
				</div>
	
				<div class="box" id="infos">
					<h3>Infos contacts</h3>
					<a href="#">+123-456-7890</a> 
					<a href="#">Example@gmail.com</a> <a
						href="#">Nantes, France - 44100</a> 
						<img src="css/images/payment.png" alt="">
				</div>
	
			</div>
	
		</section>
		
		 <%@ include file="../html/footer.html" %>
	</body>
</html>