<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	font-size: 8pt;
	color: red;
	font-weight: bold;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="http://localhost:8080/mvcmemberr/js/member.js"></script>

</head>
<body>
<form name="loginForm" method="post" action="http://localhost:8080/mvcmemberr/member/login.do">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td width="100" align="center" >아이디</td>
				<td>
					<input type="text" name="id"  id="id" placeholder="아이디입력">
					<div id="idDiv"></div>
				</td>
			</tr>
			<tr>
				<td width="100" align="center" >비밀번호</td>
				<td>
					<input type="password" name="pwd"  id="pwd" placeholder="비밀번호입력">
					<div id="pwdDiv"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
					<input type="button" id="loginBtn" value="로그인" onclick="checkLogin()">
					<input type="button" id="writeBtn" value="회원가입" onclick="location.href='/mvcmemberr/member/writeForm.do';">
					<!-- https://localhost:8080/memberServlet/member/생략가능. 같은위치에있으니까 -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>