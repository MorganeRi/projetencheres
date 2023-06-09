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
		style="width: 25%;">
	<h1 class="font-weight-bold text-center">Detail des encheres</h1>
	<br>
		<table class="table mb-0 table-hover">
			<thead>
				<tr>
					<th class="font-weight-bold text-center border-warning" scope="col">Pseudo encheriseur</th>
					<th class="font-weight-bold text-center border-warning" scope="col">Montant enchere</th>
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
					<td class="font-weight-bold text-center border-warning"><%=ench.getUtilisateur().getPseudo()%></td>
					<td class="font-weight-bold text-center border-warning"><%=ench.getMontantEnchere()%></td>
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