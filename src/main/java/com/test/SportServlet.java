package com.test;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*getParameterNames()
회원관리 폼
이름
주소
아이디
비밀번호
이메일
전송

 * */

@WebServlet("/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post방식 일 경우 인코딩 설정
		
		request.setCharacterEncoding("utf-8");
		//요청
		String[] sports = request.getParameterValues("sports");
		String gender = request.getParameter("gender");
		//응답
		
		//response 처리 할 때 인코딩 설정
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>getParameterValues</title></head>");

		out.println("<body>");
		out.println("<h1>좋아하는 운동 및 성별</h1>");
		
		for(String sport : sports) {
			out.println("좋아하는 운동:"+sport+"<br>");
		}
		
		out.println("성별:"+gender+"<br>");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
	}

}
