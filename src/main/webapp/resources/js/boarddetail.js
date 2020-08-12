/**
 * ajax Asynchronous JavaScript and XML
 * 
 */
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}
$(document).ready(function() {
	comment();
	recommendation();
});

function information() {
	if (p_userid == null) {
		document.getElementById('p_writer').value = "";
	}
	document.getElementById('p_password').value = "";
	document.getElementById('p_content').value = "";

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

function commentinsert() {
	$.ajax({
		url : "comment",
		type : "post",
		data : $('#commentform').serialize(),
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			comment();
			// information();
		},
		error : function() {
			alert("commentinsert err");
		}
	});
}

function recommendation() {
	// alert("p_seq");
	// alert(getParameterByName("p_seq"));
	var p_tableseq = document.getElementById('p_tableseq').value;
	console.log("p_tableseq : " + p_tableseq);
	$.ajax({
		url : "recommendation",
		type : "get",
		data : {
			p_tableseq : p_tableseq
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('.recommendation').html(data);
		},
		error : function() {
			alert("p_tableseq : " + p_tableseq + ", recommendation call err");
		}
	});
}

function boardupdatebtn() {
	$('.modal input.boardmotion').val('수정하기');
	// $('.modal input.boardmotion').attr('id', 'boardupdatemodalbtn');
	$('input#buttontype').val('boardupdatemodalbtn');
}
function boarddeletebtn() {
	$('.modal input.boardmotion').val('삭제하기');
	// $('.modal input.boardmotion').attr('id', 'boarddeletemodalbtn');
	$('input#buttontype').val('boarddeletemodalbtn');
}
