package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class ModifyFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		  //데이터 
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd"); 
		String gender = request.getParameter("gender"); 
		String email1 = request.getParameter("email1"); 
		String email2 = request.getParameter("email2"); 
		String tel1 = request.getParameter("tel1");
		 String tel2 = request.getParameter("tel2"); 
		 String tel3 = request.getParameter("tel3");
		 String zipcode = request.getParameter("zipcode"); 
		 String addr1 = request.getParameter("addr1");
		 String addr2 = request.getParameter("addr2");
		 
		 
		  // 세션 

		

		// session.setAttribute("memDTO",memberDTO); 이렇게 통으로 가져와도된다
		return "/member/modifyForm.jsp";
	}

}
