package mvc.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.action.Action;

/*
 * 파라미터 값에 따라 기능을 처리하도록 작성한느 곳
 * 명령어에 알맞은 비즈니스 로직 코드를 실행 한 후
 *  결과를 보여줄 뷰로 이동해야 하므로 
 *  비즈니스 로직 코드르 실행한 결과를 보여줄 뷰 페이지를 지정함  
 * 
 * */
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		
		if(cmd != null) {//명령 파라미터가 있을 경우
			
			ActionFactory factory = ActionFactory.getInstance();//객체생성
			Action action =factory.getAction(cmd);
			
			ActionForward af = action.execute(request, response);
			
			if(af.isRedirect()) {
				
				response.sendRedirect(af.getUrl());
				
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(af.getUrl());
				rd.forward(request, response);//요청들어온대로 응답하기
			}
			
		}else {//명령 파라미터가 없을 경우
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head><title>Error</title></head>");
			out.println("<body>");
			out.println("<h4>올바른 요청이 아닐 때</h4>");
			out.println("<h4>http://localhost:9090/mvc/test.do?cmd=요청키워드</h4>");
			out.println("</body>");
			out.println("</html>");
			
		}
		
		
		
		
	}

}
