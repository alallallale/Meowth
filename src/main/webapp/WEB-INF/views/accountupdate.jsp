<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="first.notice.board.vo.UserVO"%>
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
<script src="/resources/js/bootstrap-datepicker.min.js"></script>
<script src="/resources/js/bootstrap-datepicker.ko.js"></script>
<link href="/resources/css/bootstrap-datepicker.css" rel="stylesheet">
<link
	href="/resources/css/default.css?ver=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<script
	src="/resources/js/accountupdate.js?ver=<%=System.currentTimeMillis()%>"></script>
<link
	href="/resources/css/signup.css?ver=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<link rel="shortcut icon" type="image⁄x-icon" href="/resources/img/Cat paw.jpg">
<title>Meowth</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
		UserVO sessionuservo = (UserVO) session.getAttribute("uservo");
		UserVO uservo = (UserVO) request.getAttribute("uservo");
		String yourBirthday = (String) request.getAttribute("yourBirthday");
		if (sessionuservo == null) {
	%>
	<script>
		alert("로그인 후 이용 가능합니다.");
		location.href = "/";
	</script>
	<%
		} else if (uservo == null) {
	%>
	<script>
		alert("해당 계정이 존재하지 않습니다.");
		location.href = "/";
	</script>
	<%
		} else if (!sessionuservo.getP_userid().equals(uservo.getP_userid())) {
	%>
	<script>
		alert("로그인 한 계정과 요청한 계정이 일치하지 않습니다. \n session : "
				+
	<%=sessionuservo.getP_userid()%>
		+ ", 요청한 id : "
				+
	<%=uservo.getP_userid()%>
		);
		location.href = "/";
	</script>
	<%
		}
	%>
	<%
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(format.format(today)) - 20;
	%>
	<div class="container col-md-6">
		<div class="card-signin card-body">
			<form action="accountUpdate" method="post">
				<div class="row">
					<div class="form-label-group col-md-12">
					<input type="hidden" name="p_userid" value="<%=uservo.getP_userid() %>">
						<input type="text" class="form-control" id="p_userid"
							required="required" placeholder="ID"
							onkeyup="idConfirm(this.value)" data-toggle="popover"
							data-trigger="focus" data-placement="top"
							data-content="아이디는 7자 이상 20자 이하의 영어. 숫자만 가능합니다."
							value="<%=uservo.getP_userid()%>" disabled> <label
							for="p_userid" id="labelconfig">ID</label>
					</div>
					<div class="form-label-group col-md-12 confirmText"
						id="idOverlapTest"></div>

				</div>
				<div class="row">
					<div class="form-label-group col-md-12">
						<input type="password" class="form-control" id="p_password"
							name="p_password" required="required" placeholder="Password"
							onkeyup="passwordConfirm(this.value)" data-toggle="popover"
							data-trigger="focus" data-placement="top"
							data-content="비밀번호는 8자 이상 20자 이하, 특수문자, 대문자가
						포함되어야만 가능합니다."
							value="<%=uservo.getP_password()%>"> <label
							for="p_password" id="labelconfig">Password</label>
					</div>
					<div class="form-label-group col-md-12 confirmText"
						id="passwordOverlapTest"></div>
				</div>
				<div class="row">
					<div class="form-label-group col-md-12">
						<input type="hidden" id="realName" value="<%=uservo.getP_name()%>">
						<input type="text" class="form-control" id="p_name" name="p_name"
							required="required" placeholder="name"
							onkeyup="nameConfirm(this.value)" data-toggle="popover"
							data-trigger="focus" data-placement="top"
							data-content="이름은 특수문자를 제외한 2~20자만 가능합니다."
							value="<%=uservo.getP_name()%>"> <label for="p_name"
							id="labelconfig">name</label>
					</div>
					<div class="form-label-group col-md-12 confirmText"
						id="nameOverlapTest"></div>
				</div>
				<div class="row">
					<div class="form-label-group col-md-12">
						<input type="text" class="form-control" id="p_phone"
							name="p_phone" required="required" placeholder="010-0000-0000"
							onkeyup="phoneConfirm(this)" data-toggle="popover"
							data-trigger="focus" data-placement="top"
							data-content="숫자만 입력해주세요" value="<%=uservo.getP_phone()%>">
						<label for="p_phone" id="labelconfig">010-0000-0000</label>
					</div>
					<div class="form-label-group col-md-12 confirmText"
						id="phoneOverlapTest"></div>
				</div>
				<div class="row">
					<div class="form-label-group col-md-12">
						<input type="email" class="form-control" id="p_email"
							name="p_email" required="required" placeholder="e-mail"
							onkeyup="emailConfirm(this.value)" data-toggle="popover"
							data-trigger="focus" data-placement="top"
							data-content="주소에 특수문자가 없어야 합니다."
							value="<%=uservo.getP_email()%>"> <label for="p_email"
							id="labelconfig">e-mail</label>
					</div>
					<div class="form-label-group col-md-12 confirmText"
						id="emailOverlapTest"></div>
				</div>
				<div class="row">
					<div class="form-label-group col-md-12">
						<input type="date" class="form-control" id="datePicker"
							name="birth" required="required" placeholder="birth"
							onkeyup="dateConfirm(this.value)" data-toggle="popover"
							data-trigger="focus" data-placement="top"
							data-content="<%=year%>년생 부터 가입 가능합니다." value="<%=yourBirthday%>">
						<label for="birth" id="labelconfig">birth</label>
					</div>
					<div class="form-label-group col-md-12 confirmText"
						id="dateOverlapTest"></div>
				</div>
				<button class="btn btn-lg btn-primary btn-block text-uppercase"
					type="submit" onclick="return updateOverlapConfirm()">수정하기</button>
			</form>
		</div>
	</div>
</body>
</html>