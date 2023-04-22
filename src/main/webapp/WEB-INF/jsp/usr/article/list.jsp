<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article List" />
<%@ include file="../common/head.jspf"%>

<div class="mt-8 text-xl">
		<table class="table-box-type-1">
				<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성날짜</th>
						<th>작성자</th>
				</tr>

				<c:forEach var="article" items="${articles }">
						<tr>
								<th>${article.id }</th>
								<th>
										<a class="title" href="detail?id=${article.id }">${article.title }</a>
								</th>
								<th>${article.regDate.substring(0,10) }</th>
								<th>${article.memberId }</th>
						</tr>

				</c:forEach>
		</table>
</div>

<style type="text/css">
.title {
	color: gray;
}
.title:hover {
	text-decoration: underline;
	color: black;
}

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

<%@ include file="../common/foot.jspf"%>