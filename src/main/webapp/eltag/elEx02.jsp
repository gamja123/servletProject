<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.actiontag.Customer" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%
    
    ArrayList<String> singer = new ArrayList<>();
    singer.add("세븐틴");
    singer.add("뉴진스");
    request.setAttribute("singer", singer);
    
    Customer[] customer = new Customer[2];
    
    customer[0] = new Customer();
    
    customer[0].setName("홍길동");
    customer[0].setEmail("hong@naver.com"); 
    customer[0].setPhone("010-1111-2222"); 
    
    customer[1] = new Customer();
    
    customer[1].setName("가길동");
    customer[1].setEmail("ng@naver.com"); 
    customer[1].setPhone("010-1234-1234"); 
    
    request.setAttribute("customer", customer);
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>


<ul>
	<li>${singer[0] }</li>
	<li>${singer[1] }</li>
</ul>

<ul>
	<li>${customer[0].name }</li>
	<li>${customer[0].email }</li>
	<li>${customer[0].phone }</li>
</ul>

<ul>
	<li>${customer[1].name }</li>
	<li>${customer[1].email }</li>
	<li>${customer[1].phone }</li>
</ul>




</body>
</html>