<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${requestScope.id }은(는) 이미 사용 중입니다</h3>
	<%-- 아이디 <%=id %> 사용 불가능<br><br> --%>
	<form action="/mvcmemberr/member/checkId.do">
		<table>
			<tr>
				<td width="100" align="center">아이디</td>
				<td><input type="text" name="id" id="id"></td>
				<td><input type="submit" value="중복체크">
				<!-- 타입을 서브밋으로해야 데이터를! 들고간다-->
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/member.js"></script>
</body>
</html>