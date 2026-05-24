package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignupReqDTO request
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.JOIN_SUCCESS, memberService.signUp(request));
    }

    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @PathVariable @Positive Long memberId
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MY_PAGE_SUCCESS,
                memberService.getMyPage(memberId)
        );
    }
}
