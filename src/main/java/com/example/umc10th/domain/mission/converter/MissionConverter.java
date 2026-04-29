package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionListDTO toMissionListDTO() {

        MissionResDTO.MissionDetailDTO mission1 = MissionResDTO.MissionDetailDTO.builder()
                .missionId(1L)
                .status("COMPLETED")
                .points(500)
                .distance(1.7)
                .storeName("반이 학생 마라탕")
                .images(List.of("url3", "url2"))
                .build();
        return MissionResDTO.MissionListDTO.builder()
                .userId(123L)
                .missions(List.of(mission1))
                .build();
    }

    public static String toCompleteResult() {
        return null;
    }
}