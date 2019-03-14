package application.controller;

import application.bean.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rab")
public class RabbitCtrl {
    private final RabbitSender sender;

    @Autowired
    public RabbitCtrl(RabbitSender sender) {
        this.sender = sender;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public void get(HttpServletResponse response) throws InterruptedException {
        sender.send();
        response.setStatus(200);
    }
}
