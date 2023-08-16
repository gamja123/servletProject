<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
    <%@ page import="com.boardone.BoardDAO" %>
    <%@ page import="com.boardone.BoardVO" %>
    <%@ page import="java.util.*" %>
    <%@ page import="java.text.*" %>
	<%@ include file="view/color.jsp" %>
	
	<%!
	//한 페이지에 보여줄 게시물 수를 지정한다
	int pageSize = 5;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	%>
	
	<%
	String pageNum = request.getParameter("pageNum");
	//무엇으로 검색할 지 작성자 제목 내용
	String searchField = request.getParameter("searchField");
	//검색할 내용
	String searchText = request.getParameter("searchText");
	
	//파라미터로 받아온 값을 한글로 변환처리
	if(searchText !=null){
		searchText = new String(searchText.getBytes("utf-8"),"utf-8");
	}
	
	
	if(pageNum ==null){
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);
	//시작행
	int startRow = (currentPage-1) * pageSize +1;
	//마지막행
	int endRow = currentPage * pageSize;
	
	int count = 0;
	int number = 0;
	
	List<BoardVO> articleList=null;
	
	BoardDAO dbPro = BoardDAO.getInstance();
	
	//검색이 아니면 전체 글 목록을 보여주고 검색이면 검색된 내용을 보여줘야됨
	if(searchText ==null){
		count = dbPro.getArticleCount();//전체 글 수
			if(count >0){
				articleList = dbPro.getArticles(startRow,endRow);
			}
	}else {//검색이면
		count = dbPro.getArticleCount(searchField, searchText);
			if(count >0){
				articleList = dbPro.getArticles(startRow,endRow,searchField, searchText);
			}
	}
	
	number = count - (currentPage-1) * pageSize;
	
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="<%=bodyback_c%>">

<div align="center"><b>글 목록 (전체 글 : <%=count %>)</b>

<table width="700">
	<tr>
		<td align="right" bgcolor="<%=value_c%>">
			<a href="writeForm.jsp">글쓰기</a>
		</td>
	</tr>
</table>

<%if(count ==0){//글이 없을 때%>

<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>

<%}else{//글이 있을 때%>

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
	for(int i=0;i<articleList.size();i++){
		BoardVO article =(BoardVO) articleList.get(i);
	%>
	<tr height="30">
		<td><%=number-- %></td>
		
		<td width="250">
		<%
		int wid = 0;
		
		if(article.getDepth() >0){//답변글 달리면
		wid = 5* (article.getDepth());
		%>
		
		<img alt="" src="img/level.gif" width="<%=wid%>" height="16">
		<img alt="" src="img/re.gif">
		
		<%}else{ %>
		<img alt="" src="img/level.gif" width="<%=wid%>" height="16">
		
		<%} %>
		
		<a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>" >
		<%=article.getSubject() %>
		</a>
		<%if(article.getReadcount()>=5){ //조회수가 5 이상 일 때 이미지 나옴%>
		
		<img alt="" src="img/hot.gif" border="0" height="16">
		
		<%} %>
		
		
		</td>
		
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
	<%
	}
	%>




</table>
<%} %>

<%--페이징 처리 --%>

<%
if(count > 0){//6
	
	int pageBlock = 4;//3
	int imsi = count % pageSize ==0 ? 0 : 1;//1일 때는 다음페이지로 0일 때는 딱 떨어짐
	int pageCount = count / pageSize + imsi;// 6 / 2 + 0 =3
	
	int startPage =(int) ((currentPage -1) / pageBlock) * pageBlock  + 1;
	int endPage = startPage + pageBlock - 1;
	
	if(endPage > pageCount) endPage = pageCount;
	if(startPage > pageBlock) {//이전페이지
		//검색일 때와 검색이 아닐때의 페이징 처리
		if(searchText ==null){
		
	%>
	<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
	
<%}else{//검색일 때
	
%>
	<a href="list.jsp?pageNum=<%=startPage-pageBlock%>&searchField=<%=searchField%>&searchText=<%=searchText%>">[이전]</a>
	
<%}
	
	}
	for(int i =startPage; i<=endPage; i++){
		//검색일 경우와 아닐 경우
		if(searchText==null){
%>		
	<a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>		
	<%}else{//검색일 경우 %>
	<a href="list.jsp?pageNum=<%=i%>&searchField=<%=searchField%>&searchText=<%=searchText%>">[<%=i %>]</a>		
	
	
<%}
}
	//검색일 때 아닐 때
	if(endPage < pageCount){
		if(searchText==null){
		%>
		<a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
		<%}else{ %>
		<a href="list.jsp?pageNum=<%=startPage+pageBlock%>&searchField=<%=searchField%>&searchText=<%=searchText%>">[다음]</a>
	<%}
		}
	} %>


<%--검색창폼 --%>
<!-- <form action="searchList.jsp" method="post"> -->
<form action="list.jsp" method="post">
<select name="searchField">
	<option value="writer">작성자</option>
	<option value="subject">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" name="searchText">
<input type="submit" value="검색">
</form>

</div>
</body>
</html>