package com.gastroventure.lion.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditor")
public class JpaConfig {
//    private final AutobusMemberService memberService;

//    public JpaConfig(AutobusMemberService memberService) {
//        this.memberService = memberService;
//    }

//    @Bean
//    public AuditorAware<Long> auditor() {
//        return () -> Optional.of(memberService.getAccessMember().getMemberId());
//    }
}
