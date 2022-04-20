<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login - SPOT</title>
		<link href="./spot.css" rel="stylesheet">
	</head>
	<body>
		<c:set var="erreurs" value="${requestScope.errors}" scope="page" />
		<div class="rowFullPage">
			<div class="Card large">
				<form method="POST" action="login">
					<div class="formRow">
						<label for="username">Username: </label> 
						<input type="text" name="username" id="username" required/><br/>
						<c:if test="${erreurs.containsKey('username')}">${erreurs.get('username')}</c:if>
					</div>

					<div class="formRow">
						<label for="password">Password: </label> 
						<input type="password" name="password" id="password" required /><br/>
						<c:if test="${erreurs.containsKey('password')}">${erreurs.get('password')}</c:if>
					</div>

					<input type="submit" name="Se connecter" value="Se connecter" /> 
				</form>
			</div>
		</div>
	</body>
</html>