$("#checkout").click(function(){
	$.ajax({
	    type:"GET",
	    url: "http://localhost:8080/homehiber/RegisterServlet",
	    data: json,
	    success: function (jsonback) {
	        alert("租車成功 ") ;
	        window.location.assign(encodeURI("index.html"));
	    }
	});
});
