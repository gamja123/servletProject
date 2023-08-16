package com.memberone;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;


public class StudentDAO {
	
	private Connection getConnection() {
		
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
	
	//아이디가 존재 하는지 체크
	public boolean idCheck(String id) {
		
		boolean result = true;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			String sql = "select * from student where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			//rs에 값이 없으며 ㄴ
			if(!rs.next()) result = false;
			
			
		}catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
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
		return result;
		
		
	}
	
	
	/*
	 * 우편 번호를 데이터 베이스에서 검색해서 Vector에 저장해서 리턴해주는 메소드
	 * 
	 * */
	
	
	public Vector<ZipCodeVO> zipcodeRead(String dong){
		
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>(); 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();//db연결 완
			
			String sql = "select * from zipcode where dong like '" +dong+"%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ZipCodeVO tempZipcode = new ZipCodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipcode);
				
			}
			
		}catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
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
		
		
		
		return vecList;
	}//end zipcode
	
	public boolean memberInsert(StudentVO vo) {
		
		boolean flag= false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddress1());
			pstmt.setString(10, vo.getAddress2());
			
			int count = pstmt.executeUpdate();
			if(count>0) flag = true;
			
			
		}catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
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
	
		return flag;
		
	}//end memberInsert
	
	/*로그인 버튼을 클릭하면 입력한 아이디와 패스워드를 데이터 베이스에 저장 되어 있는
	 * id/pass 와 비교해서 같으면 로그인 성공,다르면 로그인 실패 처리
	 * 로그인 성공 시 return 1;
	 * 비밀번호 오류 시 return 0;
	 * 아이디가 존재하지 않을 경우 -1
	 * */
	
	public int loginCheck(String id,String pass) {
		
		int check = -1;//아이디가 없음
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			String sql = "select pass from student where id=?";
			pstmt = conn.prepareStatement(sql);
			//아이디를 이용해서 패스워드를 알아보기
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {//아이디가 있으면 패스워드 가져와서 저장
				String dbPass = rs.getString("pass");//아이건 db이름인가?
				if(pass.equals(dbPass)) 
					check=1;//db에 있는 거랑 loginCheck 의 매개변수(내가 집어넣은 비밀번호)랑 일치하면 로그인 성공
				else check=0;
			}

		}catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
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
		
		
		
		return check;

		
	}//end loginCheck
	
	//아이디로 회원의 모든 정보 가져오기 select * from student where id=?
			//vo에 저장
	public StudentVO getMember(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StudentVO vo = null;
		
		try {
			
			conn = getConnection();
			
			String sql ="select * from student where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//해당 아이디에 대한 회원이 존재하면
				vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
				
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
			
			if(pstmt != null){
				try{pstmt.close();}
				catch(SQLException s){}
			}
			
			if(conn != null){
				try{conn.close();}
				catch(SQLException s){}
			}
		}
		
		
		return vo;
	}//end getMember
	
	//정보수정 버튼을 클릭 했을 때 db에 update를 수행/ 정보 수정 메소드
	public void updateMember(StudentVO vo) {
		
		Connection conn = null;
		PreparedStatement pstmt =null;
		
		try {
			
			conn = getConnection();
			
			String sql ="update student set pass=?, phone1=?, phone2=?, phone3=?, email=?, zipcode=?, address1=?, address2=? where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,vo.getPass());
			pstmt.setString(2,vo.getPhone1());
			pstmt.setString(3,vo.getPhone2());
			pstmt.setString(4,vo.getPhone3());
			pstmt.setString(5,vo.getEmail());
			pstmt.setString(6,vo.getZipcode());
			pstmt.setString(7,vo.getAddress1());
			pstmt.setString(8,vo.getAddress2());
			pstmt.setString(9,vo.getId());
			
			pstmt.executeUpdate();
			
			
			
			
		} catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
		}finally{
			
			if(pstmt != null){
				try{pstmt.close();}
				catch(SQLException s){}
			}
			
			if(conn != null){
				try{conn.close();}
				catch(SQLException s){}
			}
		}

	}//end updateMember
	
	//회원탈퇴
	//본인이 맞으면 회원탈퇴 성공 1,본인이 맞지 않으면 회원탈퇴 실패 0
	//회원이 아닌 경우 -1
	@SuppressWarnings("resource")
	public int deleteMember(String id,String pass) {
		
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = getConnection();
			
			String sql = "select pass from student where id=?";
			pstmt = conn.prepareStatement(sql);
			//아이디를 이용해서 패스워드를 알아보기
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//아이디가 있으면 패스워드 가져와서 저장
				String dbPass = rs.getString("pass");//아이건 db이름인가?
				if(pass.equals(dbPass)) {
					String sql1 = "delete from student where id=?";
					pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				result =1;//회원탈퇴 성공
				}else { //본인학인 실패: 비밀번호 오류
				result=0;
				}//end if
			}//end if
			
		}  catch(SQLException ss){
			System.out.println("sql Exception");
		}catch(Exception e){
			System.out.println("Exception");
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
		
		return result;
	}
	
	
	
	
	
	
	
	

}
