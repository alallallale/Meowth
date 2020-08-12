<%@page import="first.notice.board.vo.UserVO"%>
<%@page import="first.notice.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
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
	<div class="container col-md-8">
		<div class="card-signin card-body">
			<%
				String sortType = request.getParameter("sortType");
				String orderType = request.getParameter("orderType");
				if ((UserVO) session.getAttribute("uservo") != null) {
					UserVO uservo = (UserVO) session.getAttribute("uservo");
			%>
			<!-- 			날짜 좋아요 싫어요 순으로 정렬하기 -->
			<a class="btn btn-primary" href="/boardwrite" role="button">글쓰기</a>
			<%
				if (uservo.getP_grade().equals("master")) {
			%>
			<!-- 			<a class="btn btn-primary" href="/boardwrite" role="button">게시판 -->
			<!-- 				삭제</a> -->
			<%
				}
				}
			%>
			<table class="table">
				<thead>
					<tr>
						<th scope="col" class="tableSeq">번호</th>
						<th scope="col" class="tableTitle">제목</th>
						<th scope="col"class="tableWriter">글쓴이</th>
						<th scope="col" class="tableDate">
						<%
						if (orderType == null) {
						%> 
						<a href="/board?sortType=p_date&orderType=Descending">작성일 ↓ </a>
						<%
						} else if (orderType.equals("Descending") && sortType.equals("p_date")) {
						%> 
						<a href="/board?sortType=p_date&orderType=Ascending">작성일 ↑ </a> 
						<%
						} else {
						%> 
						<a href="/board?sortType=p_date&orderType=Descending">작성일 ↓ </a> 
						<%
						}
						%>
						</th>
						<th scope="col" class="tableRecommentaion">
						<%
						if (orderType == null) {
						%> 
							<a href="/board?sortType=YES&orderType=Descending">좋아한 수 ↓</a> 
						<%
						} else if (orderType.equals("Descending") && sortType.equals("YES")) {
						%> 
						<a href="/board?sortType=YES&orderType=Ascending">좋아한 수 ↑ </a> 
						<%
						} else {
						%> 
						<a href="/board?sortType=YES&orderType=Descending">좋아한 수 ↓ </a> 
						<%
						}
						%>
						</th>
						<th scope="col" class="tableDecommendation">
						<%
						if (orderType == null) {
						%> <a href="/board?sortType=NO&orderType=Descending">싫어한 수 ↓</a> 
						<%
						} else if (orderType.equals("Descending") && sortType.equals("NO")) {
						%> <a href="/board?sortType=NO&orderType=Ascending">싫어한 수 ↑ </a> 
						<%
						} else {
						%> <a href="/board?sortType=NO&orderType=Descending">싫어한 수 ↓ </a> 
						<%
						}
						%>
						</th>
						<th scope="col" class="tableViewcount">조회</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
						List<String> dateList = (List<String>) request.getAttribute("dateList");
						int[][] recommendationArray = (int[][]) request.getAttribute("recommendationArray");
						for (int i = 0; i < list.size(); i++) {
					%>
					<tr>
						<td class="boardSeq"><%=list.get(i).getP_seq()%></td>
						<td class="boardTitle"><a href="/boarddetail?p_seq=<%=list.get(i).getP_seq()%>"><%=list.get(i).getP_title()%></a></td>
						<td class="boardWriter"><%=list.get(i).getP_writer()%></td>
						<td class="boardDate"><%=dateList.get(i)%></td>
						<td class="boardRecommendation"><%=recommendationArray[i][0]%></td>
						<td class="boardDecommendation"><%=recommendationArray[i][1]%></td>
						<td class="boardViewcount"><%=list.get(i).getP_viewcount()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item"><c:if test="${page ne 1}">
							<a class="page-link" href="#" onClick="fn_paging('${page - 1 }')"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a>
						</c:if></li>
					<c:forEach var="i" begin="1" end="${totalPageNumber}" step="1">
						<li class="page-item"><a class="page-link"
							href="?page=${ i }"><c:out value="${ i }" /></a></li>
					</c:forEach>
					<li class="page-item"><c:if
							test="${page ne totalPageNumber && totalPageNumber > 0}">
							<a class="page-link" href="#" onClick="fn_paging('${page + 1 }')"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a>
						</c:if></li>
				</ul>
			</nav>

		</div>
	</div>
	<script>
		function fn_paging(page) {
			location.href = "/board?page=" + page;
		}
	</script>
</body>
</html>