<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="../html/header.html" %>

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
		
<%-- 		<c:if test="${!empty listeCategories}"> --%>
			<strong>Categories</strong>
			<select name="cat" class="cat">
				<c:forEach var="choixCat" items="${listeCategories}">
					<option value="${choixCat.getLibelle()}">--${choixCat.getLibelle()}--</option>
<!-- 					<option value="3">Sport et Loisirs</option> -->
				</c:forEach>
				
			</select>
			<br>
<%-- 		</c:if> --%>
		
		<label for="libelle">libelle</label> 
		<input type="text" id="libelle" name="libelle"> 

		<button type="submit" name="action" value="creation">créer</button>
		<button type="submit" name="action" value="suppression">supprimer</button>
		<button type="submit" name="action" value="annulation">annuler</button>

	</form>
	
    <%@ include file="../html/footer.html" %>
	
</body>
</html>