package com.logon;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LogonDBBean {

private static LogonDBBean instance = new LogonDBBean();
	
	private LogonDBBean() {}
	
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private Connection getConnection()throws Exception {
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		
		return ds.getConnection();
	}
	
	public int userCheck(String id,String passwd)throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		String dbpasswd = null;
		int x = -1;// 노아이디
		
		try {
			
			conn  =getConnection();
			pstmt =  conn.prepareStatement("select passwd from tempmember where id=?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");//db에서 갖고온 패스워드
				if(dbpasswd.equals(passwd)) 
					x= 1;//회원인증 성공
				else
					x = 0;//비번 틀림
			}else
				x=-1;//아이디 존재 x
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			
			if(rs != null){
				try{rs.close();}
				catch(SQLException s){}
			}
			
			if(pstmt != null){
				try{pstmt.close();}
				catch(SQLException s){}
			}
			
			if(conn != null){
				try{conn.close();}
				catch(SQLException s){}
			}
		}
		return x;
	}//userCheck
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
