function viewPlayerDetail(username) {
	
	$.ajax({
        url: appUrl + '/all-players-ViewPlayerDetail?username=' + username,
        type: 'GET',
        dataType: 'html',
        beforeSend: function() {
        	//setInfoMessage();
        },
        success: function(data, textStatus, xhr) {
        	var obj = jQuery.parseJSON(data);
        	$("#player-name").html(obj.username);
        	$("#player-Position").html(obj.position);
        	$("#player-height").html(obj.height);
        	$("#player-weight").html(obj.weight);
        	$("#showplayerinfodiv").show();
        },
        error: function(xhr, textStatus, errorThrown) {
        	setErrorMessage(textStatus);
        }
    });
}

function updateAccount() {
	var username = $(":hidden#username").val();
	var email = $("#email").val();
	var phoneNumber = $("#phoneNumber").val();
	var dob = $("#dob").val();
	var position = $("#position").val();
	$.ajax({
        url: appUrl + '/updateAccountInfo',
        data : { username: username, email: email, phoneNumber:phoneNumber, dob:dob, position:position},
        type: 'GET',
        dataType: 'html',
        contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
        beforeSend: function() {
        	//setInfoMessage('Before send Yêu cầu thành công. Vui lòng kiểm tra email.');
        },
        success: function(data, textStatus, xhr) {
        	var obj = jQuery.parseJSON(data);
        	var mes = obj.returnMes;
        	setInfoMessage(mes);
        },
        error: function(xhr, textStatus, errorThrown) {
        	setErrorMessage(textStatus);
        }
    });
}

function addPlayer2Team(userid) {
	$.ajax({
        url: appUrl + '/addPlayer?userid=' + userid,
        type: 'GET',
        dataType: 'html',
        beforeSend: function() {
        	//setInfoMessage('Before send Yêu cầu thành công. Vui lòng kiểm tra email.');
        },
        success: function(data, textStatus, xhr) {
        	var obj = jQuery.parseJSON(data);
        	var mes = obj.returnMes;
        	setInfoMessage(mes);
        },
        error: function(xhr, textStatus, errorThrown) {
        	setErrorMessage(textStatus);
        }
    });
}

function closeplayerinfordiv() {
	$("#showplayerinfodiv").hide();
}