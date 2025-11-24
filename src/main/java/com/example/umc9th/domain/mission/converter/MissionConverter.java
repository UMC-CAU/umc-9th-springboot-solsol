package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {

    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> missionPage) {
        List<MissionResDTO.StoreMissionDTO> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toStoreMissionDTO)
                .collect(Collectors.toList());

        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .missionName(mission.getName())
                .minCost(mission.getMinCost())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }
}

