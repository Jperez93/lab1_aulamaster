<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, model.Master" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<% List<Master> list = (List<Master>) request.getAttribute("LISTOFMASTERS"); %>
<body>
<form action="MastersControler" method="post">
<input type="text" name="name" placeholder="Master">
<select name="campus">
<option value="Leganes">Leganés</option>
<option value="Getafe">Getafe</option>
<option value="Colmenarejo">Colmenarejo</option>
<option value="" selected="selected">Cualquiera</option>
</select>
<input type="number" name="year" placeholder=2021>
<input type="submit" name="action" value="Buscar Master">
</form>
<%
if(list != null){
	for(Master m : list){%>
	<p> <%= m.getId()%>-<%= m.getName()%>-<%= m.getCampus()%>-<%=m.getYear()%></p>
<%}
}
%>
</body>
</html>