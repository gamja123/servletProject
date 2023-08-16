<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.awt.Graphics2D" %>
    <%@page import="java.awt.image.renderable.ParameterBlock" %><%--압축파일을 여기에 저장한다고..? --%>
    <%@page import="java.awt.image.BufferedImage" %>
    
    <%@page import="javax.media.jai.JAI" %>
    <%@page import="javax.media.jai.RenderedOp" %>
    <%@page import="javax.imageio.ImageIO" %>
    
    <%@ page import="com.oreilly.servlet.MultipartRequest" %>
	<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
	<%@ page import="java.util.*,java.io.*" %>    
	
	<%
	
	String imagePath= request.getRealPath("upload");
	int size = 1*1024*1024;
	String filename = "";
	
	try{
		
		MultipartRequest multi = new MultipartRequest(request, imagePath, size, "utf-8", new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		
		while(files.hasMoreElements()){
			
			String file =(String) files.nextElement();
			filename = multi.getFilesystemName(file);
			
		}
		
	}catch(Exception e){e.printStackTrace();}
	
	ParameterBlock pb = new ParameterBlock();
	pb.add(imagePath+"/"+filename);
	//codec에서 이미지 읽어들이는 연산자 제공해줌 rendrerd bufferd에 임시 저장 했다가 랜더링해서 읽얻ㄹ임??
	
	RenderedOp rOp = JAI.create("fileload", pb);//파라미터 블록에 추가되어 있는 거로 파일을 생성..? 한걸 버퍼에 저장
	
	BufferedImage bi = rOp.getAsBufferedImage();
	
	BufferedImage thumb  = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);//가로세로 크기
	
	Graphics2D g = thumb.createGraphics();//썸네일 만든 거 g에 저장
	g.drawImage(bi, 0, 0, 100, 100, null);
	
	File file = new File(imagePath+"/sm_"+filename);
	ImageIO.write(thumb, "png", file);
	
	%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
-원본이미지- <br>
<img alt="" src="/upload/<%=filename%>">
<p>
-썸네일이미지- <br>
<img alt="" src="/upload/sm_<%=filename%>">
</body>
</html>