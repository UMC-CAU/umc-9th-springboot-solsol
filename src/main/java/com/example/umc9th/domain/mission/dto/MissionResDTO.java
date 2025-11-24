package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record StoreMissionListDTO(
            List<StoreMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record StoreMissionDTO(
            Long missionId,
            String missionName,
            Integer minCost,
            Integer reward,
            LocalDateTime deadline
    ){}
}

