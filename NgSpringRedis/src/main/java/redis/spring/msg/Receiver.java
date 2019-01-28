package redis.spring.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@Autowired
	public Receiver() {}

	public void receiveMessage(String message) {
		LOGGER.info("Received <" + message + ">");
	}
}
