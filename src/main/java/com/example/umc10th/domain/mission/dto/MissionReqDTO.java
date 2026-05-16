package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public class MissionReqDTO {

    @Builder
    public record CompleteMissionDTO(

            @NotNull
            Long missionId,

            @NotNull
            Long memberId
    ) {}
    public record CreateMission(
            @NotNull(message = "마감 기한은 필수입니다.")
            LocalDate deadline,

            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,

            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}
}
