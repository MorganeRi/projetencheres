<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Accueil"/>
</jsp:include>

<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="ServletAccueil">Enchere MOJONE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="ServletAjoutUtilisateur">S'inscrire</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ServletConnexion">Se connecter</a>
        </li>
      </ul>
      <div class="container">
    <form class="d-flex" role="search">
      <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>
      
    </div>
  </div>
</nav>




<jsp:include page="./fragments/foot.jsp"></jsp:include>