package com.gastroventure.lion.common.aop;

import com.gastroventure.lion.common.exception.BusinessException;
import com.gastroventure.lion.common.exception.ErrorResultResponse;
import com.gastroventure.lion.common.exception.ExceptionMessage;
import com.gastroventure.lion.persistence.dto.response.ObjectResultResponse;
import com.gastroventure.lion.services.util.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.HibernateException;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@ControllerAdvice(basePackages = "com.hae.mobility.lion")
public class GlobalExceptionHandlerAspect {
    private final ResponseService responseService;
    private final MessageSource messageSource;

    public GlobalExceptionHandlerAspect(ResponseService responseService, MessageSource messageSource) {
        this.responseService = responseService;
        this.messageSource = messageSource;
    }

    /**
     * @param runtimeException
     * @return
     */
    private ResponseEntity<Object> getErrorResultResponse(RuntimeException runtimeException) {
        HttpStatus status = HttpStatus.OK;
        BusinessException exception;
        if (runtimeException instanceof BusinessException) {
            exception = (BusinessException) runtimeException;
        } else {
            exception = new BusinessException(ExceptionMessage.SYSTEM_ERROR, runtimeException);
        }

        // 시스템 에러아닌건 다 ok 로 응답하도록 되어있음.
        if (ExceptionMessage.isInternalServerError(exception.getType())) {
            status = INTERNAL_SERVER_ERROR;
        }

        String sourceMessage;
        try {
            // Message - message 보다 우선
            sourceMessage = messageSource
                    .getMessage(exception.getMessagesCode(), exception.getParams(), LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            sourceMessage = exception.getMessage();
        }

        ErrorResultResponse body = ErrorResultResponse.builder()
                .errorCode(exception.getCode())
                .errorMsg(sourceMessage)
                .errorType(exception.getType())
                .build();

        log.error("Error-Response: {}", body.toString());
        ObjectResultResponse<ErrorResultResponse> resultResponse = responseService
                .getObjectResultFailResponse(MDC.get("API_ID"), MDC.get("TRACE_ID"), body);
        return status(status).body(resultResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(HttpServletRequest req, HandlerMethod method,
                                                          BusinessException exception) {
        log.error("Handling Exception: {}", ExceptionUtils.getRootCauseMessage(exception));

        return getErrorResultResponse(exception);
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<Object> handleHibernateException(HttpServletRequest req, HandlerMethod method,
                                                           HibernateException e) {
        log.error("Handling HibernateException: {}", ExceptionUtils.getRootCauseMessage(e));
        BusinessException exception = new BusinessException(ExceptionMessage.BUSINESS_ERROR, e);

        return getErrorResultResponse(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unHandleException(HttpServletRequest req,
                                                    HandlerMethod method,
                                                    Exception e) {
        log.error("Un handle Exception: {}", ExceptionUtils.getStackTrace(e));
        BusinessException exception = new BusinessException(ExceptionMessage.SYSTEM_ERROR, e);
        return getErrorResultResponse(exception);
    }

}
