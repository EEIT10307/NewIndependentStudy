$(window).on('load',function () {   
	
	$("#memberRegister").click(function (e) {
		
	    $("#email").val("shuuuuuuuuuuzo@gmail.com");
	    $("#phone").val("0912345678");
	    $("#pwd").val("aa!123");
	}) 
	
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
    url: "RegisterServlet",
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
        
        
    },
    error:function(responseerror){
        // alert(responseerror.responseText)
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




 // FB登入
// 檢查是否已登入，沒，do nothing
//AutoLogin檢查是否是會員by Email， 不是==> 直接註冊資料並且記錄cookies然後登入
//是，直接紀錄Cookies並登入


 
window.fbAsyncInit = function () {
    FB.init({
        appId: '1066792026823320',
        cookie: false,
        xfbml: true,
        version: 'v3.2'
    });

    // FB.AppEvents.logPageView();   
    //判斷使用者是否已登入FB     
    // Check whether the user already logged in
    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            //display user data
            // alert("Register getLoginStatus CHECK!!!!")
            //判斷是否是會員，如果不是
            getFbUserData();
        }
    });
};

// Load the JavaScript SDK asynchronously
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) { return; }
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/zh_TW/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));



// Facebook login with JavaScript SDK

// $("#fbloginbutton").click(function (e) {  
// });   

function fbLogin() {
//    alert("FB登入確認");
    FB.login(function (response) {
        if (response.authResponse) {
            // Get and display the user profile data
            getFbUserData();


        }
    }, { scope: 'email' });
};

// Fetch the user profile data from facebook
function getFbUserData() {
    FB.api('/me?fields=name,first_name,last_name,email',
        function (response) {
            console.log('Register臉書登入');
            // alert("FB登入者=> " + response.email);

            var email = response.email
            //判斷是否為會員email，AutoLoginCheck
            //Email確認是會員
            var combie = { "email": email };
            var json = JSON.stringify(combie);
            //利用ajax將json型態的email丟到Controller的AutoLoginCheck察看是否為會員的email
            $.ajax({
                type: "post",
                url: "AutoLoginCheck",
                data: json,
                success: function (jsonback) {

                    // alert("AutoLoginCheck傳回 = " + jsonback);

                    if (jsonback == null || jsonback == "") {

                        // alert("您的FB email非會員信箱");
                        
                        //判斷是否為會員email，AutoLoginCheck
                        // Email確認是會員
                        // var combie = { "email": email };
                        // var json = JSON.stringify(combie);

                        $.ajax({
                            type: "post",
                            url: "FbRegisterServlet",
                            data: json,
                            success: function (registerdata) {


                                // JSON.parse()將JSON字串剖析為JavaScript物件供操作使用。
                                //  alert("註冊資料正確，registerdata為"+registerdata);

                                var mailmember = registerdata.replace("{", "").replace("}", "").replace("\"").split(",")[1].split(":")[1];
                                // // var goto = "resucess.html?name="+mailmember
                                // var goto = "index.html?name=" + mailmember
                                // //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                                // window.location.assign(encodeURI(goto)); 
                                 //    window.location.assign(encodeURI(goto)) ; 
                                 
                                 //設定fbcookie
                                 var expire_days = 1; // 過期日期(天)
                                 var day = new Date();
                                 day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
                            // day.setTime(day.getTime() + (60 * 1000));
                            var expires = "expires=" + day.toGMTString();
                            // document.cookie = "name=test" + "; " + expires + '; domain=localhost:8080; path=/';
                            document.cookie = "email=" + reply + "; " + expires + '; path=/';
                            // alert("document.cookie=" + cookie)
                            //FB登入
                            // alert("FB登入成功")
                            window.location.assign(encodeURI(index.html));
                            // $("a#memberdescription").show();
                            alert("煩請到會員資料新增電話才可啟動租賃服務")
                            
                            if ($("#memberloginstatus").text() == "") {
                                $("#memberloginstatus").append("Welcome" + "   " + mailmember);
                                $("a#login").hide();
                                $("a#registerNav").hide();
                                $("a#memberlogoutstatus").append("登出");
                                $("a#memberdescription").show();

                            // if ($('#modeltt').is(':hidden')) {
                            // } else {
                            //     $('#modeltt').hide();
                            //     window.location.href="/motorcycleiiieduproject/index.html";
                            //     //window.location.reload(true);
                            // }
                            
                        }

                            }
                            });

                    } else {




                        //  var goto = "index.html?name="+jsonback
                        //  //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                        // alert("Email為會員資料");
                        
                        var goto = "index.html?name="+jsonback
                        //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                        window.location.assign(encodeURI(goto));
                        // // var cookies = document.cookie;
                        
                        // //  window.location.assign(encodeURI(goto)) ; 
                        // //  $("#memberloginstatus").innerHTML= "Welcome"+jsonback;
                        // //因為footer和nav會同時執行login，因此會執行兩次login check，使用此方式產生一個welcome字串
                        // //    var goto = "index.html?name="+jsonback
                        // //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                        // //    window.location.assign(encodeURI(goto)) ; 
                        // if ($("#memberloginstatus").text() == "") {
                        //     $("#memberloginstatus").append("Welcome" + "   " + jsonback);
                        //     $("a#login").hide();
                        //     $("a#registerNav").hide();
                        //     $("a#memberlogoutstatus").append("登出");
                        //     $("a#memberdescription").show();

                            //設定fbcookie
                            var expire_days = 1; // 過期日期(天)
                            var day = new Date();
                            day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
                            // day.setTime(day.getTime() + (60 * 1000));
                            var expires = "expires=" + day.toGMTString();
                            // document.cookie = "name=test" + "; " + expires + '; domain=localhost:8080; path=/';
                            // cookies = "email=" + jsonback + "; " + expires + '; path=/';
                            document.cookie = "email=" + jsonback + "; " + expires + '; path=/';
                        
                            // alert("註冊後document.cookie=" + document.cookie)
                        //     //FB登入
                        //     alert("FB登入成功")
                        //     $("a#memberdescription").show();
                        //     alert("煩請到會員資料新增電話才可啟動租賃服務")
                        //     if ($('#modeltt').is(':hidden')) {
                        //     } else {
                        //         $('#modeltt').hide();                              
                        //          window.location.reload(true);
                        //     }
                           
                        // }
                    }
                },
                error:function(responseerror){
                    // alert(responseerror.responseText)
                }

            });



        });



        
}