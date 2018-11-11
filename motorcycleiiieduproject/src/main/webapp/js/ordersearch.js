$(document).ready(function () {

    $("#buttontoreset").click(function (e) {
        e.preventDefault();


        document.getElementById("conditiontocheckorder").reset();
    });

   $("#ordercheckmaner").click(function (e) {
        e.preventDefault();
        var managercheck = { "pickupstore": "", "dropoffstore": "", "orderstatus": "" }
        var count = 0;
        $("form select").each(function () {
            if (count == 0) {
                managercheck.pickupstore = $(this).val();
                count++
            } else if (count == 1) {
                managercheck.dropoffstore = $(this).val();
                count++
            } else {
                managercheck.orderstatus = $(this).val();
                count = 0
            }
        });

    
        $.ajax({
            type: "POST",
            url: "showManagerSearchDetail",
            data: JSON.stringify(managercheck),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                table.destroy();

$("#outtableDIV").html(
    $(`<table class='table-responsive table-bordered table-striped' id='dataTable' width='100%' cellspacing='0' style='table-layout: fixed; font-size: xx-small;'>
                      <thead>
                        <tr class='text-center'>
                          <th>流水號</th>
                          <th>下訂時間</th>
                          <th>取店</th>
                          <th>取車時間</th>
                          <th>還店</th>
                          <th>還車時間</th>
                          <th>電話</th>
                          <th>車牌</th>
                          <th>折扣名稱</th>
                          <th>折扣數</th>
                          <th>機車折扣價</th>
                          <th>附加配件與數量</th>
                          <th>配件總價</th>
                          <th>訂單總價</th>
                          <th>狀態</th>
                          <th>是否為會員</th>
                          <th>是否已付款</th>
                          <th>更改訂單狀態</th>
                          <th>更改</th>
                        </tr>
                      </thead>
                      <tfoot>
                        <tr>
                          <th>流水號</th>
                          <th>下訂時間</th>
                          <th>取店</th>
                          <th>取車時間</th>
                          <th>還店</th>
                          <th>還車時間</th>
                          <th>電話</th>
                          <th>車牌</th>
                          <th>折扣名稱</th>
                          <th>折扣</th>
                          <th>機車折扣價</th>
                          <th>附加配件與數量</th>
                          <th>配件總價</th>
                          <th>訂單總價</th>
                          <th>狀態</th>
                          <th>是否為會員</th>
                          <th>是否已付款</th>
                          <th>更改訂單狀態</th>
                          <th>更改</th>                      
                        </tr>
                      </tfoot>
                      <tbody id="mamnagerorderdetail">
                       
                      </tbody>
                    </table>`)
);






                for (let k in response) {
                    var buildacce = "";
                    if (response[k].accessoriesAmount != "") {

                        var accesss = JSON.parse(response[k].accessoriesAmount)
                        var accesssobjs = Object.keys(accesss)

                        for (var p in accesssobjs) {
                            buildacce += "<span>" + accesssobjs[p] + "x" + accesss[accesssobjs[p]] + "</span><br>"
                        }
                    }
                    var orderoption = "";
                    var orderoptionbutton = "";
                    if( response[k].orderStatus == "未來訂單"){
                        orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" + "<optgroup label='訂單狀態'>"+ "<option value='進行中訂單'>取車完成</option>" +
                        "<option value='已取消訂單'>取消訂單</option>" +"</optgroup>"+"</select>" 
                        orderoptionbutton =    "<td><button type='button' class='btn btn-primary btn-sm checkOrder'>確認訂單</button></td>"
                    }else if( response[k].orderStatus == "進行中訂單"){
                        orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>環車完成</option>" +"</optgroup>"+"</select>"
                        orderoptionbutton =    "<td><button type='button' class='btn btn-success btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                    }else if (response[k].orderStatus == "未來調度"){ 
                        orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                        "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                        orderoptionbutton =    "<td><button type='button' class='btn btn-warning btn-sm checkOrder'>確認調度</button></td>"
                    }else if (response[k].orderStatus == "進行中調度"){ 
                        orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                        orderoptionbutton =    "<td><button type='button' class='btn btn-info btn-sm finDisp'>完成調度</button></td>"
                    }

                    $("#mamnagerorderdetail").append(

                        "<tr>" +
                        "<td>" + response[k].orderSerialNum + "</td>" +
                        "<td>" + response[k].orderTime + "</td>" +
                        "<td>" + response[k].pickupStore + "</td>" +
                        "<td>" + response[k].pickupDate + "</td>" +
                        "<td>" + response[k].dropoffStore + "</td>" +
                        "<td>" + response[k].dropoffDate + "</td>" +
                        "<td>" + response[k].phone + "</td>" +
                        "<td>" + response[k].licensePlate + "</td>" +
                        "<td>" + response[k].discountName + "</td>" +
                        "<td>" + response[k].totalDiscount + "</td>" +
                        "<td>" + response[k].bikePrice + "</td>" +
                        "<td>" + buildacce + "</td>" +
                        "<td>" + response[k].accessoriesTotalPrice + "</td>" +
                        "<td>" + response[k].orderTotalPrice + "</td>" +
                        "<td>" + response[k].orderStatus + "</td>" +
                        "<td>" + response[k].is_member + "</td>" +
                        "<td>" + response[k].payOrNot + "</td>" +
                        "<td>" +
                        orderoption+
                        "</td>" +
                        orderoptionbutton +
                        "</tr>");

                        orderoption = "" ; 
                        orderoptionbutton = "" ; 
                }
                
                $('#dataTable').DataTable({
                    "footerCallback": function (row, data, start, end, display) {
                        var api = this.api(), data;

                        // Remove the formatting to get integer data for summation
                        var intVal = function (i) {
                            return typeof i === 'string' ?
                                i.replace(/[\$,]/g, '') * 1 :
                                typeof i === 'number' ?
                                    i : 0;
                        };
                    }
                });

            }
        });
    });


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
                $("#pickupbranchName").append($("<option value='" + json[x] + "'></option>")
                    .text(json[x]))
                $("#dropoffbranchName").append($("<option value='" + json[x] + "'></option>")
                    .text(json[x]))
            }

        }
    });

