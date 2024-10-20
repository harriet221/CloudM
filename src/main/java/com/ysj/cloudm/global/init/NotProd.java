package com.ysj.cloudm.global.init;

import com.ysj.cloudm.domain.member.entity.Member;
import com.ysj.cloudm.domain.member.service.MemberService;
import com.ysj.cloudm.domain.monologue.service.MonologueService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(MemberService memberService,
                                         MonologueService monologueService) {
        return args -> {
            Member admin = memberService.create("admin", "1234");
            Member user1 = memberService.create("user1", "1234");
            Member user2 = memberService.create("user2", "1234");

            monologueService.create(1L, "answer1", user1);
            monologueService.create(2L, "answer2", user1);
            monologueService.create(2L, "answer3", user2);
        };
    }
}
