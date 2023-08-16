<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Board</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>

<%--새 글인지 답변글인지 구분하기 --%>
<%
//새 글일 경우 번호가 0번 , 답변글일 때는 내가 쓰고자 하는 번호를 가져와서 그 번호에 답변글을 씀
//ref 부모의 글번호를 참조한다 답변이 또 달리는 step 계속 달리는 거 depth깊이
int num = 0,ref = 1,step=0,depth=0;

try{
if(request.getParameter("num") != null){//답변글 일 때
	
	num =Integer.parseInt(request.getParameter("num"));
	ref =Integer.parseInt(request.getParameter("ref"));
	step =Integer.parseInt(request.getParameter("step"));
	depth =Integer.parseInt(request.getParameter("depth"));
	
}//end if writeProc에 이 값들 히든으로 넘겨줘야됨>? 파라미터라서 인풋태그 필요함

%>

<body bgcolor="<%=bodyback_c%>">

<div align="center"><b>글쓰기</b></div><br><br>
<form action="writeProc.jsp" name="writeForm" method="post" onsubmit="return writeSave()">

<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="ref" value="<%=ref%>">
<input type="hidden" name="step" value="<%=step%>">
<input type="hidden" name="depth" value="<%=depth%>">


<table width="400" border="1" cellpadding="0" cellsapcing="0" align="center" bgcolor="<%=bodyback_c%>">

	<tr>
		<td align="right" colspan="2" bgcolor="<%=value_c%>">
		<a href="list.jsp">글목록</a>
		</td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">이름</td>
		<td width="330">
		<input type="text" size="12" maxlength="12" name="writer"> 
		</td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">이메일</td>
		<td width="330">
		<input type="text" size="40" maxlength="40" name="email"> 
		</td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">제목</td>
		<td width="330">
		<% if(request.getParameter("num") ==null){//새글일 경우%>
		<input type="text" size="50" maxlength="50" name="subject"> 
		<%}else { //답변글 일 경우%>
		<input type="text" size="50" maxlength="50" name="subject" value="[답변글]"> 
		<%} %>
		</td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">내용</td>
		<td width="330">
			<textarea rows="13" cols="50" name="content"></textarea>
		</td>
	</tr>
	
	<tr>
		<td width="70" bgcolor="<%=value_c%>" align="center">비밀번호</td>
		<td width="330">
		<input type="password" size="10" maxlength="10" name="pass"> 
		</td>
	</tr>
	
	<tr>
		<td colspan="2" bgcolor="<%=value_c%>" align="center">
			<input type="submit" value="글쓰기" >
			<input type="reset" value="다시작성" >
			<input type="button" value="글목록" onclick="window.location='list.jsp'">
		</td>
	
	</tr>
	
</table>


</form>
<%} catch(Exception e){
	
	e.printStackTrace();
	
}

%>
</body>
</html>