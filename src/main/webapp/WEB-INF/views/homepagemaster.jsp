<%@page import="first.notice.board.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image⁄x-icon" href="/resources/img/Cat paw.jpg">
<title>Meowth</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<link
	href="/resources/css/homepagemaster.css?var=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<script
	src="/resources/js/homepagemaster.js?ver=<%=System.currentTimeMillis()%>"></script>
<style type="text/css">
.squarebox {
	width: 100%;
	height: 100%;
	border: 1px solid red;
}
</style>
</head>
<body>
	<%
		UserVO uservo = (UserVO) session.getAttribute("uservo");
		if(uservo == null){
	%>
	<script>
		alert("로그인 후 이용해주시기 바랍니다.");
		location.href = "/";
	</script>
	<%		
		}else if (!uservo.getP_grade().equals("master")) {
	%>
	<script>
		alert("관리자만 사용 가능합니다.");
		location.href = "/";
	</script>
	<%
		}
	%>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-5 accountmanagement"></div>
			<div class="col-md-1 divcenter"></div>
			<div class="col-md-5 boardstatistics"></div>
		</div>
	</div>
</body>
</html>