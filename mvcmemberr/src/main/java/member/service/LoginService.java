package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService  implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response )throws Throwable {
		//데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");	
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance(); // 싱글톤 - 메모리에 1번 생성해서 계속 사용한다
		MemberDTO memberDTO =memberDAO.login(id, pwd);// 호출
		
		
		//응답
		if(memberDTO==null) {
			return "/member/loginFail.jsp";
		}else {
//			//쿠키
//
//			Cookie cookie = new Cookie("memName", name);
//			response.addCookie(cookie); //클라이언트로 보내준다! 웹브라우저에 저장해야하니까
//			cookie.setPath("/");
//			cookie.setMaxAge(3000);//초 단위
//			
//			Cookie cookie2 = new Cookie("memId", id);
//			response.addCookie(cookie2); //클라이언트로 보내기 
//	 		cookie2.setPath("/");
//			cookie2.setMaxAge(3000);//초 단위
//			
//			//request.setAttribute("name", name);//데이터싣기 , request는 서블릿꺼
			
			//세션
			HttpSession session =request.getSession();//세션생성
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			
			//session.setAttribute("memDTO",memberDTO); 이렇게 통으로 가져와도된다
			return "/member/loginOk.jsp";
		}

	}
}
