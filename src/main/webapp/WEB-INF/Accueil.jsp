<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="fr.eni.projetenchere.bo.ArticleVendu"%>
<%@page import="fr.eni.projetenchere.bo.Categorie"%>
<%@page import="fr.eni.projetenchere.bo.Enchere"%>
<%@page import="fr.eni.projetenchere.bll.EnchereManager"%>
<%@page import="fr.eni.projetenchere.bll.EnchereManagerSing"%>
<script>
	function toggleCheckboxes() {
		var achatsRadio = document.getElementById("achatsRadio");
		var ventesRadio = document.getElementById("ventesRadio");
		var enchereCheckbox = document.getElementById("enchereCheckbox");
		var mesEncheresCheckbox = document
				.getElementById("mesEncheresCheckbox");
		var mesEncheresRemporteesCheckbox = document
				.getElementById("mesEncheresRemporteesCheckbox");
		var ventesEnCoursCheckbox = document
				.getElementById("ventesEnCoursCheckbox");
		var ventesNonDebuteesCheckbox = document
				.getElementById("ventesNonDebuteesCheckbox");
		var ventesTermineesCheckbox = document
				.getElementById("ventesTermineesCheckbox");
		if (achatsRadio.checked) {
			enchereCheckbox.disabled = false;
			mesEncheresCheckbox.disabled = false;
			mesEncheresRemporteesCheckbox.disabled = false;
			ventesEnCoursCheckbox.disabled = true;
			ventesNonDebuteesCheckbox.disabled = true;
			ventesTermineesCheckbox.disabled = true;
		} else if (ventesRadio.checked) {
			enchereCheckbox.disabled = true;
			mesEncheresCheckbox.disabled = true;
			mesEncheresRemporteesCheckbox.disabled = true;
			ventesEnCoursCheckbox.disabled = false;
			ventesNonDebuteesCheckbox.disabled = false;
			ventesTermineesCheckbox.disabled = false;
		}
	}
</script>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Accueil" />
</jsp:include>

<div class="container-fluid" style="width:50%;">
	<h1 class="text-center ">Liste des enchères</h1>
	<form role="search"
		action="<%=request.getContextPath()%>/Accueil" method="post">
		<div class="d-flex flex-row justify-content-center" >
			<input type="hidden" name="form" value="form1" />
		
<!-- 		style="width: 400px" -->
			<input class="form-control border border-warning mx-2" type="search"
				placeholder="Rechercher un article" aria-label="Search"
				name="search" style="width : 30%">


			<%
			Integer id = (Integer) session.getAttribute("id");
			List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute("listArticle");

			List<ArticleVendu> toutArticles = (List<ArticleVendu>) request.getAttribute("listToutArticle");

			String pasArticle = (String) request.getAttribute("PasArticle");
			
			EnchereManager enchereManager = EnchereManagerSing.getInstanceEnchereImpl();
			
			
			%>
<!-- 			<label class="form-label me-3" for="Categorie">Categorie -->
<!-- 			</label> placeholder="Selectionner une categorie" -->
			<br> 
<!-- 			style="width: 300px" -->
<!-- placeholder="Rechercher par categorie" -->
			 <select class="form-control border border-warning " id="Categorie" name="Categorie" style="width : 30%"
			 	>
 				<option selected>Selectionner une categorie</option>

				<%
				List<Categorie> listCategorie = (List<Categorie>) request.getAttribute("listCategorie");
				for (Categorie categorieDisponible : listCategorie) {
				%>
				<option value="<%=categorieDisponible.getLibelle()%>"><%=categorieDisponible.getLibelle()%></option>
				<%
				}
				%>
			</select>
			<button class="btn btn-warning btn-lg mx-2" type="submit" style="width : 150px">Rechercher</button>
		</div>
	</form>
	<%
	if (id != null) {
	%>
	<form class="filtreForm"
		action="<%=request.getContextPath()%>/Accueil" method="post"
		id="formradio">
		<div class="d-flex flex-row justify-content-around">
			<input type="hidden" name="form" value="form2" />
			<fieldset id="check">
				<legend class="border-top border-black">Mes Achats</legend>
				<div class="form-check">
					<!-- 				<input type="radio" name="options" id="achatsRadio" value="achats" -->
					<!-- 					onclick="toggleCheckboxes()" checked>Achats -->
					<div>
						<input type="radio" name="check" id="enchereCheckbox"
							value="encheres_ouvertes">enchères ouvertes
					</div>
					<div>
						<input type="radio" name="check" id="mesEncheresCheckbox"
							value="mes_encheres">mes enchères en cours
					</div>
					<div>
						<input type="radio" name="check"
							id="mesEncheresRemporteesCheckbox" value="encheres_remportees">mes
						enchères remportées
					</div>
				</div>
			</fieldset>
			<fieldset id="check">
				<legend class="border-top border-black">Mes ventes</legend>
				<div class="form-check">
					<!-- 				<input type="radio" name="options" id="ventesRadio" value="ventes" -->
					<!-- 					onclick="toggleCheckboxes()">Mes Ventes -->

					<div>
						<input type="radio" name="check" id="ventesEnCoursCheckbox"
							value="ventes_cours">mes ventes en cours
					</div>
					<div>
						<input type="radio" name="check" id="ventesNonDebuteesCheckbox"
							value="ventes_non_debutees">ventes non débutées
					</div>
					<div>
						<input type="radio" name="check" id="ventesTermineesCheckbox"
							value="ventes_terminees">ventes terminées
					</div>
				</div>
			</fieldset>
		</div>
		<div class="d-flex flex-row justify-content-center">
			<button class="btn btn-warning btn-lg mt-2 d-flex justify-content-center" type="submit">Appliquer
				le filtre</button>
		</div>
		<%
		}
		%>


	</form>
