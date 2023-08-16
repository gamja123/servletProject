<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.boardone.BoardDAO" %>
    <%@ page import="java.sql.*" %>
    <%request.setCharacterEncoding("utf-8"); %>
	
	<%
	int num = Integer.parseInt(request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");
    String pass = request.getParameter("pass");
    BoardDAO dbPro = BoardDAO.getInstance();
    int check = dbPro.deleteArticle(num, pass);
    
    if(check==1){//삭제 성공
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
<title></title>
</head>
<body>

	<%}else{//삭제 실패 %>
		<script type="text/javascript">
		alert("비밀번호가 틀렸습니다.");
		history.go(-1);
		</script>
	<%} %>
	
</body>
</html>