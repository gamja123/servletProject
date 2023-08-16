<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page  session="true"%>
    <%@ page import="java.util.*, java.text.*" %>
    
    <%Date time = new Date();
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    //세션에 타임 아웃 설정
    session.setMaxInactiveInterval(60);//초단위
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보</title>
</head>
<body>
세션 ID :<%=session.getId() %><br>
<%
time.setTime(session.getCreationTime());
%>
세션의 생성 시간 :<%=formatter.format(time)%><br>
<%
time.setTime(session.getLastAccessedTime());//로그아웃 되기 전 까지의 시간 저장되어있음
%>
최종 접근 시간 : <%=formatter.format(time) %><br>


</body>
</html>