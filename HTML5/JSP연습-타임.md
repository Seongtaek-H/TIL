## JSP 연습_타임

* JSP연습

```jsp
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동적 처리</title>
</head>
<body>

<%	//자바코드 사용 가능
	
	Date now = new Date();
 	
 	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 	
 	String formateNow = format.format(now);
 	
 	System.out.println(formateNow);
 	out.println("<h1>"+formateNow+"</h1>");

%>

<a href = "../day17/data_collection.html">Page이동</a>

</body>
</html>
```

