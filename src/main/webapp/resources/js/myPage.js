$(document).ready(function() {
	$(".modalbtn").click(function() {
		var modalbtnid = $(this).attr('id');
		// alert(modalbtnid);
		if (modalbtnid == 'myPageUpdate') {
			$('#exampleModalLabel').text('회원정보를 수정하시겠습니까?');
			$('button#account').text('수정하기');
			$('button#account').attr('value', 'accountUpdate');
		} else if (modalbtnid == 'myPageDelete') {
			$("#exampleModalLabel").text('회원탈퇴 하시겠습니까?');
			$('button#account').text('탈퇴하기');
			$('button#account').attr('value', 'accountDelete');
		} else {
			alert('btn id 불러오기 오류');
		}
	});
	$('button#account').click(function() {
		var id = $('td#yourid').text();
		var password = $('#mypassword').val();
		// alert($(this).attr('id') + ", your id : " + id
		// + ", your password : " + password);
		$('#exampleModal').modal('hide');
		setTimeout(function() {
			if (password == "") {
				alert("비밀번호를 입력해 주세요");
			} else {
				// alert("p_grade : " + p_grade
				// + ", p_recommentseq : "
				// + p_recommentseq + ", p_repassword : "
				// + p_repassword + ", p_tableseq : "
				// + p_tableseq);
				passwordConfirm(id, password)
			}
		}, 200);
	});
});

function passwordConfirm(id, password) {
	$.ajax({
		url : "passwordConfirm",
		type : "post",
		data : {
			id : id,
			password : password
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			if (data == 'true') {
				var modalbtnvalue = $('button#account').val();
				if (modalbtnvalue == 'accountUpdate') {
					location.href = "/accountUpdate?id=" + id;
				} else if (modalbtnvalue == 'accountDelete') {
					accountDelete(id, password);
				}
			} else if (data == 'false') {
				alert("비밀번호가 틀렸습니다.");
			} else {
				// 잘못된 값
			}
		},
		error : function() {
			alert("passwordconfirm err");
		}
	});
}

function accountDelete(id, password) {
	$.ajax({
		url : "accountDelete",
		type : "post",
		data : {
			id : id,
			password : password
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			if (data == "deleteSuccess") {
				alert("탈퇴 완료되었습니다. \n이용해주셔서 감사합니다.");
				location.href = "/";
			} else {
				alert("삭제 실패");
			}

		},
		error : function() {
			alert("accountDelete err");
		}
	});
}