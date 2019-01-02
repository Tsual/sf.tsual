package spring.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaHalloReceiver {
    @KafkaListener(topics = {"hallo"})
    public void listen(ConsumerRecord record) {
        System.out.println("hallo<<" + record.value());
    }
}
