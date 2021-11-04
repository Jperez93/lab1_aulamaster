<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
//		Getting the students from the controler (Servlet)
String message = (String) request.getAttribute("MESSAGE");
if (message == null)
	message = "";
%>
<body>
	<jsp:include page="/header.html" />

	<form action="StudentsControler" method="post">
		<input name="niaToLook" type="number" placeholder="nia"> <input
			name="action" type="submit" value="Buscar Alumno">
	</form>
	<%=message%>


</body>
</html>