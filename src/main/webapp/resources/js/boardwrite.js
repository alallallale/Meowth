$(document).ready(function() {
	$('#summernote').summernote({
		height : 300,
		minHeight : null,
		maxHeight : null,
		focus : true,
		lang : "ko-KR",
		placeholder : '최대 10000자까지 쓸 수 있습니다.',
		callbacks : { // 여기 부분이 이미지를 첨부하는 부분
			onImageUpload : function(files) {
				uploadSummernoteImageFile(files[0], this);
			}
		}
	});
	// $('.note-insert').remove();
	// var contentRegExp = '/[<]\/?[a-z]{1,}[0-9]?[>]/g';
	$('.note-editable.card-block').keyup(function(e) {
		var content = $(this).text();
		if (content.length > 10000) {
			alert("최대 10000자까지 입력 가능합니다.");
			$(this).text(content.substring(0, 10000));

		}
	});
});

function uploadSummernoteImageFile(file, editor) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data : data,
		type : "POST",
		url : "/uploadSummernoteImageFile",
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			// 항상 업로드된 파일의 url이 있어야 한다.
			$(editor).summernote('insertImage', data.url);
			console.log(data.url);
		},
		error : function(errorThrown) {
			alert("upload SummernoteImageFile err : " + errorThrown);
		},

	});
}