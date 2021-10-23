<%@page import="mc.sn.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 리스트</title>
<link rel="stylesheet" href="../css/mystyle.css">
</head>
<body>
list
<table>
	<tr><th>아이디</th><th>비번</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th></tr>
<%
	ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("key");
	for(MemberVO vo : list){
%>
		<tr>
			<td>
				<a href="./CmdController?cmd=view_update&user_id=<%=vo.getMemberId() %>"><%=vo.getMemberId() %></a></td>
			<td><%=vo.getMemberPwd() %></td>
			<td><%=vo.getMemberName() %></td>
			<td><%=vo.getMemberEmail() %></td>
			<td><%=vo.getJoinDate().toLocaleString() %></td>
			<td><a href="./CmdController?cmd=remove&user_id=<%=vo.getMemberId() %>">삭제</a></td>
		</tr>
<%
	}
%>
<tr><td colspan="6"><a href="/MVCBasicPooling/CmdController?cmd=view_input">회원가입</a></td></tr>
	
</table>
</body>
</html>