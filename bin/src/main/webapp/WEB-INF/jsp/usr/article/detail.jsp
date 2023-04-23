<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.KoreaIT.sdy.demo.vo.Article"%>
<%
	Article article = (Article) request.getAttribute("article");
	int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
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
		
		<div class="btn_box">
			<button class= "btn-text-link" type="button" onclick="history.back()">뒤로가기</button>
			
			<!-- ver 1 -->
			<c:if test="${article.actorCanDelete }">
				<a class= "btn-text-link" onclick="if(confirm('정말 삭제하시겠습니까?')==false) return false;" href="doDelete?id=${article.id }">삭제</a>
			</c:if>
			
			<!-- ver 2 -->
			<%if(loginedMemberId==article.getMemberId()) {%>
				<a class= "btn-text-link" >수정</a>
			<%} %>
		</div>
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
.btn_box {
	text-align: center;
}

.btn-text-link:hover{
	color: deepskyblue;
	text-decoration: underline;
	cursor: pointer;
}
</style>
<%@ include file = "../common/foot.jspf" %>