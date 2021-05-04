package com.gastroventure.lion.persistence.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CommonResponse {
    private String apiId;

    private int code;

    private String msg;

    private long serverTime;

    private String traceId;
}
