package spring.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaHalloReceiver {
    @KafkaListener(topics = {"zhisheng"})
    public void listen(ConsumerRecord record) {
        System.out.println("zhisheng<<" + record.value());
    }
}
