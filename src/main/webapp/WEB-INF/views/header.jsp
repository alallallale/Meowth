<%@page import="first.notice.board.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String p_userid = null;
	String p_grade = null;
	String p_name = null;
	if (session.getAttribute("uservo") != null) {
		UserVO uservo = (UserVO) session.getAttribute("uservo");
		p_userid = uservo.getP_userid();
		p_grade = uservo.getP_grade();
		p_name = uservo.getP_name();
	}
%>
<link href="/resources/css/header.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="d-flex align-items-center justify-content-center rounded home" style="font-size: 2em;" href="/">
    	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  			<path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"></path>
  			<path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"></path>
		</svg>
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<%
				if (p_userid == null) {
			%>
			<li class="nav-item active"><a class="nav-link" href="signin">로그인</a></li>
			
			<li class="nav-item"><a class="nav-link" href="signup">회원가입</a></li>
			<li class="nav-item"><a class="nav-link" href="board">게시판</a></li>
			<%
				} else {
			%>
			<li class="nav-item active"><a class="nav-link" href="signout">로그아웃</a></li>
			<li class="nav-item"><a class="nav-link" href="/myPage">마이페이지</a></li>
			<li class="nav-item"><a class="nav-link" href="board">게시판</a></li>
			<%
 					if (p_grade.equals("master")) {
 			%>
			<li class="nav-item"><a class="nav-link" href="/homepagemaster" id="red">관리자</a></li>
			<%
					}
				}
			%>
		</ul>
	</div>
</nav>
