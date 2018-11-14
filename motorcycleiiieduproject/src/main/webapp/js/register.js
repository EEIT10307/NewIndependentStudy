$(document).ready(function () {
    
  
$("#register").click(function (e) { 

var id = $("[name = 'id']").val()  ; 
var name = $("[name = 'name']").val()  ; 
var password = $("[name = 'password']").val() ; 

var combie =  {"id":id , "name":name , "password" : password} ; 
//原本combie是json物件 利用以下方法翻成json字串 ; 
var json    =  JSON.stringify(combie) ; 


$.ajax({
    type: "post",
    url: "http://localhost:8082/homehiber/RegisterServlet",
    data: json,
    success: function (jsonback) {
        alert("server傳回 = " + jsonback) ;  
  
        var goto = "resucess.html?name="+jsonback
        //重要！！ 轉傳時要編碼一次編成ＵＲＩ
        window.location.assign(encodeURI(goto)) ; 

    }
});




$("#checkall").click(function (e) { 
    e.preventDefault();
    
});



    
});
  











});