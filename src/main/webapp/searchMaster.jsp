<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.Master"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
List<Master> list = (List<Master>) request.getAttribute("LISTOFMASTERS");
%>
<body>
	<jsp:include page="/header.html" />

	<form action="MastersControler" method="post">
		<input type="text" name="name" placeholder="Master"> <select
			name="campus">
			<option value="Leganes">Leganés</option>
			<option value="Getafe">Getafe</option>
			<option value="Colmenarejo">Colmenarejo</option>
			<option value="" selected="selected">Cualquiera</option>
		</select> <input type="number" name="year" placeholder=2021> <input
			type="submit" name="action" value="Buscar Master">
	</form>
	<%
	if (list != null) {
	%>
	<table>
		<tr>
			<th><b>Titulo del master</b></th>
			<th><b>Campus</b></th>
			<th><b>Año</b></th>
			<th><b>Acción</b></th>
		</tr>


		<%
		for (Master m : list) {
		%>

		<tr>
			<form action="MastersControler" method="post">
				<input type="hidden" name="masterId" value=<%=m.getId()%>>
				<td><%=m.getName()%></td>
				<td><%=m.getCampus()%></td>
				<td><%=m.getYear()%></td>
				<td><input type="submit" name="action" value="Mostrar Detalles"></td>
			</form>
		</tr>
		<%
		}
		}
		%>
	</table>

</body>
</html>