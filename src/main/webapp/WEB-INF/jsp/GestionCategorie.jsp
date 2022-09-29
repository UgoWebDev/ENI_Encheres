<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ page import="fr.eni.enchere.bo.Categorie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../html/header.html" %>

	
	<form method="post" action="<%=request.getContextPath()%>/categorie">
	<div class="homeModif">
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
		
			<h2>Categories</h2>
			<select name="cat" class="cat">
				<c:forEach var="choixCat" items="${listeCategories}">
					<option value="${choixCat.noCategorie}">${choixCat.libelleCategorie}</option>
				</c:forEach>
			</select>
		
		<label for="libelle">Libelle</label> 
		<input type="text" id="libelle" name="libelle" value=""> 		<%-- Ajouter ici la catégorie sélectionnée --%>
	</div>
	
	<div class="btnCat">
		<button class="btn" type="submit" name="action" value="creation">Créer</button>
		<button class="btn" type="submit" name="action" value="suppression">Supprimer</button>
		<button class="btn" type="submit" name="action" value="annulation">Annuler</button>
	</div>
		
	</form>
	
    <%@ include file="../html/footer.html" %>
	
</body>
</html>