package com.gastroventure.lion.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

///**
// * - {@link org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter} 가 있는데
// * 원하는 형식이 아님..
// */
@Slf4j
@Component
public class LoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void initFilterBean() throws ServletException {
        setIncludeClientInfo(true);
        setIncludeHeaders(true);
        setIncludePayload(true);
        setIncludeQueryString(true);
        // default 50
        setMaxPayloadLength(500);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isDebugEnabled();
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        // controller 를 들어가기 전에....payload(body) 가 찍히지 않음
        logger.debug(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.debug(message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);
        wrappingRequest.setAttribute("requestTime", LocalDateTime.now());
        filterChain.doFilter(wrappingRequest, wrappingResponse);
        wrappingRequest.setAttribute("responseTime", LocalDateTime.now());
        wrappingResponse.copyBodyToResponse();
    }
}
