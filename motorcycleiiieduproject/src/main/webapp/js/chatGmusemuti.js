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

//顯示那個使用者的聊天畫面
$(document).on('click','.chat_list',function(){
	$('.active_chat').removeClass("active_chat");
	$(this).addClass("active_chat");
	var whopick = $(this).attr("id");
//	alert(whopick)
	
$(".mesgs").each(function () {
	$(this).attr("hidden" , "hidden")

});
var whopick = $(this).attr("id");
//	alert("again"+whopick)

$(".chatroom #"+whopick+"chatroom").removeAttr("hidden")



});



//配置ＳＴＯＭＰ
$(document).ready(function () {

	var websocket = null;
	 //判斷瀏覽器是否支持WebSocket
	 if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/motorcycleiiieduproject/websocket/GMM");   
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
		//	setMessageInnerHTML("WebSocket連線成功");
			



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
			var resday =  new Date() ; 
		
			var msg=innerHTML.split(" - ")
			var username = innerHTML.split("say:")[0].split("戶")[1]
			var count = 0 
//	  alert("user="+username)
//	  alert($(".chat_date").text())
	  $(".chaterroom").each(function () {
		//  alert("切割"+$(this).text().split(":")[0])
		//判斷現在這個人有沒有跟管理員聯絡過
        if(username == $(this).text().split(":")[0]){
			count++
		}
	});

//新的聯絡人加入
if(count == 0 && username != undefined   && username != "不在線上"){
  $("#getmsg").append(
  $(`	
			<div class="chat_list" id = "${username}">
			<div class="chat_people">
			  <div class="chat_img"><img src="https://ptetutorials.com/images/user-profile.png"> </div>
			  <div class="chat_ib">
			  <h5 id='inname' class = 'chaterroom'>${username}<span class="chat_date">:${resday.getFullYear()}-${resday.getMonth()+1}-${resday.getDay()}</span></h5>
			  </div>
			</div>
		  </div>
			
  `)
  );
  

  var today1 =  new Date() ; 
  $(".chatroom").append(
	  $(`
	  <div class="mesgs bg-light" id="${username}chatroom" hidden="hidden">
	  <div class="msg_history"  id="${username}msg_history">
					 <div class="incoming_msg">
					 <div class="incoming_msg_name"></div>
					 <div class="received_msg">
					   <div class="received_withd_msg">
						 <p>${msg}</p>
						 <span class="time_date"> ${today1.getHours()}:${today1.getMinutes()} </span></div>
					 </div>
				   </div>
				 </div> 
				 <div class="type_msg">
				   <div class="input_msg_write">
					 <input type="text"  id="${username}mymessage" class="write_msg" placeholder="Type a message" />
					 <button class="msg_send_btn mr-2 my-auto" id="sentoserve"  name='${username}' type="button"><i class="fas fa-paper-plane pt-2 pr-2"></i></button>
				   </div>
				 </div>
				 </div>
		 </div>

  `)
  )

  }else{
	var today1 =  new Date() ;
	$("#"+username+"chatroom").children().first().append(
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

  }
	
var enteer = $("#"+$(".active_chat").attr("id")+"msg_history");   
enteer.scrollTop(enteer[0].scrollHeight + 83);


	    }

	    //關閉websockt
	    function closeWebSocket() {
	        websocket.close();
	    }

	    //發訊息
	    function send(sendname , message) {
		//	var jsonmessage = {mem.toString() :document.getElementById('mymessage').value }
             var sendTouse = (message+"-"+$(sendname).attr("name")) 
			 var mymessage = sendTouse.split("-")[0]
			 var today =  new Date() ; 
			 $("#"+$(sendname).attr("name")+"chatroom").children().first().append(
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
$(document).on("click","#sentoserve" , function () {

//$(this).attr("name")
var message = $("#"+$(this).attr("name")+'mymessage').val()
$("#"+$(this).attr("name")+'mymessage').val("") 
	send(this , message)
	
	alert($(".active_chat").attr("id"))
 
	var enteer = $("#"+$(".active_chat").attr("id")+"msg_history");   
enteer.scrollTop(enteer[0].scrollHeight + 83);


});

$(document).keypress(function(e) {
	if(e.which == 13) {
	//	send()
	var message =$("#"+$(".active_chat").attr("id")+"mymessage").val()
var name = $("#"+$(".active_chat").attr("id")+"mymessage").next()

$("#"+$(".active_chat").attr("id")+"mymessage").val("") ;
	
	
	
	var enteer = $("#"+$(".active_chat").attr("id")+"msg_history");   
	
    //  alert($(name).attr("name"))
	// alert($("#"+$(".active_chat").attr("id")+"mymessage").val())

	send(name , message)
	

	enteer.scrollTop(enteer[0].scrollHeight + 83);
	
	}


});





});