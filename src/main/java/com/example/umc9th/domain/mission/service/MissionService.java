package com.example.umc9th.domain.mission.service;


import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.umc9th.domain.mission.entity.Mission;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public List<Mission> getMissionsByRegion(String region) {
        return missionRepository.findByStore_Region_Name(region);
    }
}
