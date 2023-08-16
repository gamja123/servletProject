<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2>include 지시어를 포함한 실습</h2>

<%
String name = "Gil Dong Hong";
%>

<%@ include file="top.jsp" %><%--다른 폴더에 있을 경우 경로 잡아주기 aa/top.jsp --%>
포함하는 페이지 지시어(include) 실습 내용
<%--  <%@ include file="bottom.jsp" %>  --%>
</body>
</html>