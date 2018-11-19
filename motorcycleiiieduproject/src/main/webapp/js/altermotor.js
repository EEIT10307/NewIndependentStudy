$(document).ready(function () {

//=======抓折扣
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
   

//==============



    var dispadetail = {};
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

    if ((today.getDate()) < 10) {
        var minDate = "0" + today.getDate();
    } else {
        var minDate = today.getDate();
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

    if ((today.getDate()) < 10) {
        var minfDate = "0" + futureday.getDate();
    } else {
        var minfDate = futureday.getDate();
    }
    var maxdate = futureday.getFullYear() + "-" + maxMonth + "-" + minfDate;
    $("#pDate").attr("max", maxdate);
    $("#dDate").attr("max", maxdate);
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


    $("#managercheck").click(function (e) {
        e.preventDefault();
       var t = $('#example').DataTable();
        t.clear()
//======
   
var resulttime = (Date.parse($("#dDate").val() + " " + $(".makeDoption").val()) - 
Date.parse($("#pDate").val() + " " + $(".makePoption").val())) / 1000 / 60 / 60
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

//=====


        $(".neededstarbranchName").children().each(function () {
            if ($(this).val() != $(".neededstarbranchName").val()) {
    
                var surchopgo = {
                    "pickupDate": $("#pDate").val() + " " + $(".makePoption").val(),
                    "dropoffDate": $("#dDate").val() + " " + $(".makeDoption").val(),
                    "pickupStore": $(this).val(),
                    "dropoffStore": $(".neededbranchName").val()
                }
                var jsonob = JSON.stringify(surchopgo);
   
                $.ajax({
                    type: "POST",
                    url: "managerDispatcher",
                    data: jsonob,
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        for (let k in response) {
           
                            t.row.add([
                                discountpar ,
                                discountName,
                                surchopgo.pickupStore,
                                response[k].bikeModel,
                                parseInt(response[k].hourPrice)* resulttime * discountpar,
                                "<button type='button' class='btn btn-outline-secondary' id='dispacher'>調度</button>"
                            ]).draw(false)


                        }
              
                    }
                });

                t.columns( [0,1] ).visible( false );

            }

        })
    });

    //插入分店
    $.ajax({
        type: "get",
        url: "showAllBranch",
        data: "data",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            var strinres = JSON.stringify(response);
            var json = JSON.parse(strinres);
            
            for (var x = 0; x < json.length; x++) {
                $(".neededbranchName").append($("<option value='" + json[x] + "'></option>")
                    .text(json[x]))
                $(".neededstarbranchName").append($("<option value='" + json[x] + "'></option>")
                    .text(json[x]))
            }
        }
    });



$("#outDIV").on("click", "#dispacher" ,  function () {
  var thisis  = $(this)
  if($("#cPhone").val().length <10){
	   alert("請輸入正確的顧客電話")
	  
	   return
  }

    var orderdayww = new Date();

    var todayee = new Date();
    if ((orderdayww.getMonth() + 1) < 10) {
        var month = "0" + (todayee.getMonth() + 1)
    } else {
        var month = todayee.getMonth() + 1
    }
    if (orderdayww.getDate() < 10) {
        var date = "0" + todayee.getDate()
    } else {
        var date = todayee.getDate()
    }
    var dd = orderdayww.getHours();
    var mm = todayee.getMinutes() + 1; //January is 0
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }

//修改調度訂單開始時間為顧客需求時間前一小時

     

    var dispadetail = {};

    dispadetail.orderSerialNum = ""
    dispadetail.phone = "1111111111"
    dispadetail.bikeModel = $(this).parent().prev().prev().text()
    dispadetail.pickupDate = $("#pDate").val() + " " + ((parseInt($(".makePoption").val().split(":")[0])-1)+":"+"00")
    dispadetail.dropoffDate = $("#pDate").val() + " " + $(".makePoption").val()
    dispadetail.totalDiscount = "0"
    dispadetail.bikePrice = "0";
    dispadetail.accessoriesAmount = ""
    dispadetail.accessoriesTotalPrice = "0"
    dispadetail.orderTotalPrice = "0"
    dispadetail.orderTime = orderdayww.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
    dispadetail.pickupStore = $(this).parent().prev().prev().prev().text()
    dispadetail.dropoffStore = $(".neededstarbranchName").val()
    dispadetail.discountName = ""
    dispadetail.orderStatus = "未來調度"
    dispadetail.is_member = "false"
    dispadetail.payOrNot = "false"




    $.ajax({ 
		type: "Post",
		url: "getlastcheckorderlist",
		data: JSON.stringify(dispadetail),
		contentType: "application/json; charset=utf-8",
		success: function (response) {
//            alert(response)
			  //======================== 
            
			//===test====
			var dispadetailend = {};

			 dispadetailend.orderSerialNum = ""
			 dispadetailend.phone = $("#cPhone").val();
			 dispadetailend.bikeModel = thisis.parent().prev().prev().text()
			 dispadetailend.pickupDate =  $("#pDate").val() + " " + $(".makePoption").val()
			 dispadetailend.dropoffDate = $("#dDate").val() + " " + $(".makeDoption").val()
			 dispadetailend.totalDiscount = thisis.parent().prev().prev().prev().prev().prev().text()
			 dispadetailend.bikePrice = thisis.parent().prev().text()
			 dispadetailend.accessoriesAmount = ""
			 dispadetailend.accessoriesTotalPrice = "0"
			 dispadetailend.orderTotalPrice = thisis.parent().prev().text()
			 dispadetailend.orderTime = orderdayww.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
			 dispadetailend.pickupStore = $(".neededstarbranchName").val()
			 dispadetailend.dropoffStore = $(".neededbranchName").val()
			 dispadetailend.discountName = thisis.parent().prev().prev().prev().prev().text()
			 dispadetailend.orderStatus = "未來訂單"
			 dispadetailend.is_member = "false"
			 dispadetailend.payOrNot = "false"
			//===test====
			
			
			
            
//            var dispadetailend = {};
//    dispadetailend.orderSerialNum = ""
//    dispadetailend.phone = "2222222222"
//    dispadetailend.bikeModel = thisis.parent().prev().prev().text()
//    dispadetailend.pickupDate =  $("#dDate").val() + " " + $(".makeDoption").val()
//    dispadetailend.dropoffDate = $("#dDate").val() + " " + ((parseInt($(".makeDoption").val().split(":")[0])+1)+":"+"00")
//    dispadetailend.totalDiscount = "0"
//    dispadetailend.bikePrice = "0"
//    dispadetailend.accessoriesAmount = ""
//    dispadetailend.accessoriesTotalPrice = "0"
//    dispadetailend.orderTotalPrice = "0"
//    dispadetailend.orderTime = orderdayww.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
//    dispadetailend.pickupStore = $(".neededbranchName").val()
//    dispadetailend.dropoffStore = thisis.parent().prev().prev().prev().text()
//    dispadetailend.discountName = ""
//    dispadetailend.orderStatus = "未來調度"
//    dispadetailend.is_member = "false"
//    dispadetailend.payOrNot = "false"

    $.ajax({ 
		type: "Post",
		url: "getlastcheckorderlist",
		data: JSON.stringify(dispadetailend),
		contentType: "application/json; charset=utf-8",
		success: function (response) {
//			alert(response)
          
 //=========== (parseInt(thisis.parent().prev().text())* resulttime * discountpar);
			
			//====test=====
          var dispadetailend = {};
		    dispadetailend.orderSerialNum = ""
		    dispadetailend.phone = "2222222222"
		    dispadetailend.bikeModel = thisis.parent().prev().prev().text()
		    dispadetailend.pickupDate =  $("#dDate").val() + " " + $(".makeDoption").val()
		    dispadetailend.dropoffDate = $("#dDate").val() + " " + ((parseInt($(".makeDoption").val().split(":")[0])+1)+":"+"00")
		    dispadetailend.totalDiscount = "0"
		    dispadetailend.bikePrice = "0"
		    dispadetailend.accessoriesAmount = ""
		    dispadetailend.accessoriesTotalPrice = "0"
		    dispadetailend.orderTotalPrice = "0"
		    dispadetailend.orderTime = orderdayww.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
		    dispadetailend.pickupStore = $(".neededbranchName").val()
		    dispadetailend.dropoffStore = thisis.parent().prev().prev().prev().text()
		    dispadetailend.discountName = ""
		    dispadetailend.orderStatus = "未來調度"
		    dispadetailend.is_member = "false"
		    dispadetailend.payOrNot = "false"
			//====tset====
			
			
			
// var dispadetailend = {};
// dispadetailend.orderSerialNum = ""
// dispadetailend.phone = $("#cPhone").val();
// dispadetailend.bikeModel = thisis.parent().prev().prev().text()
// dispadetailend.pickupDate =  $("#pDate").val() + " " + $(".makePoption").val()
// dispadetailend.dropoffDate = $("#dDate").val() + " " + $(".makeDoption").val()
// dispadetailend.totalDiscount = thisis.parent().prev().prev().prev().prev().prev().text()
// dispadetailend.bikePrice = thisis.parent().prev().text()
// dispadetailend.accessoriesAmount = ""
// dispadetailend.accessoriesTotalPrice = "0"
// dispadetailend.orderTotalPrice = thisis.parent().prev().text()
// dispadetailend.orderTime = orderdayww.getFullYear() + "-" + month + "-" + date+" "+dd+":"+mm;
// dispadetailend.pickupStore = $(".neededstarbranchName").val()
// dispadetailend.dropoffStore = $(".neededbranchName").val()
// dispadetailend.discountName = thisis.parent().prev().prev().prev().prev().text()
// dispadetailend.orderStatus = "未來訂單"
// dispadetailend.is_member = "false"
// dispadetailend.payOrNot = "false"
// alert("custorder"+ JSON.stringify(dispadetailend))
 $.ajax({ 
     type: "Post",
     url: "getlastcheckorderlist",
     data: JSON.stringify(dispadetailend),
     contentType: "application/json; charset=utf-8",
     success: function (response) {
//         alert(response)
    	 alert("調度成功")
         window.location.assign ("altermotor.html");
     },
     error:function(responseerror){
    	 alert("第三層")
         alert(responseerror.responseText)
     }
 });
         //========================

		},
		error:function(responseerror){
			alert("第二層")
			alert(responseerror.responseText)
        }
    });
            //========================
			
		},
		error:function(responseerror){
			alert("第ㄧ層")
			alert(responseerror.responseText)
        }


        
    });


    
 //=幫客人下訂=======================

 //算錢=======

 






});







});