package board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));
		//DB
		//1페이지당 5개씩
		int endNum = pg*5;
		int startNum = endNum-4;

		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> list = boardDAO.getBoardList(startNum, endNum); /* dto 한줄한줄들을 한곳에 담아야 하니까 list!*/
		
		//페이징 처리
		int totalA = boardDAO.getTotalA(); //총글수 
		int totalP = (totalA+4)/5;//페이지 번호
		request.setAttribute("totalP",totalP);
		request.setAttribute("pg",pg);
		request.setAttribute("list",list);
		return "/board/boardWriteList.jsp";
	}

}
