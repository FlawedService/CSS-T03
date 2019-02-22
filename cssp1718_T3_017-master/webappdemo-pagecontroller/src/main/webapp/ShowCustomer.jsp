<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="helper"
	class="css.webappdemopagecontroller.webpresentation.CustomersHelper"
	scope="request" />
<jsp:useBean id="helperAddress"
	class="css.webappdemopagecontroller.webpresentation.MoradaHelper"
	scope="request" />
<html>
<link rel="stylesheet" type="text/css" href="resources/app.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers Info</title>
</head>
<body>
<body>
	<h2>Clients Info</h2>

	<table style="width: 100%">
		<tr>
			<th>Name</th>
			<th>Contact Info</th>
			<th>Vat Number</th>
		</tr>
		<tr>
			<c:forEach var="cliente" items="${helper.listClients}">
				<tr>
					<td><c:out value="${cliente.designation}"></c:out></td>
					<td><c:out value="${cliente.phNumber}"></c:out></td>
					<td><c:out value="${cliente.vat}"></c:out></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	
	<a href="index.html"><button>Go To Main Page</button></a>
</body>
</html>