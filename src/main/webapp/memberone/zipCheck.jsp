<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.memberone.*,java.util.*" %>
<jsp:useBean id="dao" class="com.memberone.StudentDAO" />
<%

request.setCharacterEncoding("utf-8");

String dong = request.getParameter("dong");
String check = request.getParameter("check");

Vector<ZipCodeVO> zipcodeList = dao.zipcodeRead(dong);

int totalList = zipcodeList.size();


%>


  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<link type="text/css" rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">

<div align="center">

<b>우편번호 찾기</b>

<form action="zipCheck.jsp" method="post" name="zipForm">

	<table>
		<tr>
			<td>동이름 입력 : <input type="text" name="dong"><!-- dongㄱ져오기 -->
			<input type="button" value="검색" onclick="dongCheck()">
			</td>
		</tr>
	</table>
	<input type="hidden" name="check" value="n">
</form>


	<table>
		<%
		if(check.equals("n")){
			if(zipcodeList.isEmpty()){//비었다
				%>
		<tr><td align="center"><br>검색된 결과가 없습니다.</td></tr>		
		
		<%}else{ //결과 없을 때%>
		
		<tr><td align="center"><br>※검색 후, 아래 우편 번호를 클릭하면 자동을 입력 됩니다.</td></tr>		
		
		<%
			for(int i = 0;i<totalList;i++){
				ZipCodeVO vo = zipcodeList.elementAt(i);
				String tempZipcode =vo.getZipcode();
				String tempSido =vo.getSido();
				String tempGugun =vo.getGugun();
				String tempDong =vo.getDong();
				String tempRi =vo.getRi();
				String tempBunji =vo.getBunji();
				
			
				if(tempRi==null) tempRi="";
				if(tempBunji==null) tempBunji="";
				
			%>
		<tr><td>
			<a href="javascript:sendAddress('<%=tempZipcode%>','<%=tempSido%>','<%=tempGugun%>','<%=tempDong%>','<%=tempRi%>','<%=tempBunji%>')">
			<%=tempZipcode%>&nbsp;<%=tempSido%>&nbsp;<%=tempGugun%>&nbsp;<%=tempDong%>&nbsp;<%=tempRi%>&nbsp;<%=tempBunji%>&nbsp;			
			</a><br>
		
		<%
					}//end for		
				}//enf else
			}//end if
		%>
		</td></tr>
		
		<tr>
			<td align="center"><a href="javascript:this.close()">닫기</a></td>
		</tr>
	</table>
	
</div>



</body>
</html>