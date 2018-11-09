$(document).ready(function () {
    


var cookies = document.cookie; 

    $("#logout").click(function () { 
		
		//是否有Cookies存在
		if(cookies != "" || cookies != null || cookies != "null" || typeof(cookies) != undefined || typeof(cookies) != "undefined" || typeof(cookies) != false){

			  //Cookies是否有email資訊
			  if(cookies.indexOf("email")==-1){
				alert("Cookie資訊中未包含email資訊");
			}else{

				var email= cookies.split("email=")[1].split(";")[0];
				alert("Cookies內儲存email="+email);
				var password= cookies.split("password=")[1].split(";")[0];
				alert("Cookies內儲存password="+password);

				DelEmailCookie(email);
				DelPasswordCookie(password);
				// document.cookie = "email" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
				// document.cookie = "password" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
				// $.cookie(cookie, { expires: -1 });
				// deleteCookies();
				alert("cookie= "+document.cookie);

	};
		}else{
			alert("沒有Cookies紀錄存在");
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
function  DelEmailCookie(name){
    alert("移除cookie ="+name)
    expire_days = -1; // 過期日期(天)
    var day = new Date();
    day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
    var expires = "expires=" + day.toGMTString();
	document.cookie = "email="+ "" + "; " + expires + "; path=/";
	
    location.reload()
    // documents.cookie  =  name  +  "="  +  cval  +  ";  expires="+  exp.toGMTString();
}
//删除passwordCookie
function  DelPasswordCookie(name){
    alert("移除cookie ="+name)
    expire_days = -1; // 過期日期(天)
    var day = new Date();
    day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
    var expires = "expires=" + day.toGMTString();
	document.cookie = "password="+ "" + "; " + expires + "; path=/";	
    location.reload()
    // documents.cookie  =  name  +  "="  +  cval  +  ";  expires="+  exp.toGMTString();
}



});