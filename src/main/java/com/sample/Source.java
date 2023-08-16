package com.sample;

/*
 * 페이지 이동 방법
 * 
 * -메소드 호출을 통해서 페이지를 이동 할 수 있는 방법 (두가지)
 * 
 * -forward 방식과 redirect 방식
 * 
 * 구분			forward							redirect
 * -------------------------------------------------
 * url				url이 바뀌지 않는다				url이 바뀐다
 * ---------------------------------------------------
 * 요청객체와    객체가 유지된다					유지되지 않는다
 *  응답객체
 * ----------------------------------------------------
 * 속도			빠르다								느리다
 * -----------------------------------------------------
 * 소속			request							response
 * 
 * */
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Source")
public class Source extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Source Start");
		//페이지 이동 방식
		//1.forward 방식
		//RequestDispatcher view = request.getRequestDispatcher("Destiantion");
		//view.forward(request, response);
		//2.redirect 방식
		response.sendRedirect("Destiantion");
		
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
		
	}

}
