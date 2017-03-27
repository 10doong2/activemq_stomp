package ApacheMINA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ChatProtocolHandler implements IoHandler {
	
	private List<IoSession> sessions = Collections.synchronizedList(new ArrayList<IoSession>());
	private List<String> users = Collections.synchronizedList(new ArrayList<String>());
	
	UserVO uservo = new UserVO();
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		sessions.add(session);
		uservo.setSession(session);
		System.out.println("Session Created!");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("Session Opened!");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		String user = session.getAttribute("user").toString();
		users.remove(user);
		sessions.remove(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		session.closeNow();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String command;
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		
	}

}
