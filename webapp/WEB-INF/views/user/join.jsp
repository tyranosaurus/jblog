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

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function() {
	
	$("#join-form").submit(function() { 

		if ($("#img-checkemail").is(":visible") == false) {
			alert("이메일 중복체크를 해야한다.")
			return false;
		}

		//4.약관 체크 유무  // jQuery is checked
		if ($("#agree-prov").is(":checked") == false){
			alert("약관에 동의해야 합니다.")
			return false;
		}

		return true;
	});
	
	$("#blog-id").change(function() { // #email에 넣어진 값들이 변화(change)하면 함수를 실행시켜라
		$("#btn-checkemail").show();
		$("#img-checkemail").hide();
	});
	
	$("#btn-checkemail").click(
					function() {
						var id = $("#blog-id").val();
						if (id == "") {
							return;
						}
	
						$.ajax({
									url : "/jblog/user/checkemail?id=" + id,
									type : "get", // 통신방식 get/post 둘중 하나
									dataType : "json", //  수신 데이터 타입
									data : "", 
									success : function(response) { // 성공시 callback
	
										if (response.result != "success") {
											return;
										}
	
										if (response.data == false) {
											alert("이미 존재하는 이메일 이다. 다른거 써라");
											$("#blog-id").val("").focus();
											return;
										}
	
										// 사용가능 한 이메일
										$("#btn-checkemail").hide();
										$("#img-checkemail").show();
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
	<div class="center-content">
		<h1 class="logo" onclick="location.href='/jblog/main'">JBlog</h1>
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<form class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label> <input id="name"
				name="name" type="text" value="">

			<!-- 빈칸이면 에러메세지 나오게함 -->
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<br />
					<strong style="color: red"> <c:set var="errorName"
							value="${errors.getFieldError( 'name' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorName에 저장 -->
						<spring:message code="${errorName }"
							text="${errors.getFieldError( 'name' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="blog-id">아이디</label> <input
				id="blog-id" name="id" type="text"> <input
				id="btn-checkemail" type="button" value="id 중복체크"> <img
				id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">


			<!-- 빈칸이면 에러메세지 나오게함 -->
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('id') }">
					<br />
					<strong style="color: red"> <c:set var="errorId"
							value="${errors.getFieldError( 'id' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorName에 저장 -->
						<spring:message code="${errorId }"
							text="${errors.getFieldError( 'id' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>


			<label class="block-label" for="password">패스워드</label> <input
				id="password" name="password" type="password" />


			<!-- 빈칸이면 에러메세지 나오게함 -->
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('password') }">
					<br />
					<strong style="color: red"> <c:set var="errorPassword"
							value="${errors.getFieldError( 'password' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorName에 저장 -->
						<spring:message code="${errorPassword }"
							text="${errors.getFieldError( 'password' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>


			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
