/**
 * 코멘트에 있는 모달에 정보를 넣기 위해 사용
 */

var p_writer;
var p_content;
var p_grade;
var p_seq;
var p_tableseq;
var p_recommentseq;

$(".comment .commentdeletebtn").click(
		function() {
			var buttonElement = $(this).parent();
			// console.log(buttonElement);
			// console.log(buttonElement.text());
			p_writer = buttonElement.children('#p_writer');
			p_content = buttonElement.children('#p_content');
			p_grade = $('#p_grade').val();
			p_seq = $(this).val();
			p_tableseq = document.getElementById('p_tableseq').value;
			// alert("p_grade : " + p_grade + ", p_seq : " + p_seq);
			$('.modal #recommentdelete').attr('id', 'commentdelete');
			if (p_grade == 'master') {
				$('#exampleModalLabel').text(
						"관리자님! " + p_writer.text() + " 님의 댓글을 삭제하시겠습니까?");
				$('div.modal-body').remove();
			} else {
				$('#exampleModalLabel').text(
						p_writer.text() + " 님의 댓글을 삭제하시겠습니까?");
			}

		});

function commentDelete(p_seq, p_grade, p_password, p_tableseq) {
	// alert("p_grade : " + p_grade + ", p_seq : " + p_seq + ", p_password : "
	// + p_password)
	$.ajax({
		url : "commentDelete",
		type : "post",
		data : {
			p_seq : p_seq,
			p_grade : p_grade,
			p_password : p_password,
			p_tableseq : p_tableseq
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('#comment').html(data);
		},
		error : function() {
			alert("commentDelete err");
		}
	});
}

function information() {
	if (p_userid == null) {
		document.getElementById('p_writer').value = "";
	}
	document.getElementById('p_password').value = "";
	document.getElementById('p_content').value = "";

}

$('#commentdelete').click(
		function() {

			if ($(this).attr('id') == 'commentdelete') {
				$('#exampleModal').modal('hide');
				setTimeout(function() {
					var p_password = $("#p_deletepassword").val();
					if (p_password == "") {
						alert("비밀번호를 입력해 주세요");
					} else {
						// alert("commentDlete 작동")
						// alert("p_grade : " + p_grade + ", p_seq : " + p_seq
						// + ", p_password : " + p_password);
						commentDelete(p_seq, p_grade, p_password, p_tableseq);
					}
				}, 200);
			} else if ($(this).attr('id') == 'recommentdelete') {
				$('#exampleModal').modal('hide');
				setTimeout(function() {
					var p_repassword = $("#p_deletepassword").val();
					if (p_repassword == "") {
						alert("비밀번호를 입력해 주세요");
					} else {
						// alert("p_grade : " + p_grade
						// + ", p_recommentseq : "
						// + p_recommentseq + ", p_repassword : "
						// + p_repassword + ", p_tableseq : "
						// + p_tableseq);
						recommentDelete(p_recommentseq, p_grade, p_repassword,
								p_tableseq)
					}
				}, 200);
			}
		});

// 코멘트 클릭하면 답글창을 띄우게 만들자
$('.comment .recomment')
		.click(
				function() {

					if ($("#recomment") != null) {
						$("#recomment").remove();
					}
					var commentnumber = $(this).attr('value').replace(
							'comment', '');
					var pagenumber = getParameterByName('p_seq');

					var p_writer = $('input#p_writer').val();
					$(this)
							.parents('div.comment#comment' + commentnumber)
							.append(
									'<div class="commentposition recomment" id="recomment">'
											+ '<form  class="recommentform" id="recommentform" method="post">'
											+ '<div class="form-row">'
											+ '<div class="form-group col-md-4">'
											+ '<label for="p_writer" class="sr-only">writer</label>'
											+ '<input type="text" readonly class="form-control" id="p_rewriter" name="p_rewriter" value='
											+ p_writer
											+ ' >'
											+ '</div>'
											+ '<div class="form-group col-md-4">'
											+ '<label for="p_password" class="sr-only">Password</label>'
											+ '<input type="password" class="form-control" id="p_repassword" name="p_repassword" placeholder="Password">'
											+ '</div>'
											+ '</div>'
											+ '<div class="form-label-group">'
											+ '	<textarea rows="3" class="form-control" id="p_recontent" name="p_recontent" placeholder="내용"></textarea>'
											+ '</div>'
											+ '<button class="btn btn-sm btn-primary text-uppercase btn-block" id="recommentbtn" type="button" onclick="recommentinsert()">등록</button>'
											+ '<input type="hidden" id="p_commentseq" name="p_commentseq" value='
											+ commentnumber
											+ '>'
											+ '<input type="hidden" id="p_tableseq" name="p_tableseq" value='
											+ pagenumber + '>' + '</form>'
											+ '</div>'

							);

				});

function recommentinsert() {
	$.ajax({
		url : "recomment",
		type : "post",
		data : $('#recommentform').serialize(),
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			comment();
			// information();
		},
		error : function() {
			alert("recommentinsert err");
		}
	});
}
$(".recomment .recommentdeletebtn").click(
		function() {
			var buttonElement = $(this).parent();
			// console.log(buttonElement);
			// console.log(buttonElement.text());
			p_writer = buttonElement.children('#p_writer');
			p_content = buttonElement.children('#p_content');
			p_grade = $('#p_grade').val();
			p_recommentseq = $(this).val();
			p_tableseq = document.getElementById('p_tableseq').value;
			// alert("p_grade : " + p_grade + ", p_recommentseq : "
			// + p_recommentseq);
			$('.modal #commentdelete').attr('id', 'recommentdelete');
			if (p_grade == 'master') {
				$('#exampleModalLabel').text(
						"관리자님! " + p_writer.text() + " 님의 답글을 삭제하시겠습니까?");
				$('div.modal-body').remove();
			} else {
				$('#exampleModalLabel').text(
						p_writer.text() + " 님의 답글을 삭제하시겠습니까?");
			}
		});

function recommentDelete(p_recommentseq, p_grade, p_repassword, p_tableseq) {
	// alert("recommendDelete, p_grade : " + p_grade + ", p_recommentseq : "
	// + p_recommentseq + ", p_repassword : " + p_repassword
	// + ", p_tableseq : " + p_tableseq);
	$.ajax({
		url : "recommentDelete",
		type : "post",
		data : {
			p_recommentseq : p_recommentseq,
			p_grade : p_grade,
			p_repassword : p_repassword,
			p_tableseq : p_tableseq
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('#comment').html(data);
		},
		error : function() {
			alert("recommentDelete err");
		}
	});
}

function comment() {
	// alert("p_seq");
	// alert(getParameterByName("p_seq"));
	var p_tableseq = document.getElementById('p_tableseq').value;
	console.log("p_tableseq : " + p_tableseq);
	$.ajax({
		url : "comment",
		type : "get",
		data : {
			p_tableseq : p_tableseq
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('#comment').html(data);
		},
		error : function() {
			alert("p_tableseq : " + p_tableseq + "commentform err");
		}
	});
}
