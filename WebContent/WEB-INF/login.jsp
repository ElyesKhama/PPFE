<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>

<link rel="stylesheet" type="text/css"
	href="inc/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="inc/css/login.css" />
</head>
<body>

	<!-- -------------- NAVBAR ----------------- -->
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="/PPFE/index"><img
			src="inc/images/Logo.png" height="30" width="110"></a>
	</nav>
	<!-- -------------- NAVBAR ----------------- -->


	<c:out value="${restrict}"></c:out>
	<div class="container">
		<div class="login-form">
			<div class="main-div">
				<div class="panel">
					<h2>Admin Login</h2>
					<p>Please enter your username and password</p>
				</div>
				<form method="post" action="/PPFE/login" id="Login">
					<div class="form-group">
						<input type="text" class="form-control" id="inputUsername"
							name="inputUsername" placeholder="Username" required>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="inputPassword"
							name="inputPassword" placeholder="Password" required>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
					<c:if test="${result == false}">
						<span class="error"> Authentication failed </span>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<!-- -------------- JS SCRIPT ----------------- -->
	<script type="text/javascript" src="inc/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="inc/popper/popper.min.js"></script>
	<script type="text/javascript" src="inc/bootstrap/bootstrap.min.js"></script>
</body>
</html>