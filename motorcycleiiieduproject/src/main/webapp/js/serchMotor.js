$(document).ready(function () {
    
 
    //解碼ＵＲI
 var decode =   decodeURI(window.location.search) ; 
 //取得所要的資訊
 var  mototrname   =    decode.split('?')[1].split('=')[1] ; 
//秀該機車詳細資料
 var detail = sessionStorage.getItem(mototrname);
 var detailmotor = JSON.parse(detail);

//計算訂單資訊總共多少小時
var order = JSON.parse(sessionStorage.orderTime)
var resulttime = (Date.parse(order.dropoffDate) - Date.parse(order.pickupDate)) / 1000 / 60 / 60
var discountpar = 1;
var discountName = "0%Off";
if (resulttime >= 4 & resulttime < 8) {
    discountpar = JSON.parse(sessionStorage.getItem(4)).discountParameter;
    discountName = JSON.parse(sessionStorage.getItem(4)).remark;
} else if (resulttime >= 8 & resulttime < 12) {
    discountpar = JSON.parse(sessionStorage.getItem(8)).discountParameter;
    discountName = JSON.parse(sessionStorage.getItem(8)).remark;
} else if (resulttime >= 12 & resulttime < 24) {
    discountpar = JSON.parse(sessionStorage.getItem(12)).discountParameter;
    discountName = JSON.parse(sessionStorage.getItem(12)).remark;
} else if (resulttime >= 24) {
    discountpar = JSON.parse(sessionStorage.getItem(24)).discountParameter;
    discountName = JSON.parse(sessionStorage.getItem(24)).remark;
}

//塞圖片
 $("#showmotorimg1").attr("src", "Image/"+detailmotor.bikeModel+"_"+detailmotor.modelYear+"_001.jpg");
 $("#showmotorimg2").attr("src", "Image/"+detailmotor.bikeModel+"_"+detailmotor.modelYear+"_002.jpg");
 $("#showmotorimg3").attr("src", "Image/"+detailmotor.bikeModel+"_"+detailmotor.modelYear+"_003.jpg");
 $("#showmotorimg4").attr("src", "Image/"+detailmotor.bikeModel+"_"+detailmotor.modelYear+"_004.jpg");
 $("#showmotorimg5").attr("src", "Image/"+detailmotor.bikeModel+"_"+detailmotor.modelYear+"_005.jpg");

$("#motordetailtital").html(detailmotor.bikeModel);
$("#motordetailprice").html(detailmotor.hourPrice);

$("#motordetailPickUpDateTime").html(order.pickupDate);
$("#motordetailPickUpStore").html(order.pickupStore);
$("#motordetailDropOffDateTime").html(order.dropoffDate);
$("#motordetailDropOffStore").html(order.dropoffStore);

if(resulttime == "1"){
    $("#motordetailordertime").html(resulttime + "hr");
}else{
    $("#motordetailordertime").html(resulttime + "hrs");
}

$("#motordetailorderdiscount").html(discountName);


$(".motordetailordertotalcount").html(detailmotor.hourPrice * resulttime * discountpar+"$");

//商品描述
$("#motordetailorderdescription").html(detailmotor.description);
$("#motordetailorderspecmodel").html(detailmotor.bikeModel);
$("#motordetailorderspecyear").html(detailmotor.modelYear);
$("#motordetailorderspecfuelTank").html(detailmotor.fuelTankCapacity +"L");
$("#motordetailorderspecbrand").html(detailmotor.bikeBrand);
$("#motordetailorderspecengineType").html(detailmotor.engineType);
$("#motordetailorderspecdryWeight").html(detailmotor.dryWeight+"Kg");
$("#motordetailorderspecfronttire").html(detailmotor.tire);
$("#motordetailorderspecreartire").html(detailmotor.rearTire);
$("#motordetailorderspecbikeType").html(detailmotor.bikeType);
$("#motordetailorderspecfrontbrake").html(detailmotor.frontBrake);
$("#motordetailorderspecendbrake").html(detailmotor.rearBrake);
$("#motordetailorderspectorque").html(detailmotor.torque);
$("#motordetailorderspechorsePower").html(detailmotor.horsePower);
$("#motordetailorderspecseatHeight").html(detailmotor.seatHeight);
$("#motordetailorderspecplateType").html(detailmotor.plateType);
$("#motordetailorderspecfuelConsumption").html(detailmotor.fuelConsumption+"Km/L");
$("#motordetailorderspecfrontsusp").html(detailmotor.frontSuspension+"Km/L");
$("#motordetailorderspecrearsusp").html(detailmotor.rearSuspension+"Km/L");

$(".motorpicchange").click(function (e) { 
    e.preventDefault();
     var clickpic   = $(this).children().attr("src");
     var clickpicgoto   = $("#motorpicchanged").children().attr("src");

     $(this).children().attr("src", clickpicgoto);
     $("#motorpicchanged").children().attr("src", clickpic);
    
});
//做附件查詢
$.ajax({
    type: "POST",
    url: "compareAcceStock",
    contentType: "application/json; charset=utf-8",
    data: sessionStorage.getItem("orderTime"),
    success: function (response) {

        for (var x = 0; x < response.length; x++) {
          
            sessionStorage.setItem(response[x].acceName,response[x].acceePrice)
          
            if (response[x].acceNum >= 2) {
                var op = "<select class = \"accesele " + response[x].acceName + "\"><option>1</option><option>2</option></select>"
            } else if (response[x].acceNum == 1) {
                var op = "<select class = \"accesele " + response[x].acceName + "\"><option>1</option></select>"
            } else {
                var op = ""
            }
            if (response[x].acceNum >= 1) {
                $(".accesslist").prepend("<input class=\"accecheckbox\" type=\"checkbox\"value='" + response[x].acceName + "'>" + response[x].acceName + "</input>" + op + " <span class = '" + response[x].acceName + "'>" + response[x].acceePrice + "</span>$/per<br>");
            } else {
                $(".accesslist").prepend("<span style=\"color: gray\"><input type=\"checkbox\"  disabled=\"disabled\" value='" + response[x].acceName + "'>" + response[x].acceName + "</input>" + op + " <span class = '" + response[x].acceName + "'>" + response[x].acceePrice + "</span>$/per<br>");
            }
        }
    }
});
//配件金額計算
var accetotalcount = 0 ;
$("#acceaddmotor").html((accetotalcount+detailmotor.hourPrice * resulttime * discountpar)+"$");
$(".accesslist").on("click", function () {
    accetotalcount = 0 ; 
    $(".accecheckbox:checked").each(function () {
        //    alert("選擇配件名"+$(this).val())
        var acce = "." + $(this).val()
        accetotalcount += $("span" + acce).text() * $(acce).find(":selected").text();
    });

    $("#accecount").html(accetotalcount+"$");
    $("#acceaddmotor").html((accetotalcount+detailmotor.hourPrice * resulttime * discountpar)+"$");
});

$(".accesslist").on("change",".accesele", function () {
    accetotalcount = 0 ; 
    $(".accecheckbox:checked").each(function () {
        //    alert("選擇配件名"+$(this).val())
        var acce = "." + $(this).val()
        accetotalcount += $("span" + acce).text() * $(acce).find(":selected").text();
    });

    $("#accecount").html(accetotalcount+"$");
    $("#acceaddmotor").html((accetotalcount+detailmotor.hourPrice * resulttime * discountpar)+"$");
   

});



//製作訂單
$(".orderDoubleCheck").on("click", function () {
    var ordertype = sessionStorage.orderTime;
    var orderjson = JSON.parse(ordertype)
    var orderday = new Date();
    var acceItem = {};
    var orderdetail = {};

    $(".accecheckbox:checked").each(function () {
        //    alert("選擇配件名"+$(this).val())
        var acce = "." + $(this).val()
        //   alert("選擇配件數量"+$(acce).find(":selected").text())
        //   alert("選擇配件價格"+$("span"+acce).text()*$(acce).find(":selected").text())
        var itemname = $(this).val();
        var itemquer = $(acce).find(":selected").text();

        acceItem[itemname] = itemquer

    });
    
    //訂單
    var today = new Date();
    if ((orderday.getMonth() + 1) < 10) {
        var month = "0" + (today.getMonth() + 1)
    } else {
        var month = today.getMonth() + 1
    }
    if (orderday.getDate() < 10) {
        var date = "0" + today.getDate()
    } else {
        var date = today.getDate()
    }
    var dd = orderday.getHours();
    var mm = today.getMinutes() + 1; //January is 0
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
 var  acceItemString  = JSON.stringify(acceItem) ; 
if(acceItemString == "{}"){
acceItemString=""
}

    orderdetail.orderSerialNum = ""
    orderdetail.phone = ""
    orderdetail.bikeModel = detailmotor.bikeModel
    orderdetail.pickupDate = orderjson.pickupDate
    orderdetail.dropoffDate = orderjson.dropoffDate
    orderdetail.totalDiscount = discountpar
    orderdetail.bikePrice = $(".motordetailordertotalcount").text().replace("$", "");
    orderdetail.accessoriesAmount = acceItemString
    orderdetail.accessoriesTotalPrice = $("#accecount").text().replace("$", "")
    orderdetail.orderTotalPrice = $("#acceaddmotor").text().replace("$", "")
    orderdetail.orderTime = orderday.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
    orderdetail.pickupStore = orderjson.pickupStore
    orderdetail.dropoffStore = orderjson.dropoffStore
    orderdetail.discountName = $("#motordetailorderdiscount").text()
    orderdetail.orderStatus = "未來訂單"
    orderdetail.is_member = "false"
    orderdetail.payOrNot = "false"


    sessionStorage.orderdetailsession = JSON.stringify(orderdetail) ; 

    window.location.assign ("cart.html"); 

});

//回上頁
$(".ordergobackCheck").click(function (e) { 
    e.preventDefault();
    history.go(-1)
    
});



});