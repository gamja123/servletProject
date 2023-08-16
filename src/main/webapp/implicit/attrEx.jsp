<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%-- 속성(attribute)은 PageContext,HttpServletRequest,HttpSession,ServletContext 객체 중
 하나에 설정해놓은 객체를 말한다
 개체 Map 인스턴스와 마찬가지로 이름과 값을 쌍으로 저장한다
 각각의 객체에 저장된 속성들은 서로 다른 생명력(생존범위)이 있다
 
 PageContext:jsp 페이지 내에서만 유효함
 HttpServletRequest: 어플리케이션에서 request에 접근이 가능한 것들
 HttpSession:특정 세션에 접근할 수 있는 jsp/servlet
 ServletContext:웹 어플리케이션 내에 모든 자원에서 사용
 
 ※MVC 웹 어플리케이션에서 Model에서 View쪽으로 데이터를 넘겨줄 때 
 request 범위의 데이터를 저장해서 view측으로 전달한다
 --%>
 
 <%
 //pageContext에 속성값 저장
 pageContext.setAttribute("pageAttribute", "홍길동");
 //pageContext.setAttribute("pageAttribute", "홍길동", PageContext.PAGE_SCOPE);//api에 상수값으로 범위가 정해져있음 거의 생략함
 
 //request 객체에 속성값 저장
 request.setAttribute("requestArrtibute", "010-1234-1234");
 //pageContext.setAttribute("requestArrtibute", "010-1234-1234",PageContext.REQUEST_SCOPE);
 
 //session에 속성값 저장
 session.setAttribute("sessionAttribute","hong@naver.com");
//pageContext.setAttribute("requestArrtibute", "010-1234-1234",PageContext.SESSION_SCOPE);
 
//application 에 속성값 저장
application.setAttribute("applicationAttribute","Global In");
//pageContext.setAttribute("requestArrtibute", "010-1234-1234",PageContext.APPLICATION_SCOPE);

 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<ul>

<li>이름: <%= pageContext.getAttribute("pageAttribute") %></li>
<li>번호: <%= request.getAttribute("requestAttribute") %></li>
<li>메일: <%= session.getAttribute("sessionAttribute") %></li>
<li>회사: <%= application.getAttribute("applicationAttribute") %></li>

</ul>

<%-- <% request.getContextPath()/foward/view.jsp %> --%>










</body>
</html>