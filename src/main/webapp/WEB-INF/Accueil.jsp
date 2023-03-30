<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Accueil" />
</jsp:include>
<h1>Liste des enchères</h1>
<div class="container">
	<form class="d-flex" role="search"
		action="<%=request.getContextPath()%>/ServletAccueil" method="post">
		<input class="form-control me-3" type="search" placeholder="Search"
			aria-label="Search" name="search">
		<button class="btn btn-outline-success" type="submit">Search</button>
	</form>
</div>

<%
List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute("listArticle");

List<ArticleVendu> toutArticles = (List<ArticleVendu>) request.getAttribute("listToutArticle");%>
<div class ="container">
<select  class="form-control" id="Categorie" name="Categorie" style="width: 300px">
<option value=""> </option>

<%
	List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
  for(Categorie categorieDisponible : listCategorie)
 {
%>
    <option value="<%=categorieDisponible%>" ><%=categorieDisponible.getLibelle()%></option>
<%
}
%>
</select>
</div>
<%

if (articles != null) { %>
<div class="container d-flex flex-wrap grid gap-0 row-gap-3">
	<%
	for (ArticleVendu art : articles) {
		// System.out.println(articles.toString());
	%>



	<div class="card p-2 g-col-6">
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
} else { %>
<div class="container d-flex flex-wrap">
	<%
	for (ArticleVendu art : toutArticles) {

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
}
%>

<jsp:include page="./fragments/foot.jsp"></jsp:include>