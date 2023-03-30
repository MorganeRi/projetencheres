<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
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
	<form class="d-flex justify-content-start" role="search"
		action="<%=request.getContextPath()%>/ServletAccueil" method="post">
		<input class="form-control me-3" type="search" placeholder="Rechercher un article"
			aria-label="Search" name="search" style="width: 400px">
	
	


<%
List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute("listArticle");

List<ArticleVendu> toutArticles = (List<ArticleVendu>) request.getAttribute("listToutArticle");

String pasArticle = (String) request.getAttribute("PasArticle");%>
<br>

<label  class="form-label me-3" for="Categorie">Categorie </label>
<select  class="form-control" id="Categorie" name="Categorie" style="width: 300px" placeholder="Selectionner une categorie">
  <option selected>Selectionner une categorie</option>

<%
	List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
  for(Categorie categorieDisponible : listCategorie)
 {
%>
    <option value="<%=categorieDisponible.getNoCategorie()%>" ><%=categorieDisponible.getLibelle()%></option>
<%
}
%>
</select>
	<button class="btn btn-outline-success" type="submit">Rechercher</button>

</form>
</div>
<%

if (articles != null) { %>
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
} else { if (toutArticles != null) {%>
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
} else {
	%> 
	
	<p><%=pasArticle %></p>
	
<%	
}}
%>

<jsp:include page="./fragments/foot.jsp"></jsp:include>