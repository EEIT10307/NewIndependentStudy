

/* MIT License Copyright 2017 Creative Tim (http://www.creative-tim.com/product/material-bootstrap-wizard)
 *
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \|     |// '.
 *                 / \|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *               Buddha Bless:  "No Bugs"
 *
 * ========================================================= */
$(document).ready(function () {
    var orderdetail = {};
    //做時間選項
    var today = new Date();
    //   $("#pDate").attr("value", today);
    var futureday = new Date();
    //    alert(today.getHours())

    for (var time = 10; time < 23; time++) {
        if (time <= today.getHours()) {
            continue;
        }
        var timeformate = time + ":00"
        $(".makePoption").append($("<option value='" + timeformate + "'></option>")
            .text(timeformate))
    }

    for (var time = 10; time < 23; time++) {
        if (time <= (today.getHours() + 1)) {
            continue;
        }
        var timeformate = time + ":00"
        $(".makeDoption").append($("<option value='" + timeformate + "'></option>")
            .text(timeformate))
    }
    //限制最小日期
    if ((today.getMonth() + 1) < 10) {
        var minMonth = "0" + (today.getMonth() + 1)
    } else {
        var minMonth = today.getMonth() + 1
    }

    var mindate = today.getFullYear() + "-" + minMonth + "-" + today.getDate();
    $("#pDate").attr("min", mindate);
    $("#dDate").attr("min", mindate);
    //限制最大日期
    futureday.setMonth(today.getMonth() + 3);
    if ((futureday.getMonth() + 1) < 10) {
        var maxMonth = "0" + (futureday.getMonth() + 1)
    } else {
        var maxMonth = futureday.getMonth() + 1
    }
    var maxdate = futureday.getFullYear() + "-" + maxMonth + "-" + futureday.getDate();
    $("#pDate").attr("max", maxdate);
    $("#dDate").attr("max", maxdate);
    //製作分店選項
    $.ajax({
        type: "get",
        url: "showAllBranch",
        data: "data",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            var strinres = JSON.stringify(response);
            var json = JSON.parse(strinres);
            for (var x = 0; x < json.length; x++) {
                $(".pickupbranchName").append($("<option value='" + json[x] + "'></option>")
                    .text(json[x]))
                $(".dropoffbranchName").append($("<option value='" + json[x] + "'></option>")
                    .text(json[x]))
            }
        }
    });
    //日期預設今日
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
    var todayformat = yyyy + '-' + mm + '-' + dd;
    $("#pDate").attr("value", todayformat);
    $("#dDate").attr("value", todayformat);

  /* 有空補個日期判別式 換日*/
  $("#dDate").change(function (e) {
    e.preventDefault();
    if ($("#dDate").val() > mindate) {
        $(".makeDoption").html("")
        for (var otime = 10; otime < 23; otime++) {
            var otimeformate = otime + ":00"
            $(".makeDoption").append($("<option value='" + otimeformate + "'></option>")
                .text(otimeformate))
        }
    } else {
        $(".makeDoption").html("")
        for (var time = 10; time < 23; time++) {
            if (time <= (today.getHours() + 1)) {
                continue;
            }
            var timeformate = time + ":00"
            $(".makeDoption").append($("<option value='" + timeformate + "'></option>")
                .text(timeformate))
        }
    } 
});
$("#pDate").change(function (e) {
    e.preventDefault();
    if ($("#pDate").val() > mindate){
        $(".makePoption").html("")
        $(".makeDoption").html("")
        for (var otime = 10; otime < 23; otime++) {
            var otimeformate = otime + ":00"
            $(".makeDoption").append($("<option value='" + otimeformate + "'></option>")
                .text(otimeformate))
                $(".makePoption").append($("<option value='" + otimeformate + "'></option>")
                .text(otimeformate))
        }
        $("#dDate").attr("value", $("#pDate").val());
    }else{
        $(".makePoption").html("")
        for (var time = 10; time < 23; time++) {
            if (time <= today.getHours()) {
                continue;
            }
            var timeformate = time + ":00"
            $(".makePoption").append($("<option value='" + timeformate + "'></option>")
                .text(timeformate))
        }
    }
})
/* 有空補個日期判別式 超過22點*/
var overday = new Date();
if (overday.getHours() >= 22 || (overday.getHours() >= 0 & overday.getHours() < 10)) {
    $(".makePoption").html("")
    $(".makeDoption").html("")
    overday.setDate(overday.getDate() + 1)
    var odd = overday.getDate();
    var omm = overday.getMonth() + 1; //January is 0!
    var oyyyy = overday.getFullYear();
    var midnightformat = oyyyy + '-' + omm + '-' + odd;
    for (var otime = 10; otime < 23; otime++) {
        var otimeformate = otime + ":00"
        $(".makePoption").append($("<option value='" + otimeformate + "'></option>")
            .text(otimeformate))
        $(".makeDoption").append($("<option value='" + otimeformate + "'></option>")
            .text(otimeformate))
    }
    $("#pDate").attr("value", midnightformat);
    $("#dDate").attr("value", midnightformat);
    $("#pDate").attr("min", midnightformat);
    $("#dDate").attr("min", midnightformat);
}

    //顧客訂單查詢車子
    $("#takeserch").click(function (e) {
        e.preventDefault();
        $(".orderlistbody").children().remove();
        var surchop = {
            "pickupDate": $("#pDate").val() + " " + $(".makePoption").val(),
            "dropoffDate": $("#dDate").val() + " " + $(".makeDoption").val(),
            "pickupStore": $(".pickupbranchName").val(),
            "dropoffStore": $(".dropoffbranchName").val()
        }
        var jsonob = JSON.stringify(surchop);
        sessionStorage.orderTime = jsonob;
        // alert(jsonob)
        $.ajax({
            type: "POST",
            url: "showAllOrderFromShop",
            data: jsonob,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                for (var x = 0; x < response.length; x++) {
                    var motorname = response[x].bikeModel
                    sessionStorage.setItem(motorname, JSON.stringify(response[x]))
                    var element = $("<div class=\"col-lg-4 orderlist\"><div>").html(
                        "車名:" + response[x].bikeModel + "<br>" +
                        "廠牌:" + response[x].bikeBrand + "<br>" +
                        "引擎:" + response[x].engineType + "<br>" +
                        "價錢:" + response[x].hourPrice + "/hrs<br>" +
                        "<button class = 'checkinfro' value='" + response[x].bikeModel +
                        "' >INFO</button>"
                    );

                    $(".orderlistbody").append(element);

                }
            }

        });

        $.ajax({
            type: "get",
            url: "getDiscount",
            data: "data",
            success: function (response) {
                for (var x = 0; x < response.length; x++) {
                    var discountName = response[x].discountName
                    sessionStorage.setItem(discountName, JSON.stringify(response[x]));
                }
            }
        });
    });
