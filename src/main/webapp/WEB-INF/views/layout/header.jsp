<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>

<style>
input::placeholder {
  color: white;
  width:100%;
}

.image-box {
	height: 350px;
	overflow: auto;
	object-fit: contain;
}

img {
	max-width: 50%;
	height: auto;
}

select {
	width: 200px; /* 원하는 너비설정 */
	padding: .8em .5em; /* 여백으로 높이 설정 */
	font-family: inherit; /* 폰트 상속 */
	background:
		url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg)
		no-repeat 95% 50%; /* 네이티브 화살표 대체 */
	border: 1px solid #999;
	border-radius: 4px;
	/* iOS 둥근모서리 제거 */
	-webkit-appearance: none;
	/* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}
</style>

<body>

	<nav class="navbar navbar-expand-md bg-white navbar-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse d-flex justify-content-center mt-3" id="collapsibleNavbar">
			<!-- auth 는 인증없이 들어갈수있다는 뜻 -->
			<c:choose>
				<c:when test="${empty principal}">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link" href="/">공지사항</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
					</ul>
				</c:when>


				<c:otherwise>
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link"></a></li>
						<li class="nav-item"><a class="nav-link" href="/">공지사항</a></li>
						<li class="nav-item"><a style="color: blue;" class="nav-link active" href="/board/saveForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/Api/address">지도</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link"></a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<br>

</body>
</html>