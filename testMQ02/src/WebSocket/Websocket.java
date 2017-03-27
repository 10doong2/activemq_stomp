package WebSocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import ActiveMQ.ActiveMQ;
import ActiveMQ.Producer;

@ServerEndpoint(value = "/Websocket")
public class Websocket {
	
	/*웹 소켓이 연결되면 호출되는 이벤트*/
	@OnOpen
	public void handleOpen(){
		System.out.println("Client is now connected..");
	}
	
	/*메시지가 오면 호출되는 이벤트*/
	@OnMessage
	public String handleMessage(String message) {
		String result[]=message.split(":");
		
		String username = result[0];
		String command = result[1];
		
		Message mess = new Message();
		mess.setUsername(username);
		mess.setCommand(command);
		
		System.out.println("receive from client : "+ message);
		return message;
	}

	/*웹 소켓이 닫히면 호출되는 이벤트*/
	@OnClose
	public void handleClose(){
		System.out.println("Client is now disconnected..");
	}
	
	/*웹 소켓이 에러나면 호출되는 이벤트*/
	@OnError
	public void handleError(Throwable t){
		t.printStackTrace();
	}
	
}
