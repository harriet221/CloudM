package com.ysj.cloudm.domain.monologue.controller;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import com.ysj.cloudm.domain.monologue.service.MonologueService;
import com.ysj.cloudm.global.rs.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/monologue")
public class MonologueController {
    private final MonologueService monologueService;

    @GetMapping("/play")
    String createMonologue() {
        return "monologue/play";
    }

    @PostMapping("/play")
    @ResponseBody
    RsData createMonologue(String body) {

        if(body == null || body.trim().length() == 0)
            // throw new IllegalArgumentException("본문 없음");
            return new RsData<>(
                    "F-1",
                    "Empty body");

        Monologue monologue = monologueService.create(0L, body);

        RsData<Monologue> rs = new RsData<>(
                "S-200",
                "no.%d monologue's done.".formatted(monologue.getId()),
                monologue
        );
        return rs;
    }

    @GetMapping("/mine")
    @ResponseBody
    List<Monologue> showMyMonologues() {
        return monologueService.findMyMonologues();
    }
}

