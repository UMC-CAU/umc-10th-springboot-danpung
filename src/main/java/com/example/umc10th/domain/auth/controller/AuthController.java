package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignupReqDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.JOIN_SUCCESS, memberService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginReqDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN_SUCCESS, memberService.login(request));
    }
}
