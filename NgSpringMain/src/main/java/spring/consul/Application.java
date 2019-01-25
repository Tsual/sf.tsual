package spring.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -DEXPOSE_IP=10.73.242.161 -DEXPOSE_PORT=8080 -DTDX_INSTANCE_ID=DEMO -DCONSUL_URL=20.26.38.75 -DCONSUL_PORT=15000
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
