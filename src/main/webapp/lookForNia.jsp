<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="keywords" content="Busqueda de estudiantes">
<meta name="description" content="">
<meta name="page_type" content="np-template-header-footer-from-plugin">
<title>LookForNia</title>
<link rel="stylesheet" href="styles/nicepage.css" media="screen">
<link rel="stylesheet" href="styles/LookForNia.css" media="screen">
<!--   <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
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
<meta property="og:title" content="LookForNia">
<meta property="og:type" content="website">
</head>
<body class="u-body">
	<jsp:include page="/header.html" />

	<section class="u-clearfix u-section-1" id="sec-310a">
		<div class="u-clearfix u-sheet u-sheet-1">
			<h2 class="u-text u-text-default u-text-1">Búsqueda de
				estudiantes</h2>
			<div class="u-form u-form-1">
				<form action="StudentsControler" method="POST"
					class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form"
					style="padding: 10px;">
					<div class="u-form-group u-form-name">
						<label for="name-0967" class="u-form-control-hidden u-label"></label>
						<input type="text" placeholder="Nia" id="name-0967" name="nia"
							class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
					</div>
					<div class="u-align-center u-form-group u-form-submit">
						<input type="submit" name="action" value="Buscar Alumno"
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
			<p class="u-text u-text-default u-text-2">Para crear un
				estudiante, realice la búsqueda con el campo 'Nia' vacío</p>
		</div>
	</section>


		<jsp:include page="/footer.html" />

	
</body>
</html>