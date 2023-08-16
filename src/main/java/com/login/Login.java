package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			
			//session : 정보를 서버가 저장한다
			HttpSession session = request.getSession(false);//처음에는 세션 저장 안함
			
			if(session != null) {//null이 아니라면 로그인 정보를 저장
				
				String sessionID = session.getId();
				//세션에 저장된 아이디  가져와서 저장
				System.out.println("세션 아이디:"+sessionID);
				String user =(String) session.getAttribute("user");
				//세션에 저장된 속성값을 가져와서 저장
				
				out.println("<html>");
				out.println("<head><title></title></head>");
				out.println("<body>");
				
				out.println("<table width=300 border=1>");
				
				
				out.println("<tr>");//
				out.println("<td width=300 align=center>"+user+"님이 로그인 되었습니다</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align=center>");///
				out.println("<a href='#'>회원정보</a>");
				out.println("<a href='Logout'>로그아웃</a>");
				out.println("</td>");///
				
				out.println("</tr>");//
				
				
				out.println("</table>");
				
				out.println("</body>");
				out.println("</html>");
				
				
				
				
			}else {//로그인이 안되었을 경우 
				
				out.println("<html>");
				out.println("<head><title></title></head>");
				out.println("<body>");
				out.println("<form action='LoginCheck' method='post'>");
				out.println("<table width=300 border=1>");
				
				out.println("<tr>");
				out.println("<th width=100>아이디</th>");
				out.println("<td width=200>&nbsp;<input type='text' name='id'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th width=100>비밀번호</th>");
				out.println("<td width=200>&nbsp;<input type='password' name='pwd'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align=center colspan=2>");
				out.println("<input type='button' value='회원가입'>");
				out.println("<input type='submit' value='로그인'>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");

			}

		} finally {
			out.close();
		}
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
