package com.ysj.cloudm.global.webMvc;

import com.ysj.cloudm.global.interceptor.NeedToAdminInterceptor;
import com.ysj.cloudm.global.interceptor.NeedToLoginInterceptor;
import com.ysj.cloudm.global.interceptor.NeedToLogoutInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final NeedToLogoutInterceptor needToLogoutInterceptor;
    private final NeedToLoginInterceptor needToLonginInterceptor;
    private final NeedToAdminInterceptor needToAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needToLogoutInterceptor)
                .addPathPatterns("/member/login")
                .addPathPatterns("/member/join");
        registry.addInterceptor(needToLonginInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/monologue/**")
                .excludePathPatterns("/monologue/play")
                .excludePathPatterns("/monologue/mine");
        registry.addInterceptor(needToAdminInterceptor)
                .addPathPatterns("/admin/**");
    }
}