package application.component;

import application.bean.RabbitConsumer;
import application.bean.RabbitSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue demoQueue(){
        return new Queue("hello");
    }
}
