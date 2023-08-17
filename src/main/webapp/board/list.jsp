<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
   <%@ include file="view/color.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c }">

<div align="center"><b>글 목록 (전체 글 : ${count})</b>

<table width="700">
	<tr>
		<td align="left" bgcolor="${value_c }">
			<a href="/board/list.bdo">전체목록</a>
		</td>
		<td align="right" bgcolor="${value_c }">
			<a href="/board/writeForm.bdo">글쓰기</a>
		</td>
	</tr>
</table>

<c:if test="${count ==0 }">

<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>

</c:if>


<c:if test="${count >0 }">

<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
	<tr height="30" bgcolor="${value_c }">
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성일</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">IP</td>
	</tr>
	
	<c:forEach var="article" items="${articleList}">
	<tr height="30">
		<td width="50" align="center">
			<c:out value="${number }" /><c:set var="number" value="${ number-1}" />
		</td>
		
		<td width="250">
		
		<c:if test="${article.depth > 0 }">
			<img alt="" src="img/level.gif" width="${5 * article.depth }" height="16">
			<img alt="" src="img/re.gif">
		</c:if>
	
	
		<c:if test="${article.depth == 0 }">
			<img alt="" src="img/level.gif" width="${5 * article.depth }" height="16">
		</c:if>
		
		
		<a href="/board/content.bdo?num=${article.num}&pageNum=${currentPage}" >
			${article.subject }
		</a>
		
		<c:if test="${article.readcount > 5 }">
			<img alt="" src="img/hot.gif" border="0" height="16">
		</c:if>
	</td>
		
		<td align="center" width="100">
			<a href="mailto:${article.email }">
			${article.writer}</a>
		</td>
		
		<td align="center" width="150">
			${article.regdate }
		</td>
		
		<td align="center" width="50">
			${article.readcount }
		</td>
		
		<td align="center" width="100">
			${article.ip}
		</td>
		
	</tr>
	</c:forEach>

</table>
</c:if>

<%--페이징 처리 --%>

<c:if test="${count > 0 }">
	<c:set var="imsi" value="${count % pageSize ==0 ? 0 : 1 }" />
	<c:set var="pageCount" value="${count / pageSize + imsi }" />
	<c:set var="pageBlock" value="${4 }"></c:set>
	
	<fmt:parseNumber var="result" value="${(currentPage -1) / pageBlock }" integerOnly="true" />
	
	<c:set var="startPage" value="${result * pageBlock +1 }"></c:set>
	<c:set var="endPage" value="${startPage + pageBlock -1 }"></c:set>
	
	<c:if test="${endPage > pageCount }">
		<c:set var="endPage" value="${pageCount }"></c:set>
	</c:if>
	
	<c:if test="${startPage > pageBlock }">
		<%-- <a href="/board/list.bdo?pageNum=${startPage-pageBlock }">[이전]</a> --%>
		<a href="#" onclick="frm_sub(${startPage-pageBlock})">[이전]</a> 
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<%-- <a href="/board/list.bdo?pageNum=${i }">[${i }]</a> --%>		
		<a href="#" onclick="frm_sub(${i})">[${i}]</a> 
	</c:forEach>
	
	<c:if test="${endPage < pageCount }">
		<%-- <a href="/board/list.bdo?pageNum=${startPage+pageBlock }">[다음]</a> --%>
		<a href="#" onclick="frm_sub(${startPage+pageBlock})">[다음]</a> 
	</c:if>
	
</c:if>

<br><br>

<form action="" method="post" name="i_frm">
	<input type="hidden" name="find_box" value="${find_box }">
	<input type="hidden" name="find" value="${find }">
</form>







<%--검색창폼 --%>
<!-- <form action="searchList.jsp" method="post"> -->
<form action="/board/list.bdo" method="post" name="find_frm" onsubmit="return check()">
<select name="find" size="1">
	<option value="writer">작성자</option>
	<option value="subject">제목</option>
	<option value="content">내용</option>
</select>&nbsp;
<input type="text" name="find_box">&nbsp;
<input type="submit" value="검색">
</form>

</div>
</body>
</html>