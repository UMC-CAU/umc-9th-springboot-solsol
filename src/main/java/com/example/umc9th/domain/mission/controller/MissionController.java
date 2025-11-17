package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<List<MissionResponse>> getMissions(@RequestParam String region) {
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
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, body);
    }
}
