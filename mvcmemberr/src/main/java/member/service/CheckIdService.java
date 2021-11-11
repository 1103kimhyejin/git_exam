package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		// 데이터
		String id = request.getParameter("id");//서블릿 리퀘스트 --> jsp까지 포워드

		// DB
		MemberDAO memberDAO = MemberDAO.getInstance(); 
		boolean exist = memberDAO.isCheckedId(id);
		// 응답
		request.setAttribute("id", id);  
		if (!exist) {
			return "/member/checkIdOk.jsp?id="+id;
		} else {
			return "/member/checkIdFail.jsp?id="+id;
		}
	}

}
