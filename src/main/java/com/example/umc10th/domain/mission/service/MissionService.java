package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    public Page<MissionResDTO.MissionDetailDTO> getMyMissions(
            Long memberId,
            Boolean isCompleted,
            Pageable pageable
    ) {
        Page<MemberMission> memberMissions =
                memberMissionRepository.findMyMissions(memberId, isCompleted, pageable);

        return memberMissions.map(memberMission -> MissionResDTO.MissionDetailDTO.builder()
                .missionId(memberMission.getMission().getMissionId())
                .status(memberMission.getIsCompleted() ? "COMPLETED" : "CHALLENGING")
                .points(memberMission.getMission().getPoint())
                .distance(null)
                .storeName(memberMission.getMission().getStore().getName())
                .images(null)
                .build());
    }
    private final MissionRepository missionRepository;
    public Page<MissionResDTO.MissionDetailDTO> getAvailableMissions(
            Long memberId,
            Long locationId,
            Pageable pageable
    ) {
        Page<Mission> missions =
                missionRepository.findAvailableMissions(memberId, locationId, pageable);

        return missions.map(mission -> MissionResDTO.MissionDetailDTO.builder()
                .missionId(mission.getMissionId())
                .status("AVAILABLE")
                .points(mission.getPoint())
                .distance(null)
                .storeName(mission.getStore().getName())
                .images(null)
                .build());
    }
    @Transactional
    public String completeMission(MissionReqDTO.CompleteMissionDTO request) {
        MemberMission memberMission = memberMissionRepository
                .findByMember_MemberIdAndMission_MissionId(
                        request.memberId(),
                        request.missionId()
                )
                .orElseThrow(() -> new IllegalArgumentException("진행 중인 미션을 찾을 수 없습니다."));

        memberMission.complete();

        return "미션 완료 처리 성공";
    }

}
