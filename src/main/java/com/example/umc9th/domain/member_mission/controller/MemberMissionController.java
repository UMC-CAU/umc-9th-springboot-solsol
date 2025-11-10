package com.example.umc9th.domain.member_mission.controller;

import com.example.umc9th.domain.member_mission.dto.MemberMissionResponse;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.service.MemberMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member_missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @GetMapping
    public ApiResponse<List<MemberMissionResponse>> getByState(@RequestParam(required = false) String state) {
        List<MemberMission> missions = memberMissionService.getMemberMissionsByState(state);
        List<MemberMissionResponse> body = missions.stream()
                .map(MemberMissionResponse::from)
                .toList();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, body);
    }

    @GetMapping("/count")
    public ApiResponse<Integer> countMissions(@RequestParam(required = true) String region,
                                 @RequestParam(required = true) Long memberId) {
        System.out.println("region = " + region);
        Integer count = memberMissionService.countMemberMissionsByRegionAndMember(region, memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, count);
    }
}
