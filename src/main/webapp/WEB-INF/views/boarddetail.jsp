<%@page import="first.notice.board.vo.BoardVO"%>
<%@page import="first.notice.board.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	href="/resources/css/default.css?ver=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<link
	href="/resources/css/boarddetail.css?ver<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<script
	src="/resources/js/boarddetail.js?ver=<%=System.currentTimeMillis()%>"></script>
	<link rel="shortcut icon" type="image⁄x-icon" href="/resources/img/Cat paw.jpg">
<title>Meowth</title>
</head>
<body>
	<%
		pageContext.setAttribute("replaceChar", "\n");
	%>
	<jsp:include page="header.jsp" />
	<div class="container col-lg-8">
		<div class="card-signin card-body">
			<%
				UserVO uservo = (UserVO) session.getAttribute("uservo");
				if (uservo != null) {
					BoardVO boardvo = (BoardVO) request.getAttribute("boardvo");
			%>
			<a class="btn btn-primary" href="/boardwrite" role="button">글쓰기</a>
			<%
				if (uservo.getP_userid().equals(boardvo.getP_writer())) {
			%>
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#boardModal" onclick="boardupdatebtn()">게시글 수정</button>
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#boardModal" onclick="boarddeletebtn()">게시판 삭제</button>
			<%
				}
				}
			%>
			<div class="gallview_head">
				<hr>
				<h3>${ boardvo.p_title}</h3>
				<div class="fl">
					<span>${boardvo.p_writer }(${boardvo.p_boardip })</span><span
						class="detail_date"> ${stringDate }</span>
				</div>
				<div class="fr">
					<span>조회수 ${boardvo.p_viewcount }</span>
					<span>추천 <%= request.getAttribute("recommendationCount") %></span>
					<span>댓글 <%= request.getAttribute("commentCount") %></span>
				</div>
			</div>
			<hr>
			<div class="content">
				${boardvo.p_content}
			</div>
			<div class="recommendation"></div>
			<hr>
			<div>댓글</div>
			<hr>
			<div id="comment"></div>


			<!-- 			<input type="text" id="p_tableseq" name="p_tableseq" -->
			<%-- 				value="<%=request.getParameter("p_seq")%>"> --%>
			<input type='hidden' id="p_tableseq" name="p_tableseq"
				value="<%=request.getParameter("p_seq")%>">

		</div>
	</div>

	<%
		if (uservo != null) {
	%>
	<!-- Modal -->
	<div class="modal fade" id="boardModal" tabindex="-1" role="dialog"
		aria-labelledby="boardModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="boardconfirm" method="post">
					<div class="modal-header">
						<h5 class="modal-title" id="boardModalLabel">비밀번호를 입력하세요</h5>
					</div>
					<div class="modal-body form-label-grou">
						<input type='hidden' id="tableseq" name="tableseq" value="<%=request.getParameter("p_seq")%>"> 
						<input type="hidden" id="buttontype" name="buttontype"> 
						<input type="password" name="boardpassword" id="boardpassword" 	class="form-control" placeholder="비밀번호" required="required">
						<!-- 		<label for="p_password">비밀번호</label>				 -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						<!-- 					<button type="button" class="btn btn-primary boardmotion">삭제하기</button> -->
						<input type="submit" class="btn btn-primary boardmotion" value="삭제하기">
					</div>
				</form>
			</div>
		</div>
	</div>
	<%
		}
	%>


</body>
</html>