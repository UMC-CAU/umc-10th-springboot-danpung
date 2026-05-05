package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    JOIN_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_1", "로그인에 성공했습니다."),
    MEMBER_HOME_SUCCESS(HttpStatus.OK, "MEMBER200_2", "회원 홈 화면 조회에 성공했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}