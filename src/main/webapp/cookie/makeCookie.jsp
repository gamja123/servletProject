<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.net.URLEncoder" %><%--암호화 압축 --%>
    <%
    Cookie cookie = new Cookie("name",URLEncoder.encode("홍길동","utf-8"));
    cookie.setMaxAge(60);//초단위
    response.addCookie(cookie);
    
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 생성</title>
</head>
<body>
<%=cookie.getName()%> 쿠키의 값 = <%=cookie.getValue()%>
</body>
</html>