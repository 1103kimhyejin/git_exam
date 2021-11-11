<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
	color:red;
	font-size:9pt;
	font-weight:bold;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/boardJSP/js/board.js"></script>
</head>
<body>
<h3>글쓰기</h3>
<form name = boardwriteForm method=post action="/mvcmemberr/board/boardWrite.do">
	<table border=1 cellspacing="0">
		<tr>
			<td>제목</td>
			<td>
				<input type=text name=subject id=subject><!-- 서브밋은 네임속성 있어야한다  -->
				<div id=subjectDiv></div>
			</td>	
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name=content id=content rows="10" cols="25"></textarea>
				<div id=contentDiv></div>
			</td>	
		</tr>
		<tr>
		<td colspan="2" align="center">
			<input type="submit" id=boardwriteBtn value="글쓰기" >
			<input type="reset" value="다시작성">
		</td>
	</tr>
	</table>
</form>

</body>
</html>