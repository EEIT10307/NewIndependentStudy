$(document).ready(function () {
   // Aotologin
    // 判斷是否有cookies， 沒 do nothing(導回index.html)
    // 如果有cookies，判斷cookies內是否有紀錄email， 沒 hide 會員資料memberdescription(導回index.html)
    //如果有email，判斷是否為會員的email，沒 do nothing (導回index.html)
    //如果是會員的email，顯示會員eamil出，hide登入按鍵

    // FB登入
    // 檢查是否已登入，沒，do nothing
    //AutoLogin檢查是否是會員by Email， 不是==> 直接註冊資料並且記錄cookies然後登入
    //是，直接紀錄Cookies並登入


    var cookie = document.cookie; 

   if(cookie.indexOf("memberphone") != -1){
    var t = $('#dataTable').DataTable();
    t.clear()
var t =$('#dataTable').DataTable() ;
    var memphone= cookie.split("memberphone=")[1].split(";")[0];
    var nonmemberphone = JSON.stringify({ "nonmemberinputphone": memphone })
    $.ajax({
        type: "POST",
        url: "showMemberAndNonMemberDetail",
        data: nonmemberphone,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            for (let sobj in response) {
                var buildacce = ""
                if (response[sobj].accessoriesAmount != "") {

                    var accesss = JSON.parse(response[sobj].accessoriesAmount)
                    var accesssobjs = Object.keys(accesss)
                    for (var k in accesssobjs) {
                        buildacce +=  accesssobjs[k] + "x" + accesss[accesssobjs[k]] + "<br>"
                    }
                }
                t.row.add([
                    response[sobj].orderSerialNum ,
                    "取車:"+response[sobj].pickupStore+" "+response[sobj].pickupDate+
                    "<br>還車:"+response[sobj].dropoffStore+" "+response[sobj].dropoffDate+
                    "<br>車名:"+response[sobj].bikeModel +"<br>" +buildacce,
                    response[sobj].orderTotalPrice  ,
                    response[sobj].orderTime    ,
                           "滿意度"
                ]).draw(false)
            }
        },
        error:function(responseerror){
            alert(responseerror.responseText)
        }
    });
   }

    //  alert("cookie為"+cookie);
     //Cookie是否存在
    //  if(cookie != "" && cookie != null){
        if(cookie!=null||cookie!="null"||cookie!=""||typeof(cookie)!="undefined"||typeof(cookie)!=undefined||typeof(cookie)!="false"||typeof(cookie)!=false){
    
        //Cookies是否有email資訊
        if(cookie.indexOf("email")==-1){
            alert("Cookie資訊中未包含email資訊");
            var goto1 = "index.html"
            //重要！！ 轉傳時要編碼一次編成ＵＲＩ
            window.location.assign(encodeURI(goto1)) ; 
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
                
                        //如果要擋住非一般會員進入此頁面，將其導向goto2的index.html
                         var goto2 = "index.html"
                         //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                         window.location.assign(encodeURI(goto2)) ; 
                     }else{
                           
            
                         if($("#memberloginstatus").text() == ""){
                         $("#memberloginstatus").append("Welcome"+"   "+jsonback);
                         $("a#login").hide();
                         $("a#registerNav").hide();
                         $("a#memberlogoutstatus").append("登出");
                        }

                        // //登入後，直接將個人照片讀取路徑更改
                        // $("#wwwwimg").attr("src", "Images\\Front" + email + ".jpg");


                        }
                 },
                 error:function(responseerror){
                     alert(responseerror.responseText)
                 }
             
            });
    
        };
      }else{
        //   alert("網頁內沒任何Cookies");
          var goto = "index.html"
        		//重要！！ 轉傳時要編碼一次編成ＵＲＩ
                window.location.assign(encodeURI(goto)) ; 
               
      };




    // $("#change").click(function () { 
        //從cookies抓email
        // var email = $("[id = 'desmail']").val()  ; 
        var cookie = document.cookie; 
        var email= cookie.split("email=")[1].split(";")[0];
        // alert("目前會員="+email);
        // var password = $("[id = 'despassword']").val() ; 
        // var name = $("[id = 'desname']").val() ; 
        // var phone = $("[id = 'desphone']").val()  ; 
        // var birthday = $("[id = 'desbirthday']").val()  ; 
        // var gender = $("[id = 'desgender']").val()  ; 
        // var address = $("[id = 'desaddress']").val()  ; 
        
        
        // var combie =  {"email":email,"password":password,"name":name,"phone":phone,"birthday":birthday,"gender":gender,"address":address} ; 
        var combie =  {"email":email};
        //原本combie是json物件 利用以下方法翻成json字串 ; 
       var json    =  JSON.stringify(combie) ; 
        // alert("ANS="+json);
        //查詢目前登入的會員資料
        $.ajax({
            type: "post",
            url: "CheckSingleServlet",
            contentType : "application/json; charset=utf-8",
            data: json,
            success:function (jsonback) {
            //  alert("ajax success");
             var memdata =JSON.stringify(jsonback);
            //  alert(memdata);
             //回傳的memdata為json格式的字串，parse轉換成物件即可呼叫其內的value來使用

                var bdate = JSON.parse(memdata).birthday;
                // alert("bdate=" + bdate);
                if (bdate == null || bdate == "") {
                    // var result = "請輸入生日";
                } else {

                    var myDate = new Date(bdate);
            
                     var month = myDate.getMonth() + 1;
                     var day = myDate.getDate();
                     month = (month.toString().length == 1) ? ("0" + month) : month;
                     day = (day.toString().length == 1) ? ("0" + day) : day;
      
                     var result = myDate.getFullYear() + '-' + month + '-' + day; //更改格式後的日期
                     $("#desbirthday").attr("value",result);
             }

               
        //    var memmail =memdata.replace("{","").replace("}","").replace("\"").split(",")[1].split(":")[0];
        //    alert(memmail)
        // `alert(JSON.parse(memdata).email);
            //    $("#desmail").text(JSON.parse(memdata).email); 錯誤用法
                $("#desmail").attr("value",JSON.parse(memdata).email);
                $("#hiddenemail").attr("value",JSON.parse(memdata).email);            
                $("#despassword").attr("value",JSON.parse(memdata).password);
                $("#desname").attr("value",JSON.parse(memdata).name);
                $("#desphone").attr("value",JSON.parse(memdata).phone);
                // $("#desbirthday").attr("value",JSON.parse(memdata).birthday);
                $("#desgender").attr("value",JSON.parse(memdata).gender);
                $("#desaddress").attr("value",JSON.parse(memdata).address);
            //    alert("生日:"+result);
            },
            error:function(responseerror){
                alert(responseerror.responseText)
            }
    
        });

        //一進入網頁就執行，傳送combie={"email":email}轉成的json進入controller
        $.ajax({
            type: "post",
            url: "PhotoStringCheckServlet",
            contentType : "application/json; charset=utf-8",
            data: json,
            success:function (drop) {
            //  alert("ajax success in PhotoString ");
            //  alert(email);
            // alert("drop"+drop);
             if(drop){
                var cookie = document.cookie; 
                var email= cookie.split("email=")[1].split(";")[0];
                // alert("更新wwwwimg="+email);
                $("#wwwwimg").attr("src","Images/Front"+email+".jpg");
                
             }
            //  http://localhost:8080/motorcycleiiieduproject/Images/Frontqw@gmail.com.jpg
           //                                                 Images/Frontqw@gmail.com.jpg
            },
            error:function(responseerror){
                alert(responseerror.responseText)
            }
            });


        $("#change").click(function () { 
    var gen =  $("input[name='gender']:checked").val();

    //   $("#desgender").val()
        	var res = confirm("確認送出?");
	        if(res == true){

        var password =$("#despassword").val();
        var name = $("#desname").val() ; 
        var phone = $("#desphone").val()  ; 
        var birthday = $("#desbirthday").val()  ; 
        // var from = $("#desbirthday").val().split("-")
        // var f = new Date(from[2], from[1] - 1, from[0])
        var gender = gen.toString();
        var address = $("#desaddress").val()  ; 
                    var combie =  {"email":email,"password":password,"name":name,"phone":phone,"birthday":birthday,"gender":gender,"address":address} ; 
        //原本combie是json物件 利用以下方法翻成json字串 ; 
        //不轉成物件(=不使用stringify)即可讓後面Controller用String參數直接接數值
       var json    =  JSON.stringify(combie) ; 
        // alert("修改前="+json);
        $.ajax({
            type: "post",
            url: "ChangeServlet",
            contentType : "application/json; charset=utf-8",
            data: json,
            success:function (jsonback) {
            //  alert("ajax success");
            //  var memdata =JSON.stringify(jsonback);
            //  alert(jsonback);
  alert(jsonback)
            window.location.href="member.html";
            },
            error:function(responseerror){
                alert(responseerror.responseText)
            }
        });
    //    window.location.href="memberdescription.html";

        }else{
        	
        }
    });

    
    // var cookie = document.cookie; 
    // var email= cookie.split("email=")[1].split(";")[0];
    //     alert("Cookies內儲存email="+email);
        
    // var combieforPIC =  {"email":email}
    // var jsonforPIC   =  JSON.stringify(combieforPIC) ; 
    // alert(jsonforPIC);


    
    $("#myForm").on('submit',(function(e){
        e.preventDefault(); // 停止觸發submit


        var cookie = document.cookie;
        var email = cookie.split("email=")[1].split(";")[0];
      
        // $("#wwwwimg").attr("src", "Images\\Front" + email + ".jpg");
        // alert("Cookies內儲存email=" + email);
        // alert("upload") ; 
        var formData = new FormData($("#myForm")[0]); // 使用FormData包裝form表單來傳輸資料
        alert(formData.getAll)
        $.ajax({
          type: "POST",
          url: "upload",
          data: formData,
          cache:false, // 不需要cache
          processData: false, // jQuery預設會把data轉為query String, 所以要停用
          contentType: false, // jQuery預設contentType為'application/x-www-form-urlencoded; charset=UTF-8', 且不用自己設定為'multipart/form-data'
          dataType: 'text',
          success: function (data){
              alert("upload sucess") ; 
            //   $("#wwwwimg").attr("src","Images\\Front"+email+".jpg");
           
          },
          error:function(responseerror){
              alert(responseerror.responseText)
          }    
    
         
    
        
    });

    var combie =  {"email":email};
    //原本combie是json物件 利用以下方法翻成json字串 ; 
   var json    =  JSON.stringify(combie) ; 
    // alert("ANS="+json);
      //將目前登入會員email傳給Controller將資料存入MemberDetail的profilePhoto字串欄位中
      $.ajax({
        type: "post",
        url: "ProfilePhotoServlet",
        contentType : "application/json; charset=utf-8",
        data: json,
        success:function (jsonback) {
        //  alert("ajax check success");
            var memdata = JSON.stringify(jsonback);
            // alert(memdata);
            var portfolio =JSON.parse(memdata).profilePhoto;
            // alert("portfolio==> "+portfolio)
            if(portfolio != null || portfolio != "")
            {

                setTimeout(function(){
                    console.log('A');
                    $("#wwwwimg").attr("src", "Images\\"+portfolio);
                    window.location.reload();
                },3000);
               
            }
        },
        error:function(responseerror){
            alert(responseerror.responseText)
        }
        });



    }));


  



});
// $("a#memberlogoutstatus").click(function () {

