<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${requestScope.playlist.getName()} - SPOT</title>
		<link href="../spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<c:set var="playlists" value="${requestScope.playlists}" scope="page" />
		<c:set var="musics" value="${requestScope.musics}" scope="request" />
		<c:set var="musicsAvailable" value="${requestScope.musicsAvailable}" scope="request" />
		
		<div class="row">
			<jsp:include page="nav.jsp" />
			<div class="content">
				<p>Description : ${requestScope.playlist.getDescription()}</p>
				<div class="row">
					<form method="POST" action="playlist">
						
						<div class="formRow">
							<label for="music">Musiques:</label>
							<select name="music" id="music">
								<c:forEach var="m" items="${musicsAvailable}">
									<option value="${m.getId()}">${m.getTitle()}</option>							
								</c:forEach>
							</select>
						</div>
						<input type="hidden" name="p" value="${requestScope.playlist.getId()}" />
	
						<input type="submit" name="updatePlaylist" value="Ajouter Musique" /> 
						<input type="submit" name="deletePlaylist" value="Supprimer Playlist" /> 
					</form>
				</div>
				<div class="row spacing wrap center">
					<c:forEach var="m" items="${musics}">
						<div class="small Card" style="flex-direction: column">
							<h4>${m.getTitle()}</h4>
							<a href="./artist?a=${m.getArtist().getName()}">${m.getArtist().getName()}</a>
							<form method="POST" action="playlist">
								<input type="hidden" name="p" value="${requestScope.playlist.getId()}" />
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