$(document).ready(function () {
    

	// Button toggle of login and logout
   $("#logout").click(function (e) { 
	$("#login").remove();
	$("#d1").append("<button  id = 'login' class='execute' >登入</button>");
    $("#logout").hide();
   });
    	
	$("#d1").on("click","#login",function(){

		$("#login").hide();
		$("#logout").show();
    		})






});