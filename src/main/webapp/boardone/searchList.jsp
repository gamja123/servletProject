<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
    <%@ page import="com.boardone.*,java.util.*,java.text.*" %>
    <%@ include file="view/color.jsp"%>
    
    <%
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						BoardDAO dbPro = BoardDAO.getInstance();
					String searchField	=request.getParameter("searchField");
					String searchText	=request.getParameter("searchText");
					//System.out.println(searchField);
					//System.out.println(searchText);
					
						List<BoardVO> articleList = dbPro.getSearch(searchField,searchText);
						int count = 0;
						int number = 0;
						number = count;
						if (articleList.size() == 0) {
							
						%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<div align="center">
<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr >
		<td align="center">
			게시판에 검색된 글이 없습니다.
		</td>
	</tr>
</table>
</div>

<%}else{%>
	<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
	<tr height="30" bgcolor="<%=value_c%>">
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성일</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">IP</td>
	</tr>
<%
for (int i = 0; i < articleList.size(); i++) {
	BoardVO article =(BoardVO) articleList.get(i);
%>
	<tr height="30">
		<td><%=number-- %></td>
		
		<td width="250">
	<tr>
		<td align="center" width="100">
			<a href="mailto:<%=article.getEmail()%>">
			<%=article.getWriter() %></a>
		</td>
		
		<td align="center" width="150">
			<%=sdf.format(article.getRegdate()) %>
		</td>
		
		<td align="center" width="50">
			<%=article.getReadcount()%>
		</td>
		
		<td align="center" width="100">
			<%=article.getIp()%>
		</td>
</tr>
</table>

<%}
} %>
</body>
</html>