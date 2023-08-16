package mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcmem.control.ActionForward;

public interface Action {
	//명령어 처리 클래스
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws IOException;
	
	
}
