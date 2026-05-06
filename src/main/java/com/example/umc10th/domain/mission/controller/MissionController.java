package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/me")
    public ApiResponse<Page<MissionResDTO.MissionDetailDTO>> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam Boolean isCompleted,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMyMissions(memberId, isCompleted, PageRequest.of(page, size))
        );
    }

    @PatchMapping("/completed")
    public ApiResponse<String> completeMission(
            @RequestBody @Valid MissionReqDTO.CompleteMissionDTO request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_COMPLETE_SUCCESS,
                missionService.completeMission(request)
        );
    }
    @GetMapping("/available")
    public ApiResponse<Page<MissionResDTO.MissionDetailDTO>> getAvailableMissions(
            @RequestParam Long memberId,
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getAvailableMissions(memberId, locationId, PageRequest.of(page, size))
        );
    }
}
