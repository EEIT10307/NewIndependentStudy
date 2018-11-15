$(window).on('load',function () {
    
	// 判斷是否有cookies， 
	// 如果有cookies，判斷cookies內是否有紀錄email
//如果有email，判斷是否為會員的email
//			如果是會員的email，返回resucess.html並顯示會員eamil出來

//			否則要求他登入



 var cookie = document.cookie; 
//  alert("cookie為"+cookie);
 //Cookie是否存在
//  if(cookie != "" && cookie != null){
    if(cookie!=null||cookie!="null"||cookie!=""||typeof(cookie)!="undefined"||typeof(cookie)!=undefined||typeof(cookie)!="false"||typeof(cookie)!=false){

    //Cookies是否有email資訊
    if(cookie.indexOf("email")==-1){
        // alert("Cookie資訊中未包含email資訊");
        $("a#memberdescription").hide();
        
    }else{

        var email= cookie.split("email=")[1].split(";")[0];
        // alert("Cookies內儲存email="+email);
        //判斷是否為會員email，AutoLoginCheck
        //Email確認是會員
        //導向resucess.html
        var combie =  {"email":email} ;
        var json    =  JSON.stringify(combie) ; 
        // alert("json="+json);
        //利用ajax將json型態的email丟到Controller的AutoLoginCheck察看是否為會員的email
        $.ajax({
             type: "post",
             url: "AutoLoginCheck",
             data: json,
             success:function (jsonback) {
                //  alert("AutoLoginCheck傳回 = " + jsonback) ;
                 if(jsonback==null){
                    //  alert("Cookie內的email非會員信箱或沒email資訊");
                 }else{
                       
                    //  var goto = "index.html?name="+jsonback
                    //  //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                    //  alert("Cookie為會員資料");
                    //  window.location.assign(encodeURI(goto)) ; 
                    //  $("#memberloginstatus").innerHTML= "Welcome"+jsonback;
                    //因為footer和nav會同時執行login，因此會執行兩次login check，使用此方式產生一個welcome字串
                        if($("#memberloginstatus").text() == ""){
                     $("#memberloginstatus").append("Welcome"+"   "+jsonback);
                     $("a#login").hide();
                     $("a#registerNav").hide();
                     $("a#memberlogoutstatus").append("登出");
                    }
                    }
             }
         
        });

    };
  }else{
    //   alert("網頁內沒任何Cookies");
  };
    
//$("#login").click(function (e) { 
  $("#loginConfirm").click(function (e) { 
	
var email = $("[name = 'email']").val()  ; 
var password = $("[name = 'password']").val()  ; 


//管理員登入窗口
if(email=="gm@gmail.com" && password=="gm!123"){
    var gmpage = "gmpage.html?name="+email
        	
    //重要！！ 轉傳時要編碼一次編成ＵＲＩ
	window.location.href="revenue.html";
return;
}
var combie =  {"email":email , "password":password} ; 
//var combie =  {"email":email , "password":password , "phone" : phone} ; 
//原本combie是json物件 利用以下方法翻成json字串 ; 
var json    =  JSON.stringify(combie) ; 
// alert("email:"+email)
// alert("password:"+password)
$.ajax({
    type: "post",
    url: "http://localhost:8080/motorcycleiiieduproject/LoginServlet",
    data: json,
    success: function (jsonback) {
        // alert("server傳回 = " + jsonback) ;  
//        if(jsonback.length!=0){
//          if(jsonback.length==8){
        if(jsonback=="nullpage"){
        	  
        	var nullpage = "nullpage.html?name="+jsonback
        	
        	//重要！！ 轉傳時要編碼一次編成ＵＲＩ
        	window.location.assign(encodeURI(nullpage)) ;
        }else{
        	
        	if(jsonback.length==0){
        		
        		var errorpage = "errorpage.html?name="+jsonback
        		
        		//重要！！ 轉傳時要編碼一次編成ＵＲＩ
        		window.location.assign(encodeURI(errorpage)) ; 
        	}else{
        		
        		var goto = "index.html?name="+jsonback
        		//重要！！ 轉傳時要編碼一次編成ＵＲＩ
                window.location.assign(encodeURI(goto)) ; 
                $("#memberloginstatus").innerHTML = "Welcome"+jsonback;
        	}
        }
  
    
    }
//    error: function(jsonback){};
});







});

$("#checkall").click(function (e) { 
    e.preventDefault();
    
});

$("#pwd").blur(function (e) { 
    e.preventDefault();
    chkPassword();
  
});

});
//document.addEventListener("DOMContentLoaded", function () {
    //	document.getElementById("email").addEventListener("blur", checkName);
        // document.getElementById("pwd").addEventListener("blur", chkPassword );  //事件繫結，焦點離開 ，採用W3CDOM處理程序，取得password的ID:idPwd之後，binding chkPassword

  //  });

 function chkPassword() {
    //取得元素值
    var pwd = document.getElementById("pwd").value;
    var theResult = document.getElementById("idsp");
    //判斷元素值是否為空白，密碼長度是否大於6
    var pwdLen = pwd.length;
    var flag1 = false; var flag2 = false; var flag3 = false
   
    if (pwd == "") {
        // alert("Password must enter");
        theResult.innerHTML ="<i><img src='Images/error.png'>密碼必須輸入</i>";
    }
    else if (pwdLen >= 6) {

        for (var i = 0; i < pwdLen; i++) {
            var PwdChr = pwd.charAt(i).toUpperCase();
            if (PwdChr >= "A" && PwdChr <= "Z")
                flag1 = true;
            else if (PwdChr >= "0" && PwdChr <= "9")
                flag2 = true;
            else if (PwdChr== "!" ||PwdChr== "@"||PwdChr== "#"||PwdChr== "$"||PwdChr== "%"||PwdChr== "^"||PwdChr== "&"||PwdChr== "*")// 用encode將輸入轉碼後，如果輸入有特殊符號，轉碼的字串會與原輸入的不一樣
                flag3 = true;
            if (flag1 && flag2 && flag3) break;

        }
        if (flag1 && flag2 && flag3){
          theResult.innerHTML ="<img src='Images/correct.png'>密碼正確" ;  
        
        }
        else 
        theResult.innerHTML ="<i><img src='Images/error.png'>密碼錯誤</i>";


    }

    else
        // alert("Password length must greater than 6")
        theResult.innerHTML ="<i><img src='Images/error.png'>密碼必需至少六個數字</i>";


 

        
    
}
    //如果長度是否大於6，判斷是否包含字母、數字、特殊符號
   