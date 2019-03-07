package application.controller;

import application.model.BasicKV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sf.util.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//https://redis.io/commands
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
        final BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps("Basic0");
        hashOps.put(obj.getKey(), obj.getValue());
        log.trace("push " + obj + " to basic hash");
        log.trace("get " + hashOps.get(obj.getKey()) + " from basic hash");
        return hashOps.get(obj.getKey())+"";
    }

    @RequestMapping(value = "/2", method = {RequestMethod.GET})
    public List<String> mh2() {
        Random ran = new Random();
        final BoundListOperations<String, String> basic = redisTemplate.boundListOps("Basic1");
        for (int i = 0; i < 50; i++) {
            basic.rightPush(ran.nextInt() + "");
        }
        final ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final String s = basic.leftPop();
            if (StringHelper.isNotEmpty(s))
                strings.add(s);
        }
        return strings;
    }

    @RequestMapping(value = "/3", method = {RequestMethod.GET})
    @Cacheable("author_name")
    public String mh3(String auth_name){
        return auth_name;
    }
}
