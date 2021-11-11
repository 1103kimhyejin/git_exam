<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<%	for(int i=1; i<10; i++){%>
	<tr>
		<% 	for(int dan=1; dan<10; dan++){%>
		<td width="150" align="center"><%= dan %> * <%= i %> =  <%= dan*i %></td>
		<%}%>
	</tr>
<%}%>
</table>
</body>
</html>