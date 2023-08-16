package com.bbs;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VisitList")
public class VisitList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//응답
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			
			out.println("<html>");
			out.println("<head><title>방명록 리스트</title></head>");
			out.println("<body>");
			//db내용을 body에 뿌려
			
			//객체 선언
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//쿼리 작성
			String sql= "select no, writer, memo, regdate from visit order by no desc";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott", "tiger");
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					int no = rs.getInt("no");
					String writer = rs.getString("writer");
					String memo = rs.getString("memo");
					java.sql.Date regdate = rs.getDate("regdate");
					
					out.println("<table width=500 align=center border=1>");
					out.println("<tr>");
					
					out.println("<th width=50>번호</th>");
					out.println("<td width=50 align=center>"+no+"</td>");
					
					out.println("<th width=70>작성자</th>");
					out.println("<td width=180 align=center>"+writer+"</td>");
					
					out.println("<th width=50>작성일</th>");
					out.println("<td width=100 align=center>"+regdate+"</td>");
					
					out.println("</tr>");
					
					out.println("<tr>");
					
					out.println("<th width=50>내용</th>");
					out.println("<td colspan=5>&nbsp <textarea rows=3 cols=50>"+memo+"</textarea></td>");
					
					out.println("</tr>");
					
					out.println("</table>");
					
					
				}//while

				
			}catch (ClassNotFoundException cnfe) {
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
				
				try {
					if(rs != null) rs.close();
				}catch(SQLException ss) {
					ss.printStackTrace();
				}
			}
			
			out.println("<p align=center><a href=/bbs/Write.html>글쓰기</a></p>");
			out.println("</body>");
			out.println("</html>");

		} finally {
			out.close();//출력하고 닫아주기
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		processRequest(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		processRequest(request, response);
	}

}
