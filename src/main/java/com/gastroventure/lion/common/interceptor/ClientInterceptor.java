//package com.gastroventure.lion.common.interceptor;
//
//import com.gastroventure.lion.common.annotation.CommonResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.MDC;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.nio.charset.StandardCharsets;
//import java.util.UUID;
//
///**
// * 클라이언트의 요청에 대한 인터셉터 - filter -> interceptor -> advice -> interceptor -> filter 순으로 동작
// */
//@Slf4j
//public class ClientInterceptor extends HandlerInterceptorAdapter {
//
//    public static final String TRACE_ID = "TRACE_ID";
//    public static final String API_ID = "API_ID";
////    private final DatabaseLoggingService databaseLoggingService;
//
////    public ClientInterceptor(DatabaseLoggingService databaseLoggingService) {
////        this.databaseLoggingService = databaseLoggingService;
////    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
//        String apiId = "";
//        final CommonResponse commonResponseHandler = getCommonResponseHandler(handler);
//        if (commonResponseHandler != null) {
//            apiId = setAndGetApiId(commonResponseHandler);
//            log.info("API OUT: {}, {}", req.getRemoteHost(), req.getRequestURI());
//        }
//        // trace id 만 있는 경우는 잘못된 api 를 던진경우 or Swagger
//        String traceId = setAndGetTraceId();
//
//        req.setAttribute(TRACE_ID, traceId);
//        req.setAttribute(API_ID, apiId);
//        return true;
//    }
//
//    private String setAndGetTraceId() {
//        String traceId = "autobus-" + UUID.randomUUID().toString();
//        MDC.put(TRACE_ID, traceId);
//        return traceId;
//    }
//
//    private String setAndGetApiId(CommonResponse commonResponseHandler) {
//        String apiId;
//        apiId = commonResponseHandler.apiEnum().getApiId();
//        MDC.put(API_ID, apiId);
//        return apiId;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                Exception ex) throws Exception {
//        final CommonResponse commonResponseHandler = getCommonResponseHandler(handler);
//        if (commonResponseHandler != null) {
//            log.info("API OUT: {}, {}", request.getRemoteHost(), request.getRequestURI());
//            final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
//            final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
//
//            log.info(
//                    "ReqBody : {} / ResBody : {}",
//                    new String(cachingRequest.getContentAsByteArray(), StandardCharsets.UTF_8),
//                    new String(cachingResponse.getContentAsByteArray(), StandardCharsets.UTF_8)
//            );
////            databaseLoggingService.add(cachingRequest, cachingResponse);
//        }
//
//        // response logging
//        MDC.remove(TRACE_ID);
//        MDC.remove(API_ID);
//    }
//
//    private CommonResponse getCommonResponseHandler(Object handler) {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            CommonResponse commonResponse = handlerMethod.getMethodAnnotation(CommonResponse.class);
//            if (commonResponse != null) {
//                return commonResponse;
//            }
//        }
//
//        return null;
//    }
//}
