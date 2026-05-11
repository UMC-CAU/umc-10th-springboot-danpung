package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
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

    @Transactional
    public MissionResDTO.GetMission createMission(Long storeId, MissionReqDTO.CreateMission request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, store);
        Mission savedMission = missionRepository.save(mission);
        return MissionConverter.toGetMissionDTO(savedMission);
    }

    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0,pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        if (cursor != null && !cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()){
                case "id":
                    idCursor = Long.parseLong(cursorSplit[1]);

                    missionList = missionRepository.findMissionsByStore_StoreIdAndMissionIdLessThanOrderByMissionIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new IllegalArgumentException("지원하지 않는 query입니다.");
            }
        } else {
            missionList = missionRepository.findMissionsByStore_StoreIdOrderByMissionIdDesc(storeId, pageRequest);
        }

        nextCursor = missionList.hasContent()
                ? missionList.getContent().getLast().getMissionId() + ":" + missionList.getContent().getLast().getMissionId()
                : "-1";

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMissionDTO).getContent(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}
