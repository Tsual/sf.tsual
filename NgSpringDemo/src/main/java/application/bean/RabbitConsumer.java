package application.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RabbitListener(queues = "hello")
public class RabbitConsumer {
    private static final transient Log log = LogFactory.getLog(RabbitConsumer.class);
    private final UUID uuid = UUID.randomUUID();

    @RabbitHandler
    public void receive(String in) {
        log.info(uuid + "<<" + in);
    }
}
