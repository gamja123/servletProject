<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- html 주석 -->
	<h2> JSP Script </h2>
	
<%-- 처리부 --%>
	<% //script 연산 처리( 자바 주석)
			/* 자바 주석 */
		String scriptlet = "스크립트릿 입니다";
		String comment = "주석문입니다";
		out.println("내장 객체를 이용한 출력"+declation+"<br><br>");
	%>
<%--jsp 주석 jsp 는 out객체 있음--%>


<%--선언 부 --%>
<%! //변수 선언
	String declation = "선언문 입니다";
%>


<%! //메소드 선언

public String declationMethod(){
	return declation;
}

%>

<%--외부에 내보내기 --%>
선언문 출력하기(변수) : <%=declation %><br><br>
선언문 출력하기(메소드) : <%=declationMethod() %><br><br>
스크립트릿 출력:<%= scriptlet %><br><br>
<!-- html 주석:<%=comment %> --><br><br>
<%-- -- jsp 주석:<%=comment %> --%><br><br>



</body>
</html>