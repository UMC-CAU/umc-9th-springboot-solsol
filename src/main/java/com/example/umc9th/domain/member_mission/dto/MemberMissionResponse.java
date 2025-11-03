package com.example.umc9th.domain.member_mission.dto;

import com.example.umc9th.domain.member_mission.entity.MemberMission;

public record MemberMissionResponse(
        Long id,
        String state,
        Long memberId
) {
    public static MemberMissionResponse from(MemberMission mm) {
        Long mId = (mm.getMember() != null) ? mm.getMember().getId() : null;
        String state = (mm.getState() != null) ? mm.getState().name() : null;
        return new MemberMissionResponse(mm.getId(), state, mId);
    }
}
