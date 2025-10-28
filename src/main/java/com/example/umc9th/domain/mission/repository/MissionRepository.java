package com.example.umc9th.domain.mission.repository;

import java.util.List;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    public List<Mission> findByStore_Region_Name(String region);
}
