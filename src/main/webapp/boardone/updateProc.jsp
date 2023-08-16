<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.boardone.BoardDAO" %>
    <%@ page import="java.sql.*" %>
    
    <%request.setCharacterEncoding("utf-8"); %>
    <jsp:useBean id="article" class="com.boardone.BoardVO" scope="page">
    	<jsp:setProperty property="*" name="article"/>
    </jsp:useBean>
    <%
    String pageNum = request.getParameter("pageNum");
    BoardDAO dbPro = BoardDAO.getInstance();
    
    int check = dbPro.updateArticle(article);
    
    if(check==1){//수정 성공
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
<title></title>
</head>
<body>

	<%}else{//수정 실패 %>
		<script type="text/javascript">
		alert("비밀번호가 틀렸습니다.");
		history.go(-1);
		</script>
	<%} %>
</body>
</html>