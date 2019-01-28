package kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class KafkaHalloSender {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaHalloSender(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private Gson gson = new GsonBuilder().create();

    public void send() throws InterruptedException {
        KafkaHalloMessage message = new KafkaHalloMessage();
        final UUID uuid = UUID.randomUUID();
        message.setId(System.currentTimeMillis());
        message.setMsg(uuid.toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("hallo", uuid.toString(), gson.toJson(message));
    }
}
