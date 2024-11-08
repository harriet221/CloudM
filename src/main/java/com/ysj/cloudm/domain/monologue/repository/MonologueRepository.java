package com.ysj.cloudm.domain.monologue.repository;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.monologue.entity.Monologue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MonologueRepository {
    private final List<Monologue> monologues = new ArrayList<>();
    private Long lastIdx = 0L;

    public Monologue save(Monologue monologue) {
        if(monologue.getId() == null) {
            lastIdx++;
            monologue.setId(lastIdx);
        }
        monologues.add(monologue);

        return monologue;
    }

    public List<Monologue> findMyMonologues(Member member) {
        List<Monologue> myMonologues = new ArrayList<>();
        for(Monologue monologue : monologues) {
            if(monologue.getAuthor().equals(member)) {
                myMonologues.add(monologue);
            }
        }
        return myMonologues;
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
