package spring.consul;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class ConsulSampleController {
    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }
}
