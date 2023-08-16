<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setAttribute("name", "홍길동");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
요청 URI : ${pageContext.request.requestURI}<br><br>
request의 name 속성: ${requestScope.name}<br><br><%--pageContext에 속성값 저장..? --%>
code 파라미터 : ${param.code }<br><br> 
</body>
</html>