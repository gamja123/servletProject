<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(HttpServletResponse.SC_OK);%>
<!-- 요청이 성공적으로 이루어졌다 202 브라우저를 응답구조로 받음 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외처리</title>
</head>
<body>
요청 처리 과정에서 예외가 발생하였습니다.<br><br>
빠른 시간 내에 문제를 해결하도록 하겠습니다.<br><br>
이용자 여러분들의 ~~~~ 드림
<br><br>
에러 타입: <%=exception.getClass().getName() %><br>
에러 메세지:<b><%=exception.getMessage()%></b>
</body>
</html>