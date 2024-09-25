package com.ysj.cloudm.domain.monologue.entity;

import com.ysj.cloudm.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Monologue {
    private Long id;
    private Long questionId;
    private String body;
    private Member author;

    public Monologue(Long questionId, String body, Member author) {
        this.questionId = questionId;
        this.body = body;
        this.author = author;
    }
}
