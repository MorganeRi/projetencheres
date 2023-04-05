<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.projetenchere.bo.Enchere"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<jsp:include page="./fragments/head.jsp">
	<jsp:param name="title" value="LesEncheres" />
</jsp:include>
<body>

	<div class="container-fluid"
		style="border: 1px solid silver; width: 500px;">
		<h1>DETAIL DES ENCHERES</h1>
		<table class="table mb-0 table-hover">
			<thead>
				<tr>
					<th scope="col">Pseudo encheriseur</th>
					<th scope="col">Montant enchere</th>
				</tr>
			</thead>
			<tbody>

				<%
				List<Enchere> encheres = (List<Enchere>) request.getAttribute("listeEnchere");
				if (encheres != null) {
					Collections.sort(encheres, Comparator.comparingDouble(Enchere::getMontantEnchere));
					for (Enchere ench : encheres) {
						if (ench.getUtilisateur().getActif() == true) {
				%>
				<tr>
					<td><%=ench.getUtilisateur().getPseudo()%></td>
					<td><%=ench.getMontantEnchere()%></td>
				</tr>
				<%
				}
				}
				}
				%>
			</tbody>
		</table>
		<br>
	</div>
</body>

<jsp:include page="./fragments/foot.jsp"></jsp:include>