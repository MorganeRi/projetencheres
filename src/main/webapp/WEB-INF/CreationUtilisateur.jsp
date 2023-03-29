<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="CreationUtilisateur" />
</jsp:include>

<div class="container">
	<h1>Mon profil</h1>
	<br>
	<%
	List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
	if (listeCodesErreur != null) {
	%>
	<p style="color: red;">Erreur, la connexion n'est pas possible :</p>
	<%
	for (int codeErreur : listeCodesErreur) {
	%>
	<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
	<%
	}
	}
	%>
	<form method="post"
		action="<%=request.getContextPath()%>/ServletAjoutUtilisateur">
		<div class="input-group mb-3">
			<span class="input-group-text">Pseudo</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Pseudo" name="Pseudo" required> <label
					for="floatingInputGroup1">Pseudo</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Prenom</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Prenom" name="Prenom" required> <label
					for="floatingInputGroup1">Prenom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Nom</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Nom" name="Nom" required> <label
					for="floatingInputGroup1">Nom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Email</span>
			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInputGroup1"
					placeholder="Email" name="Email" required> <label
					for="floatingInputGroup1">Email</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Telephone</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Telephone" name="Telephone"> <label for="floatingInputGroup1">Telephone</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Rue</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Rue" name="Rue" required> <label
					for="floatingInputGroup1">Rue</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Code postal</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="CodePostal" name="CodePostal" required> <label
					for="floatingInputGroup1">Code postal</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Ville</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Ville" name="Ville" required> <label
					for="floatingInputGroup1">Ville</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Mot de passe</span>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingInputGroup1"
					placeholder="MotDePasse" name="MotDePasse" required> <label
					for="floatingInputGroup1">Mot de passe</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Confirmation Mot de passe</span>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingInputGroup1"
					placeholder="ConfirmationMotDePasse" name="ConfirmationMotDePasse" required> <label
					for="floatingInputGroup1">Confirmation Mot de passe</label>
			</div>
		</div>
		<button type="submit" class="btn btn-dark">Créer</button>
		<button type="reset" class="btn btn-dark">Annuler</button>
	</form>
</div>
</body>
</html>