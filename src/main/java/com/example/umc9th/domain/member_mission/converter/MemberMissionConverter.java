package com.example.umc9th.domain.member_mission.converter;

import com.example.umc9th.domain.member_mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMissionConverter {

    public static MemberMissionResDTO.MyProgressingMissionListDTO toMyProgressingMissionListDTO(
            Page<MemberMission> memberMissionPage) {
        List<MemberMissionResDTO.MyProgressingMissionDTO> missionList = memberMissionPage.getContent().stream()
                .map(MemberMissionConverter::toMyProgressingMissionDTO)
                .collect(Collectors.toList());

        return MemberMissionResDTO.MyProgressingMissionListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    public static MemberMissionResDTO.MyProgressingMissionDTO toMyProgressingMissionDTO(MemberMission memberMission) {
        return MemberMissionResDTO.MyProgressingMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionName(memberMission.getMission().getName())
                .minCost(memberMission.getMission().getMinCost())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}

