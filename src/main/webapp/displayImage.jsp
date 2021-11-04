<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, objects.Student"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	byte[] image = (byte[]) session.getAttribute("IMAGE");
	response.setContentType("image/gif");
	OutputStream o = response.getOutputStream();
	o.write(image);
	o.flush();
	o.close();
	%>
</body>
</html>