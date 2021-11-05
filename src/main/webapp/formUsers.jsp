<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, objects.Student"%>
<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="page_type" content="np-template-header-footer-from-plugin">
<title>formUsers</title>
<link rel="stylesheet" href="styles/nicepage.css" media="screen">
<link rel="stylesheet" href="styles/formUsers.css" media="screen">
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
<meta property="og:title" content="formUsers">
<meta property="og:type" content="website">
</head>
<%
//		Getting the students from the controler (Servlet)
Student chosenStu = (Student) request.getAttribute("STUDENT");
String message = (String) request.getAttribute("MESSAGE");
if (message == null)
	message = "";
session.setAttribute("IMAGE", chosenStu.getImage());

%>
<body class="u-body">
	<jsp:include page="/header.html" />

	<section class="u-clearfix u-section-1" id="sec-a638">
		<div class="u-clearfix u-sheet u-sheet-1">
			<div
				class="u-clearfix u-expanded-width u-gutter-0 u-layout-wrap u-layout-wrap-1">
				<div class="u-layout">
					<div class="u-layout-row">
						<div
							class="u-align-center u-container-style u-layout-cell u-left-cell u-size-23 u-layout-cell-1">
							<div
								class="u-container-layout u-valign-middle u-container-layout-1">
								<img alt="" class="u-image u-image-circle u-image-1"
									src="./displayImage.jsp" data-image-width="256"
									data-image-height="256"></div>
							</div>
						</div>
						<div
							class="u-align-left u-container-style u-layout-cell u-right-cell u-size-37 u-layout-cell-2">
							<div class="u-container-layout u-container-layout-2">
								<div class="u-form u-form-1">
									<form action="StudentsControler" method="POST"
										class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form"
										style="padding: 10px" source="custom" name="form"
										enctype="multipart/form-data">
										<div class="u-form-group u-form-name">
											<label for="name-3b9a" class="u-form-control-hidden u-label">Nia</label>
											<input type="text" placeholder="NIA" id="name-3b9a"
												name="nia"
												class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"
												readonly value=<%=chosenStu.getNia()%>>
										</div>
										<div class="u-form-email u-form-group">
											<label for="email-3b9a" class="u-form-control-hidden u-label">Nombre</label>
											<input type="text" placeholder="Nombre" id="email-3b9a"
												name="name"
												class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"
												value=<%=chosenStu.getName()%>>
										</div>
										<div class="u-form-group u-form-group-3">
											<label for="text-76cc" class="u-form-control-hidden u-label">Primer
												Apellido</label> <input type="text" placeholder="Primer Apellido"
												id="text-76cc" name="surname1"
												class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"
												value=<%=chosenStu.getSurname1()%>>
										</div>
										<div class="u-form-group u-form-group-4">
											<label for="text-bc63" class="u-form-control-hidden u-label">Segundo
												Apellido</label> <input type="text" placeholder="Segundo Apellido"
												id="text-bc63" name="surname2"
												class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"
												value=<%=chosenStu.getSurname2()%>>
										</div>
										<div class="u-form-date u-form-group u-form-group-5">
											<label for="date-be2d" class="u-form-control-hidden u-label">Fecha
												de Nacimiento</label> <input type="date" id="date-be2d"
												name="dateOfBirth"
												class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white"
												value=<%=chosenStu.getDateOfBirth()%>>
										</div>
										<div class="u-form-date u-form-group u-form-group-5">
											<label for="image" class="u-form-control-hidden u-label">Foto</label><input
												type="file" name="image">
										</div>
										<div class="u-align-left u-form-group u-form-submit">
											<input class="u-btn u-btn-submit u-button-style u-btn-1"
												type="submit" name="action" value="Crear Alumno">
										</div>
										<div class="u-align-left u-form-group u-form-submit">
											<input class="u-btn u-btn-submit u-button-style u-btn-1"
												type="submit" name="action" value="Eliminar Alumno">
										</div>
										<div class="u-align-left u-form-group u-form-submit">
											<input class="u-btn u-btn-submit u-button-style u-btn-1"
												type="submit" name="action" value="Modificar Alumno">
										</div>
										<div class="u-align-left u-form-group u-form-submit">
											<input class="u-btn u-btn-submit u-button-style u-btn-1"
												type="submit" name="action" value="Atras">
										</div>
										<div class="u-form-send-message u-form-send-success">
											Thank you! Your message has been sent.</div>
										<div class="u-form-send-error u-form-send-message">
											Unable to send your message. Please fix errors then try
											again.</div>
									</form>
									<div class="message"><%=message%></div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<jsp:include page="/footer.html" />


</body>
</html>