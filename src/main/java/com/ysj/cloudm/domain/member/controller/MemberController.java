package com.ysj.cloudm.domain.member.controller;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.service.MemberService;
import com.ysj.cloudm.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/join")
    String joinMember() {
        return "member/join";
    }

    @Data
    public static class JoinForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String confirmPassword;
    }

    @PostMapping("/join")
    String joinMember(@Valid JoinForm joinForm) {

        if(!joinForm.password.equals(joinForm.confirmPassword))
            return rq.redirect("/member/join", "Password doesn't match!");

        Member member = memberService.create(joinForm.username, joinForm.password);

        return rq.redirect("/member/login", "Welcome! Have a good time, %s!".formatted(member.getUsername()));
    }

    @GetMapping("/login")
    String loginMember() {
        return "member/login";
    }


    @GetMapping("/drop/{id}")
    String dropMember(@PathVariable("id") Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return rq.redirect("/member/login", "They don't exist");
        }
        memberService.delete(id);
        return rq.redirect("/", "Good Bye");
    }
}

