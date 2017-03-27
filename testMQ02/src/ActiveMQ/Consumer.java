package ActiveMQ;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer implements Runnable, ExceptionListener {

	private static String url = "ws://localhost:61614";

	public void run() {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue testQueue = session.createQueue("TEST1711");
			Destination destination = session.createQueue("TEST1711");
			MessageConsumer consumer = session.createConsumer(destination);

			Message message = consumer.receive(1000);

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Received: " + text);
			} else {
				System.out.println("Received: " + message);
			}
			consumer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void onException(JMSException exception) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
