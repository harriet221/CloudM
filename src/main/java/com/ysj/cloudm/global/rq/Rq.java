package com.ysj.cloudm.global.rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public String redirect(String path, String msg) {
        // msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        return "redirect:"+path+"?msg="+msg;
    }
}
