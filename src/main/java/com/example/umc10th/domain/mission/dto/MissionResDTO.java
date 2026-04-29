package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionListDTO(
            Long userId,
            List<MissionDetailDTO> missions
    ) {}

    @Builder
    public record MissionDetailDTO(
            Long missionId,
            String status,
            Integer points,
            Double distance,
            String storeName,
            List<String> images
    ) {}
}