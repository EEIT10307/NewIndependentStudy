$(document).ready(function () {
var rr = [{"name":1},{"name":3}]
alert(rr[0].name + rr[1].name)    


$("#checkall").click(function (e) { 
 
$.ajax({
    type: "POST",
    url: "http://localhost:8082/homehiber/CheckAllServlet",

    success: function (response) {

     alert("servert傳回 = " + response) ; 

     sessionStorage.myjson = response ; 
     window.location.assign ("checkall.html");     
        
    }
});




    
});





});