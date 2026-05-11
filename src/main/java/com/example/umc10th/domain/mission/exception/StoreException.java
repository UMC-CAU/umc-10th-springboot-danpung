package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import lombok.Getter;

@Getter
public class StoreException extends RuntimeException {

    private final StoreErrorCode errorCode;

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
