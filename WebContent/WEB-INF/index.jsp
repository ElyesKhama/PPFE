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
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">

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


	<div class="containerFlex">
		<div id="elemCountWarning">
			<!-- -------------- NUMBER OF WARNINGS ----------------- -->
			<div id="warningContainer">
				<p class="title">
					Warnings <i class="fas fa-exclamation-triangle"></i>
				</p>
				<p id="countWarningsDisplay"></p>
			</div>
			<!-- -------------- NUMBER OF WARNINGS ----------------- -->
			<!-- -------------- SELECT DATE ----------------- -->
			<div>
				<select class="custom-select" id="selectDate">
					<option selected value="Today" onclick="testAjax()">Today</option>
					<option value="Week" onclick="testAjax()">This week</option>
					<option value="Month" onclick="testAjax()">This month</option>
				</select>
			</div>
			<!-- -------------- SELECT DATE ----------------- -->
		</div>
		<div class="containerElem">
			<p class="title">List</p>
			<!-- -------------- TABLE ----------------- -->
			<div id="tableWarn">
				<table class="table table-sm" id="tableWarnings">
					<thead class="thead-light" id="headTableWarnings">
					</thead>
					<tbody id="bodyTableWarnings">
					</tbody>
				</table>
			</div>
			<!-- -------------- TABLE ----------------- -->
		</div>
	</div>

	<!-- -------------- CHARTS ----------------- -->
	<div class="containerFlex" id="containerFlexCharts">
		<div class="containerElem">
			<p class="title">Evolution</p>
			<div class="container chart">
				<canvas id="myChartLine"></canvas>
			</div>
		</div>
		<div class="containerElem">
			<p class="title">Repartition</p>

			<div class="container chart">
				<canvas id="myChartPie"></canvas>
			</div>
			<div class="container selectChart">
				<select class="custom-select" id="selectFilterChartPie">
					<option selected value="Amount" onclick="changeChartPie()">Amount</option>
					<option value="Priority" onclick="changeChartPie()">Priority</option>
				</select>
			</div>
		</div>
	</div>
	<!-- -------------- CHARTS ----------------- -->

	<!-- -------------- JS SCRIPT ----------------- -->
	<!-- 	colour palette generator (for charts) -->
	<script type="text/javascript">
	var jsonTest = '${json}';
    </script>
	<script type="text/javascript" src="inc/js/aWapBE.js"></script>
	<script type="text/javascript" src="inc/chartJs/Chart.min.js"></script>
	<script type="text/javascript" src="inc/js/chart.js"></script>
	<script type="text/javascript" src="inc/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="inc/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="inc/popper/popper.min.js"></script>
	<script type="text/javascript">
	jQuery(document).ready(function($) {
	    $(".headTable").click(function() {
		window.location = $(this).data('url');
	    });
	});
    </script>
	<!-- -------------- JS SCRIPT ----------------- -->

	<!--TODO: [if lte IE 8]><script type="text/javascript" src="excanvas.js"></script><![endif]-->
</body>
</html>