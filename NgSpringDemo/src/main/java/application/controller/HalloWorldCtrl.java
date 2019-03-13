package application.controller;

import application.bean.Uuid0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hallo")
public class HalloWorldCtrl {
    private Uuid0 uuid0;
    private ApplicationContext applicationContext;
    private String author;

    @Autowired
    public HalloWorldCtrl(Uuid0 uuid0, ApplicationContext applicationContext, @Value("${Author}") String author) {
        this.uuid0 = uuid0;
        this.applicationContext = applicationContext;
        this.author=author;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public String get() {
        return author + "<<" + uuid0.getUuid();
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/echo/{echo_str}")
    public String echo(@PathVariable("echo_str") String echo_str) {
        return echo_str;
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/hallop")
    public String hallop() {
        return applicationContext.getBean(Uuid0.class).getUuid().toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/di")
    public Map di() {
        Map m = new HashMap();
        m.put("count", applicationContext.getBeanDefinitionCount());
        m.put("names", applicationContext.getBeanDefinitionNames());
        return m;
    }
}
