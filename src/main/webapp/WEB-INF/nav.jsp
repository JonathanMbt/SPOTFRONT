<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="leftNav">
	<ul class="navlink">
		<li><a href="./home">Accueil</a></li>
		<li><a href="./search">Recherche</a></li>
	</ul>
	<div class="spaceDivider"></div>
	<ul class="navlink">
		<li><a href="./playlists">Créer une nouvelle playlist</a></li>
		<li><a href="./artists">Ajouter un nouvel artiste</a></li>
		<li><a href="./musics">Ajouter une nouvelle musique</a></li>
		<li><a href="./likedMusics">Titres Likés</a></li>
	</ul>
	<hr>
	<ul class="navlink scroll">
		<c:forEach var="p" items="${playlists}">
			<li><a href="./playlist?p=${p.getId()}">${p.getName()}</a></li>
		</c:forEach>
	</ul>
</div>