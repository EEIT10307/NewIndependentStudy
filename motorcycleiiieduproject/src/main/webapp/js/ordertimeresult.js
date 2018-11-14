$(document).ready(function () {
    //從前頁傳來的顧客資訊    
    var ordertime = sessionStorage.orderTime;
    var allbrand = new Set();
    var allmotortype = new Set();
    var allplateType = new Set();

    $.ajax({
        type: "POST",
        url: "showAllOrderFromShop",
        data: ordertime,
        contentType: "application/json; charset=utf-8",
        success: function (response) {

         if(response == "error"){
          alert ("日期輸入錯誤 請重新搜尋")

         }else{
            for (var x = 0; x < response.length; x++) {
                var motorname = response[x].bikeModel
                sessionStorage.setItem(motorname, JSON.stringify(response[x]))
                var element = $("<div class='col-md-4 searchItem'></div>").html(
                    "<div class='card mb-4 shadow-sm product-grid'>" +
                    "<div class='product-image'>" +
                    "<a href='#'>" +
                    "<img class='card-img-top pic-1' data-src='holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail' style='height: 225px; width: 100%; display: block;' src='Image/" + response[x].bikeModel + "_" + response[x].modelYear + "_001.jpg' data-holder-rendered='true'>" +
                    "<img class='card-img-top pic-2' data-src='holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail' style='height: 225px; width: 100%; display: block;' src='Image/" + response[x].bikeModel + "_" + response[x].modelYear + "_002.jpg' data-holder-rendered='true'></img>" +
                    "</a>" +
                    "<a href='prodDetail.html?motorname=" + response[x].bikeModel + "' class='select-options checkinfro' value='" + response[x].bikeModel + "'><i class='fa fa-arrow-right' ></i>查看詳情</a>" +
                    "</div>" +
                    "<div class='card-body product-content'>" +
                    "<div class='title'><h3 class='m-0' id='bikeModel'>" + response[x].bikeModel + "</h3><h5 id='bikeBrand'>" + response[x].bikeBrand + "</h5></div>" +
                    "<div class='card-text'>" +
                    "<h6 class='motortype'>車型: <span>" + response[x].bikeType + "</span></h6>" +
                    "<h6 class='engineType'>引擎型式: <span>" + response[x].engineType + "</span> c.c.</h6>" +
                    "<h6 class='plateType'>車牌: <span>" + response[x].plateType + "牌</span></h6>" +
                    "<h6 class='modelyear'>年份: <span>" + response[x].modelYear + "</span></h6>" +
                    "</div>" +
                    "<div class='d-flex justify-content-end text-muted my-2 price'>$" + response[x].hourPrice + "<span>/時</span></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>"
                );

                $("#mainbodymotorshow").append(element);


                allbrand.add(response[x].bikeBrand.toString())
                allmotortype.add(response[x].bikeType.toString())
                allplateType.add(response[x].plateType.toString())
                }
            }


            //製作option
            var controlnum = 0;
            for (let k of allbrand) {
                if (controlnum == 0) {

                    $("#makebrand").append("<option value=\"\">不限</option>");
                    $("#makebrand").append("<option value='"+k+"'>" + k + "</option>");
                    controlnum ++
                } else {
                    $("#makebrand").append("<option value='"+k+"'>" + k + "</option>");
                }
            }
            controlnum = 0;

            for (let k of allmotortype) {
                if (controlnum == 0) {

                    $("#maketype").append("<option value=\"\">不限</option>");
                    $("#maketype").append("<option value='"+k+"'>" + k + "</option>");
                    controlnum ++
                } else {
                    $("#maketype").append("<option value='"+k+"'>" + k + "</option>");
                }

            }
            controlnum = 0;
            for (let k of allplateType) {
                if (controlnum == 0) {

                    $("#makeplate").append("<option value=\"\">不限</option>");
                    $("#makeplate").append("<option value='"+k+"'>" + k + "</option>");
                    controlnum ++
                } else {
                    $("#makeplate").append("<option value='"+k+"'>" + k + "</option>");
                }

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

//date重新搜索
    var orderdetail = {};
    //做時間選項
    var today = new Date();
    //   $("#pDate").attr("value", today);
    var futureday = new Date();
    //    alert(today.getHours())

    for (var time = 10; time < 22; time++) {
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

    if((today.getDate())<10){
        var minDate = "0" + today.getDate() ; 
    }

    var mindate = today.getFullYear() + "-" + minMonth + "-" + minDate;
    $("#pDate").attr("min", mindate);
    $("#dDate").attr("min", mindate);
    //限制最大日期
    futureday.setMonth(today.getMonth() + 3);
    if ((futureday.getMonth() + 1) < 10) {
        var maxMonth = "0" + (futureday.getMonth() + 1)
    } else {
        var maxMonth = futureday.getMonth() + 1
    }

    if((today.getDate())<10){
        var minfDate = "0" + futureday.getDate() ; 
    }
    var maxdate = futureday.getFullYear() + "-" + maxMonth + "-" + minfDate;
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
             //選項保留前頁資訊
            $(".pickupbranchName option").each(function(){
                if($(this).text() == ordertimeobj.pickupStore){
                $(this).attr("selected" , "selected")
              }
            })  
            
            $(".dropoffbranchName option").each(function(){
                if($(this).text() == ordertimeobj.dropoffStore){
                $(this).attr("selected" , "selected")
              }
            }) 
        }
    });
    //選項保留前頁資訊

     var   ordertimeobj = JSON.parse(ordertime)
    
     $(".makePoption option").each(function(){
        if($(this).text() == ordertimeobj.pickupDate.substr(11,5)){
        $(this).attr("selected" , "selected")
      }
})
$(".makeDoption option").each(function(){
    if($(this).text() == ordertimeobj.dropoffDate.substr(11,5)){
    $(this).attr("selected" , "selected")
  }
})

 

    $("#pDate").attr("value", ordertimeobj.pickupDate.substr(0,10));
    $("#dDate").attr("value", ordertimeobj.dropoffDate.substr(0,10));
 

    /* 有空補個日期判別式 超過22點*/

    

    //顧客訂單查詢車子
    $("#motorserchdo").click(function (e) {
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
//         alert(jsonob)

        window.location.assign ("search1.html"); 


       
    });







});