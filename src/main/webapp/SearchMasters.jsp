<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.Master"%>
<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="keywords" content="Master en CIberseguridad">
<meta name="description" content="">
<meta name="page_type" content="np-template-header-footer-from-plugin">
<title>SearchMasters</title>
<link rel="stylesheet" href="styles/nicepage.css" media="screen">
<link rel="stylesheet" href="styles/SearchMasters.css" media="screen">
<!--  <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
<script class="u-script" type="text/javascript" src="nicepage.js"
	defer=""></script>-->
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
<meta property="og:title" content="SearchMasters">
<meta property="og:type" content="website">
</head>
<%
List<Master> list = (List<Master>) request.getAttribute("LISTOFMASTERS");
%>
<body class="u-body">
	<jsp:include page="/header.html" />

	<section class="u-clearfix u-section-1" id="sec-d288">
		<div class="u-clearfix u-sheet u-sheet-1">
			<h3 class="u-text u-text-default u-text-1">Búsqueda avanzada de
				másteres</h3>
			<div class="u-expanded-width u-form u-form-1">
				<form action="MastersControler" method="POST"
					class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form"
					style="padding: 10px;">
					<div class="u-form-group u-form-partition-factor-3 u-form-group-1">
						<label for="text-1226" class="u-form-control-hidden u-label">Master</label>
						<input type="text" placeholder="Master" id="text-1226" name="name"
							class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
					</div>
					<div
						class="u-form-group u-form-partition-factor-3 u-form-select u-form-group-2">
						<label for="select-530f" class="u-form-control-hidden u-label"></label>
						<div class="u-form-select-wrapper">
							<select id="select-530f" name="campus"
								class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
								<option value="Leganes">Leganés</option>
								<option value="Getafe">Getafe</option>
								<option value="Colmenarejo">Colmenarejo</option>
								<option value="" selected="selected">Cualquiera</option>
							</select>
							<svg xmlns="http://www.w3.org/2000/svg" width="14" height="12"
								version="1" class="u-caret">
								<path fill="currentColor" d="M4 8L0 4h8z"></path></svg>
						</div>
					</div>
					<div class="u-form-group u-form-partition-factor-3 u-form-group-3">
						<label for="text-2393" class="u-form-control-hidden u-label">Year</label>
						<input type="text" placeholder="2021" id="text-2393" name="year"
							class="u-border-1 u-border-grey-30 u-input u-input-rectangle u-white">
					</div>
					<div class="u-align-right u-form-group u-form-submit">
						<input type="submit" value="Buscar Master" name="action"
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
			<div class="u-expanded-width u-table u-table-responsive u-table-1">
				<%
				if (list != null) {
				%>
				<table class="u-table-entity">
					<colgroup>
						<col width="50.2%">
						<col width="20.1%">
						<col width="12.3%">
						<col width="17.4%">
					</colgroup>
					<thead class="u-black u-table-header u-table-header-1">
						<tr style="height: 21px;">
							<th class="u-border-1 u-border-black u-table-cell">Titulo
								del Máster</th>
							<th class="u-border-1 u-border-black u-table-cell">Campus</th>
							<th class="u-border-1 u-border-black u-table-cell">Año</th>
							<th class="u-border-1 u-border-black u-table-cell">Acción</th>
						</tr>
					</thead>
					<tbody class="u-table-alt-grey-5 u-table-body">
						<%
						for (Master m : list) {
						%>
						<tr style="height: 75px;">
							<form action="MastersControler" method="post">
								<input type="hidden" name="masterId" value=<%=m.getId()%>>

								<td
									class="u-border-1 u-border-grey-30 u-first-column u-grey-50 u-table-cell u-table-cell-5"><%=m.getName()%></td>
								<td class="u-border-1 u-border-grey-30 u-table-cell"><%=m.getCampus()%></td>
								<td class="u-border-1 u-border-grey-30 u-table-cell"><%=m.getYear()%></td>
								<td
									class="u-border-1 u-border-grey-30 u-table-cell u-table-cell-8"><input
									class="u-border-2 u-border-black u-btn u-button-style u-hover-black u-none u-text-black u-text-hover-white u-btn-2"
									type="submit" name="action" value="Ver Detalles"></td>
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
		</div>
	</section>


	<jsp:include page="/footer.html" />


</body>
</html>