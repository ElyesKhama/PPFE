<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<h1>Index</h1>
	<h2> List of all purchases : </h2>
	<c:out value="${sessionScope.listePurchases}"/>
	<c:forEach items="${param.listePurchases}" var="news">
		<c:out value="${news.dateDay}"/>
	</c:forEach>
</body>
</html>