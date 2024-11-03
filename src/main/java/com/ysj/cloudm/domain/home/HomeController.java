package com.ysj.cloudm.domain.home;

import com.ysj.cloudm.global.rq.Rq;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

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

    // 세션 확인 용
    @GetMapping("/session")
    @ResponseBody
    public Map<String, Object> showSession(HttpSession session) {
        return Collections.list(session.getAttributeNames()).stream()
                .collect(
                        Collectors.toMap(
                                key -> key,
                                key -> session.getAttribute(key)
                        )
                );
    }
}
