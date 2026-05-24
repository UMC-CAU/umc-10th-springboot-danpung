package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController //Json data 반환
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me/home")
    public ApiResponse<MemberResDTO.HomeDTO> getMemberHome() {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_HOME_SUCCESS, MemberConverter.toHomeDTO());
    }

    @GetMapping("/users/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
            ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MY_PAGE_SUCCESS,
                memberService.getMyPage(authMember)
        );
    }
}
