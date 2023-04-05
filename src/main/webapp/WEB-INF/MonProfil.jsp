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

<div class="container-fluid" style="width:40%;">
<ul class="list-group list-group-flush">
  <li class="list-group-item border-warning"><b>Pseudo</b> : <%=utilisateur.getPseudo()%> </li>
   <li class="list-group-item border-warning"><b>Nom</b> : <%=utilisateur.getNom()%></li>
  <li class="list-group-item border-warning"><b>Prenom</b> : <%=utilisateur.getPrenom()%></li>
  <li class="list-group-item border-warning"><b>Email</b> : <%=utilisateur.getEmail()%></li>
  <li class="list-group-item border-warning"><b>Telephone</b> : <%=utilisateur.getTelephone()%></li>
  <li class="list-group-item border-warning"><b>Rue</b> : <%=utilisateur.getRue()%></li>
  <li class="list-group-item border-warning"><b>Code Postal</b> : <%=utilisateur.getCodePostal()%></li>
  <li class="list-group-item border-warning"><b>Ville</b> : <%=utilisateur.getVille()%></li>
  <li class="list-group-item border-warning"><b>Credit</b> : <%=utilisateur.getCredit()%></li>
</ul>
<br>
	<div class="mb-1 d-flex  justify-content-end">
		<a href="ServletModifierUtilisateur" class="btn btn-warning btn-lg mb-1 shadow"
					role="button">Modifier</a>
	</div>
</div>
</body>
<jsp:include page="./fragments/foot.jsp"></jsp:include>