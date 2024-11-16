package com.ysj.cloudm.domain.member.service;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.repository.MemberRepository;
import com.ysj.cloudm.global.rs.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RsData<Member> create(String username, String password) {
        if(findByUsername(username) != null)
            return new RsData<>(400, "Sorry, username already exist!");

        password = passwordEncoder.encode(password);
        Member member = new Member(username, password);

        member = memberRepository.save(member);
        return new RsData<>(200, "Welcome! Login and have a good time!", member);
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

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
