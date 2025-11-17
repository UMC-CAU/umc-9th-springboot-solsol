package com.example.umc9th.domain.member_mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.enums.State;
import com.example.umc9th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    public Long challengeMission(Long missionId, Long memberId) {
        // 식별자만 가진 연관 엔티티 참조 생성
        Member memberRef = Member.builder().id(memberId).build();
        Mission missionRef = Mission.builder().id(missionId).build();

        // 빌더로 MemberMission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(memberRef)
                .mission(missionRef)
                .state(State.ONGOING) // 초기 상태 값 필요 시 변경
                .createdAt(LocalDateTime.now())
                .build();

        MemberMission saved = memberMissionRepository.save(memberMission);
        return saved.getId();
    }
}
