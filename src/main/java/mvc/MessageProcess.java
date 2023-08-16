package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Controller 로 부터 작업의 처리를 지시받아 처리하는 클래스
 * */

public class MessageProcess implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("message", "요청 파라미터로 부터 명령어를 전달받아 처리하는 중");
		return "/mvc/process.jsp";
	}

}
