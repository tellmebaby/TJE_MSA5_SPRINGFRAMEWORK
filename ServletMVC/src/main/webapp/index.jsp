<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
</head>
<body>
	<h1>메인 화면</h1>
	<ur>
		<li><a href="<%=root%>/board/list.do">list (게시글 목록)</a></li>
	</ur>
</body>
</html>