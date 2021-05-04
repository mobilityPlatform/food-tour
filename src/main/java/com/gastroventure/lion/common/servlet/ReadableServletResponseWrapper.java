//package com.gastroventure.lion.common.servlet;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import javax.servlet.http.HttpServletResponse;
//import java.nio.charset.Charset;
//
//@Slf4j
//public class ReadableServletResponseWrapper extends ContentCachingResponseWrapper {
//    /**
//     * Create a new ContentCachingResponseWrapper for the given servlet response.
//     *
//     * @param response the original servlet response
//     */
//    public ReadableServletResponseWrapper(HttpServletResponse response) {
//        super(response);
//    }
//
//    public String getBody() {
//        return new String(super.getContentAsByteArray(), Charset.forName("UTF-8"));
//    }
//}
