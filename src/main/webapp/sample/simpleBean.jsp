
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="com.sample.SimpleData"%>
    
    <%request.setCharacterEncoding("utf-8");
   		/* String message  =request.getParameter("message"); */
    %>
    
 <%-- <%SimpleData msg = new SimpleData(); %>    --%>
 
<%-- <% msg.setMessage(message); %> --%>

<jsp:useBean id="a" class="com.sample.SimpleData" />
<jsp:setProperty property="message" name="a"/> <!-- 폼에서 적은걸 빈으로 넘어오게 하고 저장 -->
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
메시지:<jsp:getProperty property="message" name="a"/>
<%-- <%message %> --%>
<%-- 메시지:<%=msg.getMessage() %> --%>
</body>
</html>