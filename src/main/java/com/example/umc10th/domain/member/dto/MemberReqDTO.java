package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public class MemberReqDTO {

    @Builder
    public record SignUp(

            @NotBlank
            String name,

            @NotBlank
            String password,

            List<@NotBlank String> preferFoods,

            @NotNull
            Gender gender,

            @NotBlank
            String address,

            @NotNull
            SocialType socialType
    ) {}

}