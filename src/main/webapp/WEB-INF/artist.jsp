<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${requestScope.artist.getName()} - SPOT</title>
		<link href="../spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<c:set var="playlists" value="${requestScope.playlists}" scope="request" />
		<c:set var="musics" value="${requestScope.musics}" scope="request" />
		
		<div class="row">
			<jsp:include page="nav.jsp" />
			<div class="content">
				<p>Label : ${requestScope.artist.getLabel()}</p>
				<p>Description : ${requestScope.artist.getDescription()}</p>
				<div class="row">
					<form method="POST" action="artist">
						<input type="hidden" name="a" value="${requestScope.artist.getName()}" /> 
						<input type="submit" name="deleteArtist" value="Supprimer Artiste" /> 
					</form>
				</div>
				<div class="row spacing wrap center">
					<c:forEach var="m" items="${musics}">
						<div class="small Card" style="flex-direction: column">
							<h4>${m.getTitle()}</h4>
							<form method="POST" action="artist">
								<input type="hidden" name="a" value="${requestScope.artist.getName()}" />
								<input type="hidden" name="music" value="${m.getId()}"/>
								<input type="submit" name="deleteMusic" value="Supprimer" /> 
							</form>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</body>
</html>