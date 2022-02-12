## JSP 연습_격언

* JSP연습

```jsp
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>속담 및 격언</title>
</head>
<body>

<%
	//1. 밥 먹고 지냅시다. 쇠는 달구어 졌을 때 때려야 한다.
	//2. 쇠는 달구어 졌을 때 때려야 한다.
	//3. 노력은 배신하지 않는다.
	
	Random rm = new Random();
	ArrayList<String> list = new ArrayList<String>();
	list.add("1. 밥 먹고 지냅시다.");
	list.add("2. 쇠는 달구어 졌을 때 때려야 한다.");
	list.add("3. 노력은 배신하지 않는다.");
	
	int index = rm.nextInt(3);
	//out.print(list.get(index)); //out 객체를 이용하여 클라이언트와 연결하는 네트워크 프로그램	
	//System.out.println(); //서버에서만 찍혀서 디버깅용으로 사용
%>
<h1>오늘의 격언</h1>
	<h2>
        <%=list.get(index) %> //expression	  		
    </h2>				

</body>
</html>
```

