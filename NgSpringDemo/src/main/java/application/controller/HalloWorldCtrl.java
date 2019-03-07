package application.controller.hallo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hallo")
public class HalloWorldCtrl {
    @RequestMapping(method = {RequestMethod.GET})
    public String get() {
        return "Hey";
    }
}
