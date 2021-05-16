package com.gastroventure.lion.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.gastroventure.lion.persistence.repository") // repository가 null pointer exception이 나서 추가한 어노테이션
//@EnableJpaAuditing(auditorAwareRef = "auditor")
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
