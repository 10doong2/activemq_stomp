package ActiveMQ;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import WebSocket.Message;

public class Producer implements Runnable {

	private static String url = "ws://localhost:61614";

	@Override
	public void run() {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Message mess = new Message();
			
			Queue testQueue = session.createQueue("TEST1711");
			Destination destination = session.createQueue("TEST1711");
			MessageProducer producer = session.createProducer(destination);

			String text = mess.getCommand();
			TextMessage message = session.createTextMessage(text);

			System.out.println("Sent message: " + message);
			producer.send(message);
			session.close();
			connection.close();

		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}
}
