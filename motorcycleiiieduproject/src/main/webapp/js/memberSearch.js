$(document).ready(function () {



	var cookie = document.cookie; 

	if(cookie.indexOf("memberphone") != -1){
	 var t = $('#dataTable').DataTable();
	 t.clear()
 var t =$('#dataTable').DataTable() ;
	 var memphone= cookie.split("memberphone=")[1].split(";")[0];
	 var nonmemberphone = JSON.stringify({ "nonmemberinputphone": memphone })
	 $.ajax({
		 type: "POST",
		 url: "showMemberAndNonMemberDetail",
		 data: nonmemberphone,
		 contentType: "application/json; charset=utf-8",
		 success: function (response) {

			$("#nonmemberinputphone").attr("placeholder", memphone);
			 for (let sobj in response) {
				 var buildacce = ""
				 if (response[sobj].accessoriesAmount != "") {
 
					 var accesss = JSON.parse(response[sobj].accessoriesAmount)
					 var accesssobjs = Object.keys(accesss)
					 for (var k in accesssobjs) {
						 buildacce +=  accesssobjs[k] + "x" + accesss[accesssobjs[k]] + "<br>"
					 }
				 }
				 t.row.add([
					 response[sobj].orderSerialNum ,
					 "取車:"+response[sobj].pickupStore+" "+response[sobj].pickupDate+
					 "<br>還車:"+response[sobj].dropoffStore+" "+response[sobj].dropoffDate+
					 "<br>車名:"+response[sobj].bikeModel +"<br>" +buildacce,
					 response[sobj].orderTotalPrice  ,
					 response[sobj].orderTime    ,
							"滿意度"
				 ]).draw(false)
			 }
		 },
	     error:function(responseerror){
	         alert(responseerror.responseText)
	     }
	 });
	}






	$("#phonenum").click(function (e) {
		e.preventDefault();
		if (isNaN($("#nonmemberinputphone").val())) {
			alert("請輸入數字")
		}else if($("#nonmemberinputphone").val().length == 0 ){

			var t = $('#dataTable').DataTable();
			t.clear().draw(false);

		} else if ($("#nonmemberinputphone").val().length != 10) {
			alert("請輸入正確的手機號碼(10碼)")
		} else {
	  var t = $('#dataTable').DataTable();
			t.clear()
		var t =$('#dataTable').DataTable() ;

			var nonmemberphone = JSON.stringify({ "nonmemberinputphone": $("#nonmemberinputphone").val() })

			$.ajax({
				type: "POST",
				url: "showMemberAndNonMemberDetail",
				data: nonmemberphone,
				contentType: "application/json; charset=utf-8",
				success: function (response) {

					
					for (let sobj in response) {
						var buildacce = ""

						if (response[sobj].accessoriesAmount != "") {

							var accesss = JSON.parse(response[sobj].accessoriesAmount)
							var accesssobjs = Object.keys(accesss)
							for (var k in accesssobjs) {
								buildacce +=  accesssobjs[k] + "x" + accesss[accesssobjs[k]] + "<br>"
							}
						}
						t.row.add([
							response[sobj].orderSerialNum ,
							"取車:"+response[sobj].pickupStore+" "+response[sobj].pickupDate+
							"<br>還車:"+response[sobj].dropoffStore+" "+response[sobj].dropoffDate+
							"<br>車名:"+response[sobj].bikeModel +"<br>" +buildacce,
							response[sobj].orderTotalPrice  ,
							response[sobj].orderTime    ,
							       "滿意度"
						]).draw(false)
						



					}

				},
			     error:function(responseerror){
			         alert(responseerror.responseText)
			     }
			});

		}

	});








	$(document).on('keyup', '#searchOrd', function () {
		var value = $(this).val().toLowerCase();
		$("#allord tr").filter(function () {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});


});