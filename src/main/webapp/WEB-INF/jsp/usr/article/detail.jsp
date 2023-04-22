<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail"/>
<%@ include file = "../common/head.jspf" %>
<div class="mt-8 text-xl">
		<table class="table-box-type-1">
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
								<th>${article.extra__writer }</th>
						</tr>
		</table>
</div>
<style type="text/css">
.table-box-type-1 {
	margin-left: auto;
	margin-right: auto;
	width: 700px;
	border: 2px solid black;
}
tr,th {
	border: 2px solid black;
}
</style>
<%@ include file = "../common/foot.jspf" %>