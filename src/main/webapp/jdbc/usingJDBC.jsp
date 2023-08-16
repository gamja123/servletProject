<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%

Class.forName("oracle.jdbc.driver.OracleDriver");

//드라이버 로딩
Connection con = null;
Statement stmt = null;
ResultSet rs = null;

	String 	id = "" ,
			passwd ="",
			name="",
			mem_num1="",
			mem_num2="",
			e_mail="",
			phone="",
			zipcode="",
			address="" ,
			job="";

int counter = 0;

try{
	
	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
	stmt = con.createStatement();
	rs = stmt.executeQuery("select * from tempmember");//조회 커리

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="style.css">
<!-- <style type="text/css">@import url("style.css");</style> -->
</head>
<body>

<h2>JSP 스크립트릿에서 데이터 베이스 연동</h2>
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

<%

if(rs!=null){
	
	while(rs.next()){
		id = rs.getString("id");
		passwd = rs.getString("passwd");
		name = rs.getString("name");
		mem_num1 = rs.getString("mem_num1");
		mem_num2 = rs.getString("mem_num2");
		e_mail = rs.getString("e_mail");
		phone = rs.getString("phone");
		zipcode = rs.getString("zipcode");
		address = rs.getString("address");
		job = rs.getString("job");
%>

<tr>
	<td><%=id %></td>
	<td><%=passwd %></td>
	<td><%=name %></td>
	<td><%=mem_num1 %></td>
	<td><%=mem_num2%></td>
	<td><%=e_mail %></td>
	<td><%=phone %></td>
	<td><%=zipcode%>/<%=address %></td>
	<td><%=job %></td>
</tr>


<%
		counter++;
	}//end while
}//end if
%>


</table><br><br>
total records : <%=counter %>

<%
}catch(SQLException ss){
	System.out.println("sql Exception");
}catch(Exception e){
	System.out.println("Exception");
}finally{
	
	if(rs != null){
		try{
			rs.close();
		}catch(SQLException s){}
	}
	
	if(stmt != null){
		try{
			stmt.close();
		}catch(SQLException s){}
	}
	
	if(con != null){
		try{
			con.close();
		}catch(SQLException s){}
	}

}
%>
</body>
</html>