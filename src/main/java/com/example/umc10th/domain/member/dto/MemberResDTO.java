package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeDTO {
        Long userId;
        String location;
        Integer point;
        Boolean isAlarm;
        Integer completedMissionCount;
        List<MissionDTO> missions;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO {
        Long missionId;
        String title;
        String deadline;
        String category;
        Integer points;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResultDTO {
        Long memberID;
        LocalDateTime createdAt;
    }
}