/**
 * 
 */
var userid;
var delObj;
$(document)
		.ready(
				function() {
					$('button#accountdeletebtn').click(
							function(obj) {
								console.log($(obj))
								userid = $(this).val()
								delObj = $(obj);
								$('h5#exampleModalLabel').text(
										"'" + userid + "'님의 계정을 삭제하시겠습니까?");
							});
					$('#accountdeletemodalbtn')
							.click(
									function() {
										$('#exampleModal').modal('hide');
										setTimeout(
												function() {

													$
															.ajax({
																url : "accountdeletemaster",
																type : "post",
																data : {
																	userid : userid
																},
																contentType : "application/x-www-form-urlencoded; charset=UTF-8",
																success : function(
																		data) {
																	if (data == "deleteSuccess") {
																		$(
																				'td button.btn#accountdeletebtn[value="'
																						+ userid
																						+ '"]')
																				.parent()
																				.parent()
																				.remove();
																		alert("회원탈퇴 처리되었습니다.");
																	} else {
																		alert("회원탈퇴에 실패했습니다.");
																	}
																},
																error : function() {
																	alert('accountdelete err');
																}
															});
												}, 200);
									});

				});

function accountmanagement(grade) {
	$.ajax({
		url : "accountmanagement",
		type : "get",
		data : {
			grade : grade
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			console.log("accountmanagement : " + location.search)
			$('.accountmanagement').html(data);

		},
		error : function() {
			alert("accountmanagement err");
		}
	});
}

function gradeupdate(obj) {
	var grade = $(obj).text()
	var userid = $(obj).val();
	$
			.ajax({
				url : "gradeupdate",
				type : "post",
				data : {
					grade : grade,
					userid : userid
				},
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(data) {
					if (data == "updateSuccess") {
						$(obj).parent().parent().children('button').text(grade);
						alert('등급변경 완료');
					} else {
						alert('등급변경 실패했습니다.');
					}
				},
				error : function() {
					alert("gradeupdate err \n grade: " + grade + ", userid : "
							+ userid);
				}
			});
}
