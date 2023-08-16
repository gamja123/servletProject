package com.bbs;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/VisitInsert")
public class VisitInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

//요청
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String writer = request.getParameter("writer");
		String memo = request.getParameter("memo");
		
		System.out.println("작성자:"+writer);
		System.out.println("내용"+memo);
		
		String sql = "insert into visit (no, writer, memo, regdate) values(visit_seq.nextval,?,?,sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott", "tiger");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);//값 넘어온거 집어넣을거임
			pstmt.setString(2, memo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException ss) {
			ss.printStackTrace();
		}finally {
			try {

				if(pstmt != null) pstmt.close();
			}catch(SQLException ss) {
				ss.printStackTrace();
			}
			
			try {
				if(con != null) con.close();
			}catch(SQLException ss) {
				ss.printStackTrace();
			}
		}
		
		response.sendRedirect("VisitList");//페이지 이동 목록이 웹으로 나가야됨
		
	
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
