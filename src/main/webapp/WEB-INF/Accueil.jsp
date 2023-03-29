<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="Accueil"/>
</jsp:include>

      <div class="container">
    <form class="d-flex" role="search">
      <input class="form-control me-3" type="search" placeholder="Search" aria-label="Search" name="search">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>




<jsp:include page="./fragments/foot.jsp"></jsp:include>