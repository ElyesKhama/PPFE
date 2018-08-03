<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
 <link rel="stylesheet" type="text/css" href="inc/bootstrap/bootstrap.min.css"/>
</head>
<body>
	<h1>Index</h1>
	<h2> List of all purchases : </h2>
	<c:forEach items="${sessionScope.listePurchases}" var="p">
		<c:out value="${p.id}"/>
		<c:out value="${p.countTotal}"/>
		<c:out value="${p.dateDay}"/>
		<br/>
	</c:forEach>
<script type="text/javascript" src="inc/bootstrap/bootstrap.min.js"></script>
</body>
</html>