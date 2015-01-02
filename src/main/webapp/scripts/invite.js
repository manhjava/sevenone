function invite2Team() {
	
	
	//var playerid = $("#invitedPlayer").val();
	//var teams = "";
	var json = '{"playerid":"2","teams":"4"}';
	$.ajax({
        url: appUrl + '/submitInviteToTeam?jsonValue=' + json ,
        type: 'GET',
        dataType: 'html',
        beforeSend: function() {
        	//setInfoMessage('Before send Yêu cầu thành công. Vui lòng kiểm tra email.');
        },
        success: function(data, textStatus, xhr) {
        	alert("success");
        	
//        	var obj = jQuery.parseJSON(data);
//        	
//        	$("#player-name").html(obj.username);
//        	$("#player-Position").html(obj.position);
//        	$("#player-height").html(obj.height);
//        	$("#player-weight").html(obj.weight);
//        	
//        	
//        	
//        	$("#showplayerinfodiv").show();
        	
        	//alert(obj.username);

//        	if(data.indexOf('true') > -1) {
//        		setErrorMessage('Tên đăng nhập đã tồn tại.');
//        		$("#username").focus();
//        		
//        	}
        },
        error: function(xhr, textStatus, errorThrown) {
        	setErrorMessage(textStatus);
        }
    });
	
}