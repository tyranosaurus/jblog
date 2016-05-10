<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/include/blogmenu.jsp" />

		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="/jblog/blog/${authUser.id}/blogmanaging">기본설정</a></li>
					<li><a href="/jblog/blog/${authUser.id}/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="/jblog/post/${authUser.id}/insert" method="post">
					<table class="admin-cat-write">
						<tr>
							<td class="t">제목</td>
							<td><input type="text" size="60" name="title"> 
								
								<select name="categoryNo">
									<c:forEach items="${list}" var="categoryVo" varStatus="status">
										<option value="${categoryVo.no }">${categoryVo.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
													<!-- 빈칸이면 에러메세지 나오게함 -->
								<spring:hasBindErrors name="postVo">
									<c:if test="${errors.hasFieldErrors('title') }">
										<br />
										<strong style="color: red"> <c:set var="errorTitle"
												value="${errors.getFieldError( 'title' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorName에 저장 -->
											<spring:message code="${errorTitle }"
												text="${errors.getFieldError( 'title' ).defaultMessage }" />
										</strong>
									</c:if>
								</spring:hasBindErrors> 
						</tr>
						
						<tr>
							<td class="t">내용</td>
							<td><textarea name="content"></textarea></td>
						</tr>
						
						<tr>
													<!-- 빈칸이면 에러메세지 나오게함 -->
							<spring:hasBindErrors name="postVo">
								<c:if test="${errors.hasFieldErrors('content') }">
									<br />
									<strong style="color: red"> <c:set var="errorContent"
											value="${errors.getFieldError( 'content' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorName에 저장 -->
										<spring:message code="${errorContent }"
											text="${errors.getFieldError( 'content' ).defaultMessage }" />
									</strong>
								</c:if>
							</spring:hasBindErrors>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td class="s"><input type="submit" value="포스트하기"></td>
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