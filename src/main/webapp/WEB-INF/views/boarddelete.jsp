<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link
	href="/resources/css/board.css?var=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
	<link rel="shortcut icon" type="image⁄x-icon" href="/resources/img/Cat paw.jpg">
<title>Meowth</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
		String passwordconfirm = (String) request.getAttribute("passwordconfirm");
		if (passwordconfirm == null) {
	%>
	<script>
		alert("잘못된 접근입니다.");
		history.go(-1);
	</script>
	<%
		} else if (passwordconfirm.equals("passwordfail")) {
	%>
	<script>
		alert("비밀번호가 틀렸습니다.");
		history.go(-1);
	</script>
	<%
		}else if(passwordconfirm.equals("passwordsuccess")){
	%>
	<script>
		alert("삭제가 완료되었습니다.");
		location.href="/board";
	</script>
	<%
		}
	%>
</body>
</html>