$(document).ready(function () {
   


$("#checkall").click(function (e) { 
 
$.ajax({
    type: "POST",
    url: "CheckAllServlet",
    dataType: "json",
    contentType : "application/json",
    success: function (response) {
     var jsons   =  JSON.stringify(response);
     alert("MVC傳回 = " + jsons) ; 

     sessionStorage.myjson = jsons ; 
     window.location.assign ("checkall.html");     
        
    } , 
    error:function(){
        
    }
    
  
    
});
});

$("#myForm").on('submit',(function(e){
    e.preventDefault(); // 停止觸發submit
    alert("upload") ; 
    var formData = new FormData($("#myForm")[0]); // 使用FormData包裝form表單來傳輸資料
    alert(formData.getAll)
    $.ajax({
      type: "POST",
      url: "upload",
      data:formData,
      cache:false, // 不需要cache
      processData: false, // jQuery預設會把data轉為query String, 所以要停用
      contentType: false, // jQuery預設contentType為'application/x-www-form-urlencoded; charset=UTF-8', 且不用自己設定為'multipart/form-data'
      dataType: 'text',
      success: function (data){
    	  alert("upload sucess") ; 
      }      


      

    
});
}));

//$("button").click(function (e) { 
//	   alert("hi") ; 
//
//	   $.ajax({
//	       type: "post",
//	       url: "testcontroller",
//	       data: "data",
//	       success: function (response) {
//	           
//	    	alert("sucess");
//	    	   
//	       alert(response);
//
//	       }
//	   });
//	    
//	}); 


});