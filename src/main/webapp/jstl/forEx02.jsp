<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.jstl.MemberVO,java.util.ArrayList" %>
    
    <%
    MemberVO vo1 = new MemberVO("손오공","son@naver.com",500);
    MemberVO vo2 = new MemberVO("사오정","sa@naver.com",370);
    MemberVO vo3 = new MemberVO("저팔계","pal@naver.com",100);
    
    ArrayList<MemberVO> memberList = new ArrayList<>();
    
    memberList.add(vo1);
    memberList.add(vo2);
    memberList.add(vo3);
    
    request.setAttribute("memberList", memberList);
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<h3>회원 정보</h3>
<table width="450" border="1">

	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>메일</th>
		<th>나이</th>
	</tr>
	<%--  varStatus : 루프 정보를 담는 객체를 저장할 변수명을 값으로 갖는 속성 
	varStatus 속성에 명시한 변수를 이용해서 현재 처리 중인 인덱스를 구할 수 있다 begin ,end
	--%>
	
	<c:forEach var="member" items="${memberList}" varStatus="num">
	<tr>
		<td align="center">${num.count}</td>
		<td align="center">${member.name}</td>
		<td align="center">${member.email}</td>
		<td align="center">${member.age}</td>
	</tr>
	</c:forEach>
	
	
	

</table>




</body>
</html>