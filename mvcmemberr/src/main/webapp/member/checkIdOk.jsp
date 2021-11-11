<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
String id = request.getParameter("id");

%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<h3>${requestScope.id } 은(는) 사용하실 수 있습니다</h3>
<%-- 아이디 <%=id %> 사용 가능 --%>

<input type=button value="사용하기" id="checkIdClose" > <!-- onclick="useId(<%-- <%=id%> --%>)" -->
<input type="hidden" id="checkId" value="${requestScope.id }">
<!-- html 안에서 변수설정할때 hidden을 많이 쓴다.  -->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
<!-- /*<script type="text/javascript">
 $('#checkIdClose').click(function(){
	alert('${requestScope.id }');	//자바스크립트로가면 el태그를 모르니까 못쓰고 여기는 jsp파일이니까가능한것
}); */
</script> -->
</body>
</html>