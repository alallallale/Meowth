/**
 * 추천 비추천 버튼을 누르면 ajax를 통해 테이블번호, 어떤 버튼을 눌렀는지의 값을 같이 넘겨준다, 그리고 세션을 확인해서 로그인이 된
 * 상태면 아이디와 번호로 검색을 해서 이미 추천 또는 비추천을 했는지 확인 후 추천 또는 비추천 된 상태면 이미 비추천 추천 된 상태라고
 * 알려준다 model을 사용해서 해당 model이 있으면 alert창을 띄우게 한다.
 */
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

function insertrecommendation(recommendation) {
	var p_tableseq = document.getElementById('p_tableseq').value;
	$.ajax({
		url : "recommendation",
		type : "post",
		data : {
			p_tableseq : p_tableseq,
			p_recommendation : recommendation
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			if (data == 'notLogin') {
				alert('로그인 후 이용 가능합니다.');
			} else if (data == 'haveYES') {
				alert('이미 좋아하셨습니다.');
			} else if (data == 'haveNO') {
				alert('이미 싫어하셨습니다.');
			} else if (data == 'successYES') {
				var val = parseInt($('#recommendationCount').text()) + 1;
				$('#recommendationCount').html(val);
			} else if (data == 'successNO') {
				var val = parseInt($('#decommendationCount').text()) + 1;
				$('#decommendationCount').html(val);
			}
		},
		error : function() {
			alert("recommendation insert err");
		}
	});
}
