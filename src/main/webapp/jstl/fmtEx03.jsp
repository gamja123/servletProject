<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

Number :
<fmt:formatNumber value="12345.678" type="number" /><br><br>

currency :
<fmt:formatNumber value="12345.678" type="currency" currencySymbol="￦" /><br><br>

percent :
<fmt:formatNumber value="12345.678" type="percent" /><br><br>

pattern : ".0" :
<fmt:formatNumber value="12345.678" pattern=".0" /><br><br>

<c:set var="now" value="<%=new java.util.Date() %>" />

<c:out value="${now }"></c:out><br><br>

date : <fmt:formatDate value="${now }"  type="date" ></fmt:formatDate><br><br>
time : <fmt:formatDate value="${now }"  type="time" ></fmt:formatDate><br><br>
both : <fmt:formatDate value="${now }"  type="both" ></fmt:formatDate><br><br>



</body>
</html>





