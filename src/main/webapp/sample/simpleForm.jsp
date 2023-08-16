<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%--
    <jsp:useBean id="" scope="" class=""> 
   	id:객체 인스턴스를 식별하는 이름
   	scope:객체 참조의 유효 범위(기본:page)
   	class:클래스 이름(패키지명 포함)과 위치
   	
<jsp:setProperty property="프로퍼티명" name="빈 이름(id)" value="저장할 값"/>
-빈의 속성값을 설정하는 태그
name:<jsp:useBean> 태그의 빈 이름(id)
property:값을 설정하고자 하는 빈 속성의 이름
			*:모든 인자들 중 빈 속성과 데이터형이 일치하는 것을 각각의 인자값으로 설정한다
value:빈 속성에 설정할 값을 의미함

<jsp:getProperty property="" name=""/>
-빈의 속성 값을 가져오는 태그
   	
     --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>자바 빈즈</h1>
<hr color="red">
<form action="simpleBean.jsp" method="post">

메시지: <input type="text" name="message">
		<input type="submit" value="전송">


</form>









</body>
</html>