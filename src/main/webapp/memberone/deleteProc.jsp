<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.memberone.*" %>
<jsp:useBean id="dao" class="com.memberone.StudentDAO" />

<%
String id = (String) session.getAttribute("loginID");
String pass = request.getParameter("pass");

int check =  dao.deleteMember(id, pass);

if(check == 1){
	session.invalidate();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Refresh" content="3;url=login.jsp" >
<title>회원 탈퇴</title>
</head>
<body>

<div>
<font size="5" face="궁서체">
회원 탈퇴가 완료 되었습니다.<br><br>
안녕히 가십시오.<br><br>
다음에 다시 만날 날을 기약합니다.<br><br>
3초 후 로그인 페이지로 이동합니다.
</font>
</div>

<%}else { %>

<script type="text/javascript">
alert("비밀번호가 틀렸습니다.");
history.go(-1);
</script>

<%} %>


</body>
</html>