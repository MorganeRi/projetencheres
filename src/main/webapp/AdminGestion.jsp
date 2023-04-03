<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>

<jsp:include page="./WEB-INF/fragments/head.jsp">
	<jsp:param name="title" value="Gestion du site par Super Admin " />
</jsp:include>
<h1 class="font-weight-bold text-center">Gestion du site par Super Admin</h1>

<div class="container-fluid   mt-4">
	<div class="row">
		<div class="col-md-11 mx-auto">
			<div class="position-relative card table-nowrap table-card">
                <div class="card-header align-items-center">
                    <h5 class="mb-0">Liste des catégories</h5>
                    <p class="mb-0 small text-muted">Ajout/Modification/Suppression</p>
                </div>
                <div class="table-responsive">
                    <table class="table mb-0">
                        <thead class="small text-uppercase bg-body text-muted">
                            <tr>
                                <th>ID de la catégorie</th>
                                <th>Nom de la catégorie</th>
                               
                                <th>Check</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="align-middle">
                                <td>
                                    #57473829
                                </td>
                                <td>informatique</td>
                                
								<td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
							</tr>
                            <tr class="align-middle">
                                <td>
                                    #012458780
                                </td>
                                <td>loisirs</td>
                                
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                            <tr class="align-middle">
                                <td>
                                    #76444326
                                </td>
                                <td>maison</td>
                                
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                            <tr class="align-middle">
                                <td>
                                    #12498745
                                </td>
                                <td>electromenager</td>
                                
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                            <tr class="align-middle">
                                <td>
                                    #87444654
                                </td>
                                <td>musique </td>
                                
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer text-end">
					<div class="mb-1 d-flex align-items-center justify-content-end">
						<label class="form-label me-3" for="nomCategorie">Ajouter : </label> 
						<input class="form-control" type="text" id="nomVille" name="nomVille" 
						style="width: 30%"/>
						<input type="submit" value="Enregistrer" class="btn btn-dark me-3" />
					</div>
					<a href="#!" class="btn btn-dark">Modifier la catégorie</a>
                    <a href="#!" class="btn btn-dark">Supprimer la catégorie</a>
                </div>
            </div>
		</div>
	</div>
</div>


<div class="container-fluid  mt-4">
	<div class="row">
		<div class="col-md-11 mx-auto">
			<div class="position-relative card table-nowrap table-card">
                <div class="card-header align-items-center">
                    <h5 class="mb-0">Liste des utilisateurs</h5>
                    <p class="mb-0 small text-muted">Ajout/Modification/Suppression</p>
                </div>
                <div class="table-responsive">
                    <table class="table mb-0">
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
                            <tr class="align-middle">
                                <td>
                                    #57473829
                                </td>
                                <td>louloudu35</td>
                                <td>loulou@gmail.com</td>
                                <td>
                                    <span class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span>
                                </td>
								<td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
							</tr>
                            <tr class="align-middle">
                                <td>
                                    #012458780
                                </td>
                                <td>brigittedu45</td>
                                <td>bribri@gmail.com</td>
                                <td>
                                    <span class="badge fs-6 fw-normal bg-tint-warning text-warning">Désactivé</span>
                                </td>
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                            <tr class="align-middle">
                                <td>
                                    #76444326
                                </td>
                                <td>clairefontaine</td>
                                <td>claire@papeterie.fr</td>
                                
                                <td>
                                    <span class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span>
                                </td>
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                            <tr class="align-middle">
                                <td>
                                    #12498745
                                </td>
                                <td>dede</td>
                                <td>dede@hotmail.fr</td>
                                
                                <td>
                                    <span class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span>
                                </td>
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                            <tr class="align-middle">
                                <td>
                                    #87444654
                                </td>
                                <td>lamoula</td>
                                <td>jmlamoula@gmail.com</td>
                                <td>
                                    <span class="badge fs-6 fw-normal bg-tint-success text-success">Activé</span>
                                </td>
                                <td>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkbox1">
										<label class="form-check-label" for="checkbox1"></label>
									</div>
								</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer text-end">
                    <a href="#!" class="btn btn-dark">Désactiver le compte</a>
                    <a href="#!" class="btn btn-dark">Supprimer le compte</a>
                </div>
            </div>
		</div>
	</div>
</div>
<jsp:include page="./WEB-INF/fragments/foot.jsp"></jsp:include>