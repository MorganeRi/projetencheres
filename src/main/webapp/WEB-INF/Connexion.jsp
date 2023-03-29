<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion Enchere</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body>

	<%
	List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
	if (listeCodesErreur != null) {
	%>
	<p style="color: red;">Erreur, la connexion n'est pas possible :</p>
	<%
	for (int codeErreur : listeCodesErreur) {
	%>
	<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
	<%
	}
	}
	%>

<div class="container">
	<form action="<%=request.getContextPath()%>/ServletConnexion"
		method="post">
		<label for="idPseudo">Pseudo : </label><input type="text"
			id="idPseudo" name="pseudo"
			value="<%=listeCodesErreur != null ? request.getParameter("pseudo") : ""%>" />
		<br /> <label for="idPass">Mot de passe : </label><input
			type="password" id="idPass" name="mdp"
			value="<%=listeCodesErreur != null ? request.getParameter("mdp") : ""%>" />
		<br /> <input type="submit" class="btn btn-dark" value="Se connecter" />
	</form>

</div>

</body>
</html>