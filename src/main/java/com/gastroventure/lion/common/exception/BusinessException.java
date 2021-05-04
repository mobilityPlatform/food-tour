package com.gastroventure.lion.common.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String type;
    private final String message;
    private final String messagesCode;
    private final Object[] params;
    private final Throwable exception;

    public BusinessException(ExceptionMessage exception) {
        this(exception, null, null, null);
    }

    public BusinessException(ExceptionMessage exception, Object[] params) {
        this(exception, params, null, null);
    }

    public BusinessException(ExceptionMessage exception, Throwable throwable) {
        this(exception, null, throwable, null);
    }

    public BusinessException(ExceptionMessage exception, Throwable throwable, String messagesCode) {
        this(exception, null, throwable, messagesCode);
    }

    /**
     * @param exception
     * @param messagesCode MessageSource 를 통해 출력할 메시지. 미리 선언되어 있어야 함
     */
    public BusinessException(ExceptionMessage exception, String messagesCode) {
        this(exception, null, null, messagesCode);
    }

    public BusinessException(ExceptionMessage exception, Object[] params, String messagesCode) {
        this(exception, params, null, messagesCode);
    }

    public BusinessException(ExceptionMessage exception, Object[] params, Throwable throwable, String messagesCode) {
        super(MessageFormat.format("BUSINESS EXCEPTION code = [{0}], type = [{1}], message = [{2}]",
                exception.getCode(), exception.name(), MessageFormat.format(exception.getMessage(), params)),
                throwable);

        this.code = exception.getCode();
        this.type = exception.name();
        this.message = MessageFormat.format(exception.getMessage(), params);
        this.exception = throwable;
        this.messagesCode = messagesCode;
        this.params = params;
    }


    @Override
    public String toString() {
        return super.getMessage();
    }
}

