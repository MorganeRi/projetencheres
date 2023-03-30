<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Detail Article" />
</jsp:include>



	<%
	ArticleVendu art = (ArticleVendu) request.getAttribute("article");
	%>

<div class="container" style="border: 1px solid silver;width:500px;">
<h1>Detail Article</h1>
<ul class="list-group list-group-flush">
  <li class="list-group-item"><b>Nom :</b> : <%=art.getNomArticle()%> </li>
   <li class="list-group-item"><b>Description :</b> : <%=art.getDescription()%></li>
  <li class="list-group-item"><b>Categorie : </b> : <%=art.getCategorie().getLibelle()%></li>
  <li class="list-group-item"><b>Meilleur offre : </b> </li>
  <li class="list-group-item"><b>Mise à prix : </b> : <%=art.getPrixDeVente()%></li>
  <li class="list-group-item"><b>Fin de l'enchère : </b> : <%=art.getDateFinEnchere()%></li>
  <li class="list-group-item"><b>Retrait : </b> : <%=art.getRetrait().getRue()%> <%=art.getRetrait().getCodePostal()%> <%=art.getRetrait().getVille()%></li>
  <li class="list-group-item"><b>Vendeur : </b> : <%=art.getUtilisateur().getNom()%></li>
</ul>
<form action="encherir" method="post">
<label class="form-label me-3" for="MiseAPrix"><b>Ma proposition : </b></label>
<input class="form-control" type="number" id="enchere" name="enchere" min="<%=art.getPrixDeVente()%>"  style="width: 100px"/>
<input  type="submit" value="Encherir" class="btn btn-dark me-3"/>
</form>
</div>

<jsp:include page="./fragments/foot.jsp"></jsp:include>