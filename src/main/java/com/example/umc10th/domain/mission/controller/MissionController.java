package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    @GetMapping("/")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissionList() {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS, MissionConverter.toMissionListDTO());
    }
    @PatchMapping("/completed")
    public ApiResponse<String> completeMission(
            @RequestBody MissionReqDTO.CompleteMissionDTO request
    ){
        //Jpa로 completed로 바꿔주기
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, MissionConverter.toCompleteResult());
    }
}
