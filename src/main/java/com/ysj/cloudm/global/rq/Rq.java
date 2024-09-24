package com.ysj.cloudm.global.rq;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@RequestScope
@Component
@Getter
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;
    private Member member; // cache

    public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
        this.req = req;
        this.resp = resp;
        this.memberService = memberService;
    }

    public String redirect(String path, String msg) {
        // msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        return "redirect:"+path+"?msg="+msg;
    }

    private Long getMemberId() {
        return Optional
                .ofNullable(req.getSession().getAttribute("loginMemberId"))
                .map(id -> (long) id)
                .orElse(0L);
    }

    public boolean isLogin() {
        return getMemberId() > 0L;
    }

    public Member getMember() {
        if (!isLogin()) {
            return null;
        }
        if (member == null)
            member = memberService.findById(getMemberId());
        return member;
    }

    public void setSessionAttr(String name, long value) {
        req.getSession().setAttribute(name, value);
    }

    public void removeSessionAttr(String name) {
        req.getSession().removeAttribute(name);
    }
}
