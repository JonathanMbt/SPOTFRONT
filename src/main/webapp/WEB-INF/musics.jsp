<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Musics - SPOT</title>
		<link href="../spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<c:set var="playlists" value="${requestScope.playlists}" scope="request" />
		<c:set var="artists" value="${requestScope.artists}" scope="request" />
		
		<div class="row">
			<jsp:include page="nav.jsp" />
			<div class="content">
				<form method="POST" action="musics">
					<div class="formRow">
						<label for="titre">Titre : </label> 
						<input type="text" name="titre" id="titre" required/><br/>
						<c:if test="${erreurs.containsKey('titre')}">${erreurs.get('titre')}</c:if>
					</div>
					
					<div class="formRow">
						<label for="genre">Genre : </label> 
						<input type="text" name="genre" id="genre" required/><br/>
						<c:if test="${erreurs.containsKey('genre')}">${erreurs.get('genre')}</c:if>
					</div>
					
					<div class="formRow">
						<label for="artist">Artiste :</label>
						<select name="artist" id="artist">
							<c:forEach var="a" items="${artists}">
								<option value="${a.getName()}">${a.getName()}</option>							
							</c:forEach>
						</select>
					</div>

					<input type="submit" name="createMusics" value="Créer" /> 
				</form>
			</div>
		</div>

	</body>
</html>