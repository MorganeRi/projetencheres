<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="MonProfil"/>
</jsp:include>
<body>



	<%
	Utilisateur utilisateur = (Utilisateur) request.getAttribute("Utilisateur");
	%>

<div class="container" style="border: 1px solid silver;width:500px;">
<h1>Mon Profil</h1>
<ul class="list-group list-group-flush">
  <li class="list-group-item"><b>PSEUDO</b> : <%=utilisateur.getPseudo()%> </li>
   <li class="list-group-item"><b>NOM</b> : <%=utilisateur.getNom()%></li>
  <li class="list-group-item"><b>PRENOM</b> : <%=utilisateur.getPrenom()%></li>
  <li class="list-group-item"><b>EMAIL</b> : <%=utilisateur.getEmail()%></li>
  <li class="list-group-item"><b>TELEPHONE</b> : <%=utilisateur.getTelephone()%></li>
  <li class="list-group-item"><b>RUE</b> : <%=utilisateur.getRue()%></li>
  <li class="list-group-item"><b>CODE POSTAL</b> : <%=utilisateur.getCodePostal()%></li>
  <li class="list-group-item"><b>VILLE</b> : <%=utilisateur.getVille()%></li>
</ul>
<br>
<a href="ServletModifierUtilisateur" class="btn btn-dark"
					role="button">Modifier</a>
</div>
</body>
<jsp:include page="./fragments/foot.jsp"></jsp:include>