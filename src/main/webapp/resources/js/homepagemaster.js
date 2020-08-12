/**
 * 
 */

$(document).ready(function() {
	accountmanagement();
	boardstatistics();
});

function accountmanagement() {
	$.ajax({
		url : "accountmanagement",
		type : "get",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('.accountmanagement').html(data);

		},
		error : function() {
			alert("accountmanagement err");
		}
	});
}
function boardstatistics() {
	$.ajax({
		url : "boardstatistics",
		type : "get",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('.boardstatistics').html(data);

		},
		error : function() {
			alert("accountmanagement err");
		}
	});
}