package board.action;

import java.util.Collection;
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
		
		List<BoardVO> articleList = null;
		
		BoardDAO dbPro = BoardDAO.getInstance();
		count = dbPro.getArticleCount();
		
		if(count > 0) {
			articleList = dbPro.getArticles(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
		
		//글 목록에 표시할 글 번호
		number = count - (currentPage -1)* pageSize;
		
		//해당 뷰에서 사용할 속성을 저장
		
		request.setAttribute("currentPage", new Integer(currentPage));//currentPage를 속성에 들어갈 객체(Integer)로 만들어야됨
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList",articleList);
		
		return "/board/list.jsp";
	}

}
