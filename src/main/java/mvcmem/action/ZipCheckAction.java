package mvcmem.action;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcmem.control.ActionForward;
import mvcmem.model.StudentDAO;
import mvcmem.model.ZipCodeVO;

public class ZipCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		

		request.setCharacterEncoding("utf-8");
		
		String dong = request.getParameter("dong");
		String check = request.getParameter("check");
		
		StudentDAO dao = StudentDAO.getInstance();
		
		Vector<ZipCodeVO> zipcodeList = dao.zipcodeRead(dong);
		
		int totalList = zipcodeList.size();
		
		request.setAttribute("dong", dong);
		request.setAttribute("check", check);
		request.setAttribute("zipcodeList", zipcodeList);
		request.setAttribute("totalList", totalList);
		
		return new ActionForward("/mvcmem/zipCheck.jsp", false);
	}

}
