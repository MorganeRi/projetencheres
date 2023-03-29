<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<title>CreationUtilisateur</title>
</head>
<body>
	<h1>Mon profil</h1>
	<form method="post" action="/TPSuiviDesRepas/ServletAjoutRepas">
		<div class="input-group mb-3">
			<span class="input-group-text">Pseudo</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Pseudo"> <label for="floatingInputGroup1">Pseudo</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Prenom</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Prenom"> <label for="floatingInputGroup1">Prenom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Nom</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Nom"> <label for="floatingInputGroup1">Nom</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Email</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Email"> <label for="floatingInputGroup1">Email</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Telephone</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Telephone"> <label for="floatingInputGroup1">Telephone</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Rue</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Rue"> <label for="floatingInputGroup1">Rue</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Code postal</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="CodePostal"> <label for="floatingInputGroup1">Code
					postal</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Ville</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="Ville"> <label for="floatingInputGroup1">Ville</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Mot de passe</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="MotDePasse"> <label for="floatingInputGroup1">Mot
					de passe</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">Confirmation Mot de passe</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInputGroup1"
					placeholder="ConfirmationMotDePasse"> <label
					for="floatingInputGroup1">Confirmation Mot de passe</label>
			</div>
		</div>
		<input type="submit" value="Créer" /> <input type="reset"
			value="Annuler">
	</form>
</body>
</html>