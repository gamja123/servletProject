<%@page import="java.util.Vector"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,com.jdbc.*" %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="style.css">
<!-- <style type="text/css">@import url("style.css");</style> -->
</head>
<body>

<h2>JSP에서 DBCP를 이용한 데이터 베이스 연동</h2>
<h3>회원 정보</h3>

<table bordercolor="#0000ff" border="1">

	<tr>
		<td><strong>ID</strong></td>
		<td><strong>passwd</strong></td>
		<td><strong>name</strong></td>
		<td><strong>mem_num1</strong></td>
		<td><strong>mem_num2</strong></td>
		<td><strong>e_mail</strong></td>
		<td><strong>phone</strong></td>
		<td><strong>zipcode/address</strong></td>
		<td><strong>job</strong></td>
	</tr>

<jsp:useBean id="dao" class="com.jdbc.TempMemberDAO"/>

<%
	Vector<TempMemberVO> vecList = dao.getMemberList();

	int counter = vecList.size();//행 몇개냐
	
	for(int i = 0;i<vecList.size();i++){
		TempMemberVO vo = vecList.elementAt(i);
%>



<tr>
	<td><%=vo.getId()%></td>
	<td><%=vo.getPasswd() %></td>
	<td><%=vo.getName() %></td>
	<td><%=vo.getMem_num1() %></td>
	<td><%=vo.getMem_num2()%></td>
	<td><%=vo.getE_mail() %></td>
	<td><%=vo.getPhone() %></td>
	<td><%=vo.getZipcode()%>/<%=vo.getAddress() %></td>
	<td><%=vo.getJob() %></td>
</tr>


<%
}//end for
%>


</table><br><br>
total records : <%=counter %>


</body>
</html>