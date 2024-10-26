package com.ysj.cloudm.domain.monologue.service;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.monologue.entity.Monologue;
import com.ysj.cloudm.domain.monologue.repository.MonologueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonologueService {
    private final MonologueRepository monologueRepository;

    public Monologue create(Long questionId, String body, Member author) {
        Monologue monologue = new Monologue(questionId, body, author);
        monologue = monologueRepository.save(monologue);
        return monologue;
    }

    public List<Monologue> findMyMonologues(Member member) {
        return monologueRepository.findMyMonologues(member);
    }

    public Monologue findById(Long id) {
        return monologueRepository.findById(id);
    }

    public void delete(Long id) {
        monologueRepository.delete(id);
    }

    public boolean isAuthor(Member member, Monologue monologue) {
        return member.equals(monologue.getAuthor());
    }
}
