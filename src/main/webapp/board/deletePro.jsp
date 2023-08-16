<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
   
   <c:if test="${check ==1 }">
	<meta charset="UTF-8" http-equiv="Refresh" content="0;url=/board/list.bdo?pageNum=${pageNum }">
   </c:if>
   
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>

	 <c:if test="${check ==0 }">
	 	비밀번호가 틀렸습니다.<br>
		<a href="javascript:history.go(-1)">[글 삭제 폼으로 돌아가기]</a>
	 </c:if>
</body>
</html>