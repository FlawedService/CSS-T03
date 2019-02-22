<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="SaleHelper"
	class="css.webappdemopagecontroller.webpresentation.SaleHelper"
	scope="request" />
	
<html>
<link rel="stylesheet" type="text/css" href="resources/app.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Info</title>
</head>
<body>
<body>
	<h2>Client Info</h2>
	<table style="width: 100%">
	<tr>
		<th>Identifier</th>
		<th>Sale ID</th>
		<th>Sale Status</th>
	</tr>
		
	<tr>
		<tr>
			<td><c:out value="${SaleHelper.customerId}"></c:out></td>
			<td><c:out value="${SaleHelper.id}"></c:out></td>
			<td><c:out value="${SaleHelper.status}"></c:out></td>
		</tr>
		
</table>

<a href="index.html"><button>Go To Main Page</button></a>
</body>
</html>