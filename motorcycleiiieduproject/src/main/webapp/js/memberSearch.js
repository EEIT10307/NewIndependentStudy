$(document).on('keyup','#searchOrd',function(){
	var value = $(this).val().toLowerCase();
	$("#allord tr").filter(function() {
	   $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	});
});