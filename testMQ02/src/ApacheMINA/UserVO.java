package ApacheMINA;

import org.apache.mina.core.session.IoSession;

public class UserVO {
	private String username;
	private IoSession session;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public IoSession getSession() {
		return session;
	}
	public void setSession(IoSession session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "UserVO [username=" + username + ", session=" + session + "]";
	}
}
