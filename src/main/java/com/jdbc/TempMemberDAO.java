package com.jdbc;
import java.sql.*;
import java.util.*;

public class TempMemberDAO {
	
	/*
	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String USER = "scott";
	private final String PASS = "tiger";
	
	public TempMemberDAO() {
		
		try {
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			System.out.println("Error:JDBC 드라이버 로딩 실패");
		}
		
	}
	*/
	//데이터베이스에 저장된 레코드를 불러와서 리스트로 반환
	
	public Vector<TempMemberVO> getMemberList() {//사용자 정의 메소드
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Vector<TempMemberVO> vecList = new Vector<TempMemberVO>();//<여기 안에는 객체가 들어가니까 TempMemberVO 클래스 넣기>
		
		
		try {
			
			//con = DriverManager.getConnection(JDBC_URL, USER, PASS);
			conn = ConnUtil.getConnection();
			String sql = "select * from tempmember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				TempMemberVO vo = new TempMemberVO();
				vo.setId(rs.getString("id"));//db에 있는 거로 vo 변수에 set
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1( rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2")) ;
				vo.setE_mail(rs.getString("e_mail")) ;
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode")) ;
				vo.setAddress(rs.getString("address")) ;
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
			}
			
			
		} catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
		}finally{
			
			if(rs != null){
				try{rs.close();}
				catch(SQLException s){}
			}
			
			if(stmt != null){
				try{stmt.close();}
				catch(SQLException s){}
			}
			
			if(conn != null){
				try{conn.close();}
				catch(SQLException s){}
			}

		}
		
		return vecList;
		
	}
	
	
	
	
	
	
	
	
	
	
}
