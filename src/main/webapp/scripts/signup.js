var signupErrorMessage;
function validateDob2() {
	var date = $("#dobDate").val();
	var month = $("#dobMonth").val();
	var year = $("#dobYear").val();
	if (month == 2) {
		// nam 66 co 28 ngay
		if (date > 29) {
			signupErrorMessage.push('Tháng 2 làm đéo có ' + date + ' ngày!');
		}

		if (year % 4 == 0) {
			if (date > 29) {
				signupErrorMessage.push('Tháng 2 làm đéo có ' + date + ' ngày!');
			}
		} else {
			if (date > 28) {
				signupErrorMessage.push('Tháng 2 năm ' + year + ' làm đéo có ' + date + ' ngày!');
			}
		}
	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
		if (date > 30) {
			signupErrorMessage.push('Tháng ' + month + ' làm đéo có ' + date + ' ngày!');
		}
	}
}

function validateDob() {
	//var dob = $("#dob").val();
}

function validateSignup() {
	signupErrorMessage = new Array();
	var uname = $("#username").val();
	var pass = $("#password").val();
	var pass2 = $("#confirmPassword").val();
	var email = $("#email").val();
	if(uname.length < 3){
		signupErrorMessage.push('Tên đăng nhập phải lớn hơn hai ký tự.');
		$("#username").focus();
	}
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(!re.test(email)){
		signupErrorMessage.push('Email không hợp lệ.');
	}
	if(pass.length < 4){
		signupErrorMessage.push('Mật khẩu phải lớn hơn ba ký tự.');
	}
	if (pass != pass2) {
		signupErrorMessage.push('Xác nhận mật khẩu không đúng.');
	}
	validateDob();
	if(signupErrorMessage.length > 0) {
		setErrorMessage(signupErrorMessage[0]);
		return false;
	} else {
		return true;
	}
}

function ajaxValidateUserName() {
	var uname = $("#username").val();
	//var rUrl = '/sevenone/validateUsername?username=' + uname;
	$.ajax({
        url: appUrl + '/all-playersViewPlayerDetail?username=' + uname,
        type: 'GET',
        dataType: 'html',
        beforeSend: function() {
        	//setInfoMessage('Before send Yêu cầu thành công. Vui lòng kiểm tra email.');
        },
        success: function(data, textStatus, xhr) {

        	var obj = jQuery.parseJSON(data);
        	
        	//alert(obj.username);

        	if(obj.username != undefined) {
        		setErrorMessage('Tên đăng nhập đã tồn tại.');
        		$("#username").focus();
        		
        	}
        },
        error: function(xhr, textStatus, errorThrown) {
        	setErrorMessage(textStatus);
        }
    });
}

function ajaxValidateEmail() {
	
}