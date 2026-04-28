package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController //Json data 반환
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    @GetMapping("/me/home")
    public ApiResponse<MemberResDTO.HomeDTO> getMemberHome() {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_HOME_SUCCESS, MemberConverter.toHomeDTO());
    }
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SignUp request
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.JOIN_SUCCESS, MemberConverter.toSignUpResultDTO());
    }
}
