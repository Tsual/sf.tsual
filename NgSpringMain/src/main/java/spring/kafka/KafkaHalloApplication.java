package spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaHalloApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaHalloApplication.class, args);
        final KafkaHalloSender sender = context.getBean(KafkaHalloSender.class);

        int count = 10;
        while (count-- > 0) {
            sender.send();
        }
        Thread.currentThread().sleep(20000);
    }
}
