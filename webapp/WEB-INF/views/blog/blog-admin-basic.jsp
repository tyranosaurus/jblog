<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">

$(function(){	
	//logo파일 주소가 바뀌었을 때
	$("#logo-check").on('click', function(){
		var form = new FormData(document.getElementById('uploadForm'));
		if (form == ""){
			return; }
		$.ajax({
			url:"/jblog/blog/logoupload/", 
			data: form,
			dataType: 'json',
			processData: false,
			contentType: false,
			type: "POST", 
			success: function(response){	
					console.log(response.data);
					$("#logo-img").attr("src", response.data);		
				},
			error: function(jqXHR,status,error){ 
				console.error(status+":"+error);
			}			
		});
	});
});


</script>



</head>
<body>
	<div id="container">
	
		<c:import url="/WEB-INF/views/include/blogmenu.jsp"/>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="/jblog/blog/${authUser.id}/category">카테고리</a></li>
					<li><a href="/jblog/blog/${authUser.id}/write">글작성</a></li>
				</ul>
				<form id = "uploadForm" action="/jblog/blog/${authUser.id}/basicmodify" enctype="multipart/form-data" method="post">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img id="logo-img" src="${blogVo.image}"></td>
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input id="logo" type="file" name="logo-file"></td>
			      		</tr>      			
			<!--       		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><button id = "logo-check">로고 확인</button></td>
			      		</tr> 이렇게 하면 버튼 눌렀을때 폼이 전송되서 로고확인이 아니라 아예 메인으로 돌아가는 것 같다.-->
			      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>   			
			      		</tr>           		
			      	</table>
				</form>
				
				<button id = "logo-check">로고 확인</button>
				
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