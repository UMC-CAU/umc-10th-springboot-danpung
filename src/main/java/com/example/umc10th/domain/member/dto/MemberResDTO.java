package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record HomeDTO(
            Long userId,
            String location,
            Integer point,
            Boolean isAlarm,
            Integer completedMissionCount,
            List<MissionDTO> missions
    ) {}

    @Builder
    public record MissionDTO(
            Long missionId,
            String title,
            String deadline,
            String category,
            Integer points
    ) {}

    @Builder
    public record SignUpResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}