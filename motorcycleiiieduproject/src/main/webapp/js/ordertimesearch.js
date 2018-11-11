$(window).on('load',function () {

    var orderdetail = {};
    //做時間選項
    var today = new Date();
    //   $("#pDate").attr("value", today);
    var futureday = new Date();
    //    alert(today.getHours())

    for (var time = 10; time < 23; time++) {
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
    }else{
        var minDate = today.getDate() ; 
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
    }else{
        var minfDate = futureday.getDate() ; 
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
        }
    });
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
        // alert(jsonob)

        window.location.assign ("search1.html"); 


       
    });

});