//訂單更改
    $("#outtableDIV").on("click",  ".checkOrder" ,  function (e) {
        e.preventDefault();
    //    alert($(this).parent().prev().children().val())
    //    alert($(this).parent().parent().children().first().text())

    var godate   =   JSON.stringify({"orderstatus":$(this).parent().prev().children().val() 
     , "ordersernum":$(this).parent().parent().children().first().text()})

      $.ajax({
          type: "POST",
          url: "showManagerChangeOrderStatus",
          data: godate,
          contentType:"application/JSON; charset = UTF-8",
          success: function (response) {
                
    //====================================== 
            var managercheck = { "pickupstore": "", "dropoffstore": "", "orderstatus": "" }
            var count = 0;
            $("form select").each(function () {
                if (count == 0) {
                    managercheck.pickupstore = $(this).val();
                    count++
                } else if (count == 1) {
                    managercheck.dropoffstore = $(this).val();
                    count++
                } else {
                    managercheck.orderstatus = $(this).val();
                    count = 0
                }
            });
    
        
            $.ajax({
                type: "POST",
                url: "showManagerSearchDetail",
                data: JSON.stringify(managercheck),
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    table.destroy();
    
    $("#outtableDIV").html(
        $(`<table class='table-responsive table-bordered table-striped' id='dataTable' width='100%' cellspacing='0' style='table-layout: fixed; font-size: xx-small;'>
                          <thead>
                            <tr class='text-center'>
                              <th>流水號</th>
                              <th>下訂時間</th>
                              <th>取店</th>
                              <th>取車時間</th>
                              <th>還店</th>
                              <th>還車時間</th>
                              <th>電話</th>
                              <th>車牌</th>
                              <th>折扣名稱</th>
                              <th>折扣數</th>
                              <th>機車折扣價</th>
                              <th>附加配件與數量</th>
                              <th>配件總價</th>
                              <th>訂單總價</th>
                              <th>狀態</th>
                              <th>是否為會員</th>
                              <th>是否已付款</th>
                              <th>更改訂單狀態</th>
                              <th>更改</th>
                            </tr>
                          </thead>
                          <tfoot>
                            <tr>
                              <th>流水號</th>
                              <th>下訂時間</th>
                              <th>取店</th>
                              <th>取車時間</th>
                              <th>還店</th>
                              <th>還車時間</th>
                              <th>電話</th>
                              <th>車牌</th>
                              <th>折扣名稱</th>
                              <th>折扣</th>
                              <th>機車折扣價</th>
                              <th>附加配件與數量</th>
                              <th>配件總價</th>
                              <th>訂單總價</th>
                              <th>狀態</th>
                              <th>是否為會員</th>
                              <th>是否已付款</th>
                              <th>更改訂單狀態</th>
                              <th>更改</th>                      
                            </tr>
                          </tfoot>
                          <tbody id="mamnagerorderdetail">
                           
                          </tbody>
                        </table>`)
    );

    
                    for (let k in response) {
                        var buildacce = "";
                        if (response[k].accessoriesAmount != "") {
    
                            var accesss = JSON.parse(response[k].accessoriesAmount)
                            var accesssobjs = Object.keys(accesss)
    
                            for (var p in accesssobjs) {
                                buildacce += "<span>" + accesssobjs[p] + "x" + accesss[accesssobjs[p]] + "</span><br>"
                            }
                        }
                        var orderoption = "";
                        var orderoptionbutton = "";
                        if( response[k].orderStatus == "未來訂單"){
                            orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" + "<optgroup label='訂單狀態'>"+ "<option value='進行中訂單'>取車完成</option>" +
                            "<option value='已取消訂單'>取消訂單</option>" +"</optgroup>"+"</select>" 
                            orderoptionbutton =    "<td><button type='button' class='btn btn-primary btn-sm checkOrder'>確認訂單</button></td>"
                        }else if( response[k].orderStatus == "進行中訂單"){
                            orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>環車完成</option>" +"</optgroup>"+"</select>"
                            orderoptionbutton =    "<td><button type='button' class='btn btn-success btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                        }else if (response[k].orderStatus == "未來調度"){ 
                            orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                            "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                            orderoptionbutton =    "<td><button type='button' class='btn btn-warning btn-sm checkOrder'>確認調度</button></td>"
                        }else if (response[k].orderStatus == "進行中調度"){ 
                            orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                            orderoptionbutton =    "<td><button type='button' class='btn btn-info btn-sm finDisp'>完成調度</button></td>"
                        }
    
                        $("#mamnagerorderdetail").append(
    
                            "<tr>" +
                            "<td>" + response[k].orderSerialNum + "</td>" +
                            "<td>" + response[k].orderTime + "</td>" +
                            "<td>" + response[k].pickupStore + "</td>" +
                            "<td>" + response[k].pickupDate + "</td>" +
                            "<td>" + response[k].dropoffStore + "</td>" +
                            "<td>" + response[k].dropoffDate + "</td>" +
                            "<td>" + response[k].phone + "</td>" +
                            "<td>" + response[k].licensePlate + "</td>" +
                            "<td>" + response[k].discountName + "</td>" +
                            "<td>" + response[k].totalDiscount + "</td>" +
                            "<td>" + response[k].bikePrice + "</td>" +
                            "<td>" + buildacce + "</td>" +
                            "<td>" + response[k].accessoriesTotalPrice + "</td>" +
                            "<td>" + response[k].orderTotalPrice + "</td>" +
                            "<td>" + response[k].orderStatus + "</td>" +
                            "<td>" + response[k].is_member + "</td>" +
                            "<td>" + response[k].payOrNot + "</td>" +
                            "<td>" +
                            orderoption+
                            "</td>" +
                            orderoptionbutton +
                            "</tr>");
    
                            orderoption = "" ; 
                            orderoptionbutton = "" ; 
                    }
                    
                    $('#dataTable').DataTable({
                        "footerCallback": function (row, data, start, end, display) {
                            var api = this.api(), data;
    
                            // Remove the formatting to get integer data for summation
                            var intVal = function (i) {
                                return typeof i === 'string' ?
                                    i.replace(/[\$,]/g, '') * 1 :
                                    typeof i === 'number' ?
                                        i : 0;
                            };
                        }
                    });
    
                }
            });

          }
      });
//======================================


    });

