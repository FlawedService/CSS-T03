
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="Greeting" class="css.Greeting" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
 "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloWeb Welcome message</title>
</head>

<body>
<p> Hello <c:out value ="${Greeting.title}"></c:out>  <c:out value ="${Greeting.name}"></c:out>.</p>
<p> Welcome aboard of this fantastic adventure! 
You will start with  <c:out value ="${Greeting.points}"></c:out> points. </p>
</body>

</html>