//=========================================================

    
    //秀該機車詳細資料
    $(".orderlistbody").on("click", ".checkinfro", function () {
        var mototrname = $(this).attr("value");
        var detail = sessionStorage.getItem(mototrname);
        var detailmotor = JSON.parse(detail);
        //  alert("存在session的車車="+detail)
        $(".orderlistbody").children().remove();
        //計算訂單資訊總共多少小時
        var order = JSON.parse(sessionStorage.orderTime)
        var resulttime = (Date.parse(order.dropoffDate) - Date.parse(order.pickupDate)) / 1000 / 60 / 60
        var discountpar = 1;
        var discountName = "原價";
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


        var element = $("<div class=\"col-lg-12 orderone\"><div>").html(
            "車名:<span class =\"" + detailmotor.bikeModel + "\">" + detailmotor.bikeModel + "</span><br>" +
            "廠牌:" + detailmotor.bikeBrand + "<br>" +
            "引擎:" + detailmotor.engineType + "<br>" +
            "年份:" + detailmotor.modelYear + "<br>" +
            "車牌:" + detailmotor.plateType + "<br>" +
            "座高:" + detailmotor.seatHeight + "cm<br>" +
            "乾重:" + detailmotor.dryWeight + "KG<br>" +
            "ABS:" + detailmotor.aBS + "<br>" +
            "折扣:<span class = \"discountName\">" + discountName + "</span><br>" +
            "<span hidden=\"hidden\">折扣率:<span  class = \"discountpar\">" + discountpar + "</span><br></span>" +
            "時長:<span class = \"resulttime\">" + resulttime + "</span>小時<br>" +
            "折扣後價錢試算:" + detailmotor.hourPrice * resulttime * discountpar + "<br>" +
            "<div class=\"col-lg-6 accesslist\"><div>" +
            "<button class = 'orderDoubleCheck'>orderDoubleCheck</button>"
        );
        $(".orderlistbody").append(element);
        $.ajax({
            type: "POST",
            url: "compareAcceStock",
            contentType: "application/json; charset=utf-8",
            data: sessionStorage.getItem("orderTime"),
            success: function (response) {
                for (var x = 0; x < response.length; x++) {
                    if (response[x].acceNum >= 2) {
                        var op = "<select class = \"" + response[x].acceName + "\"><option>1</option><option>2</option></select>"
                    } else if (response[x].acceNum == 1) {
                        var op = "<select class = \"" + response[x].acceName + "\"><option>1</option></select>"
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

    });



    $(".orderlistbody").on("click", ".orderDoubleCheck", function () {
        var ordertype = sessionStorage.orderTime;
        var orderjson = JSON.parse(ordertype)

        alert(ordertype)
        var orderday = new Date();
        var mototrname = $(".orderone span").attr("class");
        var detail = sessionStorage.getItem(mototrname);
        var detailmotor = JSON.parse(detail);
        var acceItem = {};
        var acceprice = 0;

        $(".accecheckbox:checked").each(function () {
            //    alert("選擇配件名"+$(this).val())
            var acce = "." + $(this).val()
            //   alert("選擇配件數量"+$(acce).find(":selected").text())
            //   alert("選擇配件價格"+$("span"+acce).text()*$(acce).find(":selected").text())
            var itemname = $(this).val();
            var itemquer = $(acce).find(":selected").text();

            acceItem[itemname] = itemquer
            acceprice += $("span" + acce).text() * $(acce).find(":selected").text();
        });
        //alert("選擇配件總額"+acceprice)
        //alert("配件內容"+JSON.stringify(acceItem))
        //alert("折扣率"+$(".orderone span.discountpar").text())
        //訂單
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
        orderdetail.totalDiscount = $(".orderone span.discountpar").text()
        orderdetail.bikePrice = detailmotor.hourPrice * ($(".orderone span.resulttime").text()) * ($(".orderone span.discountpar").text())
        orderdetail.accessoriesAmount = acceItemString
        orderdetail.accessoriesTotalPrice = acceprice
        orderdetail.orderTotalPrice = (acceprice + detailmotor.hourPrice * ($(".orderone span.resulttime").text()) * ($(".orderone span.discountpar").text()))
        orderdetail.orderTime = orderday.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
        orderdetail.pickupStore = orderjson.pickupStore
        orderdetail.dropoffStore = orderjson.dropoffStore
        orderdetail.discountName = $(".orderone span.discountName").text()
        orderdetail.orderStatus = "PRE"
        orderdetail.is_member = "false"
        orderdetail.payOrNot = "false"
        alert(JSON.stringify(orderdetail))


    
        //模擬為路人
        if (orderdetail.phone == "") {

            $(".ordercart").html(
                "<h3>請輸入電話</h3><br>" +
                "<input type='text' class = 'guestphone'><br>" +
                "<button class= 'addphone'>送出</button>"
            );


        }

        //模擬訂單確認畫面



    });
    //輸入電話監聽器
    $(".ordercart").on("click", ".addphone", function (e) {
        e.preventDefault();
        orderdetail.phone = $(".guestphone").val() ; 
        alert(JSON.stringify(orderdetail))
        var orderstring = ""
        for(var key in orderdetail){
             orderstring += "<p>"+key+":"+orderdetail[key]+"</p>"
        }

        $(".ordercart").html(
            orderstring +"<button class='sendOrder'>送出訂單</button>"
            ); 
    });

//送出訂單鈕
    $(".ordercart").on("click", ".sendOrder", function (e) {
        e.preventDefault();
        var lastorderlist = JSON.stringify(orderdetail)
        
      $.ajax({ 
          type: "Post",
          url: "getlastcheckorderlist",
          data: lastorderlist,
          contentType: "application/json; charset=utf-8",
          success: function (response) {
              alert(response)
          },
          error:function(responseerror){
         
              alert(responseerror.responseText)
        
            
          }
      });
           
        
        

    });







});