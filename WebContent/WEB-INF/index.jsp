<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>

<!-- -------------- CSS SHEETS ----------------- -->
<link rel="stylesheet" type="text/css"
	href="inc/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="inc/css/style.css" />
<!-- -------------- CSS SHEETS ----------------- -->

</head>
<body onload="testAjax()">
	<!-- -------------- NAVBAR ----------------- -->
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="#"><img src="inc/images/Logo.png"
			height="30" width="110"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>
	<!-- -------------- NAVBAR ----------------- -->

	<!-- -------------- NUMBER OF WARNINGS ----------------- -->
	<div class="container warningContainer">
		<p>Warnings</p>
		<p id="countWarningsDisplay"></p>
	</div>
	<!-- -------------- NUMBER OF WARNINGS ----------------- -->

	<!-- -------------- SELECT DATE ----------------- -->
	<div class="container">
		<select class="custom-select" id="selectDate">
			<option selected value="Today" onclick="testAjax()">Today</option>
			<option value="Week" onclick="testAjax()">This week</option>
			<option value="Month" onclick="testAjax()">This month</option>
		</select>
	</div>
	<!-- -------------- SELECT DATE ----------------- -->

	<!-- -------------- TABLE ----------------- -->
	<div class="container tableWarn">
		<table class="table table-sm table-striped" id="tableWarnings">
			<thead class="thead-light" id="headTableWarnings">
			</thead>
			<tbody id="bodyTableWarnings">
			</tbody>
		</table>
	</div>
	<!-- -------------- TABLE ----------------- -->

	<!-- -------------- CHARTS ----------------- -->
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<div class="container chart">
					<canvas id="myChartLine"></canvas>
				</div>
			</div>
			<div class="col">
				<div class="container chart">
					<canvas id="myChartPie"></canvas>
				</div>
			</div>
		</div>
	</div>
	<!-- -------------- CHARTS ----------------- -->

	<!-- -------------- JS SCRIPT ----------------- -->
	<!-- 	colour palette generator (for charts) -->
	<script src="https://codepen.io/anon/pen/aWapBE.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
	<script type="text/javascript">
		var json = '${json}';
	</script>
	<script type="text/javascript" src="inc/js/chart.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script type="text/javascript" src="inc/bootstrap/bootstrap.min.js"></script>
	<!-- -------------- JS SCRIPT ----------------- -->

	<!--TODO: [if lte IE 8]><script type="text/javascript" src="excanvas.js"></script><![endif]-->
</body>
</html>