<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- CSS -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>



</head>
<body>
	<div id="wrap">

		<!-- //header -->
		<c:import url="/WEB-INF/views/includes/header_nav.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/boardAside.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<%-- <form action="${pageContext.request.contextPath}/api/guestbook/add" method="get"> --%>
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
					<br>
					<!-- </form>	 -->
					
					
					<!-- 리스트 영역 -->
					<div id="listArea">
					</div>
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- //footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
	<!-- //wrap -->


<!-- 모달창 **************************************************************-->
<!-- 삭제모달창 -->
<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호를 입력하세요</h4>
      </div>
      <div class="modal-body">
      
      	<!-- <p>One fine body&hellip;</p> -->
      	<!-- 비밀번호 입력폼 -->
      	비밀번호<input type="text" name="password" value="">
      	<br><input type="text" name="no" value="">
  
  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
        <button id="btnModalDel" type="button" class="btn btn-primary">삭제</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 모달창 **************************************************************-->


</body>

<script type="text/javascript">

<!-- 준비가 끝나면 -->
$(document).ready(function(){
	console.log("jquery로 요청 - data만 받는 요청");
	fetchList();
});




/* 저장버튼(등록버튼)을 클릭했을때2 */
//jquery요청(json)
$("#btnSubmit").on("click", function(){
	console.log("저장버튼 클릭");
	
	//데이터 수집
	var name = $("[name=name]").val();
	var password = $("[name=password]").val();
	var content = $("[name=content]").val();
	
	var guestVo = {
		name: name, 
		password: password, 
		content: content
	};
	
	
	console.log(guestVo);
	
	
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/add2",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(guestVo),		//js객체를 JSON문자열로 변경->그래야 controller에서 @RequestBody로 받을 수 있음

		dataType : "json",
		success : function(gVo){
			//성공시 처리해야될 코드 작성
			console.log(gVo);
			
			//1개의 데이터 리스트에 추가(그리기)
			render(gVo, "up");
			
			
			//데이터 저장후, 입력폼에 있는 내용 사라지게 하기.
			$("[name=name]").val("");
			$("[name=password]").val("");
			$("[name=content]").val("");
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

	
});















/* 저장버튼(등록버튼)을 클릭했을때1 */
/* 기존방식-jquery요청(파라미터로 전송)
$("#btnSubmit").on("click", function(){
	console.log("저장버튼 클릭");
	
	//데이터 수집
	var name = $("[name=name]").val();
	var password = $("[name=password]").val();
	var content = $("[name=content]").val();
	
	var guestVo = {
		name: name, 
		password: password, 
		content: content
	};
	
	$.ajax({
			
		//url : "${pageContext.request.contextPath }/api/guestbook/add?name="+name+"&password="+password+"&content="+content,
		url : "${pageContext.request.contextPath }/api/guestbook/add",
		type : "post",
		//contentType : "application/json",
		data : guestVo,	//여기 데이터는 url에 파라미터로 간다

		dataType : "json",
		success : function(gVo){
			//성공시 처리해야될 코드 작성
			console.log(gVo);
			
			//1개의 데이터 리스트에 추가(그리기)
			render(gVo, "up");
			
			
			//데이터 저장후, 입력폼에 있는 내용 사라지게 하기.
			$("[name=name]").val("");
			$("[name=password]").val("");
			$("[name=content]").val("");
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});
*/

//리스트의 삭제버튼을 눌렀을때 -> 삭제버튼은 listArea안에 들어있다는 것을 잊지말자..!
$("#listArea").on("click", ".btnDel", function(){
	console.log("리스트>삭제버튼 클릭");
	
	//삭제버튼의 no값 꺼내오기
	var $this = $(this);
	var no = $this.data("no");	//data-no라고 썼으니까..
	//console.log(no);
	
	
	//모달창의 비밀번호만 비우기
	$("#delModal [name=password]").val("");
	//꺼낸 no를 모달창의 폼에서 no값 넣는 곳에 넣어주기
	$("[name=no]").val(no);
	
	
	//모달창 띄우기
	$("#delModal").modal("show");

});


//모달창의 삭제버튼 클릭할때
$("#btnModalDel").on("click", function(){
	console.log("모달>삭제버튼 클릭")
	
	//삭제할 데이터 모으기
	var password = $("#delModal [name=password]").val();
	var no = $("#delModal [name=no]").val();
	
	//모은 데이터는 vo로 묶어주기(2가지 방법 중 하나 골라서 하면 됨)
	var guestbookVo = {
		password: password,
		no: no
	};
	/*
	var guestbookVo = {};
	guestbookVo.password = password;
	guestbookVo.no = no;
	*/
	console.log(guestbookVo);
	
	
	//서버로 데이터 전송(ajax)
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/remove",		
		type : "post",
		//contentType : "application/json",
		data : guestbookVo,	//위에서 vo로 묶은 데이터는 여기에~

		dataType : "json",
		success : function(result){
			/*성공시 처리해야될 코드 작성*/
			console.log(result);
			
			//성공인지, 아닌지~
			if(result == "success"){
				//성공하면(비밀번호가 일치하면), 데이터 삭제하기
				$("#t"+no).remove();	//아래의 render에서 table의 id를 t28 이런식으로 적용하였다**
				//삭제 완료되면, 모달창 닫기
				$("#delModal").modal("hide");
			}else{
				$("#delModal").modal("hide");
				alert("비밀번호를 확인하세요");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});


//리스트 요청
function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		contentType : "application/json",
		data : {name: name},

		dataType : "json",
		success : function(guestbookList){	//function()안의 이름은 알아서 정하면 됨
			/*성공시 처리해야될 코드 작성*/
			console.log(guestbookList);
			//console.log(guestbookList[0].name);
			
			//화면에 data + html로 띄운다(그린다).
			//리스트니까 for문으로 그리기!
			for(var i=0; i<guestbookList.length; i++){
				render(guestbookList[i], "down");	//vo --> 화면에 그린다.
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
};


//리스트 그리기
function render(guestbookVo, opt){	//opt 옵션을 추가한다.
	console.log("render()");
	//var name = guestbookVo.name;
	
	var str = '';
	str += '<table id="t'+guestbookVo.no+'" class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>'+guestbookVo.no+'</td>';
	str += '		<td>'+guestbookVo.name+'</td>';
	str += '		<td>'+guestbookVo.regDate+'</td>';
	str += '		<td><button class="btnDel" type="button" data-no="' +guestbookVo.no+ '" data-age="">삭제</button></td>';	//data는 여러개 설정가능
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
	str += '	</tr>';
	str += '</table>';
	
	
	if(opt == "down"){
		$("#listArea").append(str);
	}else if(opt == "up"){
		$("#listArea").prepend(str);
	}else{
		console.log("opt오류");
	}
}


</script>
</html>