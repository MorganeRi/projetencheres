<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="ProfilVendeur"/>
</jsp:include>
<body>



	<%
	Utilisateur utilisateur = (Utilisateur) request.getAttribute("Utilisateur");
	%>

<h1 class="font-weight-bold text-center">Profil Vendeur</h1>
<div class="container-fluid" style="width:20%;">
<ul class="list-group list-group-flush">
  <li class="list-group-item border-warning"><b>Pseudo</b> : <%=utilisateur.getPseudo()%> </li>
   <li class="list-group-item border-warning"><b>Nom</b> : <%=utilisateur.getNom()%></li>
  <li class="list-group-item border-warning"><b>Prenom</b> : <%=utilisateur.getPrenom()%></li>
  <li class="list-group-item border-warning"><b>Email</b> : <%=utilisateur.getEmail()%></li>
  <li class="list-group-item border-warning"><b>Telephone</b> : <%=utilisateur.getTelephone()%></li>
  <li class="list-group-item border-warning"><b>Rue</b> : <%=utilisateur.getRue()%></li>
  <li class="list-group-item border-warning"><b>Code Postal</b> : <%=utilisateur.getCodePostal()%></li>
  <li class="list-group-item border-warning"><b>Ville</b> : <%=utilisateur.getVille()%></li>
</ul>
</div>
<br>
</body>
<jsp:include page="./fragments/foot.jsp"></jsp:include>