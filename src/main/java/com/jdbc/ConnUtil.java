package com.jdbc;

import java.sql.*;
//api 불러오기
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnUtil {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			 
		} catch (Exception e) {
			System.out.println("Connection 생성 실패");
		}
		
		return conn;
		
	}//end getConnection
	
	
	
}
