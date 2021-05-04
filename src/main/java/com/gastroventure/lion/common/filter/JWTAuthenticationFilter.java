package com.gastroventure.lion.common.filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Json Web Token 필터 구현
 */
public class JWTAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String headerKeyName;

    public JWTAuthenticationFilter(String headerKeyName) {
        this.headerKeyName = headerKeyName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(headerKeyName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "NoCredential";
    }
}