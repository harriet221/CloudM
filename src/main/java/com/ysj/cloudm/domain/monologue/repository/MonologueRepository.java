package com.ysj.cloudm.domain.monologue.repository;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MonologueRepository {
    private final List<Monologue> monologues;

    public Monologue save(Monologue monologue) {
        if(monologue.getId() == null) {
            monologue.setId(monologues.size() + 1L);
        }
        monologues.add(monologue);

        return monologue;
    }

    public List<Monologue> findMyMonologues() {
        return monologues;
    }
}
