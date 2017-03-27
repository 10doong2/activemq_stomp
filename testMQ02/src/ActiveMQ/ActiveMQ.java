package ActiveMQ;

public class ActiveMQ {
	public static void main(String[] args) throws Exception {

		/*Producer producer = new Producer();
		Consumer consumer = new Consumer();

		Thread threadProducer = new Thread(producer);
		threadProducer.start();

		Thread threadConsumer = new Thread(consumer);
		threadConsumer.start();*/
	}

	public void running() throws Exception {
		Producer producer = new Producer();
		Thread threadProducer = new Thread(producer);
		threadProducer.start();

	}
}