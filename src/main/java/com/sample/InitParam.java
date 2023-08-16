package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ServletConfig
 * -Container가 서블릿을 생성할 때 생성되는 객체다
 * -DD(web.xml)를 읽어서 이름/값의 쌍으로 된 초기화 파라미터를 읽어서 저장한다
 * -Servlet 객체 당 한개 씩 생성한다
 * -Servlet 에서는 getServletConfig()를 이용해서 Servlet과 관련된 ServletConfig 객체를 얻어올 수 있다 
 * -지역
 * 
 * ServletContext
 * -web application 당 하나씩 생성되는 객체다(프로젝트 당 하나씩)
 * -web application 전체에서 참조할 수 있는 초기화 파라미터를 저장한다
 * -Servlet 에서는 getServletContext()를 이용해서 ServletContext 객체를 얻어온다
 * -전역

 * */



//@WebServlet("/InitParam")

@WebServlet(name="initParam", urlPatterns = "/InitParam",
initParams = {
		@WebInitParam(name="tel",value="010-111-2222"),
		@WebInitParam(name="email",value="aa@naver.com")
})

public class InitParam extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String company;
	private String manager;
	private String tel;
	private String email;
	
	public void init() throws ServletException {
		
		//ServletContext의 초기 파라미터 값을 읽기
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");
		
		
		//ServletConfig의 초기 파라미터 값을 읽기
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
		
		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");//타입 지정 해줘야됨
		PrintWriter out = response.getWriter();//출력객체
		
		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		
		out.println("<ul>");
		out.println("<li>회사명: "+company+"</li>");
		out.println("<li>관리자: "+manager+"</li>");
		out.println("<li>전화번호: "+tel+"</li>");
		out.println("<li>이메일: "+email+"</li>");
		out.println("</ul>");
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
