package com.gastroventure.lion.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.BindingResult;

@Getter
@ToString
public class ErrorResultResponse {
    private String errorType;

    private String errorCode;

    private String errorMsg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails errorDetails;

    public ErrorResultResponse(ExceptionMessage exceptionMessage) {
        this.errorType = exceptionMessage.name();
        this.errorCode = exceptionMessage.getCode();
        this.errorMsg = exceptionMessage.getMessage();
    }

    public ErrorResultResponse(ExceptionMessage exceptionMessage, ErrorDetails errorDetails) {
        this(exceptionMessage);
        this.errorDetails = errorDetails;
    }

    @Builder(toBuilder = true)
    public ErrorResultResponse(String errorType, String errorCode, String errorMsg) {
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Getter
    public static class ErrorDetails {
        private String field;
        private String reason;

        public ErrorDetails(final BindingResult bindingResult) {
            this.field = bindingResult.getFieldError().getField();
            this.reason = bindingResult.getFieldError().getDefaultMessage();
        }
    }
}
