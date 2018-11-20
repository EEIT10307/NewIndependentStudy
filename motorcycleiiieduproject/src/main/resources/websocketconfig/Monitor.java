package websocketconfig;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Monitor implements Runnable {


    @Override
    public void run() {
    	WebSocket webSocketTest = new WebSocket();
      //  webSocketTest.sendMsg("現在時間:" + new Date());
    }

    public void sendMsg() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 20, 20, TimeUnit.SECONDS);

    }
}