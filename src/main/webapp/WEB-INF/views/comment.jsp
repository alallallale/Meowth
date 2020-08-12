<%@page import="first.notice.board.vo.RecommentVO"%>
<%@page import="java.io.Console"%>
<%@page import="first.notice.board.vo.UserVO"%>
<%@page import="first.notice.board.vo.CommentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="/resources/js/comment.js?ver=<%=System.currentTimeMillis()%>"></script>
<%

	List<CommentVO> commentlist = (List<CommentVO>) request.getAttribute("commentlist");
	List<String> dateList = (List<String>) request.getAttribute("dateList");
	List<RecommentVO> recommentlist = (List<RecommentVO>) request.getAttribute("recommentlist");
	List<String> reDateList = (List<String>) request.getAttribute("redateList");
	String passwordConfirm = (String) request.getAttribute("passwordConfirm");
	String p_grade;
	UserVO uservo = new UserVO();
	if (session.getAttribute("uservo") != null) {
		uservo = (UserVO) session.getAttribute("uservo");
		p_grade = uservo.getP_grade();

	} else {
		p_grade = "visitor";
		uservo = new UserVO();
		System.out.println("p_grade : " + p_grade);
	}
%>
<input type='hidden' id='p_grade' value='<%=p_grade%>'>
<%
	for (int i = 0; i < commentlist.size(); i++) {
%>
<div class="comment" id="comment<%=commentlist.get(i).getP_seq()%>">
	<div class="commentContents">
		<div class='fl' id='p_writer'><%=commentlist.get(i).getP_writer()%></div>
		<div class='fl' id='p_commentip'>
			(<%=commentlist.get(i).getP_commentip()%>)
		</div>
		<div class="detail_date" id='detail_date'><%=dateList.get(i)%></div>
		<%
			// 	null인걸 먼저 표시해줘야 한다
			if(uservo.getP_grade() == null){

			}else if(uservo.getP_grade().equals("master")) {
		%>
		<button type="button" class="btn btn-primary btn-sm recomment fr" id='p_seq' value='<%=commentlist.get(i).getP_seq()%>'>답글달기!</button>
		<button type="button" class="btn btn-primary btn-sm commentdeletebtn fr" data-toggle="modal" 	data-target="#exampleModal" id='p_seq' value='<%=commentlist.get(i).getP_seq()%>'>삭제하기!</button>
		<%		
			}else if(uservo.getP_userid().equals(commentlist.get(i).getP_writer())) {
		%>
		<button type="button" class="btn btn-primary btn-sm recomment fr" id='p_seq' value='<%=commentlist.get(i).getP_seq()%>'>답글달기!</button>
		<button type="button" class="btn btn-primary btn-sm commentdeletebtn fr" data-toggle="modal" 	data-target="#exampleModal" id='p_seq' value='<%=commentlist.get(i).getP_seq()%>'>삭제하기!</button>
		<%
			}else{
		%>
		<button type="button" class="btn btn-primary btn-sm recomment fr" id='p_seq' value='<%=commentlist.get(i).getP_seq()%>'>답글달기!</button>
		<%
			}
		%>
		<div class="col-sm-8 commentcontent" id='p_content'><%=commentlist.get(i).getP_content()%></div>
	</div>
</div>
<!-- 답글을 가져오게 한다 -->
<%for(int j = 0; j<recommentlist.size(); j++){
	
		if(commentlist.get(i).getP_seq() == recommentlist.get(j).getP_commentseq()){
%>		
<div class="recomment" id="recomment<%=recommentlist.get(j).getP_recommentseq() %>">
	<div class="recommentContents">
		<div class='fl' id='p_writer'><%=recommentlist.get(j).getP_rewriter()%></div>
		<div class='fl' id='p_commentip'>
			(<%=recommentlist.get(j).getP_recommentip()%>)
		</div>
		<div class="detail_date" id='detail_date'><%=reDateList.get(j)%></div>
		<%
			// 	null인걸 먼저 표시해줘야 한다
			
			if(uservo.getP_grade() == null){

			}else if(uservo.getP_grade().equals("master")) {
		%>
		<button type="button" class="btn btn-primary btn-sm recommentdeletebtn fr" data-toggle="modal" 	data-target="#exampleModal" id='p_seq' value='<%=recommentlist.get(j).getP_recommentseq() %>'>삭제하기!</button>
		<%		
			}else if(uservo.getP_userid().equals(recommentlist.get(j).getP_rewriter())) {
		%>
		<button type="button" class="btn btn-primary btn-sm recommentdeletebtn fr" data-toggle="modal" 	data-target="#exampleModal" id='p_seq' value='<%=recommentlist.get(j).getP_recommentseq()%>'>삭제하기!</button>
		<%
			}else{
		%>
		<%
			}
		%>
		<div class="col-sm-8 commentcontent" id='p_content'><%=recommentlist.get(j).getP_recontent()%></div>
	</div>
</div>
<%
		}
	}
%>

<hr>

<%
	}
%>
<%
	if (session.getAttribute("uservo") != null) {
%>
<div class="commentposition" id="comment">
	<form id="commentform" method="post">
		<div class="form-row">
			<div class="form-group col-md-4">
				<label for="p_writer" class="sr-only">writer</label> 
				<input type="text" readonly class="form-control" id="p_writer" name="p_writer" value="<%=uservo.getP_userid()%>" >
			</div>
			<div class="form-group col-md-4">
				<label for="p_password" class="sr-only">Password</label>
				<input type="password" class="form-control" id="p_password" name="p_password" placeholder="Password">
			</div>
		</div>
		<div class="form-label-group">
			<textarea rows="3" class="form-control" id="p_content" name="p_content" placeholder="내용"></textarea>
		</div>
		<button class="btn btn-sm btn-primary text-uppercase btn-block" id="commentbtn" type="button" onclick="commentinsert()">등록</button>
		<input type="hidden" id="p_tableseq" name="p_tableseq" value="<%=request.getParameter("p_tableseq")%>">
	</form>
</div>
<%
	}
%>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">비밀번호를 입력하세요</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body form-label-grou">
				<input type="password" name="p_password" id="p_deletepassword"
					class="form-control" placeholder="비밀번호" required="required">
				<!-- 		<label for="p_password">비밀번호</label>				 -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="commentdelete">삭제하기</button>
			</div>
		</div>
	</div>
</div>

<%
	if (passwordConfirm == "true" || passwordConfirm == "retrue") {
%>
<script>
	alert('삭제되었습니다.');
</script>
<%
	} else if (passwordConfirm == "false") {
%>
<script>
	alert('비밀번호가 틀렸습니다.');
</script>
<%
	} else {

	}
%>