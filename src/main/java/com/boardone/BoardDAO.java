package com.boardone;
import java.util.*;
import java.sql.*;

public class BoardDAO {
	
	private static BoardDAO instance = null;//싱글톤
	
	private BoardDAO() {//객체생성 안되게 private
		
		
	}

	public static BoardDAO getInstance() {
		
		if(instance == null) {
			synchronized (BoardDAO.class) {//클래스가 객체가 없으면 얘가 계속 만들어줘서 리턴함(?)
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	//여기서 부터 게시판 기능을 추가하면 됨(메소드 추가)
	
	//글 쓰기 폼에서 넘어온 데이터를 실제 데이터 베이스에 넣어 줄 메소드 추가
	public void insertArticle(BoardVO article) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num= article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number =0;
		String sql = "";
		try {
			
			conn = ConnUtil.getConnection();
			sql="select max(num) from board";
			pstmt = conn.prepareStatement(sql);
			rs =pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1)+1;
			else number= 1;
			
			if(num != 0) {//답변글 일 경우
				
				sql = "update board set step=step+1 where ref=? and step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2,step);
				pstmt.executeUpdate();
				step = step+1;
				depth = depth+1;
				
			}else {//새글
				
				ref = number;
				step =0;
				depth=0;
				
			}
			
			sql ="insert into board(num, writer, email, subject, pass, regdate, ref, step, depth, content, ip) values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6,ref);
			pstmt.setInt(7,step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
			pstmt.executeUpdate();
			
			
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
			
		}//end insertArticle
		
		//전체 글의 개수를 가져오는 메소드 구현
		
		public int getArticleCount() {//검색기능 오버로딩
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x=0;
			
			try {
				
				conn = ConnUtil.getConnection();
				
				pstmt = conn.prepareStatement("select count(*) from board");
				
				rs = pstmt.executeQuery();
				if(rs.next()) { 
					x= rs.getInt(1);
					}
				
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
			
		}
		/*
		public List<BoardVO> getArticles(){
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			List<BoardVO> articleList = null;
			
			try {
				conn = ConnUtil.getConnection();
				
				pstmt = conn.prepareStatement("select * from board order by num desc");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					
					articleList = new ArrayList<BoardVO>();
					
					do {
						BoardVO article = new BoardVO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPass(rs.getString("pass"));
						article.setRegdate(rs.getTimestamp("regdate"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setStep(rs.getInt("step"));
						article.setDepth(rs.getInt("depth"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
					} while (rs.next());
					
					
					
				}
				
				
				
			}catch (Exception e) {
				
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
			
			return articleList;
			
			
		}*/
		//end getArticles
		
		
		public List<BoardVO> getArticles(int start, int end){//수정1 검색 오버로딩
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			List<BoardVO> articleList = null;
			
			try {
				conn = ConnUtil.getConnection();
				//수정2
				String sql = "select * from (select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from (select * from board order by ref desc, step asc)) where rnum >= ? and rnum <=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, start );
				pstmt.setInt(2, end );
				
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					
					articleList = new ArrayList<BoardVO>(end - start+1);//수정3
					
					do {
						BoardVO article = new BoardVO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPass(rs.getString("pass"));
						article.setRegdate(rs.getTimestamp("regdate"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setStep(rs.getInt("step"));
						article.setDepth(rs.getInt("depth"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
					} while (rs.next());
					
				}
				
			}catch (Exception e) {
				
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
			
			return articleList;
			
			
		}//end getArticles
		
		
		
		//글 상세보기
		//list.jsp 페이지에서 제목을 클릭했을 경우 글 내용을 볼 수 있게 구현해야함
		//글의 num을 매개변수로 해서 하나의 글에 대한 상세 정보를 데이터 베이스에서 가져와야 함
		public BoardVO getArticle(int num) {
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			BoardVO article = null;
			
			try {
				
				conn = ConnUtil.getConnection();
				
				pstmt = conn.prepareStatement("update board set readcount=readcount+1 where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				pstmt= conn.prepareStatement("select * from board where num=?");
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
				}
				
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
			
			return article;
			
		}//getArticle
		
		/*글 상세 보기에서 글 수정 버튼을 누른 경우 updateForm.jsp 페이지로 이동하도록 하였다
		 * 글 수정 시에는 글 목록 보기에서 조회수를 증가 시킬 필요는 없다
		 * 조회수 증가를 제외 한 부분만 가져와서 수정처리하면 됨
		 * */
		
		public BoardVO updateGetArticle(int num) {//번호를 ㅇ이용해서검색할 게시물 가져와서 수정처리 해야됨 이건 수정할 글 가져오는 거
			
			
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			BoardVO article =null;
			
			try {
				
				conn = ConnUtil.getConnection();
				
				pstmt = conn.prepareStatement("select * from board where num=?");
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {//가져와서vo에 저장
					
					article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
				}
				
				
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
			return article;
			
		}//end updateGetArticle
		
		/*수정 버튼을 클릭 했을 경우 데이터 베이스에 저장되어 있던 글도 수정 처리가 되어야 한다
		 *db랑 입력한 비밀번호가 일치해야됨
		 * */
		
		public int updateArticle(BoardVO article) {
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			String dbPasswd = "";
			String sql="";
			int result = -1;
			
			try {
				
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select pass from board where num=?");
				pstmt.setInt(1, article.getNum());
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					
					dbPasswd = rs.getString("pass");
					if(dbPasswd.equals(article.getPass())) {
						//비밀번호가 일치하면 수정 처리 작성자 이메일 제목 내용
						sql = "update board set writer=?, email=?, subject=?, content=? where num=?";
						
						pstmt = conn.prepareStatement(sql);
						
						pstmt.setString(1, article.getWriter());
						pstmt.setString(2, article.getEmail());
						pstmt.setString(3, article.getSubject());
						pstmt.setString(4, article.getContent());
						pstmt.setInt(5, article.getNum());
						
						pstmt.executeUpdate();
						result = 1;//수정 성공
					}else {
						result = 0;//수정 실패
					}

				}

			}catch (Exception e) {e.printStackTrace();
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
			
		}//end updateArticle
		
		/*글 삭제 처리 
		 * 비밀번호를 입력하고 삭제 버튼을 클릭하면 데이터 베이스에 저장된 비밀번호와 비교하여 일치하면 삭제 처리를 수행한다
		 *몇 번 글을 삭제할 지 
		 * */
		
		public int deleteArticle(int num,String pass) {
			
			int result =-1;
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			String dbPasswd = "";
			
			try {
				
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select pass from board where num=?");
				pstmt.setInt(1, num);
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					
					dbPasswd = rs.getString("pass");
					if(dbPasswd.equals(pass)) {
						pstmt=conn.prepareStatement("delete from board where num=?");
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						result=1;//삭제 성공
					}else
						result=0;//삭제 실패
				}
				
			} catch (Exception e) {e.printStackTrace();
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
		}//end deleteArticle
		
		
		/*
		public List<BoardVO> getSearch(String searchField, String searchText){
			
		      ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		      String sql = null;
		      Connection conn = null;
				PreparedStatement pstmt= null;
				ResultSet rs = null;
		      try {
		    	  conn = ConnUtil.getConnection();
		               sql =" select * from board where"+searchField+"like '%"+searchText.trim()+"%' order by num desc";
		            pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					
		         while(rs.next()) {
		        	 BoardVO article = new BoardVO();
		        	 article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPass(rs.getString("pass"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRegdate(rs.getTimestamp("regdate"));
						article.setRef(rs.getInt("ref"));
						article.setStep(rs.getInt("step"));
						article.setDepth(rs.getInt("depth"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
		            list.add(article);
		         }        
		      } catch (Exception e) {e.printStackTrace();
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
		      return list;
		   }
		
		*/
		
		//---------------검색
		
		
		//검색한 내용이 몇 개있는지를 반환하는 함수(검색조건 field 검색 내용 content
		public int getArticleCount(String field,String content) {//검색기능 오버로딩
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x=0;
			
			try {
				
				conn = ConnUtil.getConnection();
				
				pstmt = conn.prepareStatement("select count(*) from board where "+field+" like '%"+content+"%'");
				
				rs = pstmt.executeQuery();
				if(rs.next()) { 
					x= rs.getInt(1);
					}
				
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
			
		}
			
			
		//검색한 내용을 리스트로 받아옴(field content start end)
			public List<BoardVO> getArticles(int start, int end,String field,String content){//수정1 검색 오버로딩
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			
			List<BoardVO> articleList = null;
			
			try {
				conn = ConnUtil.getConnection();
				//수정2
				String sql = "select * from (select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from (select * from board where "+field+" like '%"+content+"%' order by ref desc, step asc)) where rnum >= ? and rnum <=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, start );
				pstmt.setInt(2, end );
				
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					
					articleList = new ArrayList<BoardVO>(end - start+1);//수정3
					
					do {
						BoardVO article = new BoardVO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPass(rs.getString("pass"));
						article.setRegdate(rs.getTimestamp("regdate"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setStep(rs.getInt("step"));
						article.setDepth(rs.getInt("depth"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
					} while (rs.next());
					
				}
				
			}catch (Exception e) {
				
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
			
			return articleList;
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	

	
}
