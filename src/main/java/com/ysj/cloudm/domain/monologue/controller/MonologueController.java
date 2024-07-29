package com.ysj.cloudm.domain.monologue.controller;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import com.ysj.cloudm.domain.monologue.service.MonologueService;
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

    @GetMapping("/play")
    String createMonologue() {
        return "monologue/play";
    }

    @PostMapping("/play")
    String createMonologue(
            @RequestParam("body")
            @NotBlank(message = "작성 필수") String body) {

        Monologue monologue = monologueService.create(0L, body);

        String msg = "no. %d Monologue created".formatted(monologue.getId());
        return "redirect:/monologue/mine?msg="+msg;
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
            String msg = "no. %d Monologue is not exist".formatted(id);
            return "redirect:/monologue/mine?msg="+msg;
        }
        model.addAttribute("monologue", monologue);
        return "monologue/detail";
    }

    @GetMapping("/delete/{id}")
    String deleteMonologue(@PathVariable("id") Long id) {
        Monologue monologue = monologueService.findById(id);
        if(monologue == null) {
            String msg = "no. %d Monologue is not exist".formatted(id);
            return "redirect:/monologue/mine?msg="+msg;
        }
        monologueService.delete(id);

        String msg = "no. %d Monologue deleted".formatted(monologue.getId());
        return "redirect:/monologue/mine?msg="+msg;
    }
}

