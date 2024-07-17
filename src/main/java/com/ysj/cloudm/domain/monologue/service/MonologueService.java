package com.ysj.cloudm.domain.monologue.service;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import com.ysj.cloudm.domain.monologue.repository.MonologueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonologueService {
    private final MonologueRepository monologueRepository;
    public MonologueService(MonologueRepository monologueRepository) {
        this.monologueRepository = monologueRepository;
    }

    public Monologue create(Long questionId, String body) {
        Monologue monologue = new Monologue(questionId, body);
        monologue = monologueRepository.save(monologue);
        return monologue;
    }

    public List<Monologue> findMyMonologues() {
        return monologueRepository.findMyMonologues();
    }
}
