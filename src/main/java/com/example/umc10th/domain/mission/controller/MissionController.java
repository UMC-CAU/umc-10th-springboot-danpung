package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/me")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionDetailDTO>> getMyMissions(
            @RequestParam @Positive Long memberId,
            @RequestParam @NotNull Boolean isCompleted,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size
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
            @RequestParam @Positive Long memberId,
            @RequestParam @Positive Long locationId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getAvailableMissions(memberId, locationId, PageRequest.of(page, size))
        );
    }
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.GetMission> createMission(
            @PathVariable @Positive Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CREATE_SUCCESS,
                missionService.createMission(storeId, dto)
        );
    }
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable @Positive Long storeId,
            @RequestParam(defaultValue = "10") @Positive Integer pageSize,
            @RequestParam(defaultValue = "-1") @NotBlank String cursor,
            @RequestParam(defaultValue = "id") @NotBlank String query
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMissions(storeId, pageSize, cursor, query)
        );
    }
}
