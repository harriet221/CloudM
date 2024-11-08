package com.ysj.cloudm.domain.home;

import com.ysj.cloudm.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Rq rq;

    @GetMapping("/")
    String showMain() {
        return "index";
    }

    @GetMapping("/about")
    @ResponseBody
    String showAbout() {
        return "Here's Community";
    }

    @GetMapping("/admin/*")
    String showAdmin() {
        return "admin/main";
    }
}
