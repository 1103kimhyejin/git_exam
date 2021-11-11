<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#currentPagingA {
	color: red;
	text-decoration: underline;
}

#pagingA {
	color: black;
	text-decoration: none;
}

table {
	border-left: none;
	border-right: none;
}

td {
	border-left: none;
	border-right: none;
}

#subjectA:link {
	color: black;
	text-decoration: none;
}

#subjectA:visited {
	color: black;
	text-decoration: none;
}

#subjectA:hover {
	color: green;
	text-decoration: underline;
} /* 호버만 있어도 되는데 a태그에서 안될때가 있어서 네개 다 쓴다  */
#subjectA:active {
	color: black;
	text-decoration: none;
}
</style>
</head>
<body>
	<table border="1" cellspacing="0" cellpadding="5" frame="hsides"
		rules="rows">
		<tr>
			<th width="100">글번호</th>
			<!-- 헤더는 알아서 가운데정렬 해준다  -->
			<th width="100">제목</th>
			<th width="100">작성자</th>
			<th width="100">조회수</th>
			<th width="100">작성일</th>
		</tr>

		<c:if test="${list!=null }">
			<c:forEach var="boardDTO" items="${list }">

				<tr>
					<td align="center">${ boardDTO.seq}</td>
					<td width="100" align="center"><a
						href='boardView.jsp?seq=${ boardDTO.seq}&pg=${pg}' id="subjectA">
							${boardDTO.subject}</a></td>
					<td width="100" align="center">${boardDTO.name}</td>
					<td width="100" align="center">${boardDTO.hit}</td>
					<td width="100" align="center">${boardDTO.logtime}</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>

	<div style="width: 750px; text-align: center;">
		<c:forEach var="i" begin="1" end="${totalP }">
			<c:if test="${i == pg }">
				[<a id="currentPagingA" href="/mvcmemberr/board/boardList.do?pg=${i }">${i }</a>]
			</c:if>
			<c:if test="${i != pg }" >
				[<a id="pagingA" href="/mvcmemberr/board/boardList.do?pg=${i }">${i }</a>]
			</c:if>
		</c:forEach>
	</div>

</body>
</html>



























