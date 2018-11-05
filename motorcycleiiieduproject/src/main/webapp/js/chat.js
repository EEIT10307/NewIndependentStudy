$(document).on('click', '#chatWindow', function (e) {
	if ($(this).find('a').find('i').hasClass('fa-plus')) {
		$(this).find('a').find('i').removeClass('fa-plus').addClass('fa-minus');
    } else {
    	$(this).find('a').find('i').removeClass('fa-minus').addClass('fa-plus');
    }
});

$(document).on('keyup','#lookup',function(){
	var value = $(this).val().toLowerCase();
	$("#getmsg .chat_list").filter(function() {
	   $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	});
});