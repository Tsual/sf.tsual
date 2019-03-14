package application.bean;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RabbitSender {
    private RabbitTemplate rabbitTemplate;
    private Queue queue;
    private java.util.concurrent.atomic.AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    public RabbitSender(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send() throws InterruptedException {
        String message = "Hello World!";

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                rabbitTemplate.convertAndSend(queue.getName(), "SEND<<" + atomicInteger.incrementAndGet());
            }
        }, 0, 25);
        Thread.sleep(5000);
        timer.cancel();
    }
}
