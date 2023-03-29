<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.messages.LecteurMessage"%>
<%@page import="java.util.List"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Connexion"/>
</jsp:include>

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
<div class="container">
<form action="<%=request.getContextPath()%>/ServletConnexion" method="post">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" value="<%=listeCodesErreur != null ? request.getParameter("email") : ""%>">
    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="mdp"
			value="<%=listeCodesErreur != null ? request.getParameter("mdp") : ""%>">
  </div>
  <div class="mb-3 form-check">
<!--     <input type="checkbox" class="form-check-input" id="exampleCheck1"> -->
<!--     <label class="form-check-label" for="exampleCheck1">Check me out</label> -->
  </div>
  <button type="submit" class="btn btn-dark">Connexion</button>
</form>
</div>
<div class="container">
<br>
<a href="ServletAjoutUtilisateur" class="btn btn-dark"
					role="button">Créer un compte</a>
</div>

<jsp:include page="./fragments/foot.jsp"></jsp:include>