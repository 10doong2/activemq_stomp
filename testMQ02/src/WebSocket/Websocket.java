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
	
	/*�� ������ ����Ǹ� ȣ��Ǵ� �̺�Ʈ*/
	@OnOpen
	public void handleOpen(){
		System.out.println("Client is now connected..");
	}
	
	/*�޽����� ���� ȣ��Ǵ� �̺�Ʈ*/
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

	/*�� ������ ������ ȣ��Ǵ� �̺�Ʈ*/
	@OnClose
	public void handleClose(){
		System.out.println("Client is now disconnected..");
	}
	
	/*�� ������ �������� ȣ��Ǵ� �̺�Ʈ*/
	@OnError
	public void handleError(Throwable t){
		t.printStackTrace();
	}
	
}
