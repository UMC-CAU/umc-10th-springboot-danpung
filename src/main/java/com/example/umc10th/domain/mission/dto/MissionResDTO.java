package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionResDTO {

        @Builder
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MissionListDTO {
            Long userId;
            List<MissionDetailDTO> missions; // 미션 여러 개를 담는 리스트
        }

        @Builder
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MissionDetailDTO {
            Long missionId;
            String status;     // "COMPLETED"
            Integer points;    // 500
            Double distance;   // 1.7 (실수니까 Double)
            String storeName;  // "반이 학생 마라탕"
            List<String> images;
        }
}
