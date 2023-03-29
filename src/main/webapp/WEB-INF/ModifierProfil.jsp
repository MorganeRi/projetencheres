<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="ModificationUtilisateur" />
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
	
	<%
	Utilisateur utilisateur = (Utilisateur) request.getAttribute("Utilisateur");
	%>
	
	<form method="post"
		action="<%=request.getContextPath()%>/ServletModifierUtilisateur">
		<div class="input-group mb-3">
			<span class="input-group-text">Pseudo</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="Pseudo" value=<%=utilisateur.getPseudo()%> required> <label
					for="floatingInputGroup1">Pseudo</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Prenom</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="Prenom" value=<%=utilisateur.getPrenom()%> required> <label
					for="floatingInputGroup1">Prenom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Nom</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="Nom" value=<%=utilisateur.getNom()%> required> <label
					for="floatingInputGroup1">Nom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Email</span>
			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInputGroup1"
					name="Email" value=<%=utilisateur.getEmail()%> required> <label
					for="floatingInputGroup1">Email</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Telephone</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="Telephone" value=<%=utilisateur.getTelephone()%>> <label for="floatingInputGroup1">Telephone</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Rue</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="Rue" value=<%=utilisateur.getRue()%> required> <label
					for="floatingInputGroup1">Rue</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Code postal</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="CodePostal" value=<%=utilisateur.getCodePostal()%> required> <label
					for="floatingInputGroup1">Code postal</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Ville</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					name="Ville" value=<%=utilisateur.getVille()%> required> <label
					for="floatingInputGroup1">Ville</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Mot de passe actuel</span>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingInputGroup1"
					placeholder="MotDePasseActuel" name="MotDePasseActuel" required> <label
					for="floatingInputGroup1">Mot de passe</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Nouveau mot de passe</span>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingInputGroup1"
					placeholder="NouveauMotDePasse" name="MotDePasseNouveau" required> <label
					for="floatingInputGroup1">Mot de passe</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Confirmer mot de passe</span>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingInputGroup1"
					placeholder="ConfirmationMotDePasse" name="ConfirmationMotDePasse" required> <label
					for="floatingInputGroup1">Confirmation Mot de passe</label>
			</div>
		</div>
		<button type="submit" class="btn btn-dark">Enregistrer</button>
		<button type="reset" class="btn btn-dark">Supprimer mon compte</button>
	</form>
</div>
</body>
</html>