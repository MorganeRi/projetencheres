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

<div class="container-fluid mt-4">
	<div class="row">
		<div class="col-md-5 mx-auto">
<!-- 		table-nowrap -->
			<div class="position-relative card table-nowrap">
				<div class="card-header align-items-center">
					<h5 class="mb-0 text-black">Liste des catégories</h5>
					<p class="mb-0 small text-muted">Ajout/Modification/Suppression</p>
				</div>
				<%
					Categorie categorieAjoute = (Categorie) request.getAttribute("categorieARajouter");
					Categorie categorieModifie = (Categorie) request.getAttribute("categorieAModifier");
					Categorie categorieSupprime  = (Categorie) request.getAttribute("categorieASupprimer");
					if (categorieAjoute != null) {
				%>
					<br />
					<p style="color: green;">La catégorie a été ajoutée avec
					succès</p>
				<%
					} else if (categorieModifie != null){
				%>
					<p style="color: green;">La catégorie a été modifiée avec
					succès</p>
				<%
					} else if (categorieSupprime != null){
				%>
					<p style="color: green;">La catégorie a été supprimée avec
					succès</p>
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
								: </label> 
							<input class="form-control me-2" type="text"
								id="nomCategorie" name="nomCategorie" style="width: 20%" /> 
							<input type="submit" name="action" value="Ajout"
								class="btn btn-dark" style="width: 20%" />
							 <br />
							
						</div>
						
						<div class="mb-1 d-flex align-items-center justify-content-end">
							<label class="form-label me-2" for="nomCategorie">Modifier
								: </label> 
							<select class="form-control me-2" id="Categorie" name="CategorieAModifier" 
								style="width: 20%">
								<option value="Sélectionner categorie"></option>

								<%
								for (Categorie categorieDisponible : listCategorie) {
								%>
								<option value="<%=categorieDisponible.getNoCategorie()%>"><%=categorieDisponible.getLibelle()%></option>
								<%
								}
								%>
							</select>
							<input class="form-control me-2" type="text" id="nomCategorie"
								name="NouveauNomCategorie" style="width: 20%"
								placeholder="Nouveau nom" /> 
							<input type="submit" name="action"
								class="btn btn-dark" value="Modifier" style="width: 20%">
						</div>
						
						<div class="mb-1 d-flex align-items-center justify-content-end">
							<label class="form-label me-2" for="nomCategorie">Supprimer
								: </label> 
							<select class="form-control me-2" id="Categorie" name="CategorieASupprimer" 
								style="width: 20%">
								<option value=""></option>

								<%
								for (Categorie categorieDisponible : listCategorie) {
								%>
								<option value="<%=categorieDisponible.getNoCategorie()%>"><%=categorieDisponible.getLibelle()%></option>
								<%
								}
								%>
							</select>
							<input type="submit" name="action"
								class="btn btn-dark" value="Supprimer" style="width: 20%">
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</div>


<div class="container-fluid  mt-4">
	<div class="row">
		<div class="col-md-5 mx-auto">
			<div class="position-relative card table-nowrap table-card">
				<div class="card-header align-items-center">
					<h5 class="mb-0">Liste des utilisateurs</h5>
					<p class="mb-0 small text-muted">Ajout/Modification/Suppression</p>
				</div>
				<div class="table-responsive">
					<table class="table mb-0 table-hover">
						<thead class="small text-uppercase bg-body text-muted">
							<tr>
								<th>ID de l'utilisateur</th>
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
									if(booleanActif == true){
								%>
									<td>
									<span class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span>
									</td>
								<%
									} else if (booleanActif == false ){
								%>
									<td>
									<span class="badge fs-6 fw-normal bg-tint-success text-danger">Désactivé</span>
									</td>
								<%
									}
								%>
								<td>
									<div class="form-check">
										<input class="form-check-input" type="radio" 
										id="bouton+<%=utilisateurDispo.getPseudo()%>" name="choixUtilisateur" 
										value="choixAModifier">
									</div>
								</td>
							</tr>
							<%
							}
							%>
							
<!-- 							<tr class="align-middle"> -->
<!-- 								<td>#57473829</td> -->
<!-- 								<td>louloudu35</td> -->
<!-- 								<td>loulou@gmail.com</td> -->
<!-- 								<td><span -->
<!-- 									class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input" type="checkbox" id="checkbox1"> -->
<!-- 										<label class="form-check-label" for="checkbox1"></label> -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="align-middle"> -->
<!-- 								<td>#012458780</td> -->
<!-- 								<td>brigittedu45</td> -->
<!-- 								<td>bribri@gmail.com</td> -->
<!-- 								<td><span -->
<!-- 									class="badge fs-6 fw-normal bg-tint-warning text-warning">Désactivé</span> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input" type="checkbox" id="checkbox1"> -->
<!-- 										<label class="form-check-label" for="checkbox1"></label> -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="align-middle"> -->
<!-- 								<td>#76444326</td> -->
<!-- 								<td>clairefontaine</td> -->
<!-- 								<td>claire@papeterie.fr</td> -->

<!-- 								<td><span -->
<!-- 									class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input" type="checkbox" id="checkbox1"> -->
<!-- 										<label class="form-check-label" for="checkbox1"></label> -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="align-middle"> -->
<!-- 								<td>#12498745</td> -->
<!-- 								<td>dede</td> -->
<!-- 								<td>dede@hotmail.fr</td> -->

<!-- 								<td><span -->
<!-- 									class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input" type="checkbox" id="checkbox1"> -->
<!-- 										<label class="form-check-label" for="checkbox1"></label> -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="align-middle"> -->
<!-- 								<td>#87444654</td> -->
<!-- 								<td>lamoula</td> -->
<!-- 								<td>jmlamoula@gmail.com</td> -->
<!-- 								<td><span -->
<!-- 									class="badge fs-6 fw-normal bg-tint-success text-success">Activé </span> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<div class="form-check"> -->
<!-- 										<input class="form-check-input" type="checkbox" id="checkbox1"> -->
<!-- 										<label class="form-check-label" for="checkbox1"></label> -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
						</tbody>
					</table>
				</div>
				<div class="card-footer text-end">
					<a href="#!" class="btn btn-dark">Désactiver le compte</a> <a
						href="#!" class="btn btn-dark">Supprimer le compte</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./fragments/foot.jsp"></jsp:include>