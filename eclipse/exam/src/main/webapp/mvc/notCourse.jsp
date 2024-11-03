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
<table border="1">

	<tr><th>과목명 </th><th>학점 </th><th>time slot </th></tr>
	<c:forEach var="course" , items="${}">
	
	</c:forEach>
	<tr><td></td></tr>
</table>
</body>
</html>