//         // Logout from facebook
//         // function fbLogout() {
//         FB.logout(function () {
//             clearAllCookie();
//             alert("FB Login clearAllCookie to Logout!!!");
//             console.log('臉書登出登出登出登出登出登出登出登出');
//         });
//         // }

//         //清除cookie
//         function clearAllCookie() {
//             alert("清除所有Cookies!!!!!!!")
//             var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
//             if (keys) {
//                 for (var i = keys.length; i--;)
//                     document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
//             }
//             window.location.reload(true);


//         }
//     });

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
    
        /////////FB登出//////////////FB登出////////////////FB登出//////////////////FB登出////////////////
        $("a#memberlogoutstatus").click(function () {

            // Logout from facebook
            // function fbLogout() {
            FB.logout(function () {
                clearAllCookie();
                alert("FB  Logout!!!");
                console.log('臉書登出登出登出登出登出登出登出登出');
            });
            // }
    
            //清除cookie
            function clearAllCookie() {
                alert("清除所有Cookies!!!!!!!")
                var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
                if (keys) {
                    for (var i = keys.length; i--;)
                        document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
                }
                window.location.reload(true);
    
    
    
    
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
            }
    
        });
  

//////////////FB登入/////////////////FB登入/////////////////////FB登入///////

 // FB登入



 
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
            alert("getLoginStatus CHECK!!!!")
            //判斷是否是會員，如果不是
            getFbUserData();
        }else{
            // alert("FB 沒有登入!! 即將導入index.html")
            // var goto1 = "index.html"
            // //重要！！ 轉傳時要編碼一次編成ＵＲＩ
            // window.location.assign(encodeURI(goto1)) ; 
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
            console.log('臉書登入登入登入登入登入登入登入');
            alert("FB登入者=> " + response.email);

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

                    alert("AutoLoginCheck傳回 = " + jsonback);

                    if (jsonback == null || jsonback == "") {

                        alert("您的FB email非會員信箱");
                        
                        //判斷是否為會員email，AutoLoginCheck
                        // Email確認是會員
                        // var combie = { "email": email };
                        // var json = JSON.stringify(combie);

                        $.ajax({
                            type: "post",
                            url: "FbRegisterServlet",
                            data: json,
                            success: function (reply) {


                                // JSON.parse()將JSON字串剖析為JavaScript物件供操作使用。
                                //  alert("註冊資料正確，registerdata為"+registerdata);

                                // var mailmember = jsonback.replace("{", "").replace("}", "").replace("\"").split(",")[1].split(":")[1];
                                // // var goto = "resucess.html?name="+mailmember
                                // var goto = "index.html?name=" + mailmember
                                // //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                                // window.location.assign(encodeURI(goto)); 
                                 //    window.location.assign(encodeURI(goto)) ; 
                        if ($("#memberloginstatus").text() == "") {
                            $("#memberloginstatus").append("Welcome" + "   " + reply);
                            $("a#login").hide();
                            $("a#registerNav").hide();
                            $("a#memberlogoutstatus").append("登出");
                            $("a#memberdescription").show();

                            //設定fbcookie
                            var expire_days = 1; // 過期日期(天)
                            var day = new Date();
                            day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
                            // day.setTime(day.getTime() + (60 * 1000));
                            var expires = "expires=" + day.toGMTString();
                            // document.cookie = "name=test" + "; " + expires + '; domain=localhost:8080; path=/';
                            cookie = "email=" + reply + "; " + expires + '; path=/';
                            alert("document.cookie=" + cookie)
                            //FB登入
                            alert("FB登入成功")
                            $("a#memberdescription").show();
                            alert("煩請到會員資料新增電話才可啟動租賃服務")
                            if ($('#modeltt').is(':hidden')) {
                            } else {
                                $('#modeltt').hide();
                                window.location.reload(true);
                            }
                            
                        }

                            }
                            });

                    } else {




                        //  var goto = "index.html?name="+jsonback
                        //  //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                        alert("Email為會員資料");
                        var cookie = document.cookie;
                        //  window.location.assign(encodeURI(goto)) ; 
                        //  $("#memberloginstatus").innerHTML= "Welcome"+jsonback;
                        //因為footer和nav會同時執行login，因此會執行兩次login check，使用此方式產生一個welcome字串
                        //    var goto = "index.html?name="+jsonback
                        //重要！！ 轉傳時要編碼一次編成ＵＲＩ
                        //    window.location.assign(encodeURI(goto)) ; 
                        if ($("#memberloginstatus").text() == "") {
                            $("#memberloginstatus").append("Welcome" + "   " + jsonback);
                            $("a#login").hide();
                            $("a#registerNav").hide();
                            $("a#memberlogoutstatus").append("登出");
                            $("a#memberdescription").show();

                            //設定fbcookie
                            var expire_days = 1; // 過期日期(天)
                            var day = new Date();
                            day.setTime(day.getTime() + (expire_days * 24 * 60 * 60 * 1000));
                            // day.setTime(day.getTime() + (60 * 1000));
                            var expires = "expires=" + day.toGMTString();
                            // document.cookie = "name=test" + "; " + expires + '; domain=localhost:8080; path=/';
                            cookie = "email=" + jsonback + "; " + expires + '; path=/';
                            alert("document.cookie=" + cookie)
                            //FB登入
                            alert("FB登入成功")
                            $("a#memberdescription").show();
                            alert("煩請到會員資料新增電話才可啟動租賃服務")
                            if ($('#modeltt').is(':hidden')) {
                            } else {
                                $('#modeltt').hide();
                                window.location.reload(true);
                            }
                           
                        }
                    }
                }

            });



        });
}
