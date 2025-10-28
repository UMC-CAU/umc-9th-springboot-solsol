package com.example.umc9th.domain.mission.dto;

import java.time.LocalDateTime;

public record MissionResponse(
        Long id,
        String name,
        LocalDateTime deadline,
        Integer minCost,
        String storeName
) { }