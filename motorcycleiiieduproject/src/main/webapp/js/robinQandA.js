$(window).on('load', function () {

   
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
//	 	alert("月"+month)
		var day=dt.getDate()
//	 	alert("日"+day)
		var hour=dt.getHours()
//	 	alert("時間"+hour)
		var min=dt.getMinutes()
//	 	alert("分鐘"+min)
		var ss=	dt.getSeconds()
//	 	alert("秒"+ss)
//	 	alert(year+month+day+hour+min+ss)
//			$(".cc").append(year+"/"+month+"/"+day+"&nbsp;&nbsp;&nbsp;"+hour+":"+min+":"+ss)
		var co=$("[name='count']").length;//計算元素有幾個

		var questioner = $("#cus").val();//輸入者姓名
//			alert(questioner)
		var questionCoten=$("#questionCoten").text();//問答問題
//			alert(questionCoten)
		$("#appendtext").append("<tr name='count'><td>Q"+co+":</td><td>"+questioner+"</td><td>"+year+"/"+month+"/"+day+"&nbsp;&nbsp;&nbsp;&nbsp;"+hour+":"+min+":"+ss+"</td></tr><tr><td>"+questionCoten+"</td><td></td><td></td></tr>")
		//"<tr name='count'><td>Q"+co+":</td><td>"+questioner+"</td><td>"++"</td></tr><tr><td>"+questionCoten+"</td><td></td><td></td></tr>"
		if(questioner=="" || questionCoten==""){
		
			return;
		}
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
		 //        alert(responseerror.responseText)
		     }
		});
		
		
	})



})