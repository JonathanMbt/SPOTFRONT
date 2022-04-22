<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Liked Musics - SPOT</title>
		<link href="../spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<c:set var="playlists" value="${requestScope.playlists}" scope="page" />
		<c:set var="musics" value="${requestScope.musics}" scope="request" />
		
		<div class="row">
			<jsp:include page="nav.jsp" />
			<div class="content">
				<h2>Titres Likés</h2>
				<div class="row spacing wrap center">
					<c:forEach var="m" items="${musics}">
						<div class="small Card" style="flex-direction: column">
							<h4>${m.getTitle()}</h4>
							<a href="./artist?a=${m.getArtist().getName()}">${m.getArtist().getName()}</a>
							<c:if test="${m.isLikedBy(sessionScope.user.getUsername())}"><form method="POST" ><input type="hidden" value="${m.getId()}" name="id"/><input type="submit" name="unlike" value="Unlike"/></form></c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</body>
</html>