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
	<h2>수강 취소 페이지</h2>
	<input type="hidden" name="cid" value="${courseVO.cid}">

			과목명: ${courseVO.cname} <br><br>
			학점수: ${courseVO.degree} <br><br>
			담당교수: ${courseVO.lecturer} <br><br>
			Time slot: ${courseVO.slot} <br><br>
</body>
</html>