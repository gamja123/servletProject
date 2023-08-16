package com.test;
//login파라미터는 여기서 처리 해야됨
//get방식만 가져옴
import java.io.*;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String passwd  =request.getParameter("passwd");
		
		//System.out.println("아이디:"+userid);
		//System.out.println("비밀번호:"+passwd);
		
		response.setContentType("text/html; charset=UTF-8");//타입 지정 해줘야됨
		PrintWriter out = response.getWriter();//출력객체
		
		out.println("<html>");
		out.println("<head><title>Login Information</title></head>");
		out.println("<body>");
		
		out.println("아이디:"+userid+"<br>");
		out.println("비밀번호:"+passwd+"<br>");
		
		out.println("</body>");
		out.println("</html>");

		
	}

}
