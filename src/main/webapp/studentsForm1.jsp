<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.*, objects.Student" %>
<!DOCTYPE html>
<html lang="es-ES">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css" href="/practica1TIW/src/main/webapp/WEB-INF/style.css"></style>
<title>Practica 1</title>
</head>
<%
	//		Getting the students from the controler (Servlet)
		Student chosenStu = (Student) request.getAttribute("STUDENT");
		String message = (String) request.getAttribute("MESSAGE");
		if (message == null)
			message = "";
%>
<body>
<!-- Main Container -->
<div class="container"> 
  <!-- Navigation -->
  <header> <a href="">
    <h4 class="logo">AULAMASTER</h4>
    </a>
    <nav>
      <ul>
        <li><a href="#hero">HOME</a></li>
        <li><a href="#about">ABOUT</a></li>
        <li> <a href="#contact">CONTACT</a></li>
      </ul>
    </nav>
  </header>
  <!-- Hero Section -->
  <section class="hero" id="hero">
    <h2 class="hero_header">CRUD&nbsp; <span class="light">Estudiantes&nbsp;</span></h2>
    <p class="tagline">Light is a simple one page website</p>
    <form id="form1" name="form1" method="post">
	  <div class="formInput">
		<label for="nia">NIA:</label>
      	<input type="number" name="nia" id="nia" value=<%=chosenStu.getNia()%> readonly>
      	Al crear un Alumno nuevo, el nia se generará automáticamente.
	  </div>
	  <div class="formInput">
		<label for="name">Nombre:</label>
        <input type="text" name="name" id="name" value=<%= chosenStu.getName() %>>
	  </div>
	  <div class="formInput">
		<label for="sruname1">Primer Apellido:</label>
        <input type="text" name="surname1" id="surname1" value=<%= chosenStu.getSurname1() %>>
	  </div>
	  <div class="formInput">
		<label for="surname2">Segundo Apellido:</label>
        <input type="text" name="surname2" id="surname2" value=<%= chosenStu.getSurname2() %>>
	  </div>
	  <div class="formInput">
		<label for="dateOfBirth">Fecha de Nacimiento:</label>
        <input type="date" name="dateOfBirth" id="dateOfBirth" value=<%= chosenStu.getDateOfBirth() %>>
	  </div>
	  <!--  
	  <div class="formInput">
		<label for="textfield">Foto:</label>
		
        <input type="image">
	  </div>-->
	  <input name="action" type="submit" value="Crear Alumno">
	  <input name="action" type="submit" value="Eliminar Alumno">
	  <input name="action" type="submit" value="Modificar Alumno">
	  <input name="action" type="submit" value="Atras">
    </form>
    
    <div class="message"><%=message %></div>
    
  </section>
  <footer>
  </footer>
  <div class="copyright">2021- <strong>TIW. Universidad Carlos III Madrid</strong></div>
</div>
</body>
</html>