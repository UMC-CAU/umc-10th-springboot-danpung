package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select m
        from Mission m
        join m.store s
        join s.location l
        where l.locationId = :locationId
        and m.missionId not in (
            select mm.mission.missionId
            from MemberMission mm
            where mm.member.memberId = :memberId
        )
    """)
    Page<Mission> findAvailableMissions(
            @Param("memberId") Long memberId,
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}
