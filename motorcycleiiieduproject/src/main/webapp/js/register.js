$(window).on('load',function () {   
  
$("#register").click(function (e) { 


var email = $("[name = 'email']").val()  ; 
var password = $("[name = 'password']").val()  ; 
var phone = $("[name = 'phone']").val() ; 

var combie =  {"email":email , "password":password , "phone" : phone} ; 
//原本combie是json物件 利用以下方法翻成json字串 ; 
var json    =  JSON.stringify(combie) ; 
//alert("email="+email)

$.ajax({
    type: "post",
    url: "http://localhost:8080/motorcycleiiieduproject/RegisterServlet",
    data: json,
    success: function (registerdata) {
//        alert("server傳回 = " + jsonback) ;  
//  
//        var goto = "resucess.html?name="+jsonbackeg
//        //重要！！ 轉傳時要編碼一次編成ＵＲＩ
//        window.location.assign(encodeURI(goto)) ; 
        // alert(registerdata);
        // var jsonString = JSON.stringify(registerdata);
        // alert("jsonString為"+jsonString);
        // JSON.parse()將JSON字串剖析為JavaScript物件供操作使用。
//          var parseString  =JSON.parse(registerdata);
        // var jsonback = parseString.email;
        // alert(registerdata.type);
        //     alert(registerdata.email);
        //    var jsonback=registerdata.email;
    //    alert("server傳回 = " + registerdata) ;  
       if(registerdata=="nullpage"){
       	var nullpage = "nullpage.html?name="+registerdata
       	
       	//重要！！ 轉傳時要編碼一次編成ＵＲＩ
       	window.location.assign(encodeURI(nullpage)) ;
       }else{
       	
       	
       	if(registerdata=="errorlogintype"){
       		
       		var errorlogintype ="errorlogintype.html?name="+registerdata
       		
       		window.location.assign(encodeURI(errorlogintype)) ;
       	}else{
       		
       		
       		if(registerdata=="duplicateEmail"){
       			
       			var duplicateEmail = "duplicateEmail.html?name="+registerdata
       			
       			//重要！！ 轉傳時要編碼一次編成ＵＲＩ
       			window.location.assign(encodeURI(duplicateEmail)) ; 
       		}else{
       			// JSON.parse()將JSON字串剖析為JavaScript物件供操作使用。
        //  alert("註冊資料正確，registerdata為"+registerdata);
    
            var mailmember =  registerdata.replace("{","").replace("}","").replace("\"").split(",")[1].split(":")[1];
       			// var goto = "resucess.html?name="+mailmember
       			var goto = "index.html?name="+mailmember
       			//重要！！ 轉傳時要編碼一次編成ＵＲＩ
       			window.location.assign(encodeURI(goto)) ; 
       		}
       		
       		
       	}
       	
       }
        
        
    }
        
    
});




$("#checkall").click(function (e) { 
    e.preventDefault();
    
});

  
});
  

$("#pwd").blur(function (e) { 
    e.preventDefault();
    chkPassword();
  
});

});

// document.addEventListener("DOMContentLoaded", function () {
    //	document.getElementById("email").addEventListener("blur", checkName);
        // document.getElementById("pwd").addEventListener("blur", chkPassword);  //事件繫結，焦點離開 ，採用W3CDOM處理程序，取得password的ID:idPwd之後，binding chkPassword
// });

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



