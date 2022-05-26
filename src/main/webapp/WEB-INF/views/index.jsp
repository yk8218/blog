<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header2.jsp"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.min.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="stylesheet" href="/css/index.css">

<style>
@import url("https://fonts.googleapis.com/css?family=Roboto+Mono");

* {
	box-sizing: border-box;
	font-weight: normal;
}

.flip {
	position: relative;
	opacity: 0.9;
}

.flip>.front, .flip>.back {
	display: block;
	transition-timing-function: cubic-bezier(0.175, 0.885, 0.32, 1.275);
	transition-duration: 0.5s;
	transition-property: transform, opacity;
}

.flip>.front {
	transform: rotateY(0deg);
}

.flip>.back {
	position: absolute;
	opacity: 0;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	transform: rotateY(-180deg);
}

.flip:hover>.front {
	transform: rotateY(180deg);
}

.flip:hover>.back {
	opacity: 1;
	transform: rotateY(0deg);
}

.flip.flip-vertical>.back {
	transform: rotateX(-180deg);
}

.flip.flip-vertical:hover>.front {
	transform: rotateX(180deg);
}

.flip.flip-vertical:hover>.back {
	transform: rotateX(0deg);
}

.flip {
	position: relative;
	display: inline-block;
	margin-right: 2px;
	margin-bottom: 1em;
	width: 400px;
}

.flip>.front, .flip>.back {
	display: block;
	color: white;
	width: inherit;
	background-size: cover !important;
	background-position: center !important;
	height: 220px;
	padding: 1em 2em;
	background: #313131;
	border-radius: 10px;
}

.flip>.front p, .flip>.back p {
	font-size: 0.9125rem;
	line-height: 160%;
	color: #999;
}

.text-shadow {
	text-shadow: 1px 1px rgba(0, 0, 0, 0.04), 2px 2px rgba(0, 0, 0, 0.04),
		3px 3px rgba(0, 0, 0, 0.04), 4px 4px rgba(0, 0, 0, 0.04), 0.125rem
		0.125rem rgba(0, 0, 0, 0.04), 6px 6px rgba(0, 0, 0, 0.04), 7px 7px
		rgba(0, 0, 0, 0.04), 8px 8px rgba(0, 0, 0, 0.04), 9px 9px
		rgba(0, 0, 0, 0.04), 0.3125rem 0.3125rem rgba(0, 0, 0, 0.04), 11px
		11px rgba(0, 0, 0, 0.04), 12px 12px rgba(0, 0, 0, 0.04), 13px 13px
		rgba(0, 0, 0, 0.04), 14px 14px rgba(0, 0, 0, 0.04), 0.625rem 0.625rem
		rgba(0, 0, 0, 0.04), 16px 16px rgba(0, 0, 0, 0.04), 17px 17px
		rgba(0, 0, 0, 0.04), 18px 18px rgba(0, 0, 0, 0.04), 19px 19px
		rgba(0, 0, 0, 0.04), 1.25rem 1.25rem rgba(0, 0, 0, 0.04);
}
/* ================================ HEADER ============================= */
#remote {
	position: sticky;
	top: 10px;
	left: 92%;
	transition: 0.5s; /*서서히 나타내기*/
	z-index: 2;
}

#remoteb:hover {
	color: #BDBDBD;
	transition: 0.35s; /*서서히 나타내기*/
}

button, button::after {
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
}

button {
	width: 180px;
	height: 44px;
	background: none;
	border: 2px solid #1c3b58;
	border-radius: 5px;
	color: rgb(32, 32, 32, 0.7);
	display: block;
	font-size: 0.9em;
	font-weight: bold;
	padding: 0.5em 1em;
	position: relative;
	text-transform: uppercase;
	font-family: 'Nanum Gothic', sans-serif;
}

button::before, button::after {
	background: #1c3b58;
	border-radius: 5px;
	content: '';
	position: absolute;
	z-index: -1;
}

button:hover {
	color: #FFFFFF;
}

.title1 {
	animation: fadeInUp;
	animation-duration: 4s;
	animation-delay:;
	color: red;
}

.title2 {
	animation: fadeInUp;
	animation-duration: 4.5s;
	animation-delay:;
}

.title3 {
	animation: fadeInUp;
	animation-duration: 5s;
	animation-delay:;
}

.title4 {
	animation: fadeInUp;
	animation-duration: 5s;
	animation-delay:;
}

#sa {
	display: inline-block;
	margin: 0;
	font-size: 2.0ex;
	text-transform: uppercase;
	text-decoration: none;
	font-family: 'Josefin Sans', sans-serif;
}
</style>
















	<div class="container mt-4">

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th class="text-center">NUMBER</th>
					<th class="text-center">TITLE</th>
					<th class="text-right">USERNAME&nbsp;</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="board" items="${boards.content}">

						<tr>
							<td class="text-center">${board.id}</td>
							<td class="text-center"><a href="/board/${board.id}" style="color: rgba(23, 23, 23, 0.99);">${board.title}</a></td>
							<td class="text-right">${board.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr>

				</c:forEach>
			</tbody>
		</table>

	</div>

<!-- ========================== 리모컨 type.1 ========================== -->


<!-- ========================== 리모컨 type.2 ========================== -->

<c:if test="${boards.totalPages<=boards.number}">
	<span class="d-flex justify-content-center mt-1"> <a style="color: red; font-size: 14px;" href="?page=${boards.totalPages-1}">이페이지는 아직 공지사항이 게시되어있지 않습니다(누르면 마지막 페이지로 돌아갑니다)</a>
	</span>
</c:if>
<!-- justify-content-center 부트스트랩 flex 정렬할때 문법 -->


<ul class="pagination justify-content-center mt-5">

	<c:choose>
		<c:when test="${boards.first}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a style="color: #FE7348" class="page-link" href="?page=${boards.number-1}">Previous</a></li>
		</c:otherwise>
	</c:choose>

	<c:if test="${boards.number<9}">
		<c:forEach var="i" begin="0" end="9">


			<c:if test="${null ne param.stype }">
				<li class="page-item"><a style="color: #F79E5F" id="moveMove" class="page-link" href="?page=${i}&stype=${param.stype}"> ${i+1}</a></li>
			</c:if>
			<c:if test="${null eq param.stype }">
				<li class="page-item"><a style="color: #F79E5F" id="moveMove" class="page-link" href="?page=${i}"> ${i+1}</a></li>
			</c:if>
		</c:forEach>
	</c:if>

	<c:if test="${boards.number>=9}">
		<c:forEach var="i" begin="${boards.number-5}" end="${(boards.number+5)}">
			<li class="page-item"><a style="color: #F79E5F" class="page-link" href="?page=${i}"> ${i+1} </a></li>
		</c:forEach>
	</c:if>




	<c:choose>
		<c:when test="${boards.last}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:when>

		<c:otherwise>
			<li class="page-item"><a style="color: #FE7348" class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:otherwise>
	</c:choose>

</ul>
<div style="padding-bottom: 300px;"></div>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://use.fontawesome.com/releases/v5.9.0/js/all.js"></script>
<%@ include file="layout/footer.jsp"%>