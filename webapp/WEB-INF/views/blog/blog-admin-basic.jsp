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

$(function() {
	$("#logo").click(
			function() {
				$.ajax({
							url : "/jblog/blog/basicmodify?email=" + email, //  요청 URL
							type : "get", // 통신방식 get/post 둘중 하나
							dataType : "json", //  수신 데이터 타입
							data : "", 
							success : function(response) { 

								if (response.result != "success") {
									return;
								}

								if (response.data == false) {
									alert("이미 존재하는 이메일 이다. 다른거 써라");
									$("#email").val("").focus();
									return;
								}

							},
							error : function(jqXHR, status, error) { // 실패시 callback
								console.error(status + ":" + error);
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
				<form action="/jblog/blog/${authUser.id}/basicmodify" enctype="multipart/form-data" method="post">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${blogVo.image}"></td>
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input id="logo" type="file" name="logo-file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
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