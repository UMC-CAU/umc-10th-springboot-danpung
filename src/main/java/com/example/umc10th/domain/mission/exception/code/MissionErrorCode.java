package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 완료된 미션입니다."),
    MISSION_NOT_ON_GOING(HttpStatus.BAD_REQUEST, "MISSION400_2", "진행 중인 미션이 아닙니다."),
    MISSION_DISTANCE_TOO_FAR(HttpStatus.BAD_REQUEST, "MISSION400_3", "미션 장소와 거리가 너무 멉니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
