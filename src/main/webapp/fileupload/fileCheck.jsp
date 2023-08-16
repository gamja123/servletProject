<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    request.setCharacterEncoding("utf-8");
    
    String name = request.getParameter("name");
    String subject = request.getParameter("subject");
    String filename1 = request.getParameter("filename1");
    String filename2 = request.getParameter("filename2");
    String orgifilename1 = request.getParameter("orgifilename1");
    String orgifilename2 = request.getParameter("orgifilename2");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

올린 사람 : <%=name%><br>
제목 : <%=subject%><br>
파일명 1 : <a href="/upload/<%=filename1%>"><%=orgifilename1%></a>
<br>
파일명 2 : <a  href="/upload/<%=filename2%>"><%=orgifilename2%></a>

</body>
</html>