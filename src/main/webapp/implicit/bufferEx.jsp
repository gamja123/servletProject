<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //웹 브라우저에 응답을 출력하기 위한 JspWriter객체다-->out
    int bufferSize = out.getBufferSize();//버퍼의 전체크기
    int remainSize = out.getRemaining();//남은 크기 구하는 메소드
    int usedSize = bufferSize - remainSize;//사용한 크기
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

버퍼의 전체 크기:<%= bufferSize %><br>
사용한 버퍼의 크기:<% out.println(usedSize); %><br>
남은 버퍼의 크기:<%=remainSize %><br>
${bufferSize}<br>
</body>
</html>