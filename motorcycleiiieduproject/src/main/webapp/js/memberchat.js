
//配置ＳＴＯＭＰ
$(document).ready(function () {

	var cookie = document.cookie; 

		if(cookie.indexOf("email")!=-1){
		
			var membername= cookie.split("email=")[1].split(";")[0].split("@")[0];
		
		}



	var websocket = null;
	//判斷瀏覽器是否支持WebSocket
	if ('WebSocket' in window) {

		websocket = new WebSocket("ws://172.20.10.14:8080/motorcycleiiieduproject/websocket/"+membername);
	}
	else {
		alert('Not support websocket')
	}
	//==========
	//連線發生錯誤回傳方法
	websocket.onerror = function () {
		setMessageInnerHTML("連線錯誤");
	};

	//連線成功
	websocket.onopen = function () {
		setMessageInnerHTML("連線成功");
	}

	//接收到消息的回傳方法
	websocket.onmessage = function (event) {
		setMessageInnerHTML(event.data);
	}

	//關閉連線的方法
	websocket.onclose = function () {
		setMessageInnerHTML("連線關閉");
	}


	//監聽視窗關閉事件 如果視窗關閉就主動關掉websocekt連線 防止server端error
	window.onbeforeunload = function () {
		closeWebSocket();
	}

	//將消息印到網頁上面
	function setMessageInnerHTML(innerHTML) {
		var msg = innerHTML.split(" - ")
		var today1 = new Date();

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
		var div3 = $(".msg_history");
		div3.scrollTop(div3[0].scrollHeight + 83);
	}

	//關閉websockt
	function closeWebSocket() {
		websocket.close();
	}

	//發訊息
	function send() {
		var today = new Date();

		//	var jsonmessage = {mem.toString() :document.getElementById('mymessage').value }
		var message = document.getElementById('mymessage').value;
		var sendToGM = (message + "-" + "GMM")
		var mymessage = sendToGM.split("-")[0]

		$("#chatroombody").append(
			$(`	
							   <div class="outgoing_msg">
						  <div class="sent_msg">
							 <p> ${mymessage}</p>
							 <span class="time_date"> ${today.getHours()}:${today.getMinutes()}   </span> </div>
						   </div>
		`)
		)
//送給socket伺服器
		websocket.send(sendToGM);
	
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
	$(document).on("click", "#sentoserve", function () {
		send()
$("#mymessage").val("") ;
		
		var div3 = $(".msg_history");
		div3.scrollTop(div3[0].scrollHeight + 83);
		
	});


	$(document).keypress(function(e) {
		if(e.which == 13) {
			send()
$("#mymessage").val("") ;;
var div3 = $(".msg_history");
div3.scrollTop(div3[0].scrollHeight + 83);
		}
	});
	
});