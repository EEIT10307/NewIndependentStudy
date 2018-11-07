$(document).ready(function () {
    
   //解碼ＵＲI
var decode =   decodeURI(window.location.search) ; 
//取得所要的資訊
var  spli   =    decode.split('?')[1].split('=')[1] ; 


    alert( "這是resucess page = " +spli)
     

    $('#welcome').append(spli);




});