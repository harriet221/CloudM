package com.ysj.cloudm.domain.member.controller;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.service.MemberService;
import com.ysj.cloudm.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        private String passwordConfirm;
    }

    @PostMapping("/join")
    String joinMember(@Valid JoinForm joinForm) {

        if(!joinForm.password.equals(joinForm.passwordConfirm))
            return rq.redirect("/member/join", "Password doesn't match!");

        Member member = memberService.create(joinForm.username, joinForm.password);

        return rq.redirect("/member/login", "Welcome! Login and have a good time!");
    }

    @GetMapping("/login")
    String loginMember() {
        return "member/login";
    }

    @Data
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    String loginMember(@Valid LoginForm loginForm, HttpServletRequest request) {

        Member member = memberService.findByUsername(loginForm.username);

        if(member == null)
            return rq.redirect("/member/login", "Join us first!");

        if(!member.getPassword().equals(loginForm.password))
            return rq.redirect("/member/login", "Wrong password!");

        HttpSession session = request.getSession();
        session.setAttribute("loginMemberId", member.getId());

        return rq.redirect("/", "Welcome, %s!".formatted(member.getUsername()));
    }

    @GetMapping("/logout")
    String logoutMember() {
        rq.removeSessionAttr("loginMemberId");
        return rq.redirect("/", "Done, have a good one!");
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

