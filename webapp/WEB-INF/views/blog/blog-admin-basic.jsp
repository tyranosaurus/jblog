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
	$("input[type='file']").change(function(){
	   $file = $(this).val();
	   if($file == null || $.isEmptyObject($file)) return;
	   
	   var formData = new FormData(document.getElementById('uploadForm'));/* 인코딩 타입 지정해준거 */
	   
	   $.ajax({
	      url : "${pageContext.request.contextPath}/blog/${authUser.id}/imageUpdate",
	      data : formData,
	      processData : false,
	      contentType : false,
	      type : "POST",
	      success : function(response){
	         $("#logo-img").attr("src", "${pageContext.request.contextPath}" + response.data );
	         console.log(response.data);
	      },
	      error : function(xhr, status, error) {
	         console.error(status + " : " + error);
	      }
	   })
	})
	})

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