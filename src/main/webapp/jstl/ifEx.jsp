<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<c:if test="${3 gt 4 }">
이 내용은 화면에 나타나지 않습니다.
</c:if>

<c:if test="${param.type eq 'guest' }">
나의 홈에 방문을 환영합니다.<br>
회원가입을 하시면 모든 콘텐츠를 이용하실 수 있습니다.<br>
즐거운 시간 되시길 바랍니다.<br>
</c:if>

<c:if test="${param.type eq 'member' }">
회원님의 방문을 환영합니다.<br>
모든 콘텐츠를 이용하실 수 있습니다.<br>
더 나은 서비스로 보답하도록 노력하겠습니다.<br>
즐거운 시간 되시길 바랍니다.<br>
</c:if>



</body>
</html>