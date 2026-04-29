package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import lombok.Builder;

import java.util.List;

public class MemberReqDTO {

    @Builder
    public record SignUp(
            String name,
            String password,
            List<String> preferFoods,
            Gender gender,
            String address,
            SocialType socialType
    ) {}

}