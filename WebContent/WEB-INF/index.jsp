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
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">

<!-- -------------- CSS SHEETS ----------------- -->

</head>
<body onload="testAjax()">
	<!-- -------------- NAVBAR ----------------- -->
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="#"><img src="inc/images/Logo.png"
			height="30" width="110"></a>
		<div class="collapse navbar-collapse"></div>

		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<div class="btn-group">
					<button type="button" class="btn btn-secondary dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Login</button>
					<div class="dropdown-menu dropdown-menu-right" id="myDropdown">
						<a href="<c:url value = "/login"/>">LOG IN</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="btn-group">
					<button type="button" class="btn btn-secondary dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="far fa-user"></i>
					</button>
					<div class="dropdown-menu dropdown-menu-right" id="myDropdown">
						<p>
							Connected as : <br>
							<c:out value="${sessionScope.user.username }"></c:out>
							<br> <a href="<c:url value = "/logout"/>">LOG OUT</a>

						</p>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
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
		<div class="containerElem" id="elemTable">
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
	<script type="text/javascript" src="inc/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="inc/popper/popper.min.js"></script>

	<script>
	$(function() {
	    $('#myDropdown').click(function(e) {
		e.stopPropagation();
	    })
	});
    </script>

	<!-- 	colour palette generator (for charts) -->
	<script type="text/javascript">
	var jsonTest = '${json}';
    </script>
	<script type="text/javascript" src="inc/js/aWapBE.js"></script>
	<script type="text/javascript" src="inc/chartJs/Chart.min.js"></script>
	<script type="text/javascript" src="inc/js/chart.js"></script>
	<script type="text/javascript" src="inc/bootstrap/bootstrap.min.js"></script>


	<!-- -------------- JS SCRIPT ----------------- -->

	<!--TODO: [if lte IE 8]><script type="text/javascript" src="excanvas.js"></script><![endif]-->
</body>
</html>