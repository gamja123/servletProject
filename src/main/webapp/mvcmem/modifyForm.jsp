<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>
<link type="text/css" rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>

<body>

<form action="member.mdo?cmd=modifyProc" method="post" name="regForm">

<table border="1">
		<tr>	
			<td align="center" colspan="2">회원 정보 수정</td>
		</tr>

		<tr>
			<td align="right">아이디</td>
			<td>
				<input type="hidden" name="id" value="${id}">
				<c:out value="${id }" />
			</td>
		</tr>
		
		<tr>
			<td align="right">비밀번호</td>
			<td>
			<input type="password" name="pass" value="${pass }">
			</td>
		</tr>
		
		<tr>
			<td align="right">비밀번호 확인</td>
			<td>
			<input type="password" name="repass" value="${pass }">
			</td>
		</tr>
		
		<tr>
			<td align="right">이름</td>
			<td>
				<input type="hidden" name="name" value="${name}">
				<c:out value="${name}" />
			</td>
		</tr>
		
		<tr>
			<td align="right">전화번호</td>
			<td>
				<select name="phone1">
					<option value="02" ${phone1 eq '02' ? "selected='selected'" : 'null' }>02</option>
					<option value="033" ${phone1 eq '033' ? "selected='selected'" : 'null' }>033</option>
					<option value="010" ${phone1 eq '010' ? "selected='selected'" : 'null' }>010</option>
				</select>&nbsp;-&nbsp;
				<input type="text" name="phone2" size="5" value="${phone2 }">-
				<input type="text" name="phone3" size="5" value="${phone3 }">
			</td>
		</tr>
		
		<tr>
			<td align="right">이메일</td>
			<td>
			<input type="text" name="email" value="${email }">
			</td>
		</tr>
		
		<tr>
			<td align="right">우편번호</td>
			<td>
			<input type="text" name="zipcode" value="${zipcode }">&nbsp;
			<input type="button" value="찾기" onclick="zipCheck()" >
			</td>
		</tr>
		
		<tr>
			<td align="right">주소</td>
			<td>
			<input type="text" name="address1" size="80" value="${address1 }">
			</td>
		</tr>
		
		<tr>
			<td align="right">상세주소</td>
			<td>
			<input type="text" name="address2" size="50" value="${address2 }">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="정보수정"
				 onclick="updateCheck()">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="javascript:window.location='member.mdo?cmd=login'">
			</td>
		</tr>
		
		
</table>

</form>

</body>
</html>