<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mvc.* , java.util.*"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	
		<form action="/exam/main/loginCheck.do" method="post">
			<table border="1">
			<tr><th>학번</th></tr>
			
			<tr><td><input type = "text" name="sid"></td></tr>
			<tr><th>비밀번호</th></tr>
			<tr><td><input type = "password" name="password"></td></tr>
			<tr><td><input type ="submit">로그인</td></tr>
			
			</table>
				
		</form>
	
	</div>
</body>
</html>