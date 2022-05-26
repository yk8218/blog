<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header2.jsp"%>
<style>
/* ============================= TIP =========================== */
.help-tip {
	position: fixed;
	top: 18px;
	right: 18px;
	text-align: center;
	background-color: #302F2F;
	border-radius: 50%;
	width: 24px;
	height: 24px;
	font-size: 10px;
	line-height: 26px;
	cursor: default;
}

.help-tip:before {
	content: 'TIP';
	font-weight: bold;
	color: #fff;
}

.help-tip:hover p {
	display: block;
	transform-origin: 100% 0%;
	-webkit-animation: fadeIn 0.3s ease-in-out;
	animation: fadeIn 0.3s ease-in-out;
}

.help-tip p {
	display: none;
	text-align: center;
	background-color: #1E2021;
	border: 1px solid #777575;
	padding: 20px;
	width: 350px;
	position: absolute;
	border-radius: 3px;
	box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);
	right: -4px;
	color: #FFFFFF;
	font-size: 13px;
	line-height: 1.4;
	padding: 20px;
}

.help-tip p:before {
	position: absolute;
	content: '';
	width: 0;
	height: 0;
	border: 6px solid transparent;
	border-bottom-color: #1E2021;
	right: 10px;
	top: -12px;
}

.help-tip p:after {
	width: 100%;
	height: 40px;
	content: '';
	position: absolute;
	top: -40px;
	left: 0;
}
</style>


<div class="help-tip">
	<p>
		- TIP - <br>Ctrl+Shift+E로 가운데 정렬을 할수있습니다 <br>관리자는 체크박스가 보인다면 공지글을 작성할수있습니다.
	</p>
</div>

<div style="margin-bottom: 700px;" class="container">

	<form action="" method="post">
	
	<c:if test="${'admin' ne principal.user.username}">
    	<div class="row d-flex justify-content-center mb-2">
			<input style="width: 1110px" type="text" class="form-control" placeholder="Enter title" id="title"> 
		</div>
	</c:if>
	
	<c:if test="${'admin' eq principal.user.username}">
		<div class="row d-flex justify-content-center mb-2">
			<input style="width: 1045px" type="text" class="form-control" placeholder="Enter title" id="title"> 
			<input style="width: 40px" type="checkbox" class="form-control ml-4 d-flex justify-content-end" value="1" id="type">
		</div>
	</c:if>
		<div class="form-group">
		- 작성자 -<input type="text" class="form-control" value="${principal.user.username}" id="username">
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary mb-2">글쓰기 완료</button>






</div>

<script>
	$('.summernote').summernote(

			{
				placeholder : 'Hello stand alone ui',
				tabsize : 2,
				height : 450,
				toolbar : [ [ 'style', [ 'style' ] ],
						[ 'font', [ 'bold', 'underline', 'clear' ] ],
						[ 'color', [ 'color' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'table', [ 'table' ] ],
						[ 'insert', [ 'link', 'picture', 'video' ] ],
						[ 'view', [ 'fullscreen', 'codeview', 'help' ] ] ]
			});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



<!-- 글쓰기 디자인 https://summernote.org/getting-started/#without-bootstrap -->