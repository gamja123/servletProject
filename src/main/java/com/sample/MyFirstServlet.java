package com.sample;
/*
 * 외워라
 * 
 * 1.container(컨테이너) 보안,스레드 담당
 * 		-컨테이너는 웹 서버와 서블릿 사이의 통신을 지원한다
 * 		-컨테이너는 서블릿의 생명주기(life cycle)를 관리한다
 * 		-컨테이너는 새로운 요청이 들어올 때 마다 자바 스레드를 생성해서 사용자의 요청을 처리한다
 * 		-컨테이너는 선언적인 방법으로 보안 관련 내용을 설정 할 수 있다
 * 
 * 2.Servlet(서블릿)
 * 		-서블릿은 웹 서버에서 실행되는 자바 프로그램이다
 * 		-서블릿은 사용자의 요청에 따라서 동적으로 웹 컨텐츠를 만들어서 클라이언트 측에 전송하는 매우 중요한 자바 기술이다
 * 		-javax.servlet.http.HttpServlet 클래스를 무조건 상속 받아서 사용자 정의 서블릿을 작성한다
 * 		
 * 3.전송 방식(Get,Post 방식)
 * 
 * 		1.Get 방식
 * 		-서버에서 정보를 가져오는 방식
 * 		-한번에 전송 할 때 240바이트까지 전달할 수 있다
 * 		-쿼리 스트링 환경변수를 통해서 전달한다
 * 		-형식: http://localhost:8080/WebPrj/join.do?data=hello&age=10
 * 		-url 노출로 보안성이 요구되는 경우에는 무용지물이다
 * 		-검색 단어 전송할 때 많이 활용된다
 * 
 * 		2.Post 방식
 * 		-정보를 서버로 올릴 때 전송하는 방식
 * 		-전송 할 때 데이터의 크기는 제한이 없다
 * 		-url 파라미터를 사용하지 않는다
 * 		-형식: http://localhost:8080/WebPrj/join.do
 * 
 * 	4.서블릿의 실행 과정(생명주기:life cycle)
 * 		-컨테이너로 부터 요청이 들어오면 HttpServletRequest 객체와 HttpServletResponse 객체를 생성함
 * 		
 * 		-요청된 url을 분석해서 해당 서블릿 객체를 생성하고 사용자의 요청을 처리하기 위해 스레드를 생성한다
 * 		
 * 		-서블릿의 service() 메소드를 호출하여 요청을 처리하여 어떤 메소드로 처리할 지를 결정한다
 * 
 * 		-서블릿의 doGet() 메소드를 호출해서 사용자의 요청을 처리하고 응답한다
 * 		-작성된 응답처리를 클라이언트에게 전송한다
 * 		-사용자의 요청을 처리하기 위해서 생성된 스레드는 소멸된다
 * 
 * 서블릿의 라이프 사이클에 관련된 메소드
 * 1.init()
 * -서블릿을 초기화 하는 메소드,단 한번만 호출되는 메소드
 * 2.service()
 * -doGet(),doPost() 메소드 중 어느 메소드를 호출 할 지 결정하는 메소드
 * -요청이 있을 때 마다 호출되는 메소드
 * 3.destroy()
 * -서블릿이 종료되는 메소드
 * -종료될 때 단한번만 호출되는 메소드
 * */
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

//@WebServlet("*.do") 별은 앞에 뭐가 와도 다 됨
@WebServlet("/MyFirst")//url요청 매핑값 안맞으면 실행 안됨
//xml에 추가하는 방식   annotation방식 둘 다 해야됨
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답
		response.setContentType("text/html; charset=UTF-8");//타입 지정 해줘야됨
		PrintWriter out = response.getWriter();//출력객체
		Date date = new Date();//util에 있는 거
		
		out.println("<html>");
		out.println("<head><title>MyFirstServlet</title></head>");
		out.println("<body>");
		
		out.println("My First Servelt Programming<br>");
		out.println("가나다라<br>");
		out.println(date.toString());
		
		out.println("</body>");
		out.println("</html>");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
