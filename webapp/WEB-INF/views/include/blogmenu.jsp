<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- JSTL 중 function 를 사용가능 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="header">

	<c:choose>
		<c:when test="${blogVo.title == ''}">
			<h1 onclick="location.href='/jblog/blog/${authUser.id}?cateNo=&postNo='">${blogVo.id}의블로그 입니다.</h1>
			<!-- 타이틀 없을 때 -->
		</c:when>

		<c:otherwise>
			<h1 onclick="location.href='/jblog/blog/${authUser.id}?cateNo=&postNo='">${blogVo.title }</h1>
			<!-- 타이틀 있을떄 -->
		</c:otherwise>
	</c:choose>

	<ul>

		<c:choose>
			<c:when test="${empty authUser }">
				<!-- 로그인 이전의 화면 -->
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a></li>
			</c:when>

			<c:otherwise>
				<!-- 로그인 이후의 화면 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${authUser.id == userId }">
				<li><a href="${pageContext.request.contextPath}/blog/${authUser.id}/blogmanaging">블로그관리</a></li>
			</c:when>

			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/blog/${authUser.id}">${authUser.id}의 블로그 가기</a></li>
			</c:otherwise>
		</c:choose>
		
		

	</ul>
</div>