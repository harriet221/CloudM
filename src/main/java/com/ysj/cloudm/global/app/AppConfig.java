package com.ysj.cloudm.global.app;

import com.ysj.cloudm.domain.monologue.entity.Monologue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    List<Monologue> monologues() {
        return new ArrayList<>();
    }
}
