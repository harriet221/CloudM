package com.ysj.cloudm.domain.monologue.repository;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.repository.MemberRepository;
import com.ysj.cloudm.domain.monologue.entity.Monologue;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MonologueRepository {
    private final MemberRepository memberRepository;
    private final List<Monologue> monologues = new ArrayList<>();

    @PostConstruct
    void init() {
        Member user1 = memberRepository.findById(1L);
        Member user2 = memberRepository.findById(2L);

        save(new Monologue(1L, "answer 1", user1));
        save(new Monologue(2L, "answer 2", user1));
        save(new Monologue(2L, "answer 3", user2));
    }

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
