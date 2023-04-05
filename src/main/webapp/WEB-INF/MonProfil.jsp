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

<h1 class="font-weight-bold text-center">Mon profil</h1>

<div class="container-fluid" style="width:500px;">
<ul class="list-group list-group-flush">
  <li class="list-group-item"><b>Pseudo</b> : <%=utilisateur.getPseudo()%> </li>
   <li class="list-group-item"><b>Nom</b> : <%=utilisateur.getNom()%></li>
  <li class="list-group-item"><b>Prenom</b> : <%=utilisateur.getPrenom()%></li>
  <li class="list-group-item"><b>Email</b> : <%=utilisateur.getEmail()%></li>
  <li class="list-group-item"><b>Telephone</b> : <%=utilisateur.getTelephone()%></li>
  <li class="list-group-item"><b>Rue</b> : <%=utilisateur.getRue()%></li>
  <li class="list-group-item"><b>Code Postal</b> : <%=utilisateur.getCodePostal()%></li>
  <li class="list-group-item"><b>Ville</b> : <%=utilisateur.getVille()%></li>
  <li class="list-group-item"><b>Credit</b> : <%=utilisateur.getCredit()%></li>
</ul>
<br>
	<div class="mb-1 d-flex  justify-content-end">
		<a href="ServletModifierUtilisateur" class="btn btn-warning btn-lg mb-1"
					role="button">Modifier</a>
	</div>
</div>
</body>
<jsp:include page="./fragments/foot.jsp"></jsp:include>