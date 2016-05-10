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
</head>
<body>
	<div class="center-content">
		<h1 class="logo" onclick="location.href='/jblog/main'">JBlog</h1>
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<form class="login-form" name="loginform" method="post"
			action="${pageContext.request.contextPath}/user/login">
			<label>아이디</label> <input type="text" name="id"> <label>패스워드</label>
			<input type="password" name="password">

			<c:if test='${param.result == "fail" }'>
			<p style="color: red">
				<strong>로그인이 실패 했습니다. <br /> 아이디 비밀번호를 다시 확인해 주세요
				</strong>
			</p>
			</c:if>

			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
