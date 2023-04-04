<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Gestion du site par Super Admin " />
</jsp:include>
<h1 class="font-weight-bold text-center">Gestion du site par Super
	Admin</h1>

<div class="container-fluid mt-5">
	<div class="row">
		<div class="col-md-6 mx-auto">
			<!-- 		table-nowrap -->
			<div class="position-relative card table-nowrap">
				<div class="card-header align-items-center">
					<h5 class="mb-0 text-black">Liste des catégories</h5>
					<p class="mb-0 small text-muted">Ajout/Modification/Suppression</p>
				</div>
				<%
				Categorie categorieAjoute = (Categorie) request.getAttribute("categorieARajouter");
				Categorie categorieAlreadyExists = (Categorie) request.getAttribute("categorieAlreadyExists");
				Categorie categorieModifie = (Categorie) request.getAttribute("categorieAModifier");
				Categorie categorieSupprime = (Categorie) request.getAttribute("categorieASupprimer");
				if (categorieAjoute != null) {
				%>
				<br />
				<p style="color: green;">La catégorie a été ajoutée avec succès</p>
				<%
				} else if (categorieModifie != null) {
				%>
				<p style="color: green;">La catégorie a été modifiée avec succès</p>
				<%
				} else if (categorieSupprime != null) {
				%>
				<p style="color: green;">La catégorie a été supprimée avec
					succès</p>
				<%
				} else if (categorieAlreadyExists != null) {
				%>
				<p style="color: red;">La catégorie existe déjà !</p>
				<%
				}
				%>

				<div class="table-responsive">
					<table class="table mb-0 table-hover">
						<thead class="small text-uppercase bg-body text-muted">
							<tr>
								<th>ID de la catégorie</th>
								<th>Nom de la catégorie</th>

							</tr>
						</thead>
						<tbody>
							<%
							List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
							for (Categorie categorieDispo : listCategorie) {
							%>
							<tr class="align-middle">
								<td><%=categorieDispo.getNoCategorie()%></td>
								<td><%=categorieDispo.getLibelle()%></td>

							</tr>
							<%
							}
							%>

						</tbody>
					</table>
				</div>
				<div class="card-footer text-end">

					<form action="<%=request.getContextPath()%>/ServletGestionAdmin"
						method="post">
						<div class="mb-1 d-flex align-items-center justify-content-end">
							<label class="form-label me-2" for="nomCategorie">Ajouter
								: </label> <input class="form-control me-2" type="text"
								id="nomCategorie" name="nomCategorie" style="width: 30%" /> <input
								type="submit" name="action" value="Ajout" class="btn btn-dark"
								style="width: 20%" /> <br />

						</div>

						<div class="mb-1 d-flex align-items-center justify-content-end">
							<label class="form-label me-1" for="nomCategorie">Modifier
								: </label> <select class="form-control me-2" id="Categorie"
								name="CategorieAModifier" style="width: 30%">
								<option value="Sélectionner categorie"></option>

								<%
								for (Categorie categorieDisponible : listCategorie) {
								%>
								<option value="<%=categorieDisponible.getNoCategorie()%>"><%=categorieDisponible.getLibelle()%></option>
								<%
								}
								%>
							</select> <input class="form-control me-2" type="text" id="nomCategorie"
								name="NouveauNomCategorie" style="width: 30%"
								placeholder="Nouveau nom" /> <input type="submit" name="action"
								class="btn btn-dark" value="Modifier" style="width: 20%">
						</div>

						<div class="mb-1 d-flex align-items-center justify-content-end">
							<label class="form-label me-2" for="nomCategorie">Supprimer
								: </label> <select class="form-control me-2" id="Categorie"
								name="CategorieASupprimer" style="width: 30%">
								<option value=""></option>

								<%
								for (Categorie categorieDisponible : listCategorie) {
								%>
								<option value="<%=categorieDisponible.getNoCategorie()%>"><%=categorieDisponible.getLibelle()%></option>
								<%
								}
								%>
							</select> <input type="submit" name="action" class="btn btn-dark"
								value="Supprimer" style="width: 20%">
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</div>


<div class="container-fluid  mt-4">
	<div class="row">
		<div class="col-md-7 mx-auto">
			<div class="position-relative card table-nowrap table-card">
				<div class="card-header align-items-center">
					<h5 class="mb-0">Liste des utilisateurs</h5>
					<p class="mb-0 small text-muted">Comptes
						actifs/Admins/Suppression Utilisateur</p>
				</div>
				<%
				Utilisateur utilisateurActif = (Utilisateur) request.getAttribute("utilisateurOnOff");
				Utilisateur utilisateurSupprime = (Utilisateur) request.getAttribute("utilisateurASupprimer");

				if (utilisateurActif != null) {
				%>
				<br />
				<p style="color: green;">Le compte a changé de statut</p>
				<%
				} else if (utilisateurSupprime != null) {
				%>
				<p style="color: red;">L'utilisateur a été supprimé avec succès</p>

				<%
				}
				%>
				<form action="<%=request.getContextPath()%>/ServletGestionAdmin"
					method="post">
					<div class="table-responsive">

						<table class="table mb-0 table-hover">
							<thead class="small text-uppercase bg-body text-muted">
								<tr>
									<th>ID User</th>
									<th>Pseudo</th>
									<th>Mail</th>
									<th>Status</th>
									<th>Check</th>
								</tr>
							</thead>
							<tbody>

								<%
								List<Utilisateur> listUtilisateur = (List<Utilisateur>) request.getAttribute("listUtilisateur");
								for (Utilisateur utilisateurDispo : listUtilisateur) {
								%>
								<tr class="align-middle">
									<td><%=utilisateurDispo.getNoUtilisateur()%></td>
									<td><%=utilisateurDispo.getPseudo()%></td>
									<td><%=utilisateurDispo.getEmail()%></td>
									<%
									Boolean booleanActif = utilisateurDispo.getActif();
									if (booleanActif == true) {
									%>
									<td><span
										class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span>
									</td>
									<%
									} else if (booleanActif == false) {
									%>
									<td><span
										class="badge fs-6 fw-normal bg-tint-success text-danger">Désactivé</span>
									</td>
									<%
									}
									Boolean booleanAdmin = utilisateurDispo.getAdministrateur();
									if (!booleanAdmin) {
									%>
									<td>
										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="choixUtilisateur"
												value="<%=utilisateurDispo.getNoUtilisateur()%>">
										</div>
									</td>
									<%
									}
									%>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
					<div class="card-footer text-end">

						<input type="submit" name="actionUtilisateur" class="btn btn-dark"
							value="ActiverDesactiver" style="width: 30%"> <input
							type="submit" name="actionUtilisateur" class="btn btn-dark"
							value="Supprimer" style="width: 20%">

					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./fragments/foot.jsp"></jsp:include>