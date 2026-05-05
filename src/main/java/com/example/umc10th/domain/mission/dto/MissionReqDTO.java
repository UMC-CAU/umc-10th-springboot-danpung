package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class MissionReqDTO {

    @Builder
    public record CompleteMissionDTO(

            @NotNull
            Long missionId
    ) {}

}