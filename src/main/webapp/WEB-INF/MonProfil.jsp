<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="MonProfil"/>
</jsp:include>
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
	
	<a href="ServletModifierUtilisateur" class="btn btn-dark"
					role="button">Modifier</a>

</body>
<jsp:include page="./fragments/foot.jsp"></jsp:include>