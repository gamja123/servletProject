<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소스 보기</title>
</head>
<body>

<%
FileReader reader = null;

try{
	
	String path = request.getParameter("path");
	reader = new FileReader(getServletContext().getRealPath(path));
	

%>

<pre>

소스 코드= <%=path %>

<c:out value="<%=reader %>" escapeXml="true" />
<%-- escapeXml 속성이 true 면 문자열을 변경한다

<    변경--> &lt
>    변경-->  &gt
&    변경-->  &amp;
'	  변경--> &#039
"     변경--> &#034

 --%>
</pre>
<%}catch(IOException ie){
	
	%>

에러:<%=ie.getMessage()%>

<%}finally{
	if(reader!=null){
		try{
			reader.close();
		}catch(IOException i){
			i.printStackTrace();
		}
	}
				}
	%>
</body>
</html>




