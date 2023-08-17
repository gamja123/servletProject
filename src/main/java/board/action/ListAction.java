package board.action;

//import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

public class ListAction implements CommandAction {
//글 목록 처리하는 클래스
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1) * pageSize + 1; 
		int endRow = currentPage * pageSize;
		
		int count = 0;//dbcount
		int number = 0;//외부로 넘어가는 갯수
		
		//검색 폼에서 넘어 온 데이터를 파라미터 처리(db로 넘어가기 전에 처리)
		String find  =request.getParameter("find");
		String find_box = request.getParameter("find_box");
		
		if(find == null) {
			find ="no";
		}
		if(find_box == null) {
			find_box ="no";
		}
		
		List<BoardVO> articleList = null;
		
		BoardDAO dbPro = BoardDAO.getInstance();
		count = dbPro.getArticleCount(find,find_box);//전체 글 수
		
		if(count > 0) {
			articleList = dbPro.getArticles(startRow, endRow,find,find_box);
		}else {
			articleList = Collections.emptyList();
		}
		
		//글 목록에 표시할 글 번호
		number = count - (currentPage -1)* pageSize;
		
		//해당 뷰에서 사용할 속성을 저장
		
		request.setAttribute("currentPage", new Integer(currentPage));//currentPage를 속성에 들어갈 객체(Integer)로 만들어야됨
		request.setAttribute("startRow", new Integer(startRow));//객체형으로 값 저장해야됨
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList",articleList);
		request.setAttribute("find",new String(find));
		request.setAttribute("find_box",new String(find_box));
		
		return "/board/list.jsp";
	}

}
