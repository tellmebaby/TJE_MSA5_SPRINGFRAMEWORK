<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request/board</title>
</head>
<body>
	<h1>/request/board</h1>
	
	<h3>POST 요청</h3>
	<form action="<%= request.getContextPath() %>/request/board" method="post">
		<input type="text" name="no" placeholder="번호 입력" />
		<input type="submit" value="등록">
	</form>
	
	<h3>PUT 요청</h3>
	<form action="<%= request.getContextPath() %>/request/board" method="post">
		<!-- web.xml HiddenHttpMethodFilter 등록 -->
		<!-- _method 를 HTTP-Method-Override 헤더로 지정한다. -->
		<!-- X-HTTP-Method-Override 헤더 - 값 : PUT -->
		<input type="hidden" name="_method" value="PUT" />
		<input type="text" name="no" placeholder="번호 입력" />
		<input type="submit" value="등록">
	</form>
	
	
</body>
</html>