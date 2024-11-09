package com.ysj.cloudm.global.rq;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Getter
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;
    private Member member;
    private User user;

    @PostConstruct
    public void init() {
        // 세션에서 현재 로그인 한 member의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal() instanceof User) {
            this.user = (User) authentication.getPrincipal();
        } else {
            this.user = null;
        }
    }

    public String redirect(String path, String msg) {
        // msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        return "redirect:"+path+"?msg="+msg;
    }

    public String historyBack(String msg) {
        req.setAttribute("msg", msg);
        return "global/js";
    }

    public boolean isLogin() {
        return user != null;
    }

    public Member getMember() {
        if (!isLogin()) {
            return null;
        }
        if (member == null)
            member = memberService.findByUsername(user.getUsername());
        return member;
    }

    public void setSessionAttr(String name, Object value) {
        req.getSession().setAttribute(name, value);
    }

    public <T> T getSessionAttr(String name) {
        return (T) req.getSession().getAttribute(name);
    }

    public void removeSessionAttr(String name) {
        req.getSession().removeAttribute(name);
    }

    public boolean isAdmin() {
        return user.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
