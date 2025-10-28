package com.example.umc9th.domain.member_mission.service;

import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.enums.State;
import com.example.umc9th.domain.member_mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public List<MemberMission> getMemberMissionsByState(String stateValue) {
        State state = (stateValue == null || stateValue.isBlank())
                ? State.DEFAULT
                : State.from(stateValue);
        return memberMissionRepository.findByState(state);
    }

    @Transactional(readOnly = true)
    public Integer countMemberMissionsByRegionAndMember(String region, Long memberId) {
        return memberMissionRepository.countByMission_Store_Region_NameAndMember_IdAndState(region, memberId, State.COMPLETED);

    }
}
