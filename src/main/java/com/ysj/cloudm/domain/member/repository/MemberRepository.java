package com.ysj.cloudm.domain.member.repository;

import com.ysj.cloudm.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final List<Member> memberList = new ArrayList<>();

    public Member save(Member member) {
        if(member.getId() == null) {
            member.setId(memberList.size() + 1L);
        }
        memberList.add(member);

        return member;
    }

    public Member findById(Long id) {
        for(Member member : memberList) {
            if(Objects.equals(member.getId(), id))
                return member;
        }
        return null;
    }

    public void delete(Long id) {
        memberList.removeIf(member -> Objects.equals(member.getId(), id));
    }

    public Member findByUsername(String username) {
        for(Member member : memberList) {
            if(Objects.equals(member.getUsername(), username))
                return member;
        }
        return null;
    }
}
