$(document).ready(function () {

    $("#buttontoreset").click(function (e) {
        var t = $('#dataTable').DataTable();
        t.clear().draw()
        e.preventDefault();
        document.getElementById("conditiontocheckorder").reset();
    });

    
   $("#ordercheckmaner").click(function (e) {

    var t = $('#dataTable').DataTable();
    t.clear()


    

var t =$('#dataTable').DataTable() ; 

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
                        orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認訂單</button></td>"
                    }else if( response[k].orderStatus == "進行中訂單"){
                        orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>還車完成</option>" +"</optgroup>"+"</select>"
                        orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                    }else if (response[k].orderStatus == "未來調度"){ 
                        orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                        "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                        orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認調度</button></td>"
                    }else if (response[k].orderStatus == "進行中調度"){ 
                        orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                        orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finDisp'>完成調度</button></td>"
                    }
            
   var x = response[k].totalDiscount
   if(x == undefined){
       x=""
   }
                    t.row.add([
                        response[k].orderSerialNum ,
                        response[k].orderTime,
                        response[k].pickupStore,
                        response[k].pickupDate,
                        response[k].dropoffStore,
                        response[k].dropoffDate,
                        response[k].phone ,
                        response[k].licensePlate,
                        response[k].discountName ,
                        x,
                        response[k].bikePrice,
                        buildacce,
                        response[k].accessoriesTotalPrice,
                        response[k].orderTotalPrice,
                        response[k].orderStatus,
                        response[k].is_member,
                        response[k].payOrNot,
                        orderoption,
                        orderoptionbutton
                    ]).draw(false)

                

                }
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
        var t = $('#dataTable').DataTable();
        t.clear()
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
                            orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認訂單</button></td>"
                        }else if( response[k].orderStatus == "進行中訂單"){
                            orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>還車完成</option>" +"</optgroup>"+"</select>"
                            orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                        }else if (response[k].orderStatus == "未來調度"){ 
                            orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                            "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                            orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認調度</button></td>"
                        }else if (response[k].orderStatus == "進行中調度"){ 
                            orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                            orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finDisp'>完成調度</button></td>"
                        }
    
                        t.row.add([
                            response[k].orderSerialNum ,
                            response[k].orderTime,
                            response[k].pickupStore,
                            response[k].pickupDate,
                            response[k].dropoffStore,
                            response[k].dropoffDate,
                            response[k].phone ,
                            response[k].licensePlate,
                            response[k].discountName ,
                            response[k].totalDiscount,
                            response[k].bikePrice,
                            buildacce,
                            response[k].accessoriesTotalPrice,
                            response[k].orderTotalPrice,
                            response[k].orderStatus,
                            response[k].is_member,
                            response[k].payOrNot,
                            orderoption,
                            orderoptionbutton
                        ]).draw(false)


                   


                    }
                    
                
                }
            });

          }
      });
//======================================


    });

//完成訂單
$(".addmile").click(function (e) { 
    e.preventDefault();
    var t = $('#dataTable').DataTable();
    t.clear()
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
                    orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認訂單</button></td>"
                }else if( response[k].orderStatus == "進行中訂單"){
                    orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>還車完成</option>" +"</optgroup>"+"</select>"
                    orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                }else if (response[k].orderStatus == "未來調度"){ 
                    orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                    "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                    orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認調度</button></td>"
                }else if (response[k].orderStatus == "進行中調度"){ 
                    orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                    orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finDisp'>完成調度</button></td>"
                }

                t.row.add([
                    response[k].orderSerialNum ,
                    response[k].orderTime,
                    response[k].pickupStore,
                    response[k].pickupDate,
                    response[k].dropoffStore,
                    response[k].dropoffDate,
                    response[k].phone ,
                    response[k].licensePlate,
                    response[k].discountName ,
                    response[k].totalDiscount,
                    response[k].bikePrice,
                    buildacce,
                    response[k].accessoriesTotalPrice,
                    response[k].orderTotalPrice,
                    response[k].orderStatus,
                    response[k].is_member,
                    response[k].payOrNot,
                    orderoption,
                    orderoptionbutton
                ]).draw(false)
                
            }
            
           
        }
    });
//Alert超過里程要保養囉
var completeLicensePlate = $(".finOrder").parent().parent().children().first().next().next().next().next().next().next().next().text();

$.ajax({
    type:"post",
    url:"showMessageIfMileageIsOverAfterComplete",
    contentType: "application/json; charset=utf-8",
    data:JSON.stringify({"licensePlate":completeLicensePlate}),
    success : function(response) {
        var maintenanceMessage=""
        var  json3  = JSON.parse(JSON.stringify(response)); 
          if(json3==null){
        	  
          }else{
              for(i in json3){
                  var index = +i + +1;
                  maintenanceMessage+=(index+"."+json3[i].maintenanceItem+"<br>")
              }
              maintenanceMessage+="項目超過保養里程數了請盡快送保養!!!";
              $("#maintenance-modal-body").html(maintenanceMessage);
              $("#maintenance-modal").modal('show');
          }
           
    }
    });



  }
});
//======================================

$('#exampleEnterDis').modal('hide');


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
    var t = $('#dataTable').DataTable();
    t.clear()
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
                       orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認訂單</button></td>"
                   }else if( response[k].orderStatus == "進行中訂單"){
                       orderoption ="<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='訂單狀態'>"+"<option value='已完成訂單'>還車完成</option>" +"</optgroup>"+"</select>"
                       orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finOrder' data-toggle = 'modal'  data-target = '#exampleEnterDis'>完成訂單</button></td>"
                   }else if (response[k].orderStatus == "未來調度"){ 
                       orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" +  "<option value='進行中調度'>進行調度</option>" +
                       "<option value='已取消調度'>取消調度</option>" +"</optgroup>"+"</select>"
                       orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm checkOrder'>確認調度</button></td>"
                   }else if (response[k].orderStatus == "進行中調度"){ 
                       orderoption = "<select class='form-control form-control-sm' id ='selectresault'>" +  "<optgroup label='調度狀態'>" + "<option value='已完成調度'>完成調度</option>" +"</optgroup>"+"</select>"
                       orderoptionbutton =    "<td><button type='button' class='btn btn-secondary btn-sm finDisp' >完成調度</button></td>"
                   }

                t.row.add([
                    response[k].orderSerialNum ,
                    response[k].orderTime,
                    response[k].pickupStore,
                    response[k].pickupDate,
                    response[k].dropoffStore,
                    response[k].dropoffDate,
                    response[k].phone ,
                    response[k].licensePlate,
                    response[k].discountName ,
                    response[k].totalDiscount,
                    response[k].bikePrice,
                    buildacce,
                    response[k].accessoriesTotalPrice,
                    response[k].orderTotalPrice,
                    response[k].orderStatus,
                    response[k].is_member,
                    response[k].payOrNot,
                    orderoption,
                    orderoptionbutton
                ]).draw(false)
    


            
               }
               
        
           

           },
           error:function(responseerror){
               alert(responseerror.responseText)
           }
       });

     },
     error:function(responseerror){
         alert(responseerror.responseText)
     }
 });
//======================================




});

});