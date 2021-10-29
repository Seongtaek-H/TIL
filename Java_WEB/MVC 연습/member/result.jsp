<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result page</title>
</head>
<body>
Result
<%
	String message = (String)request.getAttribute("message");
	out.print(message);
%>
</body>
</html>