package com.example.umc9th.domain.member_mission.service;

import com.example.umc9th.domain.member_mission.dto.MemberMissionResDTO;

public interface MemberMissionQueryService {
    MemberMissionResDTO.MyProgressingMissionListDTO findMyProgressingMissions(Long memberId, Integer page);
}

