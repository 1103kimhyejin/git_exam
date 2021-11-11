<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/* //쿠키
Cookie[] ar = request.getCookies();//모든 쿠키들을 다 가져온다 (특정값만 꺼낼 수 없음)
if(ar !=null){
	for(int i=0; i<ar.length; i++){
		if(ar[i].getName().equals("memName")){
			ar[i].setMaxAge(0);//쿠키 삭제
			response.addCookie(ar[i]); //클라이언트 보내기
		}
		if(ar[i].getName().equals("memId")){
			ar[i].setMaxAge(0);//쿠키 삭제
			response.addCookie(ar[i]); //클라이언트 보내기
		}
	}
} */

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>로그아웃</h3>
<script type="text/javascript">
window.onload=function(){
	alert("로그아웃");
	location.href = "../index.jsp";
}
</script>
</body>
</html>