package com.ysj.cloudm.domain.monologue.controller;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import com.ysj.cloudm.domain.monologue.service.MonologueService;
import com.ysj.cloudm.global.rq.Rq;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/monologue")
public class MonologueController {
    private final MonologueService monologueService;
    private final Rq rq;

    @GetMapping("/play")
    String createMonologue() {
        return "monologue/play";
    }

    @PostMapping("/play")
    String createMonologue(
            @RequestParam("body")
            @NotBlank(message = "작성 필수") String body) {

        Monologue monologue = monologueService.create(0L, body);

        return rq.redirect("/monologue/mine", "no. %d Monologue is created".formatted(monologue.getId()));
    }

    @GetMapping("/mine")
    String showMyMonologues(Model model) {
        List<Monologue> monologues = monologueService.findMyMonologues();
        model.addAttribute("myMonologues", monologues);
        return "monologue/mine";
    }

    @GetMapping("/{id}")
    String showMonologue(Model model, @PathVariable("id") Long id) {
        Monologue monologue = monologueService.findById(id);
        if(monologue == null) {
            return rq.redirect("/monologue/mine", "no. %d Monologue does not exist".formatted(id));
        }
        model.addAttribute("monologue", monologue);
        return "monologue/detail";
    }

    @GetMapping("/delete/{id}")
    String deleteMonologue(@PathVariable("id") Long id) {
        Monologue monologue = monologueService.findById(id);
        if(monologue == null) {
            return rq.redirect("/monologue/mine", "no. %d Monologue does not exist".formatted(id));
        }
        monologueService.delete(id);

        return rq.redirect("/monologue/mine", "no. %d Monologue is deleted".formatted(id));
    }
}

