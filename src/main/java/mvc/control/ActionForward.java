package mvc.control;

/*
 * 모델 : 비즈니스 로직(vo,dao 외 다수)
 * 	 ActionForward
 * -비즈니스 로직의 수행이 필요한 경우 반드시 서블릿이 해당 요청을 처리할 수 있도록
 * 클라이언트 요청 url(test.do) 을 패턴의 형태로 하고 web.xml 에 매핑 정보를 추가하는 부분이다
 * 
 * url-pattren : *.do
 * web.xml 에 정보 등록
 * <url-pattern>*.do</url-pattern>
 * 
 *http://localhost:9090/board/a.do?cmd=list 
 *http://localhost:9090/board/a.do?cmd=insert 
 *http://localhost:9090/board/a.do?cmd=write 
 *http://localhost:9090/board/a.do?cmd=update 
 * 
 * */

/*
 * 요청 파라미터를 명령어로 전달하는 방식
 * 
 * model2 방식 개발할 때 반드시 필요한 클래스
 * 
 * 1.ControlServlet :controller의 역할을 수행하는 서블릿
 * 2.ActionFactory : 사용자의 요청을 처리할 비즈니스 로직이 구현된 XXXAction 객체를 생성하는 역할을 담당
 * 3.Action : 모든 XXXaction클래스가 구현할 인터페이스
 * 					 비즈니스 로직을 재정의 할 메소드를 선언하고 있음
 * 4.XXXAction :action 인터페이스의 메소드를 재정의 하고 있는 클래스
 * 						실질적인 비즈니스 로직의 구현체임
 * 5.ActionForward : XXXAction의 비즈니스 로직을 수행 후 ControlServlet 에게 반환하는 객체임/ url을 타고서 redirect 해주는
 * 							이동할 url 과 이동 방법을 제시하고 있음 true면 이동하고 false면 이동하지 않는
 * */



public class ActionForward {
	
	private String url;
	private boolean redirect;
	//url을 가지고 페이지로 가라 url 잘못되면 페이지를 찾을 수 없음
	
	public ActionForward() {}
	
	public ActionForward(String url) {
		this.url = url;
	}
	public ActionForward(String url,boolean redirect) {
		this.url = url;
		this.redirect = redirect;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	
	
}
