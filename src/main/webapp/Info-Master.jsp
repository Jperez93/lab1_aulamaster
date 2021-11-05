
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.*"%>
<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="keywords" content="Master en CIberseguridad">
<meta name="description" content="">
<meta name="page_type" content="np-template-header-footer-from-plugin">
<title>Info Master</title>
<link rel="stylesheet" href="styles/nicepage.css" media="screen">
<link rel="stylesheet" href="styles/Info-Master.css" media="screen">
<!--  <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>-->
<meta name="generator" content="Nicepage 3.29.1, nicepage.com">
<link id="u-theme-google-font" rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">


<script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "",
		"logo": "images/aulam.png"
}</script>
<meta name="theme-color" content="#478ac9">
<meta property="og:title" content="Info Master">
<meta property="og:type" content="website">
</head>
<%
Master m = (Master) request.getAttribute("MASTER");
List<Student> students = m.getStudents();
String message = (String) request.getAttribute("MESSAGE");
if (message == null) {
	message = "";
}
%>
<body class="u-body">
		<jsp:include page="/header.html" />
	
	<section class="u-clearfix u-section-1" id="sec-24ed">
		<div class="u-clearfix u-sheet u-sheet-1">
			<h2 class="u-text u-text-default u-text-1">
				<%=m.getName()%><br>
			</h2>
			<h3 class="u-text u-text-2"><%=m.getCampus()%></h3>
			<h3 class="u-text u-text-default u-text-3">
				<%=m.getYear()%><br>
			</h3>
			<p class="u-text u-text-default u-text-4">Texto de ejemplo. Lorem
				ipsum dolor sit amet, consectetur adipiscing elit nullam nunc justo
				sagittis suscipit ultrices.</p>
			<div class="u-expanded-width u-table u-table-responsive u-table-1">
				<%
				if (!students.isEmpty()) {
				%>
				<table class="u-table-entity">
					<colgroup>
						<col width="9.6%">
						<col width="21.8%">
						<col width="18.3%">
						<col width="17.6%">
						<col width="16.7%">
						<col width="16%">
					</colgroup>
					<thead class="u-black u-table-header u-table-header-1">
						<tr style="height: 21px;">
							<th class="u-border-1 u-border-black u-table-cell">Nia</th>
							<th class="u-border-1 u-border-black u-table-cell">Nombre</th>
							<th class="u-border-1 u-border-black u-table-cell">Primer
								Apellido</th>
							<th class="u-border-1 u-border-black u-table-cell">Segundo
								Apellido</th>
							<th class="u-border-1 u-border-black u-table-cell">Fecha de
								nacimiento</th>
							<th class="u-border-1 u-border-black u-table-cell"></th>
						</tr>
					</thead>
					<tbody class="u-table-alt-grey-5 u-table-body">
						<%
						for (Student student : students) {
							Date date = student.getDateofbirth();
						%>
						<tr style="height: 75px;">
							<form action="MastersControler" method="post">
								<input type="hidden" name="nia" value=<%=student.getNia()%>>
								<input name="masterId" type="hidden" value=<%=m.getId()%>>
							<td
								class="u-border-1 u-border-grey-30 u-first-column u-grey-50 u-table-cell u-table-cell-7"><%=student.getNia()%></td>
							<td class="u-border-1 u-border-grey-30 u-table-cell"><%=student.getName()%></td>
							<td class="u-border-1 u-border-grey-30 u-table-cell"><%=student.getSurname1()%></td>
							<td
								class="u-border-1 u-border-grey-30 u-table-cell u-table-cell-10"><%=student.getSurname2()%></td>
							<td
								class="u-border-1 u-border-grey-30 u-table-cell u-table-cell-11"><%=date.getDate()%>/<%=date.getMonth() + 1%>/<%=date.getYear() + 1900%></td>
							<td
								class="u-border-1 u-border-grey-30 u-table-cell u-table-cell-12"><input
								class="u-border-2 u-border-black u-btn u-button-style u-hover-black u-none u-text-black u-text-hover-white u-btn-1"
								type="submit" name="action" value="Eliminar Alumno"></td>
							</form>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<%
				}
				%>
			</div>

			<div class="u-form u-form-1">
				<form action="MastersControler" method="POST"
					class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form"
					style="padding: 10px;">
					<div class="u-form-group u-form-name">
						<label for="name-babd" class="u-form-control-hidden u-label"></label>
						<input type="text" placeholder="Nia" id="name-babd" name="nia"
							class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"
							required="">
					</div>
					<div class="u-align-right u-form-group u-form-submit">
						<input name="masterId" type="hidden" value=<%=m.getId()%>>
						<input name="action" type="submit" value="Inscribir Alumno"
							class="u-btn u-btn-submit u-button-style">
					</div>
					<div class="u-form-send-message u-form-send-success">
						Gracias! Tu mensaje ha sido enviado.</div>
					<div class="u-form-send-error u-form-send-message">No se
						puede enviar su mensaje. Por favor, corrija los errores y vuelva a
						intentarlo.</div>
					<input type="hidden" value="" name="recaptchaResponse">
				</form>
			</div>
		</div>
		<div class=message><%=message%></div>

	</section>
			<jsp:include page="/footer.html" />
	
</body>
</html>