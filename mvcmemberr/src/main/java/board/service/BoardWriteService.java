package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;
import member.bean.MemberDTO;

public class BoardWriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session=request.getSession();
		//방법1 세션에서 값 가져오기
		String name = (String)session.getAttribute("memName");
		String id = (String)session.getAttribute("memId");
		String email = (String)session.getAttribute("memEmail");
	
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		map.put("subject", subject);
		map.put("content", content);
		
		//DB	
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.write(map);

		//응답
		return "/board/boardWrite.jsp";
	}

}
