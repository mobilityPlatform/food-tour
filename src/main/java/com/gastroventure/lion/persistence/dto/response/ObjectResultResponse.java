package com.gastroventure.lion.persistence.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectResultResponse<T> extends CommonResponse {
    private T data;
}
