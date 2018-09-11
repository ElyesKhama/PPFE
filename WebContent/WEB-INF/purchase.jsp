<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Warnings</title>

<!-- -------------- CSS SHEETS ----------------- -->
<link rel="stylesheet" type="text/css"
	href="inc/bootstrap/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="inc/css/style.css" />
<!-- -------------- CSS SHEETS ----------------- -->

</head>
<body>
	<!-- -------------- NAVBAR ----------------- -->
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="/PPFE/index"><img
			src="inc/images/Logo.png" height="30" width="110"></a>
	</nav>
	<!-- -------------- NAVBAR ----------------- -->

	<p id="detailsWarning">
		Details : Warning n°
		<c:out value="${warning.id}"></c:out>
	</p>

	<!-- -------------- WARNING'S RECAP ----------------- -->
	<div class="container containerElem">
		<div class="row">
			<div class="col warningRecap">
				Priority :
				<c:out value="${warning.priority}"></c:out>
			</div>
			<div class="col warningRecap">
				Voucher :
				<c:out value="${warning.purchase.voucherType.description}"></c:out>
			</div>
			<div class="col warningRecap">
				Date :
				<c:out value="${warning.dateDay}"></c:out>
			</div>
		</div>
	</div>
	<!-- -------------- WARNING'S RECAP ----------------- -->

	<!-- -------------- PURCHASE DAY AND PREVIOUS DAYS RECAP ----------------- -->
	<div class="container tablePurchase containerElem">
		<div class="row">
			<div class="col">
				<span class="dayPurchaseLabel">Date</span>
			</div>
			<div class="col dayPurchase borderTable">
				<c:out value="${previousPurchase.dateDay}"></c:out>
			</div>
			<div class="col dayPurchase borderTable">
				<c:out value="${warning.dateDay}"></c:out>
			</div>
			<div class="col"></div>
		</div>
		<div class="row">
			<div class="col">
				<span class="dayPurchaseLabel">Count</span>
			</div>
			<div class="col dayPurchaseCount">
				<c:out value="${previousPurchase.countTotal}"></c:out>
			</div>
			<div class="col dayPurchaseCount">
				<c:out value="${warning.purchase.countTotal}"></c:out>
			</div>
			<div class="col dayPurchaseCount">
				<i class="fas fa-long-arrow-alt-down"></i>
				<c:out value="${warning.countDifference}"></c:out>
			</div>
		</div>
	</div>
	<!-- -------------- PURCHASE DAY AND PREVIOUS DAYS RECAP ----------------- -->

	<!-- -------------- FOOTER ----------------- -->
	<footer>
		<img id="logoAtos" src="inc/images/atosLogo.png">
	</footer>
	<!-- -------------- FOOTER ----------------- -->
</body>
</html>