$(window).on('load',function () {

var cookies = document.cookie; 
// alert("document.cookie="+document.cookie)
// alert("cookies"+cookies)

    $("a#memberlogoutstatus").click(function () { 
		// alert("Logout Button")
		//是否有Cookies存在
		if(cookies != "" || cookies != null || cookies != "null" || typeof(cookies) != undefined || typeof(cookies) != "undefined" || typeof(cookies) != false){

			  //Cookies是否有email資訊
			  if(cookies.indexOf("email")==-1){
				// alert("Cookie資訊中未包含email資訊");
				
			}else{
				//舊方式，找出email & password並刪除
				
				var email= cookies.split("email=")[1].split(";")[0];
//  HEAD
				// alert("Cookies內儲存email="+email);
				if(cookies.indexOf("password")!=-1){

					var password= cookies.split("password=")[1].split(";")[0];
					// alert("Cookies內儲存password="+password);
					DelPasswordCookie();
				}
				if(cookies.indexOf("memberphone")!=-1){

					var phone= cookies.split("memberphone=")[1].split(";")[0];
					// alert("Cookies內儲存phone="+phone);
					DelPhoneCookie();
				}
				DelEmailCookie();
				
				//新方式，全部刪掉Cookies
				clearAllCookie();
			
// =======
// 				// alert("Cookies內儲存email="+email);
// 				var password= cookies.split("password=")[1].split(";")[0];
// 				// alert("Cookies內儲存password="+password);
// 				var phone= cookies.split("memberphone=")[1].split(";")[0];
// 				DelEmailCookie(email);
// 				DelPasswordCookie(password);
// 				DelPhoneCookie(phone);
//  branch 'master' of https://github.com/EEIT10307/NewIndependentStudy.git
				// document.cookie = "email" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
				// document.cookie = "password" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
				// $.cookie(cookie, { expires: -1 });
				// deleteCookies();
				// alert("刪除後檢查document.cookie= "+document.cookie);


                $("a#memberdescription").hide();
				$("#memberloginstatus").hide();
				$("a#login").show();
				$("a#registerNav").show();
				$("a#memberlogoutstatus").hide();
				// alert("感謝您你使用!!!")
				window.location.href="index.html";
	};
		}else{
			// alert("沒有Cookies紀錄存在");
		};
    
});

// function deleteCookies(name) { 
// 	document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
// }
// function deleteCookies() { 
//   alert("進入deleteAllCookies");

// 		var keys = document.cookie.match(/[^=;]+(?=\=)/g);
// 		if(keys){
// 			for(var i = keys.length;i--;){
// 			document.cookie=keys[i]+'=0;expires='+ new Date(0).toUTCString()
// 		}
// 		}
// }

//删除emailCookie
function  DelEmailCookie(){
    // alert("移除cookie ="+name)
    expire_days = -1; // 過期日期(天)
    var day = new Date();
    day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
    var expires = "expires=" + day.toGMTString();
	document.cookie = "email="+ "" + "; " + expires + "; path=/";
	// alert("DeletEmail(document.cookie)==>"+document.cookie)
	
    location.reload()
    // documents.cookie  =  name  +  "="  +  cval  +  ";  expires="+  exp.toGMTString();
}
//删除passwordCookie
function  DelPasswordCookie(){
    // alert("移除cookie ="+name)
    expire_days = -1; // 過期日期(天)
    var day = new Date();
    day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
    var expires = "expires=" + day.toGMTString();
	document.cookie = "password="+ "" + "; " + expires + "; path=/";
	// alert("DeletPassword(document.cookie)==>"+document.cookie)
	
    location.reload()
    // documents.cookie  =  name  +  "="  +  cval  +  ";  expires="+  exp.toGMTString();
}

//清除cookie
function clearAllCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
	}
	// alert("clearAllCookie()刪除Cookies後document.cookie==>"+document.cookie);
	// alert("cookies=>"+cookies)
    window.location.reload(true);
}

//删除phoneCookie
function  DelPhoneCookie(){
    // alert("移除cookie ="+name)
    expire_days = -1; // 過期日期(天)
    var day = new Date();
    day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
    var expires = "expires=" + day.toGMTString();
	document.cookie = "memberphone="+ "" + "; " + expires + "; path=/";	
    location.reload()
    // documents.cookie  =  name  +  "="  +  cval  +  ";  expires="+  exp.toGMTString();
}

});