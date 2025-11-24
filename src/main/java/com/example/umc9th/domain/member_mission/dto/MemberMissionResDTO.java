package com.example.umc9th.domain.member_mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {
    @Builder
    public record MyProgressingMissionListDTO(
            List<MyProgressingMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyProgressingMissionDTO(
            Long memberMissionId,
            String missionName,
            Integer minCost,
            Integer reward,
            LocalDateTime deadline,
            LocalDateTime createdAt
    ){}
}

