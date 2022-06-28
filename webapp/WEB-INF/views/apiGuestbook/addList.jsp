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

<!-- jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="wrap">

		<!-- //header -->
		<c:import url="/WEB-INF/views/includes/header_nav.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

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
					<!-- </form>	 -->
					<br>
					
					
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

</body>

<script type="text/javascript">

<!-- 준비가 끝나면 -->
$(document).ready(function(){
	console.log("jquery로 요청 - data만 받는 요청");
	fetchList();
});


/* 저장버튼(등록버튼)을 클릭했을때 */
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
			/*성공시 처리해야될 코드 작성*/
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
	str += '<table class="guestRead">';
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
	str += '		<td><a href="">[삭제]</a></td>';
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