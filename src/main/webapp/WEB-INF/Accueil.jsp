<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>
<script>
	function toggleCheckboxes() {
		var achatsRadio = document.getElementById("achatsRadio");
		var ventesRadio = document.getElementById("ventesRadio");
		var enchereCheckbox = document.getElementById("enchereCheckbox");
		var mesEncheresCheckbox = document
				.getElementById("mesEncheresCheckbox");
		var mesEncheresRemporteesCheckbox = document
				.getElementById("mesEncheresRemporteesCheckbox");
		var ventesEnCoursCheckbox = document
				.getElementById("ventesEnCoursCheckbox");
		var ventesNonDebuteesCheckbox = document
				.getElementById("ventesNonDebuteesCheckbox");
		var ventesTermineesCheckbox = document
				.getElementById("ventesTermineesCheckbox");
if (achatsRadio.checked) {
			enchereCheckbox.disabled = false;
			mesEncheresCheckbox.disabled = false;
			mesEncheresRemporteesCheckbox.disabled = false;
			ventesEnCoursCheckbox.disabled = true;
			ventesNonDebuteesCheckbox.disabled = true;
			ventesTermineesCheckbox.disabled = true;
		} else if (ventesRadio.checked) {
			enchereCheckbox.disabled = true;
			mesEncheresCheckbox.disabled = true;
			mesEncheresRemporteesCheckbox.disabled = true;
			ventesEnCoursCheckbox.disabled = false;
			ventesNonDebuteesCheckbox.disabled = false;
			ventesTermineesCheckbox.disabled = false;
		}
	}
</script>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Accueil" />
</jsp:include>

<div class="container">
<h1>Liste des enchères</h1>
	<form class="d-flex" role="search"
		action="<%=request.getContextPath()%>/ServletAccueil" method="post">
		<div class="container">
			<input class="form-control me-3" type="search"
				placeholder="Rechercher un article" aria-label="Search"
				name="search" style="width: 400px">




			<%
			Integer id = (Integer) session.getAttribute("id");
			List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute("listArticle");

			List<ArticleVendu> toutArticles = (List<ArticleVendu>) request.getAttribute("listToutArticle");

			String pasArticle = (String) request.getAttribute("PasArticle");
			%>
			<br> <label class="form-label me-3" for="Categorie">Categorie
			</label> <select class="form-control" id="Categorie" name="Categorie"
				style="width: 300px" placeholder="Selectionner une categorie">
				<option selected>Selectionner une categorie</option>

				<%
				List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
				for (Categorie categorieDisponible : listCategorie) {
				%>
				<option value="<%=categorieDisponible.getNoCategorie()%>"><%=categorieDisponible.getLibelle()%></option>
				<%
				}
				%>
			</select>
			<button class="btn btn-outline-success" type="submit">Rechercher</button>
		</div>
		<%
		if (id != null) {
		%>
		<div class="form-check">
			<input type="radio" name="options" id="achatsRadio" value="achats"
				onclick="toggleCheckboxes()" checked>Achats
			<div>
				<input type="checkbox" name="encheres" id="enchereCheckbox"
					value="enchères ouvertes">enchères ouvertes
			</div>
			<div>
				<input type="checkbox" name="mesEncheres" id="mesEncheresCheckbox"
					value="mes enchères">mes enchères
			</div>
			<div>
				<input type="checkbox" name="mesEncheresRemportees"
					id="mesEncheresRemporteesCheckbox" value="mes enchères remportées">mes
				enchères remportées
			</div>
		</div>
		<div class="form-check">
			<input type="radio" name="options" id="ventesRadio" value="ventes"
				onclick="toggleCheckboxes()">Mes Ventes
			<div>
				<input type="checkbox" name="ventesEnCours"
					id="ventesEnCoursCheckbox" value="mes ventes en cours">mes
				ventes en cours
			</div>
			<div>
				<input type="checkbox" name="ventesNonDebutees"
					id="ventesNonDebuteesCheckbox" value="ventes non débutées">ventes
				non débutées
			</div>
			<div>
				<input type="checkbox" name="ventesTerminees"
					id="ventesTermineesCheckbox" value="ventes terminées">ventes
				terminées
			</div>
		</div>

		<%
		}
		%>


	</form>
</div>
<%
if (articles != null) {
%>
<div class="container d-flex align-content-stretch flex-wrap">
	<%
	for (ArticleVendu art : articles) {
		// System.out.println(articles.toString());
	%>



	<div class="card">
		<div class="card-body">
			<h5 class="card-title"><%=art.getNomArticle()%></h5>
			<h6 class="card-subtitle mb-2 text-body-secondary"><%=art.getDateDebutEnchere()%></h6>
			<p class="card-text"><%=art.getDescription()%><%=art.getNoArticle()%></p>
		</div>
	</div>

	<%
	}
	%>
</div>
<%
} else {
if (toutArticles != null) {
%>
<div class="container d-flex flex-wrap">
	<%
	for (ArticleVendu art : toutArticles) {
	%>



	<div class="card">
		<div class="card-body">
			<%
			if (id != null) {
			%>
			<h5 class="card-title text-dark">
				<a class="text-dark" href="ServletDetailArticle?idArticle=<%=art.getNoArticle()%>"><%=art.getNomArticle()%></a>

			</h5>
			<%
	
			} else {
			%>
			<h5 class="card-title text-dark"><%=art.getNomArticle()%></h5>
			<%
			}
			%>
			<h6 class="card-subtitle mb-2 text-body-secondary">
				Prix :
				<%=art.getPrixDeVente()%></h6>
			<p class="card-text">
				Fin de l'enchère :
				<%=art.getDateFinEnchere()%></p>
			<%
			if (id != null) {
			%>
			<p class="card-text">
				Vendeur : <a
					href="ServletProfilVendeur?idVendeur=<%=art.getUtilisateur().getNoUtilisateur()%>"><%=art.getUtilisateur().getNom()%></a>
			</p>
			<%
			} else {
			%>
			<p class="card-text">
				Vendeur :
				<%=art.getUtilisateur().getNom()%></p>
			<%
			}
			%>
		</div>
	</div>

	<%
	}
	%>
</div>

<%
} else {
%>

<p><%=pasArticle%></p>

<%
}
}
%>

<jsp:include page="./fragments/foot.jsp"></jsp:include>