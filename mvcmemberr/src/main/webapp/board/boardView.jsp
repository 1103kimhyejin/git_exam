<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.dao.BoardDAO" %>
<%@ page import="board.bean.BoardDTO" %>
<% 

//데이터
int seq = Integer.parseInt(request.getParameter("seq"));
int pg = Integer.parseInt(request.getParameter("pg"));


//DB
BoardDAO boardDAO = BoardDAO.getInstance();
BoardDTO boardDTO = boardDAO.boardView(seq);

//응답



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
border-left: none;
border-right: none;
}
</style>
</head>
<body>
<form>
	<table border="1" cellspacing="0" cellpadding="5" frame="hsides" rules="rows" >
			<tr>
				<td colspan="3"><h3><%=boardDTO.getSubject() %></h3></td>
				<!--세칸묶음  -->
			</tr>
			
			<tr>
				<td  width="150" align="center">글번호: <%=boardDTO.getSeq() %></td>
				<td width="150"> 작성자: <%=boardDTO.getName() %> </td>
				<td width="150"> 조회수: <%=boardDTO.getHit() %> </td>
			</tr>

			<tr>
				<td colspan="3" width="100"  align="center" valign="top">
				<pre><%=boardDTO.getContent() %></pre></td>
			</tr>
			
			<tr>
				<td><input type="button" value="목록" onclick="location.href='boardWriteList.jsp?pg=<%=pg%>'"></td>
			</tr>
		 </table>
</form>
</body>
</html>
