<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.bo.Enchere"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Detail Article" />
</jsp:include>



<%
Integer id = (Integer) session.getAttribute("id");
ArticleVendu art = (ArticleVendu) request.getAttribute("article");
Enchere enchereMax = (Enchere) request.getAttribute("enchereMax");
%>

<div class="container" style="border: 1px solid silver; width: 500px;">
	<h1>Detail Article</h1>
	<ul class="list-group list-group-flush">
		<li class="list-group-item"><img alt="photo"
			src="<%=art.getPhoto()%>"></li>
		<li class="list-group-item"><b>Nom :</b> <%=art.getNomArticle()%>
		</li>
		<li class="list-group-item"><b>Description :</b> <%=art.getDescription()%></li>
		<li class="list-group-item"><b>Categorie : </b> <%=art.getCategorie().getLibelle()%></li>
		<li class="list-group-item"><b>Meilleur offre :</b> <%=enchereMax.getMontantEnchere()%></li>
		<li class="list-group-item"><b>Mise à prix : </b> <%=art.getPrixInitial()%></li>
		<li class="list-group-item"><b>Fin de l'enchère : </b> <%=art.getDateFinEnchere()%></li>
		<li class="list-group-item"><b>Retrait : </b> <%=art.getRetrait().getRue()%>
			<%=art.getRetrait().getCodePostal()%> <%=art.getRetrait().getVille()%></li>
		<li class="list-group-item"><b>Vendeur : </b> <%=art.getUtilisateur().getNom()%></li>
	</ul>
	<%
	Enchere enchereAjoute = (Enchere) request.getAttribute("enchere");
	if (enchereAjoute != null) {
	%>
	<p style="color: green;">Votre enchere a ete prise en compte avec
		succes</p>


	<%
	}
	%>
	<%
	List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
	if (listeCodesErreur != null) {
	%>
	<p style="color: red;">Erreur, l'enchère n'est pas
		possible :</p>
	<%
	for (int codeErreur : listeCodesErreur) {
	%>
	<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
	<%
	}
	}
	%>
	<form method="post"
		action="<%=request.getContextPath()%>/ServletDetailArticle">
		<label class="form-label me-3" for="MiseAPrix"><b>Ma
				proposition : </b></label> <input class="form-control" type="number"
			id="enchere" name="enchere"
			min="<%=enchereMax.getMontantEnchere() + 1%>"
			value="<%=enchereMax.getMontantEnchere()%>" style="width: 100px" /> <input
			type="submit" value="Encherir" class="btn btn-dark me-3" />
	</form>

	<%
	if (id == art.getUtilisateur().getNoUtilisateur()) {
	%>
	<a href="ServletModifierArticle?idArticle=<%=art.getNoArticle()%>"
		class="btn btn-dark" role="button">Modifier Article</a>
	<%
	}
	%>
</div>

<jsp:include page="./fragments/foot.jsp"></jsp:include>