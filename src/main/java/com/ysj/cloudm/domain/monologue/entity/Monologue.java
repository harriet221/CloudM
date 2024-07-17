package com.ysj.cloudm.domain.monologue.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Monologue {
    private Long id;
    private Long questionId;
    private String body;

    public Monologue(Long questionId, String body) {
        this.questionId = questionId;
        this.body = body;
    }
}
