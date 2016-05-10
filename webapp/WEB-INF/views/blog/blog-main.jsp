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
</head>
<body>
	<div id="container">
	
		<c:import url="/WEB-INF/views/include/blogmenu.jsp"/>
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title}</h4>
					<p>
						${postVo.content}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="postVo" varStatus="status">
						<li><a href="/jblog/blog/${authUser.id}?cateNo=${categoryNo}&postNo=${postVo.no }">${postVo.title }</a> <span>${postVo.regDate }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${blogVo.image}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2> 
			<ul>
			
				<c:forEach items="${cateList}" var="categoryVo" varStatus="status">
					<li><a href="/jblog/blog/${authUser.id}?cateNo=${categoryVo.no }&postNo=">${categoryVo.name }</a></li>
				</c:forEach>

			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>