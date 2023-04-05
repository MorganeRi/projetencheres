<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.bo.Retrait"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>

<%@page import="java.time.LocalDateTime"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="ModifierArticle" />
</jsp:include>



<h1 class="font-weight-bold text-center">Modifier l'article</h1>
<!-- 	style="border: 1px solid silver;width:600px;" -->
<div class="container-fluid"
	style="border: 1px solid silver; width: 500px;">

	<%
	ArticleVendu articleVendu = (ArticleVendu) request.getAttribute("articleAManipuler");
	%>

	<form action="<%=request.getContextPath()%>/ServletModifierArticle"
		method="post">
		<%
		List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
		if (listeCodesErreur != null) {
		%>
		<p style="color: red;">Erreur, la modification n'est pas possible
			:</p>
		<%
		for (int codeErreur : listeCodesErreur) {
		%>
		<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%
		}
		}
		%>

		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3 mt-2" for="nomArticle">Article
				: </label> <input class="form-control mt-2" type="text" id="nomArticle"
				name="nomArticle" style="width: 300px"
				value='<%=articleVendu.getNomArticle()%>' required />
		</div>
		<br />
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3" for="Description">Description
				: </label>
			<textarea class="form-control" id="Description" name="Description"
				style="height: 100px; width: 300px" required /><%=articleVendu.getDescription()%></textarea>
		</div>
		<br />
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3" for="Categorie">Categorie : </label> <select
				class="form-control" id="Categorie" name="Categorie"
				style="width: 300px" required>
				<option value="<%=articleVendu.getCategorie().getNoCategorie()%>">
					<%=articleVendu.getCategorie().getLibelle()%></option>

				<%
				List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
				for (Categorie categorieDisponible : listCategorie) {
				%>
				<option value="<%=categorieDisponible.getNoCategorie()%>"><%=categorieDisponible.getLibelle()%></option>
				<%
				}
				%>
			</select>
		</div>
		<br />
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3" for="imageArticle">Photo de
				l'article : </label> <input class="form-control" type="file"
				id="imageArticle" name="imageArticle" accept="image/png, image/jpeg"
				style="width: 400px" />
		</div>
		<br />
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3" for="MiseAPrix">Mise à prix :
			</label> <input class="form-control" type="number" id="prixDepart"
				name="prixDepart" min="150" max="1000" style="width: 300px"
				placeholder="150" value=<%=articleVendu.getPrixInitial()%> required />
		</div>
		<br />
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3" for="DebutEnchere">Début de
				l'enchère : </label> <input class="form-control" type="datetime-local"
				id="DebutEnchere" name="DebutEnchere" style="width: 300px"
				value=<%=articleVendu.getDateDebutEnchere()%> required />
		</div>
		<br />
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<label class="form-label me-3" for="FinEnchere">Fin de
				l'enchère : </label> <input class="form-control" type="datetime-local" id="FinEnchere"
				name="FinEnchere" style="width: 300px"
				value=<%=articleVendu.getDateFinEnchere()%> required />
		</div>
		<br />
		<fieldset>
			<legend>Retrait</legend>
			<%
			Retrait retrait = (Retrait) request.getAttribute("retrait");
			%>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="nomRue">Rue : </label> <input
					class="form-control" type="text" id="nomRue" name="nomRue"
					style="width: 300px" value="<%=retrait.getRue()%>" />
			</div>
			<br />
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="codePostal">Code Postal
					: </label> <input class="form-control" type="text" id="codePostal"
					name="codePostal" style="width: 300px"
					value="<%=retrait.getCodePostal()%>" />
			</div>
			<br />
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="nomVille">Ville : </label> <input
					class="form-control" type="text" id="nomVille" name="nomVille"
					style="width: 300px" value="<%=retrait.getVille()%>" />
			</div>
			<br />
		</fieldset>
		<%
		ArticleVendu articleModifie = (ArticleVendu) request.getAttribute("articleModifie");
		if (articleModifie != null) {
		%>
		<p style="color: green;">L'article a été modifié avec succès</p>

		<%
		}
		%>
		<%
		if (LocalDateTime.now().isBefore(articleVendu.getDateDebutEnchere())) {
		%>
		<div class="mb-1 d-flex align-items-center justify-content-between">
			<input type="submit" value="Enregistrer" class="btn btn-warning btn-lg me-3 shadow" />
			<input type="reset" value="Annuler" class="btn btn-warning btn-lg me-3 shadow">
			<a href="ServletSupprimerArticle" class="btn btn-warning btn-lg me-3 shadow"
				role="button">Supprimer l'article</a>
		</div>
		<%
		} else if (LocalDateTime.now().isAfter(articleVendu.getDateDebutEnchere())) {
		%>

		<p>Vous ne pouvez plus modifier ou supprimer l'Article</p>
		<%
		}
		%>
	</form>

</div>
<jsp:include page="./fragments/foot.jsp"></jsp:include>