<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String memId =(String) session.getAttribute("MemID");
    String name =(String) session.getAttribute("NAME");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
세션 정보를 불러왔습니다.<br>
Member ID : <%=memId%><br>
Name : <%=name %>
</body>
</html>