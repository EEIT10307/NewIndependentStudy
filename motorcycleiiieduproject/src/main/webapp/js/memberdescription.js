$(document).ready(function () {



    var cookie = document.cookie; 
    //  alert("cookie為"+cookie);
     //Cookie是否存在
    //  if(cookie != "" && cookie != null){
        if(cookie!=null||cookie!="null"||cookie!=""||typeof(cookie)!="undefined"||typeof(cookie)!=undefined||typeof(cookie)!="false"||typeof(cookie)!=false){
    
        //Cookies是否有email資訊
        if(cookie.indexOf("email")==-1){
            alert("Cookie資訊中未包含email資訊");
        }else{
    
            var email= cookie.split("email=")[1].split(";")[0];
            alert("Cookies內儲存email="+email);
            //判斷是否為會員email，AutoLoginCheck
            //Email確認是會員
            //導向resucess.html
            var combie =  {"email":email} ;
            var json    =  JSON.stringify(combie) ; 
            alert("json="+json);
            //利用ajax將json型態的email丟到Controller的AutoLoginCheck察看是否為會員的email
            $.ajax({
                 type: "post",
                 url: "AutoLoginCheck",
                 data: json,
                 success:function (jsonback) {
                     alert("AutoLoginCheck傳回 = " + jsonback) ;
                     if(jsonback==null){
                         alert("Cookie內的email非會員信箱或沒email資訊");
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
          alert("網頁內沒任何Cookies");
          var goto = "index.html"
        		//重要！！ 轉傳時要編碼一次編成ＵＲＩ
                window.location.assign(encodeURI(goto)) ; 
                $("#memberloginstatus").innerHTML = "Welcome"+jsonback;
      };




    $("#change").click(function (e) { 
        //從cookies抓email
        var email = $("[id = 'desmail']").val()  ; 

        var password = $("[id = 'despassword']").val() ; 
        var name = $("[id = 'desname']").val() ; 
        var phone = $("[id = 'desphone']").val()  ; 
        var birthday = $("[id = 'desbirthday']").val()  ; 
        var gender = $("[id = 'desgender']").val()  ; 
        var address = $("[id = 'desaddress']").val()  ; 

        var combie =  {"email":email,"password":password,"name":name,"phone":phone,"birthday":birthday,"gender":gender,"address":address} ; 
      //原本combie是json物件 利用以下方法翻成json字串 ; 
       var json    =  JSON.stringify(combie) ; 
        alert("ANS="+json);
        // $.ajax({
        //     type: "post",
        //     url: "ChangeServlet",
        //     data: json,
        //     success:function (jsonback) {
        //      alert("ajax success");
        //     }
    
        // });
    });
});