<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="com.memberone.StudentDAO" />
<jsp:useBean id="vo" class="com.memberone.StudentVO" />

<jsp:setProperty property="*" name="vo"/>

<%
	boolean flag = dao.memberInsert(vo);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 확인</title>
</head>
<body bgcolor="#FFFFCC">
<br><br>
<div align="center">
<%

if(flag){//회원가입 성공

	out.println("<b>회원가입을 진심으로 축하드립니다.</b><br>");
	out.println("<a href=login.jsp>로그인</a>");
	
}else{//회원가입 실패

	out.println("<b>회원가입에 실패 하셨습니다. 다시 한 번 입력 해 주세요.</b><br>");
	out.println("<a href=regForm.jsp>다시 가입</a>");
	

}

%>

</div>

</body>
</html>