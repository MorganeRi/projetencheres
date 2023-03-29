<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./WEB-INF/fragments/head.jsp">
	<jsp:param name="title" value="AjoutArticle"/>
</jsp:include>
	<h1>Nouvelle vente</h1>
	<div class="container">
		<form action="<%=request.getContextPath()%>/ServletAjoutArticle" method="post">
			<div class="mb-1 d-flex align-items-center">
				<label class="form-label me-3" for="nomArticle">Article : </label>
				<input class="form-control" type="text" id="nomArticle" name="nomArticle" style="width: 300px"/>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center">
				<label  class="form-label me-3" for="Description">Description : </label>
				<textarea  class="form-control" id="Description" name="Description" style="height: 100px ; width: 300px"/></textarea>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center">
				<label  class="form-label me-3" for="Categorie">Categorie : </label>
				<select  class="form-control" id="Categorie" name="Categorie" style="width: 300px">
				
				
				<%
                for(String categorieDisponible:)
                {
            %>
                    <option value="<%=categorieDisponible%>" ><%=categorieDisponible%></option>
            <%
                }
            %>
<!-- 					<option value="">Choose a category</option> -->
<!-- 					<option value="multimedia">Multimedia</option> -->
<!-- 					<option value="automobile">Automobile</option> -->
<!-- 					<option value="maison">maison</option> -->
<!-- 					<option value="electromenage">Electromenager</option> -->
<!-- 					<option value="informatique">Informatique</option> -->
<!-- 					<option value="loisirs">Loisirs</option> -->
<!-- 					<option value="livres">Livres</option> -->
<!-- 					<option value="musique">Musique</option> -->
<!-- 					<option value="jardin">Jardin</option> -->
<!-- 					<option value="vetements">Vetements</option> -->
<!-- 					<option value="sport">Sport</option> -->
				</select>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center">
				<label class="form-label me-3" for="imageArticle">Photo de l'article : </label>
				<input class="form-control" type="file" id="imageArticle" name="imageArticle" accept="image/png, image/jpeg" style="width: 400px"/>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center">
				<label class="form-label me-3" for="MiseAPrix">Mise à prix : </label>
				<input class="form-control" type="number" id="prixDepart" name="prixDepart" min="150" max="1000" style="width: 300px" placeholder="150"/>
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center">
				<label class="form-label me-3" for="DebutEnchere">Début de l'enchère : </label>
				<input class="form-control" type="date" id="DebutEnchere" name="DebutEnchere" style="width: 300px" />
			</div>
			<br/>
			<div class="mb-1 d-flex align-items-center">
				<label class="form-label me-3" for="FinEnchere">Fin de l'enchère : </label>
				<input class="form-control" type="date" id="FinEnchere" name="FinEnchere" style="width: 300px"/>
			</div>
			<br/>
			<fieldset>
				<legend>Retrait</legend>
				<div class="mb-1 d-flex align-items-center">
					<label class="form-label me-3" for="nomRue">Rue : </label>
					<input class="form-control" type="text" id="nomRue" name="nomRue" style="width: 300px"/>
				</div>
				<br/>
				<div class="mb-1 d-flex align-items-center">
					<label class="form-label me-3" for="codePostal">Code Postal : </label>
					<input class="form-control" type="text" id="codePostal" name="codePostal" style="width: 300px"/>
				</div>
				<br/>
				<div class="mb-1 d-flex align-items-center">
					<label class="form-label me-3" for="nomVille">Ville : </label>
					<input class="form-control" type="text" id="nomVille" name="nomVille" style="width: 300px"/>
				</div>
				<br/>
			</fieldset>
			<div class="mb-1 d-flex align-items-center">
				<input  type="submit" value="Enregistrer" class="btn btn-dark me-3"/>
				<input  type="reset" value="Annuler" class="btn btn-dark me-3">
				<input  type="reset" value="Annuler la vente" class="btn btn-dark me-3">
			</div>
		</form>
	</div>
<jsp:include page="./WEB-INF/fragments/foot.jsp"></jsp:include>