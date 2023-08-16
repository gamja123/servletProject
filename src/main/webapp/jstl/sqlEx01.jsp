<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="sql"  uri="http://java.sun.com/jsp/jstl/sql"%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>




<sql:setDataSource dataSource="jdbc/myoracle" scope="application" var="ds"/>
<%--db연결 완 --%>

<%-- <sql:setDataSource
url="jdbc:oracle:thin:@localhost:1521:orcl"
driver="oracle.jdbc.driver.OracleDriver"
user="scott"
password="tiger"
scope="application" var="ds"
/> --%>


<sql:query var="rs" dataSource="${ds}">
	select * from tempmember
</sql:query>

<table border="1">
	<tr>
	<%--필드명 출력 --%>
		<c:forEach var="columnName" items="${rs.columnNames}">
		<th><c:out value="${columnName }" /></th>
		</c:forEach>
	</tr>
	<%--레코드의 수 만큼 반복 수행--%>
	<c:forEach var="row" items="${rs.rowsByIndex }"><%--행--%>
	<tr>
	<%--레코드의 필드 수 만큼 반복수행함--%>
	<c:forEach var="column" items="${row}" varStatus="i">
	<td>
	
		<c:if test="${ column != null}">
		<c:out value="${column }"></c:out>
		</c:if>
		
		<c:if test="${ column == null}">
		&nbsp;
		</c:if>
		
	</td>
	</c:forEach>
	</tr>
	</c:forEach>
		
		
		
		
		


</table>













</body>
</html>