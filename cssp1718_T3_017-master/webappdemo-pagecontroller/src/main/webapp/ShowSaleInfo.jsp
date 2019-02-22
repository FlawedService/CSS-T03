<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="SaleHelper"
	class="css.webappdemopagecontroller.webpresentation.SaleHelper"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sale Info</title>
</head>
<body>
<body>
	<h2>Sale Info</h2>
	<table style="width: 100%">
	<tr>
		<th>Sale Identifier</th>
		<th>Client Identifier</th>
		<th>Sale Date</th>
		<th>Sale Total</th>
		<th>Sale Status</th>
	</tr>
	<tr>
	<tr>
		<td><c:out value="${Salehelper.id}"></c:out></td>
		<td><c:out value="${Salehelper.customerId}"></c:out></td>
		<td><c:out value="${Salehelper.date}"></c:out></td>
		<td><c:out value="${Salehelper.total}"></c:out></td>
		<td><c:out value="${Salehelper.status}"></c:out></td>
	</tr>
</table>

<a href="index.html"><button>Go To Main Page</button></a>
</body>
</html>