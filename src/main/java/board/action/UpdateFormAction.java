package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		//데이터 베이스 처리
		BoardDAO dbPro = BoardDAO.getInstance();
		//데이터 베이스에서 메소드 호출(getArticle)
		BoardVO article=dbPro.updateGetArticle(num);
		//해당 뷰에 속성 저장
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("article",article);
		
		return "/board/updateForm.jsp";
	}

}
