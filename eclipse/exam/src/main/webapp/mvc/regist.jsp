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
		<h2>weq</h2>
		<table border =1>
		<tr><th>과목</th> <th>학점</th> <th>time slot</th></tr>
		<c:forEach  var = "course" items = "${courseList}">
			<tr>
				
				<td><a href="/exam/main/registForm.do?cid=${course.cid}">${course.cname}</a></td> 
				 <td>${course.degree}</td> 
	 			<td>${course.slot}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>