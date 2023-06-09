<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.bo.Enchere"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Enchere"%>
<%@page import="fr.eni.projetenchere.bll.EnchereManager"%>
<%@page import="fr.eni.projetenchere.bll.EnchereManagerSing"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Detail Article" />
</jsp:include>



<%
Integer id = (Integer) session.getAttribute("id");
ArticleVendu art = (ArticleVendu) request.getAttribute("article");
Enchere enchereMax = (Enchere) request.getAttribute("enchereMax");
Utilisateur utilisateurMax = (Utilisateur) request.getAttribute("utilisateurActuelMax");
Boolean estActif = (Boolean) session.getAttribute("actif");


if(id!=null){

%>


	<h1 class="font-weight-bold text-center">Detail Article</h1>

<div class="container-fluid"
	style="width: 20%;">

	<%
	if ((art != null && LocalDateTime.now().isAfter(art.getDateFinEnchere()) && id != null
			&& id == art.getUtilisateur().getNoUtilisateur())) {
	%>

	<h2><%=utilisateurMax != null ? (utilisateurMax.getPseudo()+" a remporté l'enchere") : ""%> 🏆</h2> 
	
	<%
	}
	%>
	<%
	if (art != null && LocalDateTime.now().isAfter(art.getDateFinEnchere()) && id != null && utilisateurMax != null
			&& id.equals(utilisateurMax.getNoUtilisateur())) {
	%>


	<h2>Bravo vous avez remporté l'enchère 🥇</h2> 🥇
	<%
	}
	%>
	<ul class="list-group list-group-flush">
		<li class="list-group-item border-warning"><img class="img-fluid" alt="photo"
			width="200" height="auto" src="<%=art.getPhoto()%>"></li>
		<li class="list-group-item border-warning"><b>Nom :</b> <%=art.getNomArticle()%>
		</li>
		<li class="list-group-item border-warning"><b>Description :</b> <%=art.getDescription()%></li>
		<li class="list-group-item border-warning"><b>Categorie : </b> <%=art.getCategorie().getLibelle()%></li>
		<%
		if (enchereMax.getMontantEnchere() == null) {
		%>
		<li class="list-group-item border-warning"><b>Meilleur offre :</b>0</li>
		<%
		} else {
		%>
		<li class="list-group-item border-warning"><b>Meilleur offre :</b> <%=enchereMax.getMontantEnchere()%></li>
		<%
		}
		%>
		<li class="list-group-item border-warning"><b>Mise à prix : </b> <%=art.getPrixInitial()%></li>
		<li class="list-group-item border-warning"><b>Fin de l'enchère : </b> <%=art.getDateFinEnchere()%></li>
		<li class="list-group-item border-warning"><b>Retrait : </b> <%=art.getRetrait().getRue()%>
			<%=art.getRetrait().getCodePostal()%> <%=art.getRetrait().getVille()%></li>
		<li class="list-group-item border-warning"><b>Vendeur : </b> <%=art.getUtilisateur().getNom()%></li>
	</ul>
	<%
	boolean enchereSoumise = false; // initialisez la variable booléenne
	Enchere enchereAjoute = (Enchere) request.getAttribute("enchere");
	if (enchereAjoute == null) {
		enchereAjoute = new Enchere();
		enchereAjoute.setDateEnchere(LocalDateTime.now());
	}
	EnchereManager enchereManager = EnchereManagerSing.getInstanceEnchereImpl();
	Enchere ench=null;
	if (enchereAjoute.getDateEnchere().isBefore(art.getDateFinEnchere())
			&& enchereAjoute.getDateEnchere().isAfter(art.getDateDebutEnchere())) {
		if(enchereManager.selectMaxEnchere(art)!=null){
			ench=enchereManager.selectMaxEnchere(art); 
		}
		if (id != art.getUtilisateur().getNoUtilisateur()&& estActif==true && (ench==null || ench.getUtilisateur().getNoUtilisateur()!=id)) {
			if (request.getMethod().equals("POST")) {
		enchereSoumise = true;
	%>
	<p style="color: green;">Votre enchere a ete prise en compte avec
		succes</p>


	<%
			}
	List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
	if (listeCodesErreur != null) {
	%>
	<p style="color: red;">Erreur, l'enchère n'est pas possible :</p>
	<%
	for (int codeErreur : listeCodesErreur) {
	%>
	<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
	<%
	}
	}
	%>

		<br>
		<form method="post"
			action="<%=request.getContextPath()%>/ServletDetailArticle">
			<div class="mb-1 d-flex flex-row justify-content-center">
				<label class="form-label me-3 mb-2" for="MiseAPrix">
					<b> Faire une proposition : </b>
				</label> 
				<input class="form-control me-3 mb-2" type="number"
					id="enchere" name="enchere"
					<%if (enchereMax.getMontantEnchere() == 0) {%>
					min="<%=art.getPrixInitial() + 1%>" <%} else {%>
					min="<%=enchereMax.getMontantEnchere() + 1%>" <%}%>
					<%if (enchereMax.getMontantEnchere() == 0) {%>
					value="<%=art.getPrixInitial() + 1%>" <%} else {%>
					value="<%=enchereMax.getMontantEnchere() + 1%>" <%}%>
					style="width: 100px" />
				 <input type="submit" value="Encherir"
					class="btn btn-warning btn-lg me-3 mb-2 shadow" />
			</div>
		</form>
	
	
		<%
		}
		%>
		<%
		}
		%>
	
		<%
		if (id == art.getUtilisateur().getNoUtilisateur() && LocalDateTime.now().isBefore(art.getDateDebutEnchere())) {
		%>
	
		<a href="./ModifierArticle?idArticle=<%=art.getNoArticle()%>"
			class="btn btn-warning btn-lg shadow" role="button">Modifier Article</a> 
		<a href="./SupprimerArticle?idArticle=<%=art.getNoArticle()%>"
			class="btn btn-warning btn-lg shadow" role="button">Supprimer Article</a>
		<%
		}}
		%>
		
		<%
	if (id == art.getUtilisateur().getNoUtilisateur() && LocalDateTime.now().isAfter(art.getDateFinEnchere())) {
	%>
	<a href="./DetailEnchere?idArticle=<%=art.getNoArticle()%>"
		class="btn btn-warning btn-lg shadow" role="button">Consulter le detail des encheres</a>
	<%
	}
	%>

	
</div>

<jsp:include page="./fragments/foot.jsp"></jsp:include>