//完成訂單
$(".addmile").click(function (e) { 
    e.preventDefault();

//    alert($(this).parent().prev().children().val())
//    alert($(this).parent().parent().children().first().text())
var godate   =   JSON.stringify({"orderstatus":$(".finOrder").parent().prev().children().val() 
, "ordersernum":$(".finOrder").parent().parent().children().first().text() , "newmileage":$(".guestmile").val() })

$.ajax({
  type: "POST",
  url: "showManagerFinishedOrder",
  data: godate,
  contentType:"application/JSON; charset = UTF-8",
  success: function (response) {
        
//====================================== 
    var managercheck = { "pickupstore": "", "dropoffstore": "", "orderstatus": "" }
    var count = 0;
    $("form select").each(function () {
        if (count == 0) {
            managercheck.pickupstore = $(this).val();
            count++
        } else if (count == 1) {
            managercheck.dropoffstore = $(this).val();
            count++
        } else {
            managercheck.orderstatus = $(this).val();
            count = 0
        }
    });


    $.ajax({
        type: "POST",
        url: "showManagerSearchDetail",
        data: JSON.stringify(managercheck),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            table.destroy();

$("#outtableDIV").html(
$(`<table class='table-responsive table-bordered table-striped' id='dataTable' width='100%' cellspacing='0' style='table-layout: fixed; font-size: xx-small;'>
                  <thead>
                    <tr class='text-center'>
                      <th>流水號</th>
                      <th>下訂時間</th>
                      <th>取店</th>
                      <th>取車時間</th>
                      <th>還店</th>
                      <th>還車時間</th>
                      <th>電話</th>
                      <th>車牌</th>
                      <th>折扣名稱</th>
                      <th>折扣數</th>
                      <th>機車折扣價</th>
                      <th>附加配件與數量</th>
                      <th>配件總價</th>
                      <th>訂單總價</th>
                      <th>狀態</th>
                      <th>是否為會員</th>
                      <th>是否已付款</th>
                      <th>更改訂單狀態</th>
                      <th>更改</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>流水號</th>
                      <th>下訂時間</th>
                      <th>取店</th>
                      <th>取車時間</th>
                      <th>還店</th>
                      <th>還車時間</th>
                      <th>電話</th>
                      <th>車牌</th>
                      <th>折扣名稱</th>
                      <th>折扣</th>
                      <th>機車折扣價</th>
                      <th>附加配件與數量</th>
                      <th>配件總價</th>
                      <th>訂單總價</th>
                      <th>狀態</th>
                      <th>是否為會員</th>
                      <th>是否已付款</th>
                      <th>更改訂單狀態</th>
                      <th>更改</th>                      
                    </tr>
                  </tfoot>
                  <tbody id="mamnagerorderdetail">
                   
                  </tbody>
                </table>`)
);


            for (let k in response) {
                var buildacce = "";
                if (response[k].accessoriesAmount != "") {

                    var accesss = JSON.parse(response[k].accessoriesAmount)
                    var accesssobjs = Object.keys(accesss)

                    for (var p in accesssobjs) {
                        buildacce += "<span>" + accesssobjs[p] + "x" + accesss[accesssobjs[p]] + "</span><br>"
                    }
                }
                var orderoption = "";
                var orderoptionbutton = "";
                if( response[k].orderStatus == "未來訂單"){
                    orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" + "<optgroup label='訂單狀態'>"+ "<option value='進行中訂單'>取車完成</option>" +
                    "<option value='已取消訂單'>取消訂單</option>" +"</optgroup>"+"</select>" 
                    orderoptionbutton =    "<td><button type='button' class='btn btn-primary btn-sm checkOrder'>確認訂單</button></td>"
                }else if( response[k].orderStatus == "進行中訂單"){
                    orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>環車完成</option>" +"</optgroup>"+"</select>"
                    orderoptionbutton =    "<td><button type='button' class='btn btn-success btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                }else if (response[k].orderStatus == "未來調度"){ 
                    orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                    "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                    orderoptionbutton =    "<td><button type='button' class='btn btn-warning btn-sm checkOrder'>確認調度</button></td>"
                }else if (response[k].orderStatus == "進行中調度"){ 
                    orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                    orderoptionbutton =    "<td><button type='button' class='btn btn-info btn-sm finDisp'>完成調度</button></td>"
                }

                $("#mamnagerorderdetail").append(

                    "<tr>" +
                    "<td>" + response[k].orderSerialNum + "</td>" +
                    "<td>" + response[k].orderTime + "</td>" +
                    "<td>" + response[k].pickupStore + "</td>" +
                    "<td>" + response[k].pickupDate + "</td>" +
                    "<td>" + response[k].dropoffStore + "</td>" +
                    "<td>" + response[k].dropoffDate + "</td>" +
                    "<td>" + response[k].phone + "</td>" +
                    "<td>" + response[k].licensePlate + "</td>" +
                    "<td>" + response[k].discountName + "</td>" +
                    "<td>" + response[k].totalDiscount + "</td>" +
                    "<td>" + response[k].bikePrice + "</td>" +
                    "<td>" + buildacce + "</td>" +
                    "<td>" + response[k].accessoriesTotalPrice + "</td>" +
                    "<td>" + response[k].orderTotalPrice + "</td>" +
                    "<td>" + response[k].orderStatus + "</td>" +
                    "<td>" + response[k].is_member + "</td>" +
                    "<td>" + response[k].payOrNot + "</td>" +
                    "<td>" +
                    orderoption+
                    "</td>" +
                    orderoptionbutton +
                    "</tr>");

                    orderoption = "" ; 
                    orderoptionbutton = "" ; 
            }
            
            $('#dataTable').DataTable({
                "footerCallback": function (row, data, start, end, display) {
                    var api = this.api(), data;

                    // Remove the formatting to get integer data for summation
                    var intVal = function (i) {
                        return typeof i === 'string' ?
                            i.replace(/[\$,]/g, '') * 1 :
                            typeof i === 'number' ?
                                i : 0;
                    };
                }
            });
        }
    });
  }
});
//======================================

$('#exampleEnterDis').modal('hide');
// $("#exampleEnterDis").removeClass("show");
// $("#exampleEnterDis").css("display", "none");
// $("#exampleEnterDis").attr("aria-hidden", true);
// $("#page-top").removeClass("modal-open");

});

