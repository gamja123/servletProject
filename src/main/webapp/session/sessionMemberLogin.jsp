<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="color.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션을 사용한 회원 인증</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">

<form action="sessionMemberLoginOk.jsp" method="post">

<table width="300" align="center" border="1">
		<tr>	
			<td align="center" colspan="2" bgcolor="<%=title_c%>">회원 로그인</td>
		</tr>
		
		<tr>
			<td align="right" width="100" bgcolor="<%=title_c%>">아이디</td>
			<td width="200" bgcolor="<%=value_c%>">&nbsp;&nbsp;
			<input type="text" name="id" size="20">
			</td>
		</tr>
		
		<tr>
			<td align="right" width="100" bgcolor="<%=title_c%>">비밀번호</td>
			<td width="200" bgcolor="<%=value_c%>">&nbsp;&nbsp;
			<input type="password" name="passwd" size="20">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center" bgcolor="<%=bodyback_c%>">
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="다시작성" >
			</td>
		</tr>
</table>

</form>
</body>
</html>