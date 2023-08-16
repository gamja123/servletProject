package mvcmem.control;

import mvcmem.action.Action;
import mvcmem.action.DeleteFormAction;
import mvcmem.action.DeleteProcAction;
import mvcmem.action.IdcheckAction;
import mvcmem.action.IndexAction;
import mvcmem.action.LoginFormAction;
import mvcmem.action.LoginProcAction;
import mvcmem.action.LogoutAction;
import mvcmem.action.ModifyFormAction;
import mvcmem.action.ModifyProcAction;
import mvcmem.action.RegFormAction;
import mvcmem.action.RegProcAction;
import mvcmem.action.ZipCheckAction;

public class ActionFactory {
	
private static ActionFactory factory;
	
	private ActionFactory() {}
	
	public static synchronized ActionFactory getInstance() {
		
		if(factory==null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	public Action getAction(String cmd) {
	
		Action action = null;//업캐스팅..? Action action = new IndexAction(); 아하
		
		switch (cmd) {
		case "index":
			action = new IndexAction();
			break;
		
		case "regForm":
			action = new RegFormAction();
			break;
		
		case "idCheck":
			action = new IdcheckAction();
			break;
			
		case "zipCheck":
			action = new ZipCheckAction();
			break;
			
		case "regProc":
			action = new RegProcAction();
			break;
			
		case "login":
			action = new LoginFormAction();
			break;
			
		case "loginProc":
			action = new LoginProcAction();
			break;
			
		case "logout":
			action = new LogoutAction();
			break;

		case "modifyForm":
			action = new ModifyFormAction();
			break;
			
		case "modifyProc":
			action = new ModifyProcAction();
			break;
			
		case "deleteForm":
			action = new DeleteFormAction();
			break;
		
		case "deleteProc":
			action = new DeleteProcAction();
			break;
			
		default: 
			action = new IndexAction();
			break;
		}
		
		return action;
		
	}
	
	
	
	
	
}
