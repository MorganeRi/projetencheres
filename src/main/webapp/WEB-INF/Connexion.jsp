<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="fr.eni.projetenchere.bo.Utilisateur"%>
<%@page import="java.util.List"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Connexion"/>
</jsp:include>
	<h1>Connexion</h1>
	
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
<div class="container-fluid">
<form action="<%=request.getContextPath()%>/ServletConnexion" method="post">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Adresse E-mail ou pseudo</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="E-mail" name="email" value="<%=listeCodesErreur != null ? request.getParameter("email") : ""%>">

  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Mot de passe</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Mot de passe" name="mdp"
			value="<%=listeCodesErreur != null ? request.getParameter("mdp") : ""%>">
  </div>
  <div class="mb-3 form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe"> se souvenir de moi
<!--     <label class="form-check-label" for="exampleCheck1">Check me out</label> -->
  </div>
  <button type="submit" class="btn btn-dark">Connexion</button>
</form>
</div>
<div class="container-fluid">
<br>
<a href="ServletAjoutUtilisateur" class="btn btn-dark"
					role="button">Créer un compte</a>
</div>
<%}else{ %>
<div class="container-fluid">
<p>Vous êtes connecté</p>
</div>
<%} %>
<jsp:include page="./fragments/foot.jsp"></jsp:include>