package com.gastroventure.lion.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${service.security.enabled}")
    private Boolean enabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(enabled) {
            http.httpBasic().disable() // Form 로그인 미사용
                    .csrf().disable() // CSRF 미사용
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 미사용
                    .and()
                    .authorizeRequests()
                    .anyRequest().permitAll();
        }
        else {
            http.httpBasic().disable() // Form 로그인 미사용
                    .csrf().disable() // CSRF 미사용
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 미사용
                    .and()
                    .authorizeRequests()
                    .anyRequest().permitAll();
        }
    }
}
