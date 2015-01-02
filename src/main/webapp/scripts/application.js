var appUrl = '/sevenone';
function setErrorMessage(message) {
	$("#7bd-message").removeClass().addClass( "error-message" ).html(message);
}

function setInfoMessage(message) {
	$("#7bd-message").removeClass().addClass( "info-message" ).html(message);
}
var openForm = false;
var isIE = (function(){
	 
    var undef,
        v = 3,
        div = document.createElement('div'),
        all = div.getElementsByTagName('i');
    
    while (
        div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
        all[0]
    );
    
    return v > 4 ? v : undef;
    
}());

$(document)
		.click(
				function(event) {
					var _x = 0;
					var _y = 0;
					if (!isIE) {
						_x = event.pageX;
						_y = event.pageY;
					}
					if (isIE) {
						_x = event.clientX + document.body.scrollLeft;
						_y = event.clientY + document.body.scrollTop;
					}

					os = $("#loginbox").offset();
					if ((_x < os.left || _x > (os.left + 449) || _y < os.top || _y > (os.top + 317))
							&& ($(event.target).parents().index(
									$('#loginbox')) == -1) && openForm) {
						$("#loginbox").slideUp("slow", function() {
							openForm = false;
						});

					}
					
				});
function openLoginBox() {
	if ($("#loginbox").is(":hidden")) {
		setVisibleForm(true);
	} else {
		setVisibleForm(false);
	}
}

function setVisibleForm(b) {
	if (b) {
		$("#loginbox").stop(0, 0).slideDown("slow", function() {
			openForm = true;
		});
	} else {
		$("#loginbox").stop(0, 0).slideUp("slow", function() {
			openForm = false;
		});
	}
}
