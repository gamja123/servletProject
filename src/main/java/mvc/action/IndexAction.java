package mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.control.ActionForward;


/*
 * 명령어를 처리하는 클래스 
 * 즉 비즈니스 로직을 실제로 처리하는 곳 
 * 이동할 url과 방법을 제시해서 컨트롤로 전송함
 * 
 * */
public class IndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("IndexAction의 execute() 수행되었다");
		return new ActionForward("index.jsp", false);//url 이동방식
		
	}

}
