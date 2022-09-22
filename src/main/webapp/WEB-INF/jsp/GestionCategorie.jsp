<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
		<label for="libelle">libelle</label> 
		<input type="text" id="libelle" name="libelle"> 

		<button type="submit" name="action" value="creation">créer</button>
		<button type="submit" name="action" value="suppression">supprimer</button>
		<button type="submit" name="action" value="annulation">annuler</button>

	</form>
</body>
</html>