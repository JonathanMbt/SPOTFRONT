<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Search - SPOT</title>
		<link href="../spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<c:set var="playlists" value="${requestScope.playlists}" scope="request" />
		<c:set var="artists" value="${requestScope.artists}" scope="request" />
		<c:set var="musics" value="${requestScope.musics}" scope="request" />
		<c:set var="playlists_search" value="${requestScope.playlists_search}" scope="request"/>
		
		<div class="row">
			<jsp:include page="nav.jsp" />
			<div class="content">
				<form method="POST" action="search">
					<input type="text" name="search" />
					<input type="submit" name="searchButton" value="Rechercher" /> 
				</form>
				<h2>Artistes</h2>
				<div class="row spacing wrap center">
					<c:forEach var="a" items="${artists}">
						<div class="small Card" style="flex-direction: column">
							<h4>${a.getName()}</h4>
						</div>
					</c:forEach>
				</div>
				<h2>Musiques</h2>
				<div class="row spacing wrap center">
					<c:forEach var="m" items="${musics}">
						<div class="small Card" style="flex-direction: column">
							<h4>${m.getTitle()}</h4>
							<a href="./artist?a=${m.getArtist().getName()}">${m.getArtist().getName()}</a>
						</div>
					</c:forEach>
				</div>
				<h2>Playlists</h2>
				<div class="row spacing wrap center">
					<c:forEach var="p" items="${playlists_search}">
						<div class="small Card" style="flex-direction: column">
							<h4>${p.getName()}</h4>
							<a href="./playlist?p=${p.getId()}">${p.getName()}</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</body>
</html>