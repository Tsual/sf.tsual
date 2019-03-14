package application.component;

import application.bean.RabbitConsumer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ScheduledTask0 {
    private static final transient Log log = LogFactory.getLog(ScheduledTask0.class);

    @Scheduled(fixedRate = 1000)
    public void logHeartBeat() {
        log.info(new Timestamp(System.currentTimeMillis()));
    }
}
