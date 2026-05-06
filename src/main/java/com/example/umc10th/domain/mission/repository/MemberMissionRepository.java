package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("""
    select mm
    from MemberMission mm
    where mm.member.memberId = :memberId
    and mm.isCompleted = :isCompleted
""")
    Page<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("isCompleted") Boolean isCompleted,
            Pageable pageable
    );
    Optional<MemberMission> findByMember_MemberIdAndMission_MissionId(
            Long memberId,
            Long missionId
    );
}
