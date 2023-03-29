<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon Profil</title>
</head>
<body>

<h1>Mon Profil</h1>

	<%
	Utilisateur utilisateur = (Utilisateur) request.getAttribute("Utilisateur");
	%>


	<p>Pseudo : <%=utilisateur.getPseudo()%></p>
	<p>Nom : <%=utilisateur.getNom()%></p>
	<p>Prénom : <%=utilisateur.getPrenom()%></p>
	<p>Email : <%=utilisateur.getEmail()%></p>
	<p>Téléphone : <%=utilisateur.getTelephone()%></p>
	<p>Rue : <%=utilisateur.getRue()%></p>
	<p>Code Postal : <%=utilisateur.getCodePostal()%></p>
	<p>Ville : <%=utilisateur.getVille()%></p>
	

</body>
</html>