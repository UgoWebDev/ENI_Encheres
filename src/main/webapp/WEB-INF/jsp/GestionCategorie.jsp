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

	<div class="homeModif">
	<form method="post" action="<%=request.getContextPath()%>/categorie">
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
			<br>
		
		<label for="libelle">libelle</label> 
		<input type="text" id="libelle" name="libelle" value=""> 		<%-- Ajouter ici la catégorie sélectionnée --%>

		<button class="btn" type="submit" name="action" value="creation">créer</button>
		<button class="btn" type="submit" name="action" value="suppression">supprimer</button>
		<button class="btn" type="submit" name="action" value="annulation">annuler</button>

	</form>
	</div>
    <%@ include file="../html/footer.html" %>
	
</body>
</html>