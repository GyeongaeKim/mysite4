<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/includes/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
					<c:if test="${!empty authUser }">
						<button id="btnImgUpload">이미지올리기</button>
					</c:if>
						<div class="clear"></div>
					
			
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${requestScope.galleryList}" var="galleryVo" varStatus="status">
							<li>
								<div class="view" >
									<img class="imgItem" src="">
									<div class="imgWriter">작성자: <strong>${authUser.name}</strong></div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
<!-- 모달창 **************************************************************-->		
<!-- 이미지등록 팝업(모달)창 -->
<div class="modal fade" id="addModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">이미지등록</h4>
				
			</div>
			
			<form action="${pageContext.request.contextPath }/gallery/addImg" method="post" enctype="multipart/form-data">
				<div class="modal-body">
					<div class="form-group">
						<label class="form-text">글작성</label>
						<input id="addModalUserNo" type="text" name="userNo" value="${authUser.no}" >
						<input id="addModalContent" type="text" name="content" value="" >
					</div>
					<div class="form-group">
						<label class="form-text">이미지선택</label>
						<input id="file" type="file" name="file" value="" >
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn" id="btnUpload">등록</button>
				</div>
			</form>
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- 이미지보기 팝업(모달)창 -->
<div class="modal fade" id="viewModal">
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">이미지보기</h4>
			</div>
			<div class="modal-body">
				
				<div class="formgroup" >
					<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				
				<div class="formgroup">
					<p id="viewModelContent"></p>
				</div>
				
			</div>
			<form method="" action="">
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<c:if test="${galleryVo.userNo == authUser.no }">
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
					</c:if>
				</div>
			</form>
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->	
<!-- 모달창 **************************************************************-->

</body>

<script type="text/javascript">

//이미지올리기 버튼 눌렀을 때
$("#btnImgUpload").on("click", function(){
	console.log("이미지올리기 버튼");
	
	
	//모달창의 내용 비우기
	$("#addModal [name=content]").val("");
	$("#addModal [name=file]").val("");
	
	//유저정보를 모달창의 폼에서 userNo 넣은 곳에 넣어주기
	//$("[name=userNo]").val(userNo);
	
	
	//모달창 띄우기
	$("#addModal").modal("show");
});


//이미지 클릭했을 때
$("#viewArea").on("click", ".view", function(){
	console.log("이미지 클릭!");
	
	//모달창 띄우기
	$("#viewModal").modal("show");
});



//이미지-모달창의 삭제버튼 클릭할때
$("#btnDel").on("click", function(){
	console.log("모달>삭제버튼 클릭");
	
	//삭제버튼의 no값 꺼내오기
	var $this = $(this);
	var no = $this.data("no");
	//console.log(no);
	
});


</script>

</html>

