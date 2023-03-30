<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
     
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="AjoutArticle"/>
</jsp:include>
	<h1 class="font-weight-bold text-center">Nouvelle vente</h1>
<!-- 	style="border: 1px solid silver;width:600px;" -->
	<div class="container" style="border: 1px solid silver;width:500px;">
		<form action="<%=request.getContextPath()%>/ServletAjoutArticle" method="post">
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3 mt-2" for="nomArticle">Article : </label>
				<input class="form-control mt-2" type="text" id="nomArticle" name="nomArticle" style="width: 300px"/>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label  class="form-label me-3" for="Description">Description : </label>
				<textarea  class="form-control" id="Description" name="Description" style="height: 100px ; width: 300px"/></textarea>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label  class="form-label me-3" for="Categorie">Categorie : </label>
				<select  class="form-control" id="Categorie" name="Categorie" style="width: 300px">
				<option value=""> </option>
				
				<%
 				List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
                  for(Categorie categorieDisponible : listCategorie)
                 {
            %>
                    <option value="<%=categorieDisponible.getNoCategorie() %>" ><%=categorieDisponible.getLibelle()%></option>
            <%
                }
            %>
				</select>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="imageArticle">Photo de l'article : </label>
				<input class="form-control" type="file" id="imageArticle" name="imageArticle" accept="image/png, image/jpeg" style="width: 400px"/>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="MiseAPrix">Mise à prix : </label>
				<input class="form-control" type="number" id="prixDepart" name="prixDepart" min="150" max="1000" style="width: 300px" placeholder="150" value="150"/>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="DebutEnchere">Début de l'enchère : </label>
				<input class="form-control" type="date" id="DebutEnchere" name="DebutEnchere" style="width: 300px" />
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<label class="form-label me-3" for="FinEnchere">Fin de l'enchère : </label>
				<input class="form-control" type="date" id="FinEnchere" name="FinEnchere" style="width: 300px"/>
			</div>
			<br/>
			<fieldset>
				<legend>Retrait</legend>
				<%
					Utilisateur utilisateur = (Utilisateur) request.getAttribute("Utilisateur");
				%>
				<div class="mb-1 d-flex align-items-center justify-content-between">
					<label class="form-label me-3" for="nomRue">Rue : </label>
					<input class="form-control" type="text" id="nomRue" name="nomRue" style="width: 300px" value="<%=utilisateur.getRue()%>"/>
				</div>
				<br/>
				<div class="mb-1 d-flex align-items-center justify-content-between">
					<label class="form-label me-3" for="codePostal">Code Postal : </label>
					<input class="form-control" type="text" id="codePostal" name="codePostal" style="width: 300px" value="<%=utilisateur.getCodePostal()%>"/>
				</div>
				<br/>
				<div class="mb-1 d-flex align-items-center justify-content-between">
					<label class="form-label me-3" for="nomVille">Ville : </label>
					<input class="form-control" type="text" id="nomVille" name="nomVille" style="width: 300px" value="<%=utilisateur.getVille()%>"/>
				</div>
				<br/>
			</fieldset>
			<div class="mb-1 d-flex align-items-center justify-content-between">
				<input  type="submit" value="Enregistrer" class="btn btn-dark me-3"/>
				<input  type="reset" value="Annuler" class="btn btn-dark me-3">
				<input  type="reset" value="Annuler la vente" class="btn btn-dark me-3">
			</div>
		</form>
	</div>
<jsp:include page="./fragments/foot.jsp"></jsp:include>