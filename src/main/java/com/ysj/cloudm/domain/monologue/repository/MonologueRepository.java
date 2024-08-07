package com.ysj.cloudm.domain.monologue.repository;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MonologueRepository {
    private final List<Monologue> monologues = new ArrayList<>() {{
        add(new Monologue(1L,1L, "answer 1"));
        add(new Monologue(2L,2L, "answer 2"));
        add(new Monologue(3L,3L, "answer 3"));
    }};

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

    public Monologue findById(Long id) {
        for(Monologue monologue : monologues) {
            if(Objects.equals(monologue.getId(), id))
                return monologue;
        }
        return null;
    }

    public void delete(Long id) {
        monologues.removeIf(monologue -> Objects.equals(monologue.getId(), id));
    }
}
