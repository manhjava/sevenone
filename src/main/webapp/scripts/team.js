var atags = document.getElementById('toc').getElementsByTagName('a');
var divsContent = new Array();
function pageLoad() {
	for (var i = 0; i < atags.length; i++) {
		var a = atags[i];
		divsContent.push(a.hash);
		a.addEventListener("click", function(){
			showTab($(this).text());
		});
	}
	showTab($(atags[0]).text());
}

function showTab(name) {
	for (var i = 0; i < atags.length; i++) {
		var a = atags[i];
		if($(a).text() == name) {
			$(a).removeClass();
			$(a).addClass("active");
		} else {
			$(a).removeClass();
			$(a).addClass("inactive");
		}
	}
	
	for (var i = 0; i < divsContent.length; i++) {
		if(divsContent[i] == '#tab-' + name.toLowerCase()) {
			$(divsContent[i]).removeClass("inactive");
			$(divsContent[i]).addClass("active");
		} else {
			$(divsContent[i]).removeClass("active");
			$(divsContent[i]).addClass("inactive");
		}
		
	}
	//load tab data
	if ("OVERVIEW" == name) {
		//showOverView();
	} else if ("SQUAD" == name) {
		//showSquad();
	} else if ("STATISTICS" == name) {
		//showStatistics();
	} else if ("STADIUM" == name) {
		//showStadium();
	} else if ("SCHEDULE" == name) {
		//showSchedule();
	}
	
}

function showOverView() {
	$.ajax({
		url: '/content/BppltagHandler?ACTION=updatesql&tagname=' + tagname,
		data : { sql: sqlquery },
        type: 'POST',
        dataType: 'html',
        beforeSend: function() {
        	$("#loadingimage").html("<img src=\"/content/pl/system/modules/com.bp.pensionline.aataxmodeller/resources/gfx/spinning_image.gif\" alt=\"running...\" />");
        },
        success: function(data, textStatus, xhr) {
        	alert(data);
        	$('#'+tagname).html(sqlquery);
        	$("#loadingimage").html("");
        },
        error: function(xhr, textStatus, errorThrown) {
        	alert(textStatus);
        }
    });
}

function showSquad() {
	$.ajax({
		url: '/content/BppltagHandler?ACTION=updatesql&tagname=' + tagname,
		data : { sql: sqlquery },
        type: 'POST',
        dataType: 'html',
        beforeSend: function() {
        	$("#loadingimage").html("<img src=\"/content/pl/system/modules/com.bp.pensionline.aataxmodeller/resources/gfx/spinning_image.gif\" alt=\"running...\" />");
        },
        success: function(data, textStatus, xhr) {
        	alert(data);
        	$('#'+tagname).html(sqlquery);
        	$("#loadingimage").html("");
        },
        error: function(xhr, textStatus, errorThrown) {
        	alert(textStatus);
        }
    });
}

function showStatistics() {
	$.ajax({
		url: '/content/BppltagHandler?ACTION=updatesql&tagname=' + tagname,
		data : { sql: sqlquery },
        type: 'POST',
        dataType: 'html',
        beforeSend: function() {
        	$("#loadingimage").html("<img src=\"/content/pl/system/modules/com.bp.pensionline.aataxmodeller/resources/gfx/spinning_image.gif\" alt=\"running...\" />");
        },
        success: function(data, textStatus, xhr) {
        	alert(data);
        	$('#'+tagname).html(sqlquery);
        	$("#loadingimage").html("");
        },
        error: function(xhr, textStatus, errorThrown) {
        	alert(textStatus);
        }
    });
}

function showStadium() {
	$.ajax({
		url: '/content/BppltagHandler?ACTION=updatesql&tagname=' + tagname,
		data : { sql: sqlquery },
        type: 'POST',
        dataType: 'html',
        beforeSend: function() {
        	$("#loadingimage").html("<img src=\"/content/pl/system/modules/com.bp.pensionline.aataxmodeller/resources/gfx/spinning_image.gif\" alt=\"running...\" />");
        },
        success: function(data, textStatus, xhr) {
        	alert(data);
        	$('#'+tagname).html(sqlquery);
        	$("#loadingimage").html("");
        },
        error: function(xhr, textStatus, errorThrown) {
        	alert(textStatus);
        }
    });
}