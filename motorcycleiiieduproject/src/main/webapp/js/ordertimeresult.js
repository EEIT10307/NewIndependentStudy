$(document).ready(function () {
    //從前頁傳來的顧客資訊    
    var ordertime = sessionStorage.orderTime;

    $.ajax({
        type: "POST",
        url: "showAllOrderFromShop",
        data: ordertime,
        contentType: "application/json; charset=utf-8",
        success: function (response) {

            for (var x = 0; x < response.length; x++) {
                var motorname = response[x].bikeModel
                sessionStorage.setItem(motorname, JSON.stringify(response[x]))
                var element = $("<div class='col-md-4 searchItem'></div>").html(
                    "<div class='card mb-4 shadow-sm product-grid'>" +
                    "<div class='product-image'>" +
                    "<a href='#'>" +
                    "<img class='card-img-top pic-1' data-src='holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail' style='height: 225px; width: 100%; display: block;' src='https://www.supermoto8.net/images/data/2171219-cb1100rs_001H_preview.jpeg' data-holder-rendered='true'>" +
                    "<img class='card-img-top pic-2' data-src='holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail' style='height: 225px; width: 100%; display: block;' src='https://www.supermoto8.net/images/data/2171219-cb1100rs_002H_preview.jpeg' data-holder-rendered='true'></img>" +
                    "</a>" +
                    "<a href='prodDetail.html' class='select-options checkinfro' value='" + response[x].bikeModel + "'><i class='fa fa-arrow-right'></i>查看詳情</a>" +
                    "</div>" +
                    "<div class='card-body product-content'>" +
                    "<div class='title'><h3 class='m-0' id='bikeModel'>" + response[x].bikeModel + "</h3><h5 id='bikeBrand'>" + response[x].bikeBrand + "</h5></div>" +
                    "<div class='card-text'>" +
                    "<h6 class='engineType'>排氣量: <span>" + response[x].engineType + "</span> c.c.</h6>" +
                    "<h6 class='plateType'>車牌: <span>" + response[x].plateType + "牌</span></h6>" +
                    "<h6 class='plateType'>年份: <span>" + response[x].modelYear + "</span></h6>" +
                    "</div>" +
                    "<div class='d-flex justify-content-end text-muted my-2 price'>$" + response[x].hourPrice + "<span>/時</span></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>"
                );

                $("#mainbodymotorshow").append(element);

            }
        }

    });

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



});