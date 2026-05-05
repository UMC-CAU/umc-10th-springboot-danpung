package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER409_1", "이미 존재하는 회원입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER400_1", "비밀번호가 일치하지 않습니다."),
    MEMBER_FORBIDDEN(HttpStatus.FORBIDDEN, "MEMBER403_1", "해당 정보에 접근할 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
