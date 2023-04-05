<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="ModificationUtilisateur" />
</jsp:include>

<div class="container-fluid" style="width: 40%;">
	<h1 class="font-weight-bold text-center">Mon profil</h1>
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
	<%
	Utilisateur utilisateur2 = (Utilisateur) request.getAttribute("Utilisateur2");
	Utilisateur utilisateur3 = (Utilisateur) request.getAttribute("Utilisateur3");
	if ((utilisateur2 != null)||(utilisateur3 != null)) {
	%>
	<p style="color: green;">Les modifications ont bien été prises en compte</p>
	<%
	}
	%>
	
	<form method="post"
		action="<%=request.getContextPath()%>/ServletModifierUtilisateur">
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Pseudo</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="Pseudo" value=<%=utilisateur.getPseudo()%> required> <label
					for="floatingInputGroup1">Pseudo</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Prenom</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="Prenom" value=<%=utilisateur.getPrenom()%> required> <label
					for="floatingInputGroup1">Prenom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Nom</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="Nom" value=<%=utilisateur.getNom()%> required> <label
					for="floatingInputGroup1">Nom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Email</span>
			<div class="form-floating">
				<input type="email" class="form-control border-warning" id="floatingInputGroup1"
					name="Email" value=<%=utilisateur.getEmail()%> required> <label
					for="floatingInputGroup1">Email</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Telephone</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="Telephone" value=<%=utilisateur.getTelephone()%>> <label for="floatingInputGroup1">Telephone</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Rue</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="Rue" value="<%=utilisateur.getRue()%>" required> <label
					for="floatingInputGroup1">Rue</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Code postal</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="CodePostal" value=<%=utilisateur.getCodePostal()%> required> <label
					for="floatingInputGroup1">Code postal</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Ville</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning" id="floatingInputGroup1"
					name="Ville" value=<%=utilisateur.getVille()%> required> <label
					for="floatingInputGroup1">Ville</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Mot de passe actuel</span>
			<div class="form-floating">
				<input type="password" class="form-control border-warning" id="floatingInputGroup1"
					placeholder="MotDePasseActuel" name="MotDePasseActuel" required> <label
					for="floatingInputGroup1">Mot de passe</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Nouveau mot de passe</span>
			<div class="form-floating">
				<input type="password" class="form-control border-warning" id="floatingInputGroup1"
					placeholder="NouveauMotDePasse" name="MotDePasseNouveau"> <label
					for="floatingInputGroup1">Mot de passe</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Confirmer mot de passe</span>
			<div class="form-floating">
				<input type="password" class="form-control border-warning" id="floatingInputGroup1"
					placeholder="ConfirmationMotDePasse" name="ConfirmationMotDePasse"> <label
					for="floatingInputGroup1">Confirmation Mot de passe</label>
			</div>
		</div>
		<div class="mb-3 d-flex align-items-center justify-content-between">
			<button type="submit" class="btn btn-warning btn-lg ">Enregistrer</button>
			<a href="ServletSupprimerCompte" class="btn btn-warning btn-lg"
						role="button">Supprimer mon compte</a>
		</div>
	</form>
</div>
<jsp:include page="./fragments/foot.jsp"></jsp:include>