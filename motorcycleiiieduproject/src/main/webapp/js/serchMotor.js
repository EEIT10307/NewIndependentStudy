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
 var cookie = document.cookie;

if(cookie.indexOf("memberphone")==-1){
    var memphone = ""
    var memcheck = "false"

}else{
    var memphone= cookie.split("memberphone=")[1].split(";")[0];
  
    var memcheck = "true" 
}

if(acceItemString == "{}"){
acceItemString=""
}
    
    orderdetail.orderSerialNum = ""
    orderdetail.phone = memphone.trim().toString()
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
    orderdetail.is_member = memcheck
    orderdetail.payOrNot = "false"


    sessionStorage.orderdetailsession = JSON.stringify(orderdetail) ; 

 //    alert(JSON.stringify(orderdetail))

    window.location.assign ("cart.html"); 

});

//回上頁
$(".ordergobackCheck").click(function (e) { 
    e.preventDefault();
    history.go(-1)
    
});
//-------------------------------------------------------------------------------
//<tr>
//<th scope="row">1</th>
//<td>Mark</td>
//<td>Otto</td>
//<td>@mdo</td>
//</tr>
var detail2 = sessionStorage.getItem(mototrname);
var detailmotor2 = JSON.parse(detail);
$.ajax({
	type : "post",
	url : "selectbikerew",
	data : {"bikeModel":detailmotor2.bikeModel,"modelYear":detailmotor2.modelYear},
	success : function(response) {
//		alert(response)
			var cd=JSON.parse(response)
//			alert(cd)
		for(i in cd){
			
			if(cd[i].satisfacation==1)
			var t1="<img src='Image/tire.png' style='width:30px'>";
			if(cd[i].satisfacation==2)
			var t1="<img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'>";
			if(cd[i].satisfacation==3)
				var t1="<img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'>";
			if(cd[i].satisfacation==4)
				var t1="<img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'>";
			if(cd[i].satisfacation==5)
				var t1="<img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'><img src='Image/tire.png' style='width:30px'>";
			
		$(".star").append("<tr>" +
							"<th scope='row'>"+(+i+1)+"</th>"+
							"<td>"+t1+"</td>" +
							"<td>"+cd[i].reviewContent+"</td>" +
							"<td>"+cd[i].reviewTime+"</td>" +
							"</tr>"
							)
		}
	}
});
//這邊是ROBIN

var detail1 = sessionStorage.getItem(mototrname);
var detailmotor1 = JSON.parse(detail);


var a=0;//判斷該不該給管理者輸入回覆  0為不該 1為該

var dt = new Date();
var year=dt.getFullYear()
var month=dt.getMonth()+1
//alert("月"+month)
var day=dt.getDate()
//alert("日"+day)
var hour=dt.getHours()
//alert("時間"+hour)
var min=dt.getMinutes()
//alert("分鐘"+min)
var ss=	dt.getSeconds()
//alert("秒"+ss)
var selectQAZ={"bikeModel":detailmotor1.bikeModel,"modelYear":detailmotor1.modelYear}
//	alert(selectQAZ)
$.ajax({
		type : "post",
		url : "selectQA",
		data:	selectQAZ,
		success : function(response) {
//				alert(response)
			if(response==""){
				return;
			}
		var all=JSON.parse(response);
		
			for(i in all){
//					alert(all[i].answerTime)//回答時間
//					alert(all[i].questioner);//人名
//					alert(all[i].questionCotent);//問題
//             alert(all[i].questionDate);//時間sanswerContent
					$("#appendtext").append("<tr name='count' ><td>Q"+i+":</td><td>"+all[i].questioner+"</td><td>"+all[i].questionDate+"</td></tr><tr id='q"+i+"'><td>"+all[i].questionCotent+"</td><td><input type='hidden' id='qAndASerialNum"+i+"' value='"+all[i].qAndASerialNum+"'></td><td></td></tr>");
				if(all[i].administratorID != null){
				$("#appendtext").append("<tr ><td>A"+i+":</td><td style='color:red;'>"+all[i].administratorID+"回覆</td><td>"+all[i].answerTime+"</td></tr><tr><td>"+all[i].answerContent+"</td><td></td><td></td></tr>")	
				}else{
					$("#appendtext").append("<tr id='Amid' ><td>管理者:<input type='text' id='ans"+i+"'></td><td >回覆:<textarea id='ansquction"+i+"'></textarea></td><td><button type='button' class='btn btn-outline-success' id='ai"+i+"'>完成</button></td></tr>")
				}
				
			}
			var b=parseInt(i)+1;//取得迴圈總數 轉為數字+1
//				alert("c="+b)
		
				$("#appendtext").on("click",".btn-outline-success",function() {
//						alert($(this).attr('id'));
					var z=$(this).attr('id').charAt(2)//取得送出ID
//						alert(z)
					var ans=$("#ans"+z+"").val();//取得管理員回應名稱
//						alert(ans)
					var ansquction=$("#ansquction"+z+"").val();//管理員回覆訊息
//						alert(ansquction)
					var qAndASerialNum=$("#qAndASerialNum"+z+"").val();//客人的流水號
//						alert(qAndASerialNum)
					if(ans=="" || ansquction==""){
						return;
					}
					$("#q"+z+"").after("<tr ><td>A"+z+":</td><td style='color:red;'>"+ans+"回覆</td><td>"+year+"/"+month+"/"+day+"&nbsp;&nbsp;&nbsp;&nbsp;"+hour+":"+min+":"+ss+"</td></tr><tr><td>"+ansquction+"</td><td></td><td></td></tr>");
				//更新
				var ansupdate={"qAndASerialNum":qAndASerialNum,"ans":ans,"ansquction":ansquction}
		$.ajax({
			type : "post",
			url : "updateQA",
			data : ansupdate,
			success : function(response) {
			}
		});
				})
		
			
			
			if(a==1){
				
			}else{
	
			$("[id='Amid']").hide();
			}
		}
			
//				
			
			
	})
$("td.table").addClass("tdborder");//class=table 的元素td 套入CSS
//	--------------------------------------------------------------------------------------------

//按下送出 產生商品的問與答
	$("#zzzzz").click(function() {
		var dt = new Date();
		var year=dt.getFullYear()
		var month=dt.getMonth()+1
		var day=dt.getDate()
		var hour=dt.getHours()
		var min=dt.getMinutes()
		var ss=	dt.getSeconds()
		var co=$("[name='count']").length;//計算元素有幾個

		var questioner = $("#cus").val();//輸入者姓名

		var questionCoten=$("#questionCoten").val();//問答問題

		if(questioner=="" || questionCoten==""){
			return;
		}
		$("#appendtext").append("<tr name='count'><td>Q"+co+":</td><td>"+questioner+"</td><td>"+year+"/"+month+"/"+day+"&nbsp;&nbsp;&nbsp;&nbsp;"+hour+":"+min+":"+ss+"</td></tr><tr><td>"+questionCoten+"</td><td></td><td></td></tr>")
		$("#cus").val("");
		$("#questionCoten").html("");
//			型號and年份  BikeModel   ModelYear
		var getall={"questioner":questioner,"questionCoten":questionCoten,"bikeModel":detailmotor1.bikeModel,"modelYear":detailmotor1.modelYear}
		var too=JSON.stringify(getall)
		$.ajax({
			type : "post",
			url : "insertQA",
			data : too,
			contentType:"application/json; charset=utf-8",
			success : function(response) {
			},
		     error:function(responseerror){
		     }
		});
		
		
	})


});



