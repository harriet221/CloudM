package com.ysj.cloudm.domain.member.controller;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.service.MemberService;
import com.ysj.cloudm.global.rq.Rq;
import com.ysj.cloudm.global.rs.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @PreAuthorize("isAnonymous()")
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

    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    String joinMember(@Valid JoinForm joinForm) {
        // TODO: 프론트로 뺄 로직
        if(!joinForm.password.equals(joinForm.passwordConfirm))
            return rq.redirect("/member/join", "Password doesn't match!");

        RsData<Member> joinMemberRs = memberService.create(joinForm.username, joinForm.password);
        if(!joinMemberRs.isSuccess()) {
            return rq.historyBack(joinMemberRs.getMsg());
        }
        return rq.redirect("/member/login", joinMemberRs.getMsg());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    String loginMember() {
        return "member/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/drop/{id}")
    String dropMember(@PathVariable("id") Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return rq.redirect("/member/login", "They don't exist");
        }
        memberService.delete(id);
        return rq.redirect("/", "Good Bye");
    }

    @GetMapping("/csrf-token")
    @ResponseBody
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}

