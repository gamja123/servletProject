package mvc.control;

import mvc.action.*;

//객체 생성
public class ActionFactory {
	
	private static ActionFactory factory;
	
	private ActionFactory() {}
	
	public static synchronized ActionFactory getInstance() {
		
		if(factory==null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	//액션을 가지고 오는 메소드
	
	public Action getAction(String cmd) {
		
		Action action = null;
		//cmd 가 인덱스면 인덱스액션의 객체를 만들어라
		if(cmd.equals("index")) {
			action = new IndexAction();
		}
		return action;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
