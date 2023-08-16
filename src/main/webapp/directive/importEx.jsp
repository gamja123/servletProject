<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
java.util.Calendar cal = java.util.Calendar.getInstance();//자바 코드는 바디에 넣어도 되고 html밖에 해도 되고
%>
오늘은 <%=cal.get(java.util.Calendar.YEAR) %>년
		<%=cal.get(java.util.Calendar.MONTH)+1 %>월
		<%= cal.get(java.util.Calendar.DATE)%>일 입니다.

</body>
</html>