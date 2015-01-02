function validateLoginForm() {
	var name = $( "#j_username" ).val();
	var pass = $( "#j_password" ).val();
	
	if(name.length == 0){
		setErrorMessage('Đm, Sai tên đăng nhập rồi con!');
		$( "#j_username" ).focus();
		return false;
	}
	if(pass.length == 0){
		setErrorMessage('Sai mật khẩu!');
		$( "#j_password" ).focus();
		return false;
	}
	return true;
	
}
function requestRecoveryToken() {
    if ($("#j_username").val().length == 0) {
    	setErrorMessage('Tên đăng nhập là clgt!');
        $("#j_username").focus();
    } else {
    	$.ajax({
            url: appUrl + '/requestRecoveryToken?username=' + $("#j_username").val(),
            type: 'GET',
            dataType: 'html',
            beforeSend: function() {
                //$('#'+id+' .contentarea').html('<img src="/function-demos/functions/ajax/images/loading.gif" />');
            },
            success: function(data, textStatus, xhr) {

            	setInfoMessage('Yêu cầu thành công. Vui lòng kiểm tra email.');
            },
            error: function(xhr, textStatus, errorThrown) {
            	setErrorMessage(textStatus);
            }
        });
    }
    
    
}