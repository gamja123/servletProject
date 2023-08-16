package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {
//글 쓰기 폼 처리하는 명령어 클래스
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//제목글 답변글 구분
		
		int num = 0,ref = 1,step=0,depth=0;

		try{
		if(request.getParameter("num") != null){//답변글 일 때
			num =Integer.parseInt(request.getParameter("num"));
			ref =Integer.parseInt(request.getParameter("ref"));
			step =Integer.parseInt(request.getParameter("step"));
			depth =Integer.parseInt(request.getParameter("depth"));
		}
		} catch(Exception e){
			e.printStackTrace();
		}
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		
		
		return "/board/writeForm.jsp";
	}
}
