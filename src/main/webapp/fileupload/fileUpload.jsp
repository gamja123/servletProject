<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.oreilly.servlet.MultipartRequest" %><%--파일 업로드 처리 클래스 --%>       
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %><%--파일 중복 처리 클래스 --%>       
<%@ page import="java.util.*" %><%--enu --%>       

<%
String uploadPath = request.getRealPath("upload");//서버에 저장된 파일들의 폴더경로
int size = 10*1024*1024;//10mb kb*kb

//폼에 있는 파라미터 받을 변수
String name = "";
String subject ="";
String filename1 = "";
String filename2 = "";
String orgifilename1 = "";
String orgifilename2 = "";

try{

	//업로드 처리 객체 생성 
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
	
	name = multi.getParameter("name");
	subject = multi.getParameter("subject");
	
	Enumeration files = multi.getFileNames();//업로드 폼에서 전송된 file type의 파라미터를 Enumeration 타입으로 반환처리 함
	
	String file1 =(String) files.nextElement();//Enumeration에 요소 있는지 없는지 확인
	filename1 = multi.getFilesystemName(file1);//클라이언트(사용자)가 업로드한 파일을 실제적으로 업로드된 이름을 반환한다 폼을 통해
	orgifilename1 = multi.getOriginalFileName(file1);//클라이언트(사용자)가 업로드한 파일의 원래 이름을 반환한다 하드디스크에 저장된
	
	String file2 =(String) files.nextElement();
	filename2 = multi.getFilesystemName(file2);
	orgifilename2 = multi.getOriginalFileName(file2);
	
}catch(Exception e){
	e.printStackTrace();
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form action="fileCheck.jsp" method="post" name="filecheck">

<input type="hidden" name="name" value="<%=name%>">
<input type="hidden" name="subject" value="<%=subject%>">
<input type="hidden" name="filename1" value="<%=filename1%>">
<input type="hidden" name="filename2" value="<%=filename2%>">
<input type="hidden" name="orgifilename1" value="<%=orgifilename1%>">
<input type="hidden" name="orgifilename2" value="<%=orgifilename2%>">

</form>
<a href="#" onclick="javascript:filecheck.submit()">업로드 확인 페이지</a>
<%--업로드 한 거 페이지에서 확인 --%>

</body>
</html>