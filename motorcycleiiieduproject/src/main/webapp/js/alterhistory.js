$(document).ready(function () {
    
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
//=========
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
            url: "checkDispatcherHistory",
            data: JSON.stringify(managercheck),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                for (let k in response) {
           
                    t.row.add([
                        response[k].pickupStore ,
                        response[k].dropoffStore,
                        response[k].bikeModel,
                        response[k].licensePlate,
                        response[k].pickupDate             
                    ]).draw(false)


                }







            }
        });




    });

});