package websocketconfig;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{paraName}")
public class WebSocket {
	
 //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
  //  public static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>() ;    
    private static ConcurrentHashMap<String, WebSocket> webSocketSet2 = new ConcurrentHashMap<String, WebSocket>();
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //当前发消息的人员编号
    private String userno = "";

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session  , @PathParam("paraName") String paraName , EndpointConfig config) {
        this.session = session;
        
        webSocketSet2.put(paraName, this) ; 
        userno = paraName ; 
     //   webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("連線加入,目前連線數為" + getOnlineCount());
        System.out.println("連線是誰 , :" + paraName);
        System.out.println(config);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
    	webSocketSet2.remove(userno);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
  //  @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
//        //群发消息
//        for (WebSocket item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
        
        sendToUser(message) ; 
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("發生錯誤");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	WebSocket.onlineCount--;
    }

	
    @OnMessage
	public void sendToUser(String message) {
    	System.out.println("使用者發來:"+message);
    	String sendUserno = message.split("-")[1];
        String sendMessage = message.split("-")[0];
        System.out.println("發給誰:"+ sendUserno);
    try {
    	if(webSocketSet2.get(sendUserno) != null) {
    		webSocketSet2.get(sendUserno).sendMessage("用戶"+userno+"say:"+sendMessage)  ; 
    		
    	}else {
    		System.out.println("不在");
    		webSocketSet2.get(userno).sendMessage("該用戶不在線上"); ; 
    		
    	}
    	
    	
    }catch (Exception e) {
		// TODO: handle exception
	}
    	
    	
		
	}
 

  

}
