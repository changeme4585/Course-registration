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
	<a href="/exam/main/apply.do">수강신청   </a>
	 <a href="/exam/main/login.do">로그아웃 </a><br>

 	<table border =1>
 			<tr><th colspan="3" style="text-align: center;">${name}님의 수강과목</th></tr>
 		 <tr><th>과목</th> <th>학점</th> <th>time slot</th></tr>
 		 <c:forEach  var = "myCourse" items = "${myCourseList}">
 		 <tr> <td><a href= "/exam/main/delete.do?cid=myCourse.cid">${myCourse.cname}</a></td>
	 		 <td>${myCourse.degree}</td> 
	 		<td>${myCourse.slot}</td>
 		 </tr>
 		 </c:forEach>
 	</table>
 	</div>
</body>
</html>	