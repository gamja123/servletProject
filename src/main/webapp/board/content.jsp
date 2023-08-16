<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="view/color.jsp" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>


<body bgcolor="${bodyback_c }">

<div align="center"><b>글 상세 보기</b><br><br>
<form action="">

<table width="500" border="1" cellpadding="0" cellspacing="0" align="center" align="center" bgcolor="${bodyback_c }">

<tr height="30">
	<td align="center" width="125" bgcolor="${value_c }">글 번호</td>
	<td align="center" width="125" >
			${article.num }
	</td>

	<td align="center" width="125" bgcolor="${value_c }">조회수</td>
	<td align="center" width="125" >
			${article.readcount }
	</td>
</tr>

<tr height="30">
	<td align="center" width="125" bgcolor="${value_c }">작성자</td>
	<td align="center" width="125" >
			${article.writer }
	</td>
	
	<td align="center" width="125" bgcolor="${value_c }">작성일</td>
	<td align="center" width="125" >
			${ article.regdate}
	</td>
	
</tr>

<tr height="30">
	<td align="center" width="125" bgcolor="${value_c }">글제목</td>
	<td align="center" width="375" colspan="3">
			${article.subject }
	</td>
</tr>

<tr height="30">
	<td align="center" width="125" bgcolor="${value_c }">글내용</td>
	<td align="center" width="375" colspan="3">
		<pre>${ article.content}</pre>
	</td>
</tr>

<tr height="30">
	<td align="center" bgcolor="${value_c }" colspan="4">
		
		<input type="button" value="글수정" onclick="document.location.href='/board/updateForm.bdo?num=${article.num }&pageNum=${pageNum }'">
		&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="button" value="글삭제" onclick="document.location.href='/board/deleteForm.bdo?num=${article.num }&pageNum=${pageNum }'">
		&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="button" value="답변글쓰기" onclick="document.location.href='/board/writeForm.bdo?num=${article.num }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">
		&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="button" value="글목록" onclick="document.location.href='/board/list.bdo?pageNum=${pageNum }'">
		&nbsp;&nbsp;&nbsp;&nbsp;
		
	</td>
</tr>

</table>

</form>
</div>

</body>
</html>