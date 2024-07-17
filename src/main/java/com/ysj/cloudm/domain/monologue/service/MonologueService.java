package com.ysj.cloudm.domain.monologue.service;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import com.ysj.cloudm.domain.monologue.repository.MonologueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonologueService {
    private final MonologueRepository monologueRepository;

    public Monologue create(Long questionId, String body) {
        Monologue monologue = new Monologue(questionId, body);
        monologue = monologueRepository.save(monologue);
        return monologue;
    }

    public List<Monologue> findMyMonologues() {
        return monologueRepository.findMyMonologues();
    }
}
