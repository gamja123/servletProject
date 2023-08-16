package com.test;
//init destroy service 체크하고 생성
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//초기화 작업을 담당한다
	public void init() throws ServletException {
		System.out.println("init() 호출");
	}

	//was 에서 소멸 웹애플리케이션서버
	public void destroy() {
		System.out.println("destroy() 호출");
	}

	//요청을 처리하는 일을 담당
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("무엇을 도와 드릴까요");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