</div>


<%
if (articles != null) {
%>
<!-- align-content-stretch flex-wrap -->
<div class="container-fluid" style="width:80%;">
	<div class="d-flex flex-wrap justify-content-around">
	<%
	for (ArticleVendu art : articles) {
	%>



	<div class="card my-2 mx-2 border-warning " style="width: 250px" >
		<div class="card-body ">
			<%
			if (id != null) {
			%>
			<h5 class="card-title text-center">
				<a class="text-dark"
					href="./DetailArticle?idArticle=<%=art.getNoArticle()%>" class="link-warning"><%=art.getNomArticle()%></a>

			</h5>
			<%
			} else {
			%>
			<h5 class="card-title text-dark"><%=art.getNomArticle()%></h5>
			<%
			}
			%>
			<h6 class="card-subtitle mb-2 text-body-secondary">
				Prix :
				<%
				if(enchereManager.selectMaxEnchere(art)!=null){
				
				Enchere ench=enchereManager.selectMaxEnchere(art); 
				if (ench.getMontantEnchere() == null) {
					%>
					0 💰
					<%
					} else if (ench.getUtilisateur().getActif()==true){
					%>
					<%=ench.getMontantEnchere()%> 💰
					<%
					} else { %>
						<%=art.getPrixInitial()%>
				<%	}
					
			} else { %>
			<%=art.getPrixInitial()%> 💰
			<%}%>
				</h6>
			<p class="card-text">
				Fin de l'enchère : 
				<%=art.getDateFinEnchere()%> 📅</p>
			<%
			if (id != null) {
			%>
			<p class="card-text text-center">
				Vendeur : 
				
				<%if(art.getUtilisateur().getNoUtilisateur()!=id){ %>
				
					<a href="./ProfilVendeur?idVendeur=<%=art.getUtilisateur().getNoUtilisateur()%>"><%=art.getUtilisateur().getNom()%></a>
					
					<%}else{ %>
					<a href="./Profil"><%=art.getUtilisateur().getNom()%></a>
					
				
			</p>
			<%
					}
			} else {
			%>
			<p class="card-text text-center">
				Vendeur :
				<%=art.getUtilisateur().getNom()%></p>
			<%
			}
			%>
		</div>
	</div>

	<%
	}
	%>
	</div>
</div>
<%
} else {
if (toutArticles != null) {
%>
<div class="container-fluid"  style="width:1000px;">
	<div class="d-flex flex-wrap justify-content-around">
	<%
	for (ArticleVendu art : toutArticles) {
	%>



	<div class="card my-2 mx-2 border-warning " style="width: 250px">
		<div class="card-body ">
			<%
			if (id != null) {
			%>
			<h5 class="card-title text-center">
				<a href="./DetailArticle?idArticle=<%=art.getNoArticle()%>" class="link-warning"><%=art.getNomArticle()%></a>

			</h5>
			<%
			} else {
			%>
			<h5 class="card-title text-dark"><%=art.getNomArticle()%></h5>
			<%
			}
			%>
			<h6 class="card-subtitle mb-2 text-body-secondary">
				Prix :
				<%
				if(enchereManager.selectMaxEnchere(art)!=null){
				
				Enchere ench=enchereManager.selectMaxEnchere(art); 
				if (ench.getMontantEnchere() == null) {
					%>
					0 💰
					<%
					} else if (ench.getUtilisateur().getActif()==true){
					%>
					<%=ench.getMontantEnchere()%> 💰
					<%
					} else { %>
						<%=art.getPrixInitial()%> 💰
				<%	}
					
			} else { %>
			<%=art.getPrixInitial()%> 💰
			<%}%>
				</h6>
			<p class="card-text">
				Fin de l'enchère : 
				<%=art.getDateFinEnchere()%> 📅</p>
			<%
			if (id != null) {
			%>
			<p class="card-text text-center">
				Vendeur : <a
					href="./ProfilVendeur?idVendeur=<%=art.getUtilisateur().getNoUtilisateur()%>"><%=art.getUtilisateur().getNom()%></a>
			</p>
			<%
			} else {
			%>
			<p class="card-text text-center">
				Vendeur :
				<%=art.getUtilisateur().getNom()%></p>
			<%
			}
			%>
		</div>
	</div>

	<%
	}
	%>
	</div>
</div>

<%
} else {
%>

<p><%=pasArticle%></p>

<%
}
}
%>

<jsp:include page="./fragments/foot.jsp"></jsp:include>