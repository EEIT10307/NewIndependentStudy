$(document).ready(function () {


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
								// buildacce += "<h6 id='accessory'>配件 <span class='ml-5' id='acc'>" + accesssobjs[k] + "</span><span id='accNum'>x" + accesss[accesssobjs[k]] + "</span></h6>"
								buildacce +=  accesssobjs[k] + "x" + accesss[accesssobjs[k]] + "<br>"
							}
						}
						// $("#allord").append(
						// 	"<tr class='item'>" +
						// 	"<td>" +
						// 	"<p id='ordSerialNum'>" + response[sobj].orderSerialNum + "</p>" +
						// 	"</td>" +
						// 	"<td>" +
						// 	"<h6>取車  <span class='ml-5' id='brStore'>" + response[sobj].pickupStore + "</span><span class='ml-1' id='brTime'>"+response[sobj].pickupDate+"</span></h6>" +
						// 	"<h6>還車  <span class='ml-5' id='rtStore'>" + response[sobj].dropoffStore + "</span><span class='ml-1' id='rtTime'>"+response[sobj].dropoffDate+"</span></h6>" +
						// 	"<h6 id='model'>車名  <span class='ml-5'>" + response[sobj].bikeModel + "</span></h6>" +
						// 	buildacce +
						// 	"</td>" +
						// 	"<td id='price'>$" + response[sobj].orderTotalPrice + "</td>" +
						// 	"<td class='text-center'>" + response[sobj].orderTime + "</td>" +
						// 	"<td class='actions'>" +
						// 	"<p class='text-right' id='rate'>滿意度<span class='ml-5'>4</span></p>" +
						// 	"<p id='reviews' style='height: 120px; overflow-y:hidden;'>I think I started out at under $3 per month 5 or 6 years ago. The price has steadily increased and my latest renewal offer was $16.99 per month. Additionally last year my wife started having spam problems and it seems like someone gained access to all her email correspondence. The only place that we could pin it down to was the justhost SMTP server. Support was very evasive when I tried to explain what I thought happened.</p>" +
						// 	"</td>" +
						// 	"</tr>"
						// );
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