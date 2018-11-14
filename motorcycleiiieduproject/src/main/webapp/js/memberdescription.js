$(document).ready(function () {



    var cookie = document.cookie; 
    //  alert("cookie為"+cookie);
     //Cookie是否存在
    //  if(cookie != "" && cookie != null){
        if(cookie!=null||cookie!="null"||cookie!=""||typeof(cookie)!="undefined"||typeof(cookie)!=undefined||typeof(cookie)!="false"||typeof(cookie)!=false){
    
        //Cookies是否有email資訊
        if(cookie.indexOf("email")==-1){
            // alert("Cookie資訊中未包含email資訊");
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
            var bdate= JSON.parse(memdata).birthday;
             
                var myDate = new Date(bdate);
       
                var month = myDate.getMonth() + 1;
				var day = myDate.getDate();
				month = (month.toString().length == 1) ? ("0" + month) : month;
				day = (day.toString().length == 1) ? ("0" + day) : day;
 
				var result = myDate.getFullYear() + '-' + month + '-' + day; //更改格式後的日期

               
        //    var memmail =memdata.replace("{","").replace("}","").replace("\"").split(",")[1].split(":")[0];
        //    alert(memmail)
        // alert(JSON.parse(memdata).email);
            //    $("#desmail").text(JSON.parse(memdata).email); 錯誤用法
                $("#desmail").attr("value",JSON.parse(memdata).email);
                $("#hiddenemail").attr("value",JSON.parse(memdata).email);            
                $("#despassword").attr("value",JSON.parse(memdata).password);
                $("#desname").attr("value",JSON.parse(memdata).name);
                $("#desphone").attr("value",JSON.parse(memdata).phone);
                // $("#desbirthday").attr("value",JSON.parse(memdata).birthday);
                $("#desbirthday").attr("value",result);
                $("#desgender").attr("value",JSON.parse(memdata).gender);
                $("#desaddress").attr("value",JSON.parse(memdata).address);
            
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
             if(drop){
                var cookie = document.cookie; 
                var email= cookie.split("email=")[1].split(";")[0];
                // alert("更新wwwwimg="+email);
                $("#wwwwimg").attr("src","Images/Front"+email+".jpg");
                
             }
            //  http://localhost:8080/motorcycleiiieduproject/Images/Frontqw@gmail.com.jpg
           //                                                 Images/Frontqw@gmail.com.jpg
            }
            });


        $("#change").click(function () { 
                   

        var password =$("#despassword").val();
        var name = $("#desname").val() ; 
        var phone = $("#desphone").val()  ; 
        var birthday = $("#desbirthday").val()  ; 
        // var from = $("#desbirthday").val().split("-")
        // var f = new Date(from[2], from[1] - 1, from[0])
        var gender = $("#desgender").val()  ; 
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
            }
        });
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
        // alert(formData.getAll)
        $.ajax({
          type: "POST",
          url: "upload",
          data: formData,
          cache:false, // 不需要cache
          processData: false, // jQuery預設會把data轉為query String, 所以要停用
          contentType: false, // jQuery預設contentType為'application/x-www-form-urlencoded; charset=UTF-8', 且不用自己設定為'multipart/form-data'
          dataType: 'text',
          success: function (data){
            //   alert("upload sucess") ; 
            //   $("#wwwwimg").attr("src","Images\\Front"+email+".jpg");
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
                $("#wwwwimg").attr("src", "Images\\"+portfolio);

            }
        }
        });



    }));





});