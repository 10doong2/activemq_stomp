package WebSocket;

public class Message {
	private String username;
	private String command;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	@Override
	public String toString() {
		return "FROM"+username + " : " + command + "\n";
	}
}
