package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import java.util.List;

public class MemberConverter {

    public static MemberResDTO.HomeDTO toHomeDTO() {

        MemberResDTO.MissionDTO mission1 = MemberResDTO.MissionDTO.builder()
                .missionId(101L)
                .title("반이 학생 마라탕")
                .deadline("D-7")
                .category("중식당")
                .points(500)
                .build();

        MemberResDTO.MissionDTO mission2 = MemberResDTO.MissionDTO.builder()
                .missionId(102L)
                .title("다른 맛집 미션")
                .deadline("D-3")
                .category("일식당")
                .points(300)
                .build();

        return MemberResDTO.HomeDTO.builder()
                .userId(23L)
                .location("서울시 동작구 상도동")
                .point(1500)
                .isAlarm(true)
                .completedMissionCount(5)
                .missions(List.of(mission1, mission2))
                .build();
    }
    public static MemberResDTO.SignUpResultDTO toSignUpResultDTO() {
        return MemberResDTO.SignUpResultDTO.builder()
                .memberID(1L)
                .createdAt(java.time.LocalDateTime.now())
                .build();
    }
}