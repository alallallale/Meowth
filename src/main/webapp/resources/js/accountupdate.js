/**
 * 
 */
$(document).ready(function() {
	$('[data-toggle="popover"]').popover();
	$('.popover-dismiss').popover({
		trigger : 'focus'
	})
	passwordConfirm($('#p_password').val());
	nameConfirm($('#p_name').val());
	phoneConfirm($('#p_phone')[0]);
	emailConfirm($('#p_email').val());
	dateConfirm($('#datePicker').val());
});

function passwordConfirm(password) {
	var passwordRegExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
	var SpecialCharactersRegExp = /[A-Z]/g;
	var digitTegExp = /[0-9]/g;
	if (password.length <= 20 && password.length >= 8) {
		if (SpecialCharactersRegExp.test(password)) {
			if (passwordRegExp.test(password)) {
				if (digitTegExp.test(password)) {
					$('#passwordOverlapTest').text("※ 사용가능한 비밀번호 입니다.");
					$('#passwordOverlapTest').css('color', 'green');
				} else {
					$('#passwordOverlapTest').text("※ 숫자가 포함되어야 합니다.");
					$('#passwordOverlapTest').css('color', 'red');
				}
			} else {
				$('#passwordOverlapTest').text("※ 특수문자가 포함되어야 합니다.");
				$('#passwordOverlapTest').css('color', 'red');
			}
		} else {
			$('#passwordOverlapTest').text("※ 대문자가 포함되어야 합니다.");
			$('#passwordOverlapTest').css('color', 'red');
		}
	} else {
		$('#passwordOverlapTest').text("※ 영어와 숫자로 8~20글자로만 가능합니다.");
		$('#passwordOverlapTest').css('color', 'red');
	}
}

function nameConfirm(name) {
	var nameRegExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
	if ($('#realName').val() == name) {
		$('#nameOverlapTest').text("※ 회원님의 이름 입니다.");
		$('#nameOverlapTest').css('color', 'green');
	} else {
		if (name.length <= 20 && name.length >= 2) {
			// 이름의 길이가 20자 이내여아만 하고 특수문자가 없어야 한다.
			if (nameRegExp.test(name)) {
				$('#nameOverlapTest').text(
						"※ 이름은 2 글자 이상 20글자 이하이고 특수문자는 사용할 수 없스니다.");
				$('#nameOverlapTest').css('color', 'red');
			} else {
				$
						.ajax({
							url : "nameConfirm",
							type : "get",
							data : {
								p_name : name
							},
							contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							success : function(data) {
								if (data == "true") {
									$('#nameOverlapTest').text(
											"※ 사용가능한 이름 입니다.");
									$('#nameOverlapTest').css('color', 'green');
								} else if (data == "false") {
									$('#nameOverlapTest').text(
											"※ 이미 사용중인 이름 입니다.");
									$('#nameOverlapTest').css('color', 'red');
								} else {
									$('#nameOverlapTest').text("이름을 입력해 주세요");
									$('#nameOverlapTest').css('color', 'black');
								}
							},
							error : function() {
								alert("nameConfirm err");
							}
						});
			}
		} else {
			$('#nameOverlapTest').text(
					"※ 이름은 2 글자 이상 20글자 이하이고 특수문자는 사용할 수 없스니다.");
			$('#nameOverlapTest').css('color', 'red');
		}
	}
}

function phoneConfirm(phone) {
	var phoneRegExp = /^\d{2,3}-\d{3,4}-\d{4}$/g;
	var number = phone.value.replace(/[^0-9]/g, "");
	var tel = "";

	// 서울 지역번호(02)가 들어오는 경우
	if (number.substring(0, 2).indexOf('02') == 0) {
		if (number.length < 3) {
			return number;
		} else if (number.length < 6) {
			tel += number.substr(0, 2);
			tel += "-";
			tel += number.substr(2);
		} else if (number.length < 10) {
			tel += number.substr(0, 2);
			tel += "-";
			tel += number.substr(2, 3);
			tel += "-";
			tel += number.substr(5);
		} else {
			tel += number.substr(0, 2);
			tel += "-";
			tel += number.substr(2, 4);
			tel += "-";
			tel += number.substr(6);
		}

		// 서울 지역번호(02)가 아닌경우
	} else {
		if (number.length < 4) {
			return number;
		} else if (number.length < 7) {
			tel += number.substr(0, 3);
			tel += "-";
			tel += number.substr(3);
		} else if (number.length < 11) {
			tel += number.substr(0, 3);
			tel += "-";
			tel += number.substr(3, 3);
			tel += "-";
			tel += number.substr(6);
		} else {
			tel += number.substr(0, 3);
			tel += "-";
			tel += number.substr(3, 4);
			tel += "-";
			tel += number.substr(7);
		}
	}
	if (tel.length > 13) {
		tel = tel.substr(0, 13);
	}
	phone.value = tel;
	if (phoneRegExp.test(phone.value)) {
		$('#phoneOverlapTest').text("※ 전화번호가 맞습니다.");
		$('#phoneOverlapTest').css('color', 'green');
	} else {
		$('#phoneOverlapTest').text("※ 전화번호를 입력해 주세요.");
		$('#phoneOverlapTest').css('color', 'red');
	}
}

function emailConfirm(email) {
	var emailRegExp = /[@]{1}[A-z0-9]{2,50}[\.]{1}/g;
	if (emailRegExp.test(email)) {
		$('#emailOverlapTest').text("※ 이메일 입니다.");
		$('#emailOverlapTest').css('color', 'green');
	} else {
		$('#emailOverlapTest').text("※ 주소에 특수문자를 제외해 주세요");
		$('#emailOverlapTest').css('color', 'red');
	}
}

var today = new Date();
var thisYear = parseInt(today.getFullYear());
function dateConfirm(date) {
	if (date.split("-").length == 3) {
		var yyyy = date.split("-")[0];
		if (yyyy >= (thisYear - 19)) {
			$('#dateOverlapTest').text(thisYear - 20 + "년생부터 회원가입이 가능합니다.");
			$('#dateOverlapTest').css('color', 'red');
		} else if (yyyy <= 1900) {
			$('#dateOverlapTest').text("나이가 너무 많으십니다 어르신......");
			$('#dateOverlapTest').css('color', 'red');
		} else {
			$('#dateOverlapTest').text("가입이 가능한 나이 입니다.");
			$('#dateOverlapTest').css('color', 'green');
		}
	} else {
		$('#dateOverlapTest').text("생년월일 입력해 주세요");
		$('#dateOverlapTest').css('color', 'blue');
	}
}
function updateOverlapConfirm() {
	if ($('#passwordOverlapTest').text() == "※ 사용가능한 비밀번호 입니다."
			&& ($('#nameOverlapTest').text() == "※ 회원님의 이름 입니다." || $(
					'#nameOverlapTest').text() == "※ 사용가능한 이름 입니다.")
			&& $('#phoneOverlapTest').text() == "※ 전화번호가 맞습니다."
			&& $('#emailOverlapTest').text() == "※ 이메일 입니다."
			&& $('#dateOverlapTest').text() == "가입이 가능한 나이 입니다.") {
		return true;
	} else {
		alert("가입조건을 잘 살펴봐주세요.");
		return false;
	}
}