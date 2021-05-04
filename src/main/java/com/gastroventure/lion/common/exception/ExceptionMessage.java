package com.gastroventure.lion.common.exception;

/**
 * MessageSource 와 연결이 매끄럽지 않다.
 * message 파라미터가 의미가 없어진다.
 */
public enum ExceptionMessage {

    USER_SESSION_EXPIRED(ExceptionType.LION_6500.name(), "인증세션이 만료되었습니다. 다시 로그인 하시기 바랍니다."),
    USER_AUTH_FAILED(ExceptionType.LION_6100.name(), "해당 리소스에 접근할 수 없습니다."),
    USER_ACCESS_DENIED(ExceptionType.LION_6100.name(), "보유한 권한으로 접근할수 없는 리소스입니다."),

    /**************************************************************
     * User Error
     **************************************************************/
    NULL_PARAM(ExceptionType.LION_6100.name(), "필수항목이 누락되었습니다.({0})"),

    /**************************************************************
     * Data Error
     **************************************************************/
    BUSINESS_ERROR(ExceptionType.LION_6500.name(), "처리 과정에 오류가 발생하였습니다."),
    NOT_EXISTS(ExceptionType.LION_6500.name(), "{0}가(이) 존재하지 않습니다."),
    NO_RESULT(ExceptionType.LION_6500.name(), "검색 결과가 없습니다."),
    INVALID_DATE_FORMAT(ExceptionType.LION_6500.name(), "올바른 날짜값 형식이 아닙니다."),
    INVALID_DATETIME_FORMAT(ExceptionType.LION_6500.name(), "올바른 시간값 형식이 아닙니다."),
    INVALID_BOOKMARK_TYPE(ExceptionType.LION_6500.name(), "올바른 즐겨찾기 형식이 아닙니다."),
    INVALID_REQUEST_PARAM(ExceptionType.LION_6500.name(), "요청파라미터가 유효하지 않습니다."),
    MAX_COUNT_EXCEPTION(ExceptionType.LION_6500.name(), ""),
    GEOJSON_FILE_NOT_FOUND(ExceptionType.LION_6500.name(), "요청하신 노선의 경로가 존재하지 않습니다."),

    /**************************************************************
     * System Error
     **************************************************************/
    SYSTEM_ERROR(ExceptionType.LION_6900.name(), "시스템 에러입니다.");

    private final String code;
    private final String message;

    ExceptionMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * SYSTEM_ERROR 로 지정되면 500 에러로 판단
     *
     * @param exceptionType
     * @return
     */
    public static boolean isInternalServerError(String exceptionType) {
        return ExceptionMessage.SYSTEM_ERROR.name().equals(exceptionType);
    }

}
