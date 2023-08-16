<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
    <jsp:useBean id="vo" class="com.jstl.MemberVO" />
    
    <%--targer 값을 설정할 객체를 의미
    property 프로퍼티 이름을 지정 
    value 프로퍼리 값을 지정 
    var 변수 지정 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%-- <%String name="홍길동";
	vo.setName(name);//이랑 똑같은거여..?
		%> --%>
<c:set property="name" value="홍길동" target="${vo }" />

<c:set property="email" target="${vo }">hong@naver.com</c:set>

<c:set var="age" value="500" />
<c:set property="age" value="${age }" target="${vo }" />

<h3>회원 정보</h3>
<ul>
	<li>이름: ${vo.name }</li>
	<li>메일: ${vo.email}</li>
	<li>나이: ${vo.age }</li>
</ul>



</body>
</html>