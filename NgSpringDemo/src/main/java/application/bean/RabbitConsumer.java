package application.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "hello")
public class RabbitConsumer {
    private static final transient Log log = LogFactory.getLog(RabbitConsumer.class);

    @RabbitHandler
    public void receive(String in){
        log.info(in);
    }
}
