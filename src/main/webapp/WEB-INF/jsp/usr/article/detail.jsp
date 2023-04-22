<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기 페이지</title>
</head>
<body>
		<h1>Article Detail</h1>

		<hr />
		<table border="1">
						<tr>
								<th>번호</th>
								<th>${article.id }</th>
						</tr>
						
						
						<tr>
								<th>제목</th>
								<th>${article.title }</th>
						</tr>
						
						<tr>
								<th>내용</th>
								<th>${article.body }</th>
						</tr>
						
						<tr>
								<th>작성날짜</th>
								<th>${article.regDate}</th>
						</tr>
						<tr>
								<th>수정날짜</th>
								<th>${article.updateDate}</th>
						</tr>
						
						<tr>
								<th>작성자</th>
								<th>${article.memberId }</th>
						</tr>
		</table>


</body>
</html>