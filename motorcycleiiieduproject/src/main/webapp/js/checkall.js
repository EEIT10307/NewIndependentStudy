$(document).ready(function () {
    
  alert(window.sessionStorage.myjson)
//將網頁傳過來的資料作成ＪＳＯＮ物件
  var  json  = JSON.parse(window.sessionStorage.myjson); 

var txt = "" ; 
//取出ＪＳＯＮ物件
for(var x = 0 ; x< json.length ; x++) {

   txt +="<tr>" ; 
   txt += "<td>"+json[x].pk+"</td>"  ; 
  txt += "<td>"+json[x].name+"</td>"  ; 
  txt += "<td>"+json[x].id+"</td>"  ; 
  txt += "<td>"+json[x].password+"</td>"  ; 
  txt += "<td><button id = 'change'>修改</button><button id = 'delete'>刪除</button></td>"  ; 
   txt += "</tr>" ; 
}

$("#tbody").html(txt);

//刪除功能
$(document).on("click", "#delete" , function () {
  //取得該button的母代的姐妹的第一個 就是ＰＫ格子
var PK = $(this).parent().siblings().eq(0).text() ; 
//將該button的母代的母代（也就是Tr）存到變數
var moth = $(this).parent().parent() ; 



  var combie =  {"pk":PK } ; 
//原本combie是json物件 利用以下方法翻成json字串 ; 
var json    =  JSON.stringify(combie) ;
  $.ajax({
    type: "post",
    url: "http://localhost:8082/homehiber/DeleteServlet",
    data: json,
    success: function (response) {
     //伺服器刪除成功後要把該元素移除掉
      moth.remove(); 
    }
  });

  
});

//修改按鈕
$("tbody").on("click", "#change" ,function () {

   //將要修改的元素參考存入變數
  var name = $(this).parent().siblings().eq(1) ; 
  var id = $(this).parent().siblings().eq(2) ;
  var password = $(this).parent().siblings().eq(3) ;
  //將當下元素內的值存入變數
  var namete = $(this).parent().siblings().eq(1).text() ; 
  var idte = $(this).parent().siblings().eq(2).text() ;
  var passwordte = $(this).parent().siblings().eq(3).text() ;
  var mother = $(this).parent();
//利用innerHtml把內文取代成可修改的input元素
  name.html("<input type = 'text' name = 'name' value = ' "+namete+"' >") ;
  id.html("<input type = 'text' name = 'id' value = ' "+idte+"' >") ; 
  password.html("<input type = 'text' name = 'password' value = ' "+passwordte+"' >") ; 
 
//把原本的按鈕清掉 插入新的按鈕
  mother.empty();
  mother.append("<button id = 'changecheck'>確認修改</button><button id = 'rollback'>取消修改</button>");


  
  
  });

  //取消修改
  $("tbody").on("click", "#rollback" ,function () {

    var mother = $(this).parent();   
    //將原本元素上的值取出
    var name =  $(this).parent().siblings().eq(1).children().attr("value");
    var id =    $(this).parent().siblings().eq(2).children().attr("value");
    var password =    $(this).parent().siblings().eq(3).children().attr("value");
    //把原本值塞回去
    $(this).parent().siblings().eq(1).html(name) ; 
   $(this).parent().siblings().eq(2).html(id) ;
   $(this).parent().siblings().eq(3).html(password) ;
    

//把原本的按鈕清掉 插入新的按鈕
    mother.empty() ; 
    mother.append("<button id = 'change'>修改</button><button id = 'delete'>刪除</button>");
    });
//確認修改
    $("tbody").on("click", "#changecheck" ,function () {
      var PK = $(this).parent().siblings().eq(0).text() ; 
      var mother = $(this).parent();  
      //取得目前打字在格子內的內文 
      var name =    $(this).parent().siblings().eq(1).children().val();
      var id =    $(this).parent().siblings().eq(2).children().val();
      var password =  $(this).parent().siblings().eq(3).children().val();

      var pname = $(this).parent().siblings().eq(1) ; 
      var pid = $(this).parent().siblings().eq(2) ;
      var ppassword = $(this).parent().siblings().eq(3) ;
//江改好的值用json字串給伺服器
var combie =  {"pk":PK , "name":name , "id":id , "password":password  } ; 
  //原本combie是json物件 利用以下方法翻成json字串 ; 
  var json    =  JSON.stringify(combie) ;
    $.ajax({
      type: "post",
      url: "http://localhost:8082/homehiber/ChangeServlet",
      data: json,
      success: function (response) {
        alert("恭喜更新成功") ; 
        //更新完成後把更新的值塞回去原本的元素內
        pname.html(name);
        pid.html(id);
        ppassword.html(password) ; 
//把原本的按鈕清掉 插入新的按鈕
        mother.empty() ; 
        mother.append("<button id = 'change'>修改</button><button id = 'delete'>刪除</button>");
        
      }
  
    });


   
       
      });


});