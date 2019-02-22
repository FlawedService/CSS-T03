<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helperAddress" class="css.webappdemopagecontroller.webpresentation.SaleHelper" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delivery Info</title>
</head>
<body>
<h3>Morada a ser Entregue</h3>
	<%--Customer vat :<c:out value="${helperAddress.vat}"></c:out>
	Address Info :<c:out value="${helperAddress.address}"></c:out>--%>
	
<a href="index.html"><button>Go To Main Page</button></a>
</body>
</html>