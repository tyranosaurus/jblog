<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">

var CategoryList = function() {
	$.ajax({
		url : "/jblog/category/${authUser.id}/list2", /* ${pageContext.request.contextPath}/gb?a=ajax-list&p= */
		type : "get",
		dataType : "json",
		data : "",
		success : function(response) {
			if (response.result != "success") {
				return;
			}
				if (response.data.length == 0) {
				console.log("리스트가 없습니다.====================");
				return;
			}
			// HTML 생성한 후에 UL에 append 하는 작업 
			var length = response.data.length;
			$.each(response.data, function(index, vo) { // response.data에서 데이터를 하나씩 봅아서 함수 실행, response.data는 배열상태고 하나씩 뽑아서 vo에 넣는다.
					$("#ct-list").append(renderHtml(vo, length));
					length--;
				});
		},
		error : function(xhr, status, error) {
			console.error(status + ":" + error);
		}
	});
};
	
var renderHtml = function(vo, length){
	var html =
		"<tr class='categorylist'>" + 
			"<td>" + length + "</td>" +
			"<td>" + vo.name + "</td>" +
			"<td>" + vo.postNo + "</td>" +
			"<td>" + vo.description + "</td>" +
			"<td><a href='#' id='del' data-no='" + vo.no + "'>" +
			    "<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>" +
		"</tr>";
	return html;
};	
	
$(function() {
	
	$(document).on("click", "#del", function(){
		event.preventDefault();
		var no = $(this).attr("data-no");
		
		$.ajax({
			url:"/jblog/category/${authUser.id}/delete?no=" + no,
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				if(response.result != "success"){
					return;
				}

				$(".categorylist").remove();
				CategoryList();
			},
			error:function(xhr/*XMLHttpRequest*/, status, error){
				console.error(status + ":" + error);
			}
		})
	})
	
	$("#insertCate").on('click', function() {
		//console.log("버튼 잘 눌러짐");
		var cateName = $("#cateName").val();
		var cateDesc = $("#cateDesc").val();

		if (cateName == "") {
			return;
		}

		if (cateDesc == "") {
			return;
		}

		$.ajax({
			url : "/jblog/category/${authUser.id}/insert", // 요청 URL
			type : "post", // 통신방식 get/post 둘중 하나
			dataType : "json", //  수신 데이터 타입
			data : "name=" + cateName + "&desc=" + cateDesc, // post방식인 경우 서버에 전달할 파라미터 데이터 ex) a=checkemail&email=tyranosaurus@nate.com
			//contentType:"application/json",   // 보내는 데이터가 JSON형식인 경우 , 반드시 post방식인 경우로 보내야함 ex)data 부분 :  "{"a":"checkemail", email:afsdf@naver.com"}"
			success : function(response) { // 성공시 callback

				if (response.result != "success") {
					return;
				}

				if (response.data == null) {
					return;
				}
				
				$(".categorylist").remove();
				CategoryList();
			},
			error : function(jqXHR, status, error) { // 실패시 callback
				console.error(status + ":" + error);
			}
		});

	});
	CategoryList();
});
</script>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/include/blogmenu.jsp" />

		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="/jblog/blog/${authUser.id}/blogmanaging">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="/jblog/blog/${authUser.id}/write">글작성</a></li>
				</ul>
				<table id="ct-list" class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="cateName" type="text" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="cateDesc" type="text" name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="insertCate" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>