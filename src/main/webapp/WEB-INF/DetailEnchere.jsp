<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Enchere"%>
<%@page import="java.util.List"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="LesEncheres"/>
</jsp:include>
<body>

<div class="container-fluid" style="border: 1px solid silver;width:500px;">
<h1>DETAIL DES ENCHERES</h1>
	<%
	List<Enchere> enchere = (List<Enchere>) request.getAttribute("enchere");
	for (Enchere ench : enchere) {
	}
	%>
<br>
</div>
</body>

<jsp:include page="./fragments/foot.jsp"></jsp:include><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>