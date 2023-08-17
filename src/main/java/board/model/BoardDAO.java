package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	
	private static BoardDAO instance = null;
	
	private BoardDAO() {}

	public static BoardDAO getInstance() {
		
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	//게시판 메소드 시작
	//전체 글 개수 가져오는
	public int getArticleCount(String find,String find_box) {//검색기능 오버로딩
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x=0;
		
		try {
			
			conn = ConnUtil.getConnection();
			
			if(find.equals("writer")) {
				pstmt = conn.prepareStatement("select count(*) from board where writer=?");
				pstmt.setString(1, find_box);
			}else if(find.equals("subject")) {
				pstmt = conn.prepareStatement("select count(*) from board where subject like '%"+find_box+"%'");
			}else if(find.equals("content")) {
				pstmt = conn.prepareStatement("select count(*) from board where content like '%"+find_box+"%'");
			}else {
				pstmt = conn.prepareStatement("select count(*) from board");
			}
			
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
		
	}//end
	
	
	public List<BoardVO> getArticles(int start, int end,String find,String find_box){//수정1 검색 오버로딩
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		List<BoardVO> articleList = null;
		
		try {
			conn = ConnUtil.getConnection();
			//수정2
			StringBuffer sql = new StringBuffer();//문자열만 저장하는 임시 공간
			
			sql.append("select * from ");
			sql.append("(select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from ");
			
			if(find.equals("writer")) {//검색 조건이 작성자일 경우
				sql.append("(select * from board where writer=? order by ref desc, step asc)) where rnum >= ? and rnum <=?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, find_box);
				pstmt.setInt(2, start );
				pstmt.setInt(3, end );
			}else if(find.equals("subject")) {//검색 조건이 제목일 경우
				sql.append("(select * from board where subject like '%"+find_box+"%' order by ref desc, step asc)) where rnum >= ? and rnum <=?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start );
				pstmt.setInt(2, end );
			}else if(find.equals("content")) {//검색 조건이 내용일 경우
				sql.append("(select * from board where content like '%"+find_box+"%' order by ref desc, step asc)) where rnum >= ? and rnum <=?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start );
				pstmt.setInt(2, end );
			}else {//검색조건 없음
				sql.append("(select * from board order by ref desc, step asc)) where rnum >= ? and rnum <=?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start );
				pstmt.setInt(2, end );
			}
			
			/*
			String sql = "select * from (select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from (select * from board order by ref desc, step asc)) where rnum >= ? and rnum <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start );
			pstmt.setInt(2, end );
			*/
			
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
	
	
	
	
	
}
