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
<link
	href="/resources/css/boardwrite.css?ver=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<script src="/resources/js/boardwrite.js?ver=<%= System.currentTimeMillis()%>"></script>
<script src="/resources/js/summernote-lite.min.js"></script>
<script src="/resources/js/summernote-ko-KR.min.js"></script>
<link href="/resources/css/summernote/summernote-lite.min.css" rel="stylesheet">
<link rel="shortcut icon" type="image⁄x-icon" href="/resources/img/Cat paw.jpg">
<title>Meowth</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-8 col-lg-10 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<form action="boardwrite" method='post' enctype="multipart/form-data">
							<div class="form-label-group">
								<%
									if (session.getAttribute("uservo") != null) {
										UserVO uservo = (UserVO) session.getAttribute("uservo");
								%>
								<input type="text" name="p_writer"
									value="<%=uservo.getP_userid()%>" id="p_writer"
									class="form-control" required readonly>
								<label for="p_writer">작성자</label>
								<%
									} else {
								%>
									<script>
										alert('로그인 후 이용해주세요.');
										location.href='signin';
									</script>
								<%
									}
								%>
							</div>
							<div class="form-label-group">
								<input type="password" name="p_password" id="p_password"
									placeholder="비밀번호" class="form-control" required autofocus>
								<label for="p_password">비밀번호</label>
							</div>
							<div class="form-label-group">
								<input type="text" name="p_title" id="p_title"
									class="form-control" placeholder="제목" required="required">
								<label for="p_title">제목</label>
							</div>
							<div>
								<textarea class="form-control" name="p_content" id="summernote"
									rows="5" placeholder="내용" required="required"></textarea>

							</div>

							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit" onclick="return confirmword();" >작성완료</button>
								
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>