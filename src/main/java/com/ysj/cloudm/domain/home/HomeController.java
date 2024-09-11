package com.ysj.cloudm.domain.home;

import com.ysj.cloudm.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;

    @GetMapping("/")
    String showMain(Model model, HttpServletRequest req) {
        Long loginedMemberId =  Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("loginMemberId"))
                .map(Cookie::getValue)
                .mapToLong(Long::parseLong)
                .findFirst()
                .orElse(0L);
        if(loginedMemberId > 0L)
            model.addAttribute("loginedMember", memberService.findById(loginedMemberId));
        return "index";
    }

    @GetMapping("/about")
    @ResponseBody
    String showAbout() {
        return "Here's Community";
    }
}
