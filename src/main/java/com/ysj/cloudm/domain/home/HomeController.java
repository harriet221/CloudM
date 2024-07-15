package com.ysj.cloudm.domain.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    String showMain() {
        return "index";
    }

    @GetMapping("/about")
    @ResponseBody
    String showAbout() {
        return "Here's Community";
    }
}
