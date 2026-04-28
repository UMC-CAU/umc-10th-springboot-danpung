package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberReqDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUp{
        private String name;
        private String password;
        private List<String> preferFoods;
        private Gender gender;
        private String address;
        private SocialType socialType;
    }

}
