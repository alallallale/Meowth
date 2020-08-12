<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="first.notice.board.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta  http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<script src="/resources/js/myPage.js?ver=<%=System.currentTimeMillis()%>"></script>
	<link href="/resources/css/default.css?ver=<%=System.currentTimeMillis() %>" rel="stylesheet">
	<link href="/resources/css/myPage.css?ver=<%=System.currentTimeMillis() %>" rel="stylesheet">
	<%
		UserVO uservo = (UserVO) session.getAttribute("uservo");
		UserVO getMyAccount = (UserVO)request.getAttribute("getMyAccount");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		String signinDate = dateFormat.format(getMyAccount.getP_date());
		String birthday = dateFormat.format(getMyAccount.getP_birth());
		if(uservo == null || getMyAccount == null) {
			%>
			<script>
			alert("로그인 후 이용 가능합니다.");
			location.href="/signin";
			</script>
	<%
		}
	%>
	<link rel="shortcut icon" type="image⁄x-icon" href="/resources/img/Cat paw.jpg">
<title>Meowth</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container col-md-8">
		<div class="card-signin card-body">
			<table class="profile">
				<tr>
					<td class="tabletitle">아이디 </td><td class="tablecontents" id='yourid'><%=getMyAccount.getP_userid() %></td>
				</tr>
				<tr>
					<td class="tabletitle">이메일</td><td class="tablecontents" id='youremail'><%=getMyAccount.getP_email() %></td>
				</tr>
				<tr>
					<td class="tabletitle">등급</td><td class="tablecontents" id='yourgrade'><%=getMyAccount.getP_grade() %></td>
				</tr>
				<tr>
					<td class="tabletitle">전화번호</td><td class="tablecontents" id='yourphonenumber'><%=getMyAccount.getP_phone() %></td>
				</tr>
				<tr>
					<td class="tabletitle">생일</td><td class="tablecontents" id='yourbirthday'><%=birthday %></td>
				</tr>
				<tr>
					<td class="tabletitle">가입일</td><td class="tablecontents" id='yoursignindate'><%=signinDate %></td>
				</tr>
			</table>
			<div class="myPagebtn">
				<%
				if(getMyAccount.getP_grade().equals("master") ){
				} else {
				%>
				<a class="btn btn-success modalbtn" id="myPageUpdate" data-toggle="modal" 	data-target="#exampleModal">정보 수정하기</a>
				<a class="btn btn-warning modalbtn" id="myPageDelete" data-toggle="modal" 	data-target="#exampleModal">회원 탈퇴하기</a>				
				<%
				}
				%>
			</div>
		</div>
	</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">어떤 버튼을 눌렀니?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body form-label-grou">
				<h5 class="modal-title" id="exampleModalText">비밀번호를 입력하세요</h5>
				<input type="password" name="mypassword" id="mypassword"
					class="form-control" placeholder="비밀번호" required="required">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="account">삭제하기</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>