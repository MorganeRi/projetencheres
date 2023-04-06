<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<%@page import="java.util.List"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Connexion" />
</jsp:include>
<h1 class=" font-weight-bold text-center">Connexion</h1>

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
Integer id = (Integer) session.getAttribute("id");
if (id == null) {
%>
<div class="container-fluid" style="width: 30%;">
	<form action="<%=request.getContextPath()%>/ServletConnexion"
		method="post">

		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Adresse
				e-mail ou pseudo</span>
			<div class="form-floating">
				<input type="text" class="form-control border-warning"
					id="exampleInputEmail1" aria-describedby="emailHelp"
					placeholder="E-mail" name="email"
					value="<%=listeCodesErreur != null ? request.getParameter("email") : ""%>">
				<label for="floatingInputGroup1">Adresse e-mail ou pseudo</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text text-warning bg-dark">Mot de
				passe</span>
			<div class="form-floating">
				<input type="password" class="form-control border-warning"
					id="exampleInputPassword1" placeholder="Mot de passe" name="mdp"
					value="<%=listeCodesErreur != null ? request.getParameter("mdp") : ""%>">
				<label for="floatingInputGroup1">Mot de passe</label>
			</div>
		</div>
		<div class="p-2 mx-auto d-flex flex-column align-items-center">
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1"
					name="rememberMe"> se souvenir de moi
			</div>

			<a href="./ServletResetPassword?email=<%=request.getParameter("email")%>" class="lien">Mot de passe oublié</a>
			<button type="submit"
				class="btn btn-warning btn-lg mb-8 p-2 mt-2 shadow"
				style="width: 200px">Connexion</button>
			<a href="ServletAjoutUtilisateur"
				class="btn btn-warning btn-lg mb-8 p-2 mt-2 shadow"
				style="width: 200px" role="button">Créer un compte</a>
		</div>
	</form>
</div>

<%
} else {
%>
<div class="container-fluid">
	<p>Vous êtes connecté</p>
</div>
<%
}
%>
<jsp:include page="./fragments/foot.jsp"></jsp:include>