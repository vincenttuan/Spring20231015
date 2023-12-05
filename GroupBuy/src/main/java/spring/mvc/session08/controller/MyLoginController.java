package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyLoginController {

    @RequestMapping("/login")
    public String login() {
        return "login"; // 返回 login 视图
    }

}

