<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초기 파라미터 값 읽기</title>
</head>
<body>
초기 파라미터 목록<br>
<%
	Enumeration enumData = application.getInitParameterNames();
while(enumData.hasMoreElements()){
	String initParamName = (String) enumData.nextElement();
%>

<%=initParamName %> : <%=application.getInitParameter(initParamName) %>
<br>


<%
}
%>
</body>
</html>