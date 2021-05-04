package com.gastroventure.lion.services.util;

import com.gastroventure.lion.persistence.dto.response.CodeResponse;
import com.gastroventure.lion.persistence.dto.response.CommonResponse;
import com.gastroventure.lion.persistence.dto.response.ObjectResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 응답 서비스
 * Auth
 */
@Slf4j
@Service
public class ResponseService {

    public <T> ObjectResultResponse<T> getObjectResultResponse(String apiId, String traceId, T data) {
        ObjectResultResponse<T> result = getResultResponse(apiId, traceId, data);
        setSuccessResult(result);
        return result;
    }

    public <T> ObjectResultResponse<T> getObjectResultFailResponse(String apiId, String traceId, T data) {
        ObjectResultResponse<T> result = getResultResponse(apiId, traceId, data);
        setFailResult(result);
        return result;
    }

    private <T> ObjectResultResponse<T> getResultResponse(String apiId, String traceId, T data) {
        ObjectResultResponse<T> result = new ObjectResultResponse<>();
        result.setApiId(apiId);
        result.setData(data);
        result.setTraceId(traceId);
        ZonedDateTime zoneDt = Instant.ofEpochMilli(result.getServerTime()).atZone(ZoneId.systemDefault());
        log.debug("response time : {}, {}", result.getServerTime(), zoneDt);
        return result;
    }

    public CommonResponse getSuccessResult() {
        CommonResponse result = new CommonResponse();
        setSuccessResult(result);
        return result;
    }

    public void setFailResult(CommonResponse result) {
        result.setCode(CodeResponse.FAIL.getCode());
        result.setMsg(CodeResponse.FAIL.getMessage());
        result.setServerTime(System.currentTimeMillis());
    }

    private void setSuccessResult(CommonResponse result) {
        result.setCode(CodeResponse.SUCCESS.getCode());
        result.setMsg(CodeResponse.SUCCESS.getMessage());
        result.setServerTime(System.currentTimeMillis());
    }
}