//.......
    $("#outtableDIV").on("click",  ".finOrder" ,   function (e) {
        e.preventDefault();
 
$("#motoplate").text($(this).parent().parent().children().first().next().next().next().next().next().next().next().text());
 
//======================================
    });
//..........

//完成調度
$("#outtableDIV").on("click",  ".finDisp" ,   function (e) {
    e.preventDefault();
   //    alert($(this).parent().prev().children().val())
//    alert($(this).parent().parent().children().first().text())

var godate   =   JSON.stringify({"orderstatus":$(this).parent().prev().children().val() 
, "ordersernum":$(this).parent().parent().children().first().text()})

 $.ajax({
     type: "POST",
     url: "showManagerFinishedDiapatcher",
     data: godate,
     contentType:"application/JSON; charset = UTF-8",
     success: function (response) {
           
//====================================== 
       var managercheck = { "pickupstore": "", "dropoffstore": "", "orderstatus": "" }
       var count = 0;
       $("form select").each(function () {
           if (count == 0) {
               managercheck.pickupstore = $(this).val();
               count++
           } else if (count == 1) {
               managercheck.dropoffstore = $(this).val();
               count++
           } else {
               managercheck.orderstatus = $(this).val();
               count = 0
           }
       });

   
       $.ajax({
           type: "POST",
           url: "showManagerSearchDetail",
           data: JSON.stringify(managercheck),
           contentType: "application/json; charset=utf-8",
           success: function (response) {
               table.destroy();

$("#outtableDIV").html(
   $(`<table class='table-responsive table-bordered table-striped' id='dataTable' width='100%' cellspacing='0' style='table-layout: fixed; font-size: xx-small;'>
                     <thead>
                       <tr class='text-center'>
                         <th>流水號</th>
                         <th>下訂時間</th>
                         <th>取店</th>
                         <th>取車時間</th>
                         <th>還店</th>
                         <th>還車時間</th>
                         <th>電話</th>
                         <th>車牌</th>
                         <th>折扣名稱</th>
                         <th>折扣數</th>
                         <th>機車折扣價</th>
                         <th>附加配件與數量</th>
                         <th>配件總價</th>
                         <th>訂單總價</th>
                         <th>狀態</th>
                         <th>是否為會員</th>
                         <th>是否已付款</th>
                         <th>更改訂單狀態</th>
                         <th>更改</th>
                       </tr>
                     </thead>
                     <tfoot>
                       <tr>
                         <th>流水號</th>
                         <th>下訂時間</th>
                         <th>取店</th>
                         <th>取車時間</th>
                         <th>還店</th>
                         <th>還車時間</th>
                         <th>電話</th>
                         <th>車牌</th>
                         <th>折扣名稱</th>
                         <th>折扣</th>
                         <th>機車折扣價</th>
                         <th>附加配件與數量</th>
                         <th>配件總價</th>
                         <th>訂單總價</th>
                         <th>狀態</th>
                         <th>是否為會員</th>
                         <th>是否已付款</th>
                         <th>更改訂單狀態</th>
                         <th>更改</th>                      
                       </tr>
                     </tfoot>
                     <tbody id="mamnagerorderdetail">
                      
                     </tbody>
                   </table>`)
);


               for (let k in response) {
                   var buildacce = "";
                   if (response[k].accessoriesAmount != "") {

                       var accesss = JSON.parse(response[k].accessoriesAmount)
                       var accesssobjs = Object.keys(accesss)

                       for (var p in accesssobjs) {
                           buildacce += "<span>" + accesssobjs[p] + "x" + accesss[accesssobjs[p]] + "</span><br>"
                       }
                   }
                   var orderoption = "";
                   var orderoptionbutton = "";
                   if( response[k].orderStatus == "未來訂單"){
                       orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" + "<optgroup label='訂單狀態'>"+ "<option value='進行中訂單'>取車完成</option>" +
                       "<option value='已取消訂單'>取消訂單</option>" +"</optgroup>"+"</select>" 
                       orderoptionbutton =    "<td><button type='button' class='btn btn-primary btn-sm checkOrder'>確認訂單</button></td>"
                   }else if( response[k].orderStatus == "進行中訂單"){
                       orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>環車完成</option>" +"</optgroup>"+"</select>"
                       orderoptionbutton =    "<td><button type='button' class='btn btn-success btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                   }else if (response[k].orderStatus == "未來調度"){ 
                       orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                       "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                       orderoptionbutton =    "<td><button type='button' class='btn btn-warning btn-sm checkOrder'>確認調度</button></td>"
                   }else if (response[k].orderStatus == "進行中調度"){ 
                       orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                       orderoptionbutton =    "<td><button type='button' class='btn btn-info btn-sm finDisp' >完成調度</button></td>"
                   }

                   $("#mamnagerorderdetail").append(

                       "<tr>" +
                       "<td>" + response[k].orderSerialNum + "</td>" +
                       "<td>" + response[k].orderTime + "</td>" +
                       "<td>" + response[k].pickupStore + "</td>" +
                       "<td>" + response[k].pickupDate + "</td>" +
                       "<td>" + response[k].dropoffStore + "</td>" +
                       "<td>" + response[k].dropoffDate + "</td>" +
                       "<td>" + response[k].phone + "</td>" +
                       "<td>" + response[k].licensePlate + "</td>" +
                       "<td>" + response[k].discountName + "</td>" +
                       "<td>" + response[k].totalDiscount + "</td>" +
                       "<td>" + response[k].bikePrice + "</td>" +
                       "<td>" + buildacce + "</td>" +
                       "<td>" + response[k].accessoriesTotalPrice + "</td>" +
                       "<td>" + response[k].orderTotalPrice + "</td>" +
                       "<td>" + response[k].orderStatus + "</td>" +
                       "<td>" + response[k].is_member + "</td>" +
                       "<td>" + response[k].payOrNot + "</td>" +
                       "<td>" +
                       orderoption+
                       "</td>" +
                       orderoptionbutton +
                       "</tr>");

                       orderoption = "" ; 
                       orderoptionbutton = "" ; 
               }
               
               $('#dataTable').DataTable({
                   "footerCallback": function (row, data, start, end, display) {
                       var api = this.api(), data;

                       // Remove the formatting to get integer data for summation
                       var intVal = function (i) {
                           return typeof i === 'string' ?
                               i.replace(/[\$,]/g, '') * 1 :
                               typeof i === 'number' ?
                                   i : 0;
                       };
                   }
               });

           }
       });

     }
 });
//======================================




});

});