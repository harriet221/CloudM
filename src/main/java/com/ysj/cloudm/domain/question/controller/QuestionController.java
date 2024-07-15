package com.ysj.cloudm.domain.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {

    @GetMapping("/question/today")
    String showTodayQuestion() {
        return "today";
    }

}
