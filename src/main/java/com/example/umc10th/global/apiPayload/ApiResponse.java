package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.code.BaseErrorCode;
import com.example.umc10th.global.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor //모든 변수를 생성자에 넣는다
@Getter
@JsonPropertyOrder
public class ApiResponse <T>{   //회원정보는 MemberDto, 게시물정보는 PostDto ...
    @JsonProperty("isSuccess")
    private final boolean success;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private final T result;


    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result){
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
