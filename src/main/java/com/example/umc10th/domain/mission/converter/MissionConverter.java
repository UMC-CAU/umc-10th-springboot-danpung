package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
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

    public static Mission toMission(MissionReqDTO.CreateMission request, Store store) {
        return Mission.builder()
                .store(store)
                .deadline(request.deadline())
                .point(request.point())
                .text(request.conditional())
                .build();
    }

    public static MissionResDTO.GetMission toGetMissionDTO(Mission mission) {
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getMissionId())
                .point(mission.getPoint())
                .conditional(mission.getText())
                .build();
    }
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
