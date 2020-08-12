<%@page import="first.notice.board.vo.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="/resources/css/accountmanagement.css?ver=<%=System.currentTimeMillis()%>"
	rel="stylesheet">
<script
	src="/resources/js/accountmanagement.js?ver=<%=System.currentTimeMillis()%>"></script>

<%
	List<UserVO> userList = (List<UserVO>) request.getAttribute("userList");
	List<String> userDate = (List<String>) request.getAttribute("userDate");
%>
<div class="container">
	<h1>회원관리</h1>
	<div class="accountTable">
		<div class="row" id="gradeselectgroup">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-people" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path fill-rule="evenodd" d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8zm-7.995-.944v-.002.002zM7.022 13h7.956a.274.274 0 0 0 .014-.002l.008-.002c-.002-.264-.167-1.03-.76-1.72C13.688 10.629 12.718 10 11 10c-1.717 0-2.687.63-3.24 1.276-.593.69-.759 1.457-.76 1.72a1.05 1.05 0 0 0 .022.004zm7.973.056v-.002.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816zM4.92 10c-1.668.02-2.615.64-3.16 1.276C1.163 11.97 1 12.739 1 13h3c0-1.045.323-2.086.92-3zM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4z" />
			</svg>
			<div class="btn-group">
				<button type="button" class="btn btn-warning dropdown-toggle btn-sm"
					id="gradeupdatebtn" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<%
						if (request.getParameter("grade") == null) {
							%>all
					<%
						} else {
							out.print(request.getParameter("grade"));
						}
					%>
				</button>
				<div class="dropdown-menu">
					<button class="dropdown-item"
						onclick="accountmanagement(this.textContent)">all</button>
					<button class="dropdown-item"
						onclick="accountmanagement(this.textContent)">single</button>
					<button class="dropdown-item"
						onclick="accountmanagement(this.textContent)">double</button>
					<button class="dropdown-item"
						onclick="accountmanagement(this.textContent)">triple</button>
					<button class="dropdown-item"
						onclick="accountmanagement(this.textContent)">master</button>
				</div>
			</div>

		</div>
		<div class="table-responsive-md">
			<table class="table table-sm table-hover ">
				<thead>
					<tr>
						<th class="col">NO.</th>
						<th class="col">ID</th>
						<th class="col">가입일</th>
						<th class="col">강제탈퇴</th>
						<th class="col">등급수정</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 0; i < userList.size(); i++) {
					%>
					<tr>
						<td><%=i + 1%></td>
						<td id="accountid"><%=userList.get(i).getP_userid()%></td>
						<td><%=userDate.get(i)%></td>
						<td><button class="btn btn-danger btn-sm"
								id="accountdeletebtn" data-toggle="modal"
								data-target="#exampleModal"
								value="<%=userList.get(i).getP_userid()%>">
								<svg width="1em" height="1em" viewBox="0 0 16 16"
									class="bi bi-trash" fill="currentColor"
									xmlns="http://www.w3.org/2000/svg">
  <path
										d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
  <path fill-rule="evenodd"
										d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
</svg>
							</button></td>
						<td><div class="btn-group" id="gradechange">
								<button type="button"
									class="btn btn-warning dropdown-toggle btn-sm"
									id="gradeupdatebtn" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"><%=userList.get(i).getP_grade()%></button>
								<div class="dropdown-menu">
									<button class="dropdown-item" onclick="gradeupdate(this)"
										value="<%=userList.get(i).getP_userid()%>">single</button>
									<button class="dropdown-item" onclick="gradeupdate(this)"
										value="<%=userList.get(i).getP_userid()%>">double</button>
									<button class="dropdown-item" onclick="gradeupdate(this)"
										value="<%=userList.get(i).getP_userid()%>">triple</button>
									<div class="dropdown-divider"></div>
									<button class="dropdown-item" onclick="gradeupdate(this)"
										value="<%=userList.get(i).getP_userid()%>">master</button>
								</div>
							</div></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">강제 탈퇴시키겠습니까?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary"
					id="accountdeletemodalbtn">탈퇴시키기</button>
			</div>
		</div>
	</div>
</div>