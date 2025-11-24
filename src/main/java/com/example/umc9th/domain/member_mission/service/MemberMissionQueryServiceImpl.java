package com.example.umc9th.domain.member_mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member_mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.member_mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.enums.State;
import com.example.umc9th.domain.member_mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberMissionConverter memberMissionConverter;

    @Override
    public MemberMissionResDTO.MyProgressingMissionListDTO findMyProgressingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberMission> result = memberMissionRepository.findAllByMemberAndState(
                member, State.ONGOING, pageRequest);

        return memberMissionConverter.toMyProgressingMissionListDTO(result);
    }
}

