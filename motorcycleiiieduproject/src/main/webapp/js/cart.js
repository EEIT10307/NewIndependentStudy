$(document).ready(function () {

var    orderdetail =  sessionStorage.orderdetailsession;
 var orderdetailstr    =  JSON.parse(orderdetail)

 var detail = sessionStorage.getItem(orderdetailstr.bikeModel);

 var detailmotor = JSON.parse(detail);

//orderdetailstr.bikeMode
$("#motordetailordermodel").text(orderdetailstr.bikeModel);
$("#motordetailordercc").text(detailmotor.engineType.split("cc")[0]);
$("#motordetailorderbrand").text(detailmotor.plateType+"牌");
$("#motordetailorderyear").text(detailmotor.modelYear);
$("#price").text(detailmotor.hourPrice+"/hr");
$("#showmotorimg1").attr("src", "Image/"+detailmotor.bikeModel+"_"+detailmotor.modelYear+"_001.jpg");
$("#subtotal").text(orderdetailstr.bikePrice);

var acceitems = orderdetailstr.accessoriesAmount
if(acceitems != ""){
  var accejson    = JSON.parse(acceitems);
   var   keys  = Object.keys(accejson)

   for(var k in keys){

   var accetable  =   "<tr class='item'><td data-th='商品'><div class='row'><div class='col-sm-4 d-none d-md-block'>"+
	"<img src='"+"Image/"+keys[k]+".jpg"+"' style='height: 180px; width: 180px; display: block; object-fit: cover;' class='img-responsive' />"+
							"</div>"+
							"<div class='col-sm-8'>"+
								"<h4 class='nomargin'>"+keys[k]+"</h4>"+
							"</div>"+
						"</div>"+
					"</td>"+
					"<td data-th='單價' id='price' val='"+sessionStorage.getItem(keys[k])+"'>"+sessionStorage.getItem(keys[k])+"</td>"+
					"<td data-th='數量'>"+
						"<input disabled='disabled' type='number' class='form-control text-center'  value='"+accejson[keys[k]]+"' id='number'>"+
					"</td>"+
					"<td data-th='小計' class='text-center' id='subtotal' value='"+sessionStorage.getItem(keys[k]) * accejson[keys[k]]+"'>"+sessionStorage.getItem(keys[k]) * accejson[keys[k]]+"</td>"+
					"<td class='actions' data-th=''>"+
					"<button hidden='hidden' class='btn btn-danger btn-sm'><i class='far fa-trash-alt'></i></button></td></tr>"
$("#accetablerun").append(accetable);
   }
}
$("#totalamo").text(orderdetailstr.orderTotalPrice);
$("#takepickup").text(orderdetailstr.pickupDate);
$("#takedropoff").text(orderdetailstr.dropoffDate);
$("#takepickupstore").text(orderdetailstr.pickupStore);
$("#takedropoffstore").text(orderdetailstr.dropoffStore);

//回上頁
$("#gobacke").on("click",function(){
history.go(-1)
})
//按鈕做toggle 	判斷會員
if(orderdetailstr.phone != ""){
$("#checkout").click(function (e) { 
	e.preventDefault();
	
	alert(JSON.stringify(orderdetailstr))

	$.ajax({ 
		type: "Post",
		url: "getlastcheckorderlist",
		data: JSON.stringify(orderdetailstr),
		contentType: "application/json; charset=utf-8",
		success: function (response) {
			alert(response)
			window.location.assign ("index.html");
		},
		error:function(responseerror){
	   
			alert(responseerror.responseText)
	  
		  
		}
	});

});
}else{

$("#checkout").attr("data-toggle","modal");
$("#checkout").attr("data-target","#exampleEnterPhone");

}


$(".addphone").click(function (e) { 
	e.preventDefault();

	if(isNaN($(".guestphone").val())){
		alert("請輸入數字")
	}else if($(".guestphone").val().length != 10){
		alert("請輸入正確的手機號碼(10碼)")
	}else{
	orderdetailstr.phone = $(".guestphone").val();
	$.ajax({ 
		type: "Post",
		url: "getlastcheckorderlist",
		data: JSON.stringify(orderdetailstr),
		contentType: "application/json; charset=utf-8",
		success: function (response) {
			alert(response)
			window.location.assign ("index.html"); 

		},
		error:function(responseerror){
	   
			alert(responseerror.responseText)
	  
		  
		}
	});
	}
});




});