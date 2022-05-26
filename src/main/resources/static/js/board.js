let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
		$("#btn-productsave").on("click", () => {
			this.productsave();
		});
		$("#btn-productdelete").on("click", () => {
			this.productdeleteById();
		});
		$("#btn-productupdate").on("click", () => {
			this.productupdate();
		});
		$("#btn-product-reply-save").on("click", () => {
			this.productreplySave();
		});
		$("#btn-favorite").on("click", () => {
			this.favoriteSave();
			this.favoritemupdate();
		});
		$("#btn-product-reply-reply-save").on("click", () => {
			this.productreplyreplySave();
		});
		$("#btn-favoritejim").on("click", () => {
			this.favoritejim();
		});
		$("#btn-favoritejimu").on("click", () => {
			this.favoritejimu();
		});
		$("#btn-count").on("click", () => {
			this.productupdatecount();
		});
		$("#btn-deliveryupdate").on("click", () => {
			this.deliveryupdate();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			type: $("#type").val(),
			username: $("#username").val()
			
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},

	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	replySave: function() {
		let data = {
			content: $("#reply-content").val(),
			username: $("#username").val()
		};
		let boardid = $("#boardid").val();
		
		$.ajax({
			type: "POST",
			url: "/api/board/"+boardid+"/reply/",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp){
			alert("댓글 작성 완료..");
			location.href = "/board/"+boardid
		}).fail(function(error){
			alert(JSON.stringify(error));
		});

	},
	
	replyDelete: function(boardid, replyid) {
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+boardid+"/reply/"+replyid,
			dataType: "json"
		}).done(function(resp){
			alert("댓글 삭제 성공.");
			location.href = "/board/"+boardid
		}).fail(function(error){
			alert(JSON.stringify(error));
		});

	},
	
	productsave: function() {
		let data = {
			title: $("#title").val(),
			adminname: $("#adminname").val(),
			content: $("#content").val(),
			price: $("#price").val(),
			discount: $("#discount").val(),
			discountpercentage: $("#discountpercentage").val(),
			type1: $("#type1").val(),
			type2: $("#type2").val(),
			fee: $("#fee").val(),
			info: $("#info").val(),
			img1: $("#img1").val(),
			img2: $("#img2").val(),
			img3: $("#img3").val(),
			img4: $("#img4").val(),
			mcount: $("#mcount").val(),
			username: $("#username").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/productboard",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("상품등록이 완료되었습니다.");
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	
	
	
		productdeleteById: function() {
		let id = $("#id").val();
		

		$.ajax({
			type: "DELETE",
			url: "/api/productboard/"+id,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},

	productupdate: function() {
		let id = $("#id").val();


		let data = {
			title: $("#title").val(),
			adminname: $("#adminname").val(),
			content: $("#content").val(),
			price: $("#price").val(),
			discount: $("#discount").val(),
			discountpercentage: $("#discountpercentage").val(),
			type1: $("#type1").val(),
			type2: $("#type2").val(),
			fee: $("#fee").val(),
			info: $("#info").val(),
			img1: $("#img1").val(),
			img2: $("#img2").val(),
			img3: $("#img3").val(),
			img4: $("#img4").val(),
			mcount: $("#mcount").val(),
			username: $("#username").val()
		};

		$.ajax({
			type: "PUT",
			url: "/api/productboard/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("제품글수정 완료했습니다.");
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	
	productreplySave: function() {
		let data = {
			content: $("#product-reply-content").val(),
			kinds: $("#kinds").val(),
            starpoint1: $("input:radio[name='starpoint']:checked").val(),
			username: $("#username").val()
		};
		let productboardid = $("#productboardid").val();
		
		$.ajax({
			type: "POST",
			url: "/api/productboard/"+productboardid+"/reply/",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp){
			alert("댓글 작성 완료...");
			location.href = "/productboard/"+productboardid
		}).fail(function(error){
			alert(JSON.stringify(error));
		});

	},
	
	productreplyDelete: function(productboardid, productreplyid) {
		$.ajax({
			type: "DELETE",
			url: "/api/productboard/"+productboardid+"/productreply/"+productreplyid,
			dataType: "json"
		}).done(function(resp){
			alert("댓글 삭제 성공.");
			location.href = "/productboard/"+productboardid
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	
	productreplyreplySave: function(replyreplyboardid, replyreplyreplyid) {
		let data = {
			content: $("#replyreplycontent").val(),
			replyreplyid: $("#replyreplyid").val(),
			username: $("#replyreplyusername").val()
		};
		
		let productboardid = $("#productboardid").val();

		$.ajax({
			type: "POST",
			url:  "/api/replyproductboard/"+productboardid+"/replyreply/",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("대답글 작성이 완료되었습니다.");
			location.href = "/productboard/"+productboardid
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	productreplyreplyDelete: function(productboardid, productreplyreplyid) {
		$.ajax({
			type: "DELETE",
			url: "/api/replyproductboard/"+productboardid+"/replyreply/"+productreplyreplyid,
			dataType: "json"
		}).done(function(resp){
			alert("댓글 삭제 성공.");
			location.href = "/productboard/"+productboardid
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	


	
	
	
		favoriteSave: function() {
		let data = {
			title: $("#title").val(),
			price: $("#price").val(),
			discountpercentage: $("#discountpercentage").val(),
			fee: $("#fee").val(),
			info: $("#info").val(),
			img1: $("#img1").val(),
			username: $("#username").val(),
			adminusername: $("#adminname").val(),
			ordercount: $("#mcount2").val(),
			orderaddress: $("#orderaddress").val(),
			ordercontent: $("#ordercontent").val(),
			type: $("#type").val(),
			productido: $("#productido").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/productboard/favorite",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("상품을 장바구니에 담았습니다.");
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	favoritemupdate: function() {
		let id = $("#id").val();

		let data = {
			mcount: $("#mcount").val()-$("#mcount2").val(),
		};

		$.ajax({
			type: "PUT",
			url: "/api/productboardmc/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	
	favoritejim: function() {
		let data = {
			fname: $("#fname").val(),
			ftitle: $("#ftitle").val(),
			fvalue: $("#fvalue").val(),
			id: $("#fid").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/productboardjim",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("찜하기가 완료되었습니다.");
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	favoritejimu: function() {
		let id = $("#fid").val();
		
		let fid = $("#ffid").val();
		

		$.ajax({
			type: "DELETE",
			url: "/api/productboardjim/"+fid,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("즐겨찾기 해제가 완료되었습니다.");
			location.href = "/productpageindex";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	
	
	productupdatecount: function() {
		let id = $("#id").val();

		let data = {
			count: $("#count").val()
		};
		
		
		$.ajax({
			type: "PUT",
			url: "/api/productboardcount/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("조회수 수정 완료.");
			location.href = "/productboard/"+id
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
		productcount: function(productboardid, productboardcount) {
		let data = {
			count: productboardcount
		};
		$.ajax({
			type: "PUT",
			url: "/api/productboardcount/"+productboardid+"/count/"+productboardcount,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			location.href = "/productboard/"+productboardid
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	deliveryupdate:  function(favoriteid, favoritedelivery) {
		let data = {
			delivery: favoritedelivery
		};

		$.ajax({
			type: "PUT",
			url: "/api/favoriteid/"+favoriteid+"/favoritedelivery/"+favoritedelivery,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("배송완료 처리되었습니다.");
			location.href = "/productfavorite";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	

}

index.init();