package com.gastroventure.lion.persistence.dto.response;

public enum CodeResponse {

    SUCCESS(200, "요청성공"),
    FAIL(-100, "처리실패");

    int code;
    String message;

    private CodeResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
