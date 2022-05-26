<%@page import="com.cos.blog.model.User"%>
<%@page import="com.cos.blog.config.auth.PrincipalDetail"%>
<%@page import="com.cos.blog.model.Board"%>
<%@page import="org.springframework.security.oauth2.core.user.OAuth2User"%>
<%@page import="org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header2.jsp"%>

<div class="container">


	
	<span></span> 
	<br /> 
	<br />
	<div>
		글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i> ${board.username} </i></span> <br />
	</div>
	<div class="form-group">
		<h3>${board.title}</h3>
	</div>

	<hr />

	<div class="form-group">
		<div class="mt-3 mb-3">${board.content}</div>
	</div>
	<hr />
	<button class="btn btn-secondary mb-3" onclick="history.back()">돌아가기</button>
	<c:if test="${board.username==principal.user.username}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning mb-3">수정</a>
		<button id="btn-delete" class="btn btn-danger mb-3">삭제</button>
	</c:if>


	<div class="card">
		<form>
			<input id="boardid" type="hidden" value="${board.id}" />
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" value="${principal.user.username}" id="username">
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
	<br />
	<div style="margin-bottom:500px;" class="card">
		<div class="card-header">댓글 리스트${reply.id}</div>

		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic mr-1">작성자 : ${reply.user.username}</div>
						<c:if test="${reply.username==principal.user.username}">
							<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>

	</div>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


<!-- 글쓰기 디자인 https://summernote.org/getting-started/#without-bootstrap -->