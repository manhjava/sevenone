window.fbAsyncInit = function() {
  FB.init({
    appId      : '1405362513035177',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.1' // use version 2.1
  });

  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
	  console.log(response.status);
  });

  };

function facebooklogin() {
	
	FB.getLoginStatus(function(response) {
		if (response.status === 'connected') {
		      // Logged into your app and Facebook.
			var accesstk = response.authResponse.accessToken;
			fbConnected(accesstk);
		    } else if (response.status === 'not_authorized') {
		      // The person is logged into Facebook, but not your app.
		    	console.log('not_authorized');
		    } else {
		      // The person is not logged into Facebook, so we're not sure if
		      // they are logged into this app or not.
		    	FB.login(function(response) {
					if (response.authResponse) {
						var accesstk = response.authResponse.accessToken;
						console.log('Welcome!  Fetching your information.... ');
						fbConnected(accesstk);
					} else {
						console.log('User cancelled login or did not fully authorize.');
					}
				}, {scope: 'public_profile,email'});
		    }
	  });
	
	
}

function fbConnected(accessToken) {
	console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      var email = response.email;
      var name = response.name;
      
      $.ajax({
          url: appUrl + '/facebooklogin',
          type: 'POST',
          data : { token: accessToken, email:email,name:name },
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
      
      console.log('Successful login for: ' + email);
      console.log('Thanks for logging in, ' + name);
    });
}