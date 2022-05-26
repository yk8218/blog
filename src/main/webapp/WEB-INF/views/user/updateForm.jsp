<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header2.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">Username</label> <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username" id="username" readonly>
		</div>

		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password</label> 
				<input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
		
		    <div class="form-group">
			<label for="email">Email</label> 
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email"readonly>
	     	</div>
		<small>
		<a>아이디:${principal.user.id}</a>
		<a>이메일:${principal.user.email}</a>
		<a>권한:${principal.user.role}${principal.user.rule}</a>
		<a>연동:${principal.user.oauth}</a>
		</small>
		
	</form>
	<button id="btn-update" class="btn btn-primary mt-2">회원수정완료</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>