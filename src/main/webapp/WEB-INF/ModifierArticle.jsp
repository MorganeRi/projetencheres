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
<div class="container-fluid" style="width: 30%;">

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
		<br>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Article</span>
			<div class="form-floating">
				<input class="form-control border-warning" type="text"
					id="nomArticle" name="nomArticle"
					value='<%=articleVendu.getNomArticle()%>' required /> <label
					class="floatingInputGroup1" for="nomArticle">Article</label>
			</div>
		</div>
		<br />
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Description</span>
			<div class="form-floating">
				<textarea class="form-control border-warning" id="Description"
					name="Description" required /><%=articleVendu.getDescription()%></textarea>
				<label class="floatingInputGroup1" for="Description">Description
				</label>
			</div>
		</div>
		<br />
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Categorie</span>
			<div class="form-floating">
				<select class="form-control border-warning" id="Categorie"
					name="Categorie" required>
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
				</select> <label class="floatingInputGroup1" for="Categorie">Categorie
				</label>
			</div>
		</div>
		<br />
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Photo de
				l'article</span>
			<div class="form-floating">
				<select class="form-control border-warning" id="photo"
					name="imageArticle">
					<option value=""></option>
					<option value="<%=request.getContextPath()%>/images/multimedia.jpg">Multimedia</option>
					<option value="<%=request.getContextPath()%>/images/automobile.png">Automobile</option>
					<option value="<%=request.getContextPath()%>/images/maison.jpg">Maison</option>
					<option
						value="<%=request.getContextPath()%>/images/electromenager.jpg">Electromenager</option>
					<option
						value="<%=request.getContextPath()%>/images/informatique.jpg">Informatique</option>
					<option value="<%=request.getContextPath()%>/images/loisirs.jpg">Loisirs</option>

					<option value="<%=request.getContextPath()%>/images/livre.jpg">Livres</option>
					<option value="<%=request.getContextPath()%>/images/musique.avif">Musique</option>
					<option value="<%=request.getContextPath()%>/images/jardin.jpg">Jardin</option>
					<option value="<%=request.getContextPath()%>/images/vetements.jpg">Vetements</option>
					<option value="<%=request.getContextPath()%>/images/sport.avif">Sports</option>

				</select> <label class="floatingInputGroup1" for="Categorie">Photo de
					l'article</label>
			</div>
		</div>
		<br />
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Mise à
				prix</span>
			<div class="form-floating">
				<input class="form-control border-warning" type="number"
					id="prixDepart" name="prixDepart" min="150" max="1000"
					placeholder="150" value=<%=articleVendu.getPrixInitial()%> required />
				<label class="floatingInputGroup1" for="MiseAPrix">Mise à
					prix</label>
			</div>
		</div>
		<br />
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Début de
				l'enchère</span>
			<div class="form-floating">
				<input class="form-control border-warning" type="datetime-local"
					id="DebutEnchere" name="DebutEnchere"
					value=<%=articleVendu.getDateDebutEnchere()%> required /> <label
					class="floatingInputGroup1" for="DebutEnchere">Début de
					l'enchère</label>
			</div>
		</div>
		<br />
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Fin de
				l'enchère</span>
			<div class="form-floating">
				<input class="form-control border-warning" type="datetime-local"
					id="FinEnchere" name="FinEnchere"
					value=<%=articleVendu.getDateFinEnchere()%> required /> <label
					class="floatingInputGroup1" for="FinEnchere">Fin de
					l'enchère : </label>
			</div>
		</div>
		<br />
		<fieldset>
			<legend class="text-center">Retrait</legend>
			<%
			Retrait retrait = (Retrait) request.getAttribute("retrait");
			%>
			<div class="input-group mb-3">
				<span class="input-group-text text-warning bg-dark">Rue</span>
				<div class="form-floating">
					<input class="form-control border-warning" type="text" id="nomRue"
						name="nomRue" value="<%=retrait.getRue()%>" /> <label
						class="floatingInputGroup1" for="nomRue">Rue : </label>
				</div>
			</div>
			<br />
			<div class="input-group mb-3">
				<span class="input-group-text text-warning bg-dark">Code
					Postal</span>
				<div class="form-floating">
					<input class="form-control border-warning" type="text"
						id="codePostal" name="codePostal"
						value="<%=retrait.getCodePostal()%>" /> <label
						class="floatingInputGroup1" for="codePostal">Code Postal :
					</label>
				</div>
			</div>
			<br />
			<div class="input-group mb-3">
				<span class="input-group-text text-warning bg-dark">Code
					Postal</span>
				<div class="form-floating">
					<input class="form-control border-warning" type="text"
						id="nomVille" name="nomVille"
						value="<%=retrait.getVille()%>" /> <label
						class="floatingInputGroup1" for="nomVille">Ville : </label>
				</div>
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
			<input type="submit" value="Enregistrer"
				class="btn btn-warning btn-lg me-3 shadow" /> <input type="reset"
				value="Annuler" class="btn btn-warning btn-lg me-3 shadow">
			<a href="ServletSupprimerArticle"
				class="btn btn-warning btn-lg me-3 shadow" role="button">Supprimer
				l'article</a>
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