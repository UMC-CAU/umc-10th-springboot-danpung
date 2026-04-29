package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionReqDTO {

    @Builder
    public record CompleteMissionDTO(
            Long missionId
    ) {}

}