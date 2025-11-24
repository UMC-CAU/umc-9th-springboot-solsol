package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.StoreMissionListDTO findStoreMissions(Long storeId, Integer page);
}

