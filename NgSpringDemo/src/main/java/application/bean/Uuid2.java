package application.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

public class Uuid2 {
    private UUID uuid=UUID.randomUUID();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @PostConstruct
    public void init(){

    }

    @PreDestroy
    public void destroy(){

    }

}
