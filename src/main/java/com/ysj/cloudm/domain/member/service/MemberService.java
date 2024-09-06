package com.ysj.cloudm.domain.member.service;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member create(String username, String password) {
        Member member = new Member(username, password);
        member = memberRepository.save(member);
        return member;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public void modify(Long id, String username, String password) {
        Member member = findById(id);
        member.setUsername(username);
        member.setPassword(password);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }
}
