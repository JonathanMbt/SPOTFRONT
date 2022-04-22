<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Artists - SPOT</title>
		<link href="../spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<c:set var="playlists" value="${requestScope.playlists}" scope="request" />
		
		<div class="row">
			<jsp:include page="nav.jsp" />
			<div class="content">
				<form method="POST" action="artists">
					<div class="formRow">
						<label for="name">Nom : </label> 
						<input type="text" name="name" id="name" required/><br/>
						<c:if test="${erreurs.containsKey('name')}">${erreurs.get('name')}</c:if>
					</div>
					
					<div class="formRow">
						<label for="label">Label : </label> 
						<input type="text" name="label" id="label" required/><br/>
						<c:if test="${erreurs.containsKey('label')}">${erreurs.get('label')}</c:if>
					</div>

					<div class="formRow">
						<label for="description">Description: </label> 
						<textarea name="description" id="description" ></textarea><br/>
						<c:if test="${erreurs.containsKey('description')}">${erreurs.get('description')}</c:if>
					</div>

					<input type="submit" name="createArtist" value="Créer" /> 
				</form>
			</div>
		</div>

	</body>
</html>