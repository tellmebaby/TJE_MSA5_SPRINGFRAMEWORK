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
	
	<h3>POST - checkbox 요청</h3>
	<form action="<%= request.getContextPath() %>/request/check" method="post">
		
		<input type="checkbox" name="hobby" id="movie" value="movie" />
		<label for="movie">영화감상</label>
		
		<input type="checkbox" name="hobby" id="music" value="music" />
		<label for="music">음악감상</label>
		
		<input type="checkbox" name="hobby" id="book" value="book" />
		<label for="book">독서</label>
		
		<input type="submit" value="등록">
	</form>
	
	<h3>POST - 회원가입</h3>
	<form action="<%= request.getContextPath() %>/request/user" method="post">
		
		
		<label for="id">아이디</label>
		<input type="text" name="id" id="id" placeholder="아이디" />
		<br>
		
		<label for="name">이름</label>
		<input type="text" name="name" id="name" placeholder="이름" />
		<br>
		
		<input type="checkbox" name="hobby" id="movie" value="movie" />
		<label for="movie">영화감상</label>
		<br>
		
		<input type="checkbox" name="hobby" id="music" value="music" />
		<label for="music">음악감상</label>
		<br>
		
		<input type="checkbox" name="hobby" id="book" value="book" />
		<label for="book">독서</label>
		<br>
		
		<label for="birth">생일</label>
		<input type="date" name="birth" id="birth" />
		<br>
		
		<input type="submit" value="회원가입">
	</form>
	
	
	<h3>/request/file</h3>
	<form action="<%= request.getContextPath() %>/request/file" 
		  method="post" enctype="multipart/form-data">
	<input type="file" name="file"> <br>
	<input type="submit" value="업로드"> <br>
	</form>
	
	<hr>
	
	<h3>/request/file/multi</h3>
	<form action="<%= request.getContextPath() %>/request/file/multi" 
		  method="post" enctype="multipart/form-data">
	<input type="file" name="file" multiple> <br>
	<input type="submit" value="업로드"> <br>
	</form>
	
	<hr>
	
	<h3>/request/file/board</h3>
	<form action="<%= request.getContextPath() %>/request/file/board" 
		  method="post" enctype="multipart/form-data">
	<input type="text" name="title" placeholder="제목"> <br>
	<input type="text" name="writer" placeholder="작성자"> <br>
	<input type="text" name="content" placeholder="내용"> <br>
	<br>
	<input type="file" name="fileList" multiple> <br>
	<input type="submit" value="업로드"> <br>
	</form>
	<div style="height: 1000px;"></div>
	
	
	
</body>
</html>