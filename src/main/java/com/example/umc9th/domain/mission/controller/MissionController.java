package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<List<MissionResponse>> getMissions(@RequestParam String region) {
        List<Mission> missions = missionService.getMissionsByRegion(region);
        List<MissionResponse> body = missions.stream()
                .map(m -> new MissionResponse(
                        m.getId(),
                        m.getName(),
                        m.getDeadline(),
                        m.getMinCost(),
                        m.getStore().getName()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(body);
    }
}
