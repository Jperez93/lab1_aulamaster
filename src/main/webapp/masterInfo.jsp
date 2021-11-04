<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
Master m = (Master) request.getAttribute("MASTER");
List<Student> students = m.getStudents();
String message = (String) request.getAttribute("MESSAGE");
if (message == null) {
	message = "";
}
%>
<body>
	<jsp:include page="/header.html" />


	<h2><%=m.getName()%></h2>
	<h3><%=m.getCampus()%>-<%=m.getYear()%></h3>

	<%
	if (!students.isEmpty()) {
	%>
	<table>
		<thead>
			<tr>
				<th>NIA</th>
				<th>Nombre</th>
				<th>Primer Apellido</th>
				<th>Segundo Apellido</th>
				<th>Fecha de Nacimiento</th>
				<th></th>
			</tr>
		</thead>

		<%
		for (Student student : students) {
			Date date = student.getDateofbirth();
		%>
		<tr>
			<form action="MastersControler" method="post">
				<input type="hidden" name="nia" value=<%=student.getNia()%>>
				<input name="masterId" type="hidden" value=<%=m.getId()%>>
				<td><%=student.getNia()%></td>
				<td><%=student.getName()%></td>
				<td><%=student.getSurname1()%></td>
				<td><%=student.getSurname2()%></td>
				<td><%=date.getDate()%>/<%=date.getMonth() + 1%>/<%=date.getYear() + 1900%></td>
				<td><input type="submit" name="action" value="Eliminar Alumno"></td>
			</form>
		</tr>
		<%
		}
		%>

	</table>
	<%
	}
	%>
	<form action="MastersControler" method="post">
		<input name="masterId" type="hidden" value=<%=m.getId()%>> <input
			name="nia" type="text" placeholder="NIA"> <input
			name="action" type="submit" value="Inscribir Alumno">
	</form>

	<div class=message><%=message%></div>

</body>
</html>