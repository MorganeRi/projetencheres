<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Integer id = (Integer) session.getAttribute("id");

String pseudo = (String) session.getAttribute("pseudo");

Boolean estAdmin = (Boolean) session.getAttribute("admin");
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
<body style="padding-bottom:100px;">

	<nav
		class="sticky-top navbar navbar-expand-lg bg-body-tertiary bg-dark bg-gradient"
		data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="./Accueil"><img
				class="img-fluid" alt="logo" src="./images/logo.jpg" width="100"
				height="auto"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mr-auto">
					<%
					if (id != null) {
					%>
					<li class="nav-item"><a class="nav-link text-white font-weight-bold"
						href="./VendreUnArticle">Vendre un article</a></li>
					<li class="nav-item"><a class="nav-link text-white font-weight-bold"
						href="./Profil">Mon profil</a></li>

					<%
					if (estAdmin) {
					%>
					<li class="nav-item"><a class="nav-link text-white font-weight-bold"
						href="./Administrateur">Interface Administrateur</a></li>
					<%
					}

					} else {
					%>
					<li class="nav-item"><a class="nav-link text-white font-weight-bold"
						href="./Inscription">S'inscrire</a></li>
					<li class="nav-item"><a class="nav-link text-white font-weight-bold"
						href="./seConnecter">Se connecter</a></li>
					<%
					}
					%>

				</ul>
				<ul class="navbar-nav" style="margin-left:auto;">
					<%
					if (id != null) {
					%>
					<li class="nav-item font-weight-bold"><a class="nav-link text-white"
						href="./Deconnexion">Déconnexion [<%=pseudo%>]
					</a></li>
					<%
					}
					%>
				</ul>

			</div>

		</div>
	</nav>