package mvc.action;

import java.io.*;
import javax.servlet.http.*;
import mvc.control.ActionForward;



//모든 명령어 클래스가 공통으로 구현해야하는 메소드를 선언
public interface Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws IOException;
	
}
