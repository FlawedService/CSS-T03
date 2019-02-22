<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helperAddress" class="css.webappdemopagecontroller.webpresentation.MoradaHelper" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>address Info</title>
</head>
<body>
<h2>Address Info</h2>
<h3>Morada adicionada</h3>
	Customer vat :<c:out value="${helperAddress.vat}"></c:out>
	Address Info :<c:out value="${helperAddress.address}"></c:out>
<a href="index.html"><button>Go To Main Page</button></a>
</body>
</html>