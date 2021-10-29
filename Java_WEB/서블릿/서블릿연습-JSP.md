### 서블릿연습

* jsp 파일 생성

```jsp
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member 등록</title>
<style type="text/css">
	table {
		border-collapse : collapse;
		border : 1px solid black;
		margin : 50px auto;
	}
	
	td {
		border-collapse : collapse;
		border : 1px solid black;
	}
</style>
</head>
<body>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = sdf.format(new Date());
%>
<form action="/MVCBasic/CmdController" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="user_id"></td>
			<td><input type="button" name="" value="id확인"></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="pwd"></td>
			<td></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="user_name"></td>
			<td></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"></td>
			<td></td>
		</tr>
		<tr>
			<td>가입일</td>
			<td><input type="text" name="" value="<%=now%>" readonly="readonly"></td>
			<td></td>
		</tr>
		<tr>
			<td><input type="submit" value="가입"></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
</form>
</body>
</html>
```



