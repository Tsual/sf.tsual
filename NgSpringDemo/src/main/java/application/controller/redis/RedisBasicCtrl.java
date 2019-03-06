package application.controller.redis;

import application.bean.BasicKV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/redis/basic")
public class RedisBasicCtrl {
    private StringRedisTemplate redisTemplate;
    private transient final Log log = LogFactory.getLog(RedisBasicCtrl.class);

    @Autowired
    public RedisBasicCtrl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/1")
    public String mh1() {
        Random ran = new Random();
        BasicKV obj = new BasicKV(ran.nextInt() + "", ran.nextInt() + "");
        final BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps("Basic");
        hashOps.put(obj.getKey(), obj.getValue());
        log.trace("push " + obj + " to basic hash");
        log.trace("get " + hashOps.get(obj.getKey()) + " from basic hash");
        return hashOps.get(obj.getKey()).toString();
    }
}
