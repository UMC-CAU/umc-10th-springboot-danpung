package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.code.BaseSuccessCode;
import com.example.umc10th.global.code.GeneralSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    REVIEW_POST_SUCCESS(HttpStatus.CREATED, "REVIEW201_1", "리뷰 작성에 성공했습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, "REVIEW200_2", "리뷰 삭제에 성공했습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "REVIEW200_3", "리뷰 수정에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
