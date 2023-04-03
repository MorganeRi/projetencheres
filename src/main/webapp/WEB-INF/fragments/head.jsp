<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Integer id = (Integer) session.getAttribute("id");



String pseudo = (String) session.getAttribute("pseudo");



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=request.getParameter("title")%></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body>

	<nav
		class="sticky-top navbar navbar-expand-lg bg-body-tertiary bg-dark bg-gradient"
		data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="ServletAccueil"><img class="img-fluid" alt="logo" src="/projetencheres/images/logo.jpg" width="100" height="auto"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<%
					if (id != null) {
					%>
					<li class="nav-item"><a class="nav-link"
						href="ServletAjoutArticle">Vendre un article</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServletMonProfil">Mon profil</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServletDeconnexion">Déconnexion [<%=pseudo%>]
					</a></li>
					<%
					} else {
					%>
					<li class="nav-item"><a class="nav-link"
						href="ServletAjoutUtilisateur">S'inscrire</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServletConnexion">Se connecter</a></li>
					<%
					}
					%>

				</ul>


			</div>

		</div>
	</nav>
