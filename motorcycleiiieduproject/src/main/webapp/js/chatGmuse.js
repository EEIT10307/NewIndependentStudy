$(document).on('click', '#chatWindow', function (e) {
	if ($(this).find('a').find('i').hasClass('fa-plus')) {
		$(this).find('a').find('i').removeClass('fa-plus').addClass('fa-minus');
    } else {
    	$(this).find('a').find('i').removeClass('fa-minus').addClass('fa-plus');
    }
});

$(document).on('keyup','#lookup',function(){
	var value = $(this).val().toLowerCase();
	$("#getmsg .chat_list").filter(function() {
	   $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	});
});

$(document).on('click','#close',function(){
	$("#chatbox").addClass("d-none");
});
//配置ＳＴＯＭＰ
$(document).ready(function () {

	var membername = "myboss"


	var websocket = null;
	 //判斷瀏覽器是否支持WebSocket
	 if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/motorcycleiiieduproject/websocket/GM");   
    }
    else {
        alert('Not support websocket')
    }
	 //==========
	//連線發生錯誤回傳方法
	    websocket.onerror = function () {
	        setMessageInnerHTML("WebSocket連線錯誤");
	    };

	    //連線成功
	    websocket.onopen = function () {
	        setMessageInnerHTML("WebSocket連線成功");
	    }

	    //接收到消息的回傳方法
	    websocket.onmessage = function (event) {
	        setMessageInnerHTML(event.data);
	    }

	    //關閉連線的方法
	    websocket.onclose = function () {
	        setMessageInnerHTML("WebSocket連線關閉");
	    }

	   
	    //監聽視窗關閉事件 如果視窗關閉就主動關掉websocekt連線 防止server端error
	    window.onbeforeunload = function () {
	        closeWebSocket();
	    }

	    //將消息印到網頁上面
	    function setMessageInnerHTML(innerHTML) {
	    	var msg=innerHTML.split(" - ")
			var today1 =  new Date() ; 
			$("#chatroombody").append(
				$(`
							   <div class="incoming_msg">
							   <div class="incoming_msg_name"></div>
							   <div class="received_msg">
								 <div class="received_withd_msg">
								   <p>${msg}</p>
								   <span class="time_date"> ${today1.getHours()}:${today1.getMinutes()} </span></div>
							   </div>
							 </div>
						   </div>
	
	
			`)
			)

var div3=$(".msg_history");
div3.scrollTop(div3[0].scrollHeight +83);

	    }

	    //關閉websockt
	    function closeWebSocket() {
	        websocket.close();
	    }

	    //發訊息
	    function send() {


		//	var jsonmessage = {mem.toString() :document.getElementById('mymessage').value }
			var message =   document.getElementById('mymessage').value;
             var sendTouse = (message+"-"+"同學") 
			 var mymessage = sendTouse.split("-")[0]
			 var today =  new Date() ; 
			 $("#chatroombody").append(
				 $(`	
									<div class="outgoing_msg">
							   <div class="sent_msg">
								  <p> ${mymessage}</p>
								  <span class="time_date"> ${today.getHours()}:${today.getMinutes()}   </span> </div>
								</div>
			 `)
			 )
	        websocket.send(sendTouse);
			
	    }
	 
	//========  
	   //發送 
//	    <div class="outgoing_msg">
//        <div class="sent_msg">
//          <p>Apollo University, Delhi, India Test</p>
//          <span class="time_date"> 11:01 AM    |    Today</span> </div>
//      </div>
	    
	    
	    
	    //伺服器傳回
//	    <div class="incoming_msg">
//        <div class="incoming_msg_name"></div>
//        <div class="received_msg">
//          <div class="received_withd_msg">
//            <p>We work directly with our designers and suppliers,
//              and sell direct to you, which means quality, exclusive
//              products, at a price anyone can afford.</p>
//            <span class="time_date"> 11:01 AM    |    Today</span></div>
//        </div>
//      </div>
//    </div>
	    
	 
	//========
$(document).on("click","#sentoserve" , function () {

	send()
	$("#mymessage").val("") ;;
		});
	
	
		$(document).keypress(function(e) {
			if(e.which == 13) {
				send()
	$("#mymessage").val("") ;;
			}
		});

});