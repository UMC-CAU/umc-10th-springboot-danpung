package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;

import java.util.List;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignupReqDTO request, String encodedPassword) {
        return Member.builder()
                .name(request.name())
                .gender(request.gender())
                .birthday(request.birth())
                .email(request.email())
                .password(encodedPassword)
                .socialType(SocialType.LOCAL)
                .point(0)
                .build();
    }

    public static MemberResDTO.SignupResultDTO toSignupResultDTO(Member member) {
        return MemberResDTO.SignupResultDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member, Long reviewCount) {
        return MemberResDTO.MyPageDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .reviewCount(reviewCount)
                .build();
    }

    public static MemberResDTO.HomeDTO toHomeDTO() {
        return MemberResDTO.HomeDTO.builder()
                .userId(1L)
                .location("서울")
                .point(0)
                .isAlarm(false)
                .completedMissionCount(0)
                .missions(List.of())
                .build();
    }
}
