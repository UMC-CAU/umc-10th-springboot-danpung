package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record SignupReqDTO(

            @Valid
            @NotNull
            AgreeDTO agree,

            @NotBlank
            String name,

            @NotNull
            Gender gender,

            @NotNull
            LocalDate birth,

            @NotBlank
            String address,

            String detailAddress,

            List<String> foodList,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String password

    ) {}

    public record AgreeDTO(

            @NotNull
            Boolean age,

            @NotNull
            Boolean service,

            @NotNull
            Boolean privacy,

            Boolean location,

            Boolean marketing

    ) {}

    public record LoginReqDTO(
            @NotBlank
            @Email
            String email,

            @NotBlank
            String password
    ){}
}
