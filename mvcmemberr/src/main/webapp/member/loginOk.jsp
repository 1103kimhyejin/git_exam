<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name = null;
String id =null;

/* //쿠키
Cookie[] ar = request.getCookies();//모든 쿠키들을 다 가져온다 (특정값만 꺼낼 수 없음)
if(ar !=null){
	for(int i=0; i<ar.length; i++){
	
		String cookieName = ar[i].getName();//쿠키명
		String cookieValue = ar[i].getValue(); //값
		
		System.out.println("쿠키명 = " + cookieName);
		System.out.println("값 = "+ cookieValue);
		//if(ar[i]) 
		if(cookieName.equals("memName")) name = cookieValue;
		if(cookieName.equals("memId")) id = cookieValue;
	}
} */
//name = (String)session.getAttribute("memName");
//id = (String)session.getAttribute("memId");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img{
	width: 100px;
	height: 100px;
	cursor: pointer;
	}
</style>
</head>
<body>
<%-- <%=name %>님 로그인  --%>
<img src="../image/스폰지밥.png" onclick="location.href='../index.jsp'">
${sessionScope.memName }님 로그인
<%--  ${requestScope.name }님 로그인--%>
<br><br>

<input type="button" value="로그아웃" onclick="location.href='/mvcmemberr/member/logout.do'">
<input type="button" value="회원정보수정" onclick="location.href='/mvcmemberr/member/modifyForm.do'">
</body>
</html>