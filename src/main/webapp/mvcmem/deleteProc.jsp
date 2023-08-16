<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Refresh" content="3;url=memeber.mdo?cmd=login" >
<title>회원 탈퇴</title>
</head>
<body>
<c:set var="check" value="${requestScope.check }" />

		<div align="center">
			<font size="5" face="궁서체">
			회원 탈퇴가 완료 되었습니다.<br><br>
			안녕히 가십시오.<br><br>
			다음에 다시 만날 날을 기약합니다.<br><br>
			3초 후 로그인 페이지로 이동합니다.
			</font>
		</div>
	
	<c:if test="${check eq 0 }">
	<script type="text/javascript">
	alert("비밀번호가 틀렸습니다.");
	history.go(-1);
	</script>
	</c:if>
	




</body>
</html>