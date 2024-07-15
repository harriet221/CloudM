package com.ysj.cloudm.domain.monologue.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/monologue")
public class MonologueController {

    private List<Monologue> monologues = new ArrayList<>();

    @GetMapping("")
    String createMonologue() {
        return "monologue/play";
    }

    @GetMapping("/play")
    @ResponseBody
    RsData createMonologue(@RequestParam("body") String body) {
        Monologue monologue = new Monologue(monologues.size()+1L, 0L, body);
        monologues.add(monologue);

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
        return monologues;
    }
}

@AllArgsConstructor
@Getter
class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;
}

@AllArgsConstructor
@Getter
class Question {
    private Long id;
    private String content;
}

@AllArgsConstructor
@Getter
class Monologue {
    private Long id;
    private Long questionId;
    private String body;
}