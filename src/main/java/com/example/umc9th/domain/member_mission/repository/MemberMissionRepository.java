package com.example.umc9th.domain.member_mission.repository;

import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    List<MemberMission> findByState(State state);
    // mission.store.region + member.id 기준 카운트
    @Transactional(readOnly = true)
    public Integer countByMission_Store_Region_NameAndMember_IdAndState(String region, Long memberId, State state);

}
