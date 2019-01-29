package spring.consul;

import spring.mybatis.dao.KVMapper;
import spring.mybatis.entity.KV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class ConsulSampleController {
    private final KVMapper kvMapper;

    @Autowired
    public ConsulSampleController(KVMapper kvMapper) {
        this.kvMapper = kvMapper;
    }

    @RequestMapping("/demo/consul")
    public String home() {
        return "Hello world";
    }

    @RequestMapping("/demo/mybatis")
    public KV get() {
        KV kv = kvMapper.selectByPrimaryKey("demo");
        if (kv == null) {
            final KV insert_obj = new KV();
            insert_obj.setKey("demo");
            insert_obj.setValue("123456");
            kvMapper.insert(insert_obj);
            kv = kvMapper.selectByPrimaryKey("demo");
        }
        return kv;
    }
